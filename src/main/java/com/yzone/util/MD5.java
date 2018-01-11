package com.yzone.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

public class MD5 {

    public static final String getMd5(String str) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            return MD5.bytesToHex(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {

            return "";
        } catch (UnsupportedEncodingException e) {

            return "";
        }
    }

    // 通过位运算，将字节数组到十六进制的转换
    public static final String bytesToHex(byte[] byteArray) {

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;

        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }

        return new String(resultCharArray);
    }

    public static void main(String[] args) {
        System.out.println(MD5.getMd5("abc"));
    }
}
