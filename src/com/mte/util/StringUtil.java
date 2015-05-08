package com.mte.util;

/**
 * Created by java on 3/23/15.
 */
public class StringUtil {


    public static boolean validateNull(String args) {
        if (args == null || args.length() == 0 || args.equalsIgnoreCase("null")) {
            return true;
        } else {
            return false;
        }
    }


    public static String chanageNull(String source, String target) {
        if (source == null || source.length() == 0
                || source.equalsIgnoreCase("null")) {
            return target;
        } else {
            return source;
        }
    }

    /**
     * Filter <, >,\n
     */
    public static final String filterStr(String str) {
        str = str.replaceAll(";", "");
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("'", "");
        str = str.replaceAll("--", " ");
        str = str.replaceAll("/", "");
        str = str.replaceAll("%", "");
        str = str.replaceAll(" ", "&nbsp;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("\n", "<br>");
        str = str.replaceAll("\r", "<br>");
        return str;
    }
}
