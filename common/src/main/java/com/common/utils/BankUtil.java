package com.common.utils;

public class BankUtil {

    public static String tran(String str, int m, int n, String rep) {
        StringBuffer hehe = new StringBuffer(str);
        int j = 0;
        while (m + n * j < str.length()) {
            hehe.insert(m + (n + 1) * j, rep);
            j++;
        }
        return hehe.toString();
    }


    public static String hideBank(String bankNum) {
//        String substring = bankNum.substring(0, 4);
        String substring1 = bankNum.substring(bankNum.length() - 4, bankNum.length());
        StringBuilder sb = new StringBuilder();
        sb.append("**** **** **** **** ").append(substring1);
        return sb.toString();
    }

}
