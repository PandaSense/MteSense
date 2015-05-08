package com.mte.util;

/**
 * Created by java on 3/18/15.
 */
public class UnicodeHex {

    public static String decode(String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        String[] ss = str.split("\\\\");
        int i = 0, j = 0;
        int length = ss.length;
        char[] cs = new char[length];
        for (; i < ss.length; i++) {
            if (!ss[i].trim().equals("")) {
                cs[j] = (char) (Integer.valueOf(ss[i].substring(1), 16).intValue());
                j++;
            }
        }
        return new String(cs);
    }

    public static String encode(String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (c > 256) {
                sb.append("\\u");
            } else {
                sb.append("\\x");
            }
            sb.append(Integer.toHexString(c));
        }
        return sb.toString();
    }

}