package com.common.utils;

import java.security.MessageDigest;

import static com.qiniu.android.common.Constants.UTF_8;

/**
 * @desc: MD5加密
 * @date: 2016/12/29
 */
public class MD5Utils {
    private MD5Utils() {
        throw new UnsupportedOperationException("error...");
    }

    public static String byteArrayToHexString(byte byteArray[]) {
        StringBuffer strHex = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++)
            strHex.append(byteToHexString(byteArray[i]));
        return strHex.toString();
    }

    private static String byteToHexString(byte bt) {
        int i = bt;
        if (i < 0) i += 256;
        int j = i / 16;
        int k = i % 16;
        return hexDigits[j] + hexDigits[k];
    }

    public static String MD5Encode(String strOrigin) {
        String strAim = null;
        try {
            strAim = new String(strOrigin);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            strAim = byteArrayToHexString(messageDigest.digest(strAim.getBytes()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return strAim;
    }

    /**
     *   加密文本
     * @param text
     * @return
     */
    public static String encrypt(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(text.getBytes(UTF_8));
            return toHex(digest.digest());
        } catch (Exception e) {
            return "";
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuffer buffer = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; ++i) {
            buffer.append(Character.forDigit((bytes[i] & 240) >> 4, 16));
            buffer.append(Character.forDigit(bytes[i] & 15, 16));
        }
        return buffer.toString();
    }

    public static byte[] MD5Encode2ByteArray(String strOrigin) {
        String strAim = null;
        byte[] byteAim = null;
        try {
            strAim = new String(strOrigin);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byteAim = messageDigest.digest(strAim.getBytes());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return byteAim;
    }

    private static final String hexDigits[] = {
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f"
    };
}
