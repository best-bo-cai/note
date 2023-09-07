package com.liu.utils.cipher

import java.io.InputStream
import java.io.OutputStream
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.*
import javax.crypto.spec.SecretKeySpec


enum class FORMAT {
    RAW, HEX, BASE64
}

fun ByteArray.convertTo(mode: FORMAT): String {
    return when (mode) {
        FORMAT.RAW -> {
            String(this)
        }

        FORMAT.HEX -> {
            this.bytesToHex()
        }

        FORMAT.BASE64 -> {
            java.util.Base64.getEncoder().encodeToString(this)
        }
    }
}

fun String.convertTo(mode: FORMAT): ByteArray {
    return when (mode) {
        FORMAT.RAW -> {
            this.toByteArray()
        }

        FORMAT.HEX -> {
            this.hexToBytes()
        }

        FORMAT.BASE64 -> {
            java.util.Base64.getDecoder().decode(this)
        }
    }
}

/**
 * 将字节数组转换为十六进制字符串
 *
 * @param bytes 字节数组
 * @return 十六进制字符串
 */
fun ByteArray.bytesToHex(): String {
    val sb = StringBuilder()
    for (b in this) {
        sb.append(String.format("%02X", b))
    }
    return sb.toString()
}

/**
 * 将十六进制字符串转换为字节数组
 *
 * @param hex 十六进制字符串
 * @return 字节数组
 */
fun String.hexToBytes(): ByteArray {
    val len = length
    val bytes = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        bytes[i / 2] = ((this[i].digitToInt(16) shl 4) + this[i + 1].digitToInt(16)).toByte()
        i += 2
    }
    return bytes
}

class CipherHelper {
    private var cipher: ICipher

    private constructor(cipher: ICipher) {
        this.cipher = cipher
    }

    interface ICipher {
        fun generateCipher(opmode: Int, keyBytes: ByteArray): Cipher
    }

    class AESCipher : ICipher {
        override fun generateCipher(opmode: Int, keyBytes: ByteArray): Cipher {
            return Cipher.getInstance("AES/ECB/PKCS5Padding").apply {
                val keySpec = SecretKeySpec(keyBytes, "AES")
                init(opmode, keySpec)
            }
        }
    }

    class RSACipher : ICipher {
        private fun getRsaPublicKey(publickey: ByteArray): PublicKey {
            val keySpec = X509EncodedKeySpec(publickey)
            val keyFactory = KeyFactory.getInstance("RSA")
            return keyFactory.generatePublic(keySpec)
        }

        private fun getRsaPrivateKey(privateKey: ByteArray): PrivateKey {
            val keySpec = PKCS8EncodedKeySpec(privateKey)
            val keyFactory = KeyFactory.getInstance("RSA")
            return keyFactory.generatePrivate(keySpec)
        }

        override fun generateCipher(opmode: Int, keyBytes: ByteArray): Cipher {
            val keySpec = when (opmode) {
                Cipher.ENCRYPT_MODE -> {
                    getRsaPublicKey(keyBytes)
                }

                Cipher.DECRYPT_MODE -> {
                    getRsaPrivateKey(keyBytes)
                }

                else -> {
                    throw IllegalArgumentException("opmode $opmode is not support")
                }
            }

            return Cipher.getInstance("RSA/ECB/PKCS1Padding").apply {
                init(opmode, keySpec)
            }
        }
    }

    companion object {
        private var map = mutableMapOf<String, CipherHelper>()

        @JvmStatic
        fun getInstance(transformation: String): CipherHelper {
            val item = map.getOrPut(transformation) {
                when (transformation) {
                    "AES" -> {
                        CipherHelper(AESCipher())
                    }

                    "RSA" -> {
                        CipherHelper(RSACipher())
                    }

                    else -> {
                        throw IllegalArgumentException("transformation $transformation is not support")
                    }
                }
            }

            return item
        }

        @JvmStatic
        @JvmOverloads
        fun generateRsaKeyPair(keySize: Int = 2048): KeyPair {
            val keyGenerator = KeyPairGenerator.getInstance("RSA")
            keyGenerator.initialize(keySize)
            return keyGenerator.generateKeyPair()
        }

        @JvmStatic
        @JvmOverloads
        fun generateAesKey(keySize: Int = 128): SecretKey {
            val keyGenerator = KeyGenerator.getInstance("AES")
            keyGenerator.init(keySize)
            return keyGenerator.generateKey()
        }
    }

    @Throws(IllegalBlockSizeException::class, BadPaddingException::class)
    fun encrypt(
        plaintext: ByteArray,
        keyBytes: ByteArray
    ): ByteArray {
        val generateCipher = cipher.generateCipher(Cipher.ENCRYPT_MODE, keyBytes)
        return generateCipher.doFinal(plaintext)
    }

    fun encrypt(
        inputStream: InputStream,
        keyBytes: ByteArray
    ): InputStream {
        val generateCipher = cipher.generateCipher(Cipher.ENCRYPT_MODE, keyBytes)
        return CipherInputStream(
            inputStream,
            generateCipher
        )
    }

    fun encrypt(outputStream: OutputStream, keyBytes: ByteArray): OutputStream {
        val generateCipher = cipher.generateCipher(Cipher.ENCRYPT_MODE, keyBytes)
        return CipherOutputStream(
            outputStream,
            generateCipher
        )
    }

    @Throws(IllegalBlockSizeException::class, BadPaddingException::class)
    @JvmOverloads
    fun encrypt(
            plaintext: String,
            keytext: String,
            keyMode: FORMAT = FORMAT.RAW,
            textMode: FORMAT = FORMAT.BASE64
    ): String {
        val keyBytes = keytext.convertTo(keyMode)
        val encrypt = encrypt(plaintext.toByteArray(), keyBytes)
        return encrypt.convertTo(textMode)
    }

    @Throws(IllegalBlockSizeException::class, BadPaddingException::class)
    fun decrypt(
        encryptedBytes: ByteArray,
        keyBytes: ByteArray
    ): ByteArray {
        val generateCipher = cipher.generateCipher(Cipher.DECRYPT_MODE, keyBytes)
        return generateCipher.doFinal(encryptedBytes)
    }

    fun decrypt(
        inputStream: InputStream,
        keyBytes: ByteArray
    ): InputStream {
        val generateCipher = cipher.generateCipher(Cipher.DECRYPT_MODE, keyBytes)
        return CipherInputStream(inputStream, generateCipher)
    }

    fun decrypt(outputStream: OutputStream, keyBytes: ByteArray): OutputStream {
        val generateCipher = cipher.generateCipher(Cipher.DECRYPT_MODE, keyBytes)
        return CipherOutputStream(
            outputStream,
            generateCipher
        )
    }

    @Throws(IllegalBlockSizeException::class, BadPaddingException::class)
    @JvmOverloads
    fun decrypt(
            encrypttext: String,
            keytext: String,
            keyMode: FORMAT = FORMAT.RAW,
            textMode: FORMAT = FORMAT.BASE64
    ): String {
        val keyBytes = keytext.convertTo(keyMode)
        val decrypt = decrypt(encrypttext.convertTo(textMode), keyBytes)
        return decrypt.convertTo(FORMAT.RAW)
    }
}