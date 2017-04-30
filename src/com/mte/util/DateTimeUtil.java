package com.mte.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/23/15
 */
public class DateTimeUtil {

    /**
     * @return yyyyMMddHHmmss 20110810155638
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    /**
     * @return yyyyMMddHHmmssSSS 20130526002728796
     */
    public static String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    /**
     * @return yyyyMMdd 20110810
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * @return HHmmss 155638
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(new Date());
    }

    /**
     * @return HHmmssSSS 155039527
     */
    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
        return sdf.format(new Date());
    }

    /**
     * @param format
     * @return yyyy-MM-dd HH:mm:ss:SSS
     */
    public static String formatedTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * get specified time string in specified date format.
     *
     * @param days       days after or before current date, use + and - to add.
     * @param dateFormat the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
     */
    public static String addDaysByFormatter(int days, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, days);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(cal.getTime());
    }

    /**
     * get specified time string in specified date format.
     *
     * @param months     months after or before current date, use + and - to add.
     * @param dateFormat the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
     */
    public static String addMonthsByFormatter(int months, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, months);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(cal.getTime());
    }

    /**
     * get specified time string in specified date format.
     *
     * @param years      years after or before current date, use + and - to add.
     * @param dateFormat the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
     */
    public static String addYearsByFormatter(int years, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, years);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(cal.getTime());
    }

    /**
     * get first day of next month in specified date format.
     *
     * @param dateFormat the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
     */
    public static String firstDayOfNextMonth(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(cal.getTime());
    }

    /**
     * get first day of specified month and specified year in specified date
     * format.
     *
     * @param year       the year of the date.
     * @param month      the month of the date.
     * @param dateFormat the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
     */
    public static String firstDayOfMonth(int year, int month, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(cal.getTime());
    }

    /**
     * @param month      the month of the date.
     * @param dateFormat the formatter of date, such as:yyyy-MM-dd HH:mm:ss:SSS.
     */
    public static String firstDayOfMonth(int month, String dateFormat) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(cal.getTime());
    }

    /**
     * get the system current milliseconds.
     */
    public static String getMilSecNow() {
        return String.valueOf(System.currentTimeMillis());
    }


}
