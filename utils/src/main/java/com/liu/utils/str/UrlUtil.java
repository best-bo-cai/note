package com.liu.utils.str;

import java.io.UnsupportedEncodingException;

public class UrlUtil {
   private final static String ENCODE = "UTF-8";
   /**
    * URL 解码
    */
   public static String getURLDecoderString(String str) throws UnsupportedEncodingException {
       String result = "";
       if (null == str) {
           return "";
       }
       result = java.net.URLDecoder.decode(str, ENCODE);
       return result;
   }
   /**
    * URL 转码
    */
   public static String getURLEncoderString(String str) throws UnsupportedEncodingException{
       String result = "";
       if (null == str) {
           return "";
       }
       result = java.net.URLEncoder.encode(str, ENCODE);
       return result;
   }
   /**
   	* 测试
   	*/
   public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "\\\\127.0.0.1\\share-lx\\789.docx";
        //转码
        String urlEncoderString = UrlUtil.getURLEncoderString(url);
        System.out.println(urlEncoderString);
        //解码
        String urlDecoderString = UrlUtil.getURLDecoderString(urlEncoderString);
        System.out.println(urlDecoderString);
    }
}