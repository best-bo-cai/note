package com.liu.utils.cipher;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.InputStream;

/**
 * 请求转化器（RSA+AES混合加密）
 */

public class RequestConvertor {
    private byte[] keyBytes;
    private final CipherHelper helper = CipherHelper.getInstance("AES");

    public RequestConvertor(String encryptCode, String rsaPrivateKey) {
        try {
            if (StringUtils.isEmpty(encryptCode)) {
                return;
            }
            String aesKey = CipherHelper.getInstance("RSA").decrypt(encryptCode, rsaPrivateKey, FORMAT.BASE64);
            keyBytes = CipherHelperKt.convertTo(aesKey, FORMAT.BASE64);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("解密失败");
        }
    }

    public InputStream decode(InputStream inputStream) {
        if (keyBytes == null) {
            return inputStream;
        } else {
            return helper.decrypt(inputStream, keyBytes);
        }
    }

}