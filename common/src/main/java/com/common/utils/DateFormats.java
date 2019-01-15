//package com.common.utils;
//
//import android.annotation.SuppressLint;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.ray.common.lang.Strings;
//import com.ray.common.log.Tags;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author syy
// */
//@SuppressLint("SimpleDateFormat")
//public final class DateFormats {
//
//    private static final long SECOND_IN_MILLIS = android.text.format.DateUtils.SECOND_IN_MILLIS;
//    private static final long MINUTE_IN_MILLIS = android.text.format.DateUtils.MINUTE_IN_MILLIS;
//    private static final long HOUR_IN_MILLIS = android.text.format.DateUtils.HOUR_IN_MILLIS;
//    private static final long YEAR_IN_MILLIS = android.text.format.DateUtils.YEAR_IN_MILLIS;
//    public static final SimpleDateFormat SIMPLE_TIME_FORMAT_HHMMSS = new SimpleDateFormat("HH:mm:ss");
//    public static final SimpleDateFormat SIMPLE_TIME_FORMAT = new SimpleDateFormat("HH:mm");
//    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm");
//    public static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//    public static final SimpleDateFormat FULL_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    public static final SimpleDateFormat FULL_DATE_TIME_FORMAT2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//    public static final SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy年MM月dd号");
//    public static final SimpleDateFormat YMD_FORMAT_DAY = new SimpleDateFormat("yyyy年MM月dd日");
//    public static final SimpleDateFormat MD_FORMAT = new SimpleDateFormat("MM月dd号");
//    public static final SimpleDateFormat MD_FORMAT2 = new SimpleDateFormat("MM-dd");
//    public static final SimpleDateFormat INCOME_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
//    public static final SimpleDateFormat YMD_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd");
//    public static final SimpleDateFormat REFERRAL_TIME_FORMAT2 = new SimpleDateFormat("yyyy.MM.dd HH:mm");
//    public static final SimpleDateFormat YM = new SimpleDateFormat("yyyy-MM");
//    /**
//     * api服务器返回的时间
//     */
//    public static final SimpleDateFormat NET_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ");
//    public static final SimpleDateFormat NET_DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//    public static final SimpleDateFormat NET_DATE_FORMAT3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//    public static final SimpleDateFormat SIGN_UP_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
//    /**
//     * 就诊记录搜索时间
//     */
//    public static final SimpleDateFormat MEDICAL_RECORD_QUERY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
//
//    /**
//     * 防止每次调用的时候创建新的对象
//     */
//    private static Calendar cal;
//
//    public static long getTimeStamp(String time, SimpleDateFormat sdf)
//            throws ParseException {
//
//        Date date = sdf.parse(time);
//        return date.getTime();
//    }
//
//    public static long getTimeStampIgnoreException(String time, SimpleDateFormat sdf){
//        try {
//            Date date = sdf.parse(time);
//            return date.getTime();
//        } catch (ParseException e) {
//            Log.w(e.getMessage(), time + " -> " + sdf);
//            return -1;
//        }
//    }
//
//
//    public static String getTimeStr(long time, SimpleDateFormat sdf) {
//        return sdf.format(new Date(time));
//
//    }
//    /**
//     * 将long类型转化为Date
//     * @param str
//     * @return
//     * @throws ParseException
//     */
//    public static Date LongToDare(long str) {
//        return new Date(str * 1000);
//    }
//
//    public static String getTimeStr(Date time, SimpleDateFormat sdf) {
//        return sdf.format(time);
//    }
//
//    /**
//     * @return 解析失败返回-1
//     */
//    public static long getTimeStamps(String time, SimpleDateFormat... sdf) {
//        if (sdf != null && sdf.length > 0 && !TextUtils.isEmpty(time)) {
//            for (SimpleDateFormat format : sdf) {
//                try {
//                    Date date = format.parse(time);
//                    return date.getTime();
//                } catch (ParseException e) {
//                    Log.w(e.getMessage(), time + " -> " + format);
//                }
//            }
//            return -1;
//        } else {
//            return -1;
//        }
//    }
//
//    public static long getNowTimeStamp() {
//        return System.currentTimeMillis();
//    }
//
//    public static String getNowTimeStr(SimpleDateFormat sdf) {
//        long time = System.currentTimeMillis();
//        return sdf.format(new Date(time));
//    }
//
//    /**
//     * 格式为 yyyy-MM-dd HH:mm
//     *
//     * @param date date
//     * @return
//     */
//    public static String getFullDate(Date date) {
//        synchronized (FULL_DATE_FORMAT) {
//            return getTimeStr(date.getTime(), FULL_DATE_FORMAT);
//        }
//
//
//    }
//
//    public static String getFullDate(long time) {
//        synchronized (FULL_DATE_TIME_FORMAT) {
//            return getTimeStr(time, FULL_DATE_TIME_FORMAT);
//        }
//
//    }
//
//    public static String getNetTimeReplaceT(String time, SimpleDateFormat sdf) {
//        if (TextUtils.isEmpty(time)) {
//            return "";
//        }
//        try {
//            long timeStamp = getTimeStamp(time, sdf);
//            return getFullDate(timeStamp);
//        } catch (ParseException e) {
//            return "";
//            //e.printStackTrace();
//        }
//    }
//
//    /**
//     * 时间展示没有统一，暂时分开写
//     * C#json时间格式（/Date(*******+0800)/)）转为yy/MM/dd HH:mm:ss
//     */
//    public static String dateTimeToDateString(String datetime) throws ParseException {
//        if (TextUtils.isEmpty(datetime)) {
//            return null;
//        }
//        datetime = datetime.replace("/Date(", "");
//        datetime = datetime.replace("+0800)/", "");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        synchronized (NET_DATE_FORMAT2) {
//            Date date = new Date(getTimeStamp(datetime, NET_DATE_FORMAT2));
//            String result = dateFormat.format(date);
//            return TextUtils.isEmpty(result) ? null : result.substring(2);
//        }
//
//    }
//
//    public static String dateTimeToDateString(String datetime, SimpleDateFormat simpleDateFormat) throws ParseException {
//        if (TextUtils.isEmpty(datetime)) {
//            return null;
//        }
//        datetime = datetime.replace("/Date(", "");
//        datetime = datetime.replace("+0800)/", "");
//        synchronized (NET_DATE_FORMAT2) {
//            Date date = new Date(getTimeStamp(datetime, NET_DATE_FORMAT2));
//            String result = simpleDateFormat.format(date);
//            return TextUtils.isEmpty(result) ? "" : result;
//        }
//    }
//
//    public static String getWeekByTime(long time) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(time);
//        return getWeekStr(cal.get(Calendar.DAY_OF_WEEK));
//    }
//
//    public static String getWeekStr(int week) {
//        switch (week) {
//            case Calendar.MONDAY:
//                return "周一";
//            case Calendar.TUESDAY:
//                return "周二";
//            case Calendar.WEDNESDAY:
//                return "周三";
//            case Calendar.THURSDAY:
//                return "周四";
//            case Calendar.FRIDAY:
//                return "周五";
//            case Calendar.SATURDAY:
//                return "周六";
//            case Calendar.SUNDAY:
//                return "周日";
//            default:
//                break;
//        }
//        return "";
//    }
//
//    public static CharSequence getWeekStr(List<Integer> weeks) {
//        if (weeks == null || weeks.size() <= 0) {
//            return "";
//        }
//        Collections.sort(weeks);
//        StringBuilder result = new StringBuilder();
//        Integer[] weekArray = new Integer[weeks.size()];
//        weeks.toArray(weekArray);
//        boolean isWorkDay = weekArray.length == 5 && weekArray[0] == Calendar.MONDAY && weekArray[4] == Calendar.FRIDAY;
//        boolean isFullWeek = weekArray.length == 7 && weekArray[0] == Calendar.SUNDAY && weekArray[6] == Calendar.SATURDAY;
//        if (isFullWeek) {
//            return "每天";
//        }
//        if (isWorkDay) {
//            return "工作日";
//        }
//        for (Integer aWeekArray : weekArray) {
//            if (aWeekArray != null) {
//                result.append(getWeekStr(aWeekArray)).append("，");
//            }
//        }
//        return result.length() > 0 ? result.subSequence(0, result.length() - 1) : "";
//    }
//
//    public static String getTimeDiffDesc2(Date date, boolean isHasTime) {
//        if (date == null) {
//            return null;
//        }
//        return getTimeDiffDesc2(date.getTime(), isHasTime);
//    }
//
//    /**
//     * 按照最新时间戳规则生成日期时间字符串（1.6版本，2015-12-03）
//     *
//     * @param time
//     * @param isHasTime 是否需要时间，false只有日期没有时间，true两者都有
//     * @return
//     */
//    public static String getTimeDiffDesc2(long time, boolean isHasTime) {
//
//        // 今天
//        Calendar today = Calendar.getInstance();
//        today.set(Calendar.HOUR_OF_DAY, 0);
//        today.set(Calendar.MINUTE, 0);
//        today.set(Calendar.SECOND, 0);
//
//        // 昨天
//        Calendar yesterday = Calendar.getInstance();
//        yesterday.add(Calendar.DATE, -1);
//        yesterday.set(Calendar.HOUR_OF_DAY, 0);
//        yesterday.set(Calendar.MINUTE, 0);
//        yesterday.set(Calendar.SECOND, 0);
//
//        Calendar thenCalendar = Calendar.getInstance();
//        thenCalendar.setTimeInMillis(time);
//
//        int mon = thenCalendar.get(Calendar.MONTH);
//        int day = thenCalendar.get(Calendar.DAY_OF_MONTH);
//        int hour = thenCalendar.get(Calendar.HOUR_OF_DAY);
//        int min = thenCalendar.get(Calendar.MINUTE);
//
//        String sh = "", sm = "";
//        if (hour < 10) {
//            sh = "0";
//        }
//        if (min < 10) {
//            sm = "0";
//        }
//        if (thenCalendar.after(today)) {
//            return genTimeStr(hour, sh, sm, min);
//        } else if (thenCalendar.before(today) && (thenCalendar.after(yesterday))) {
//            String strDesc = "昨天";
//            if (isHasTime) {
//                return strDesc + ' ' + genTimeStr(hour, sh, sm, min);
//            }
//            return strDesc;
//        } else {
//            StringBuilder sb = new StringBuilder();
//            if (thenCalendar.get(Calendar.YEAR) != today.get(Calendar.YEAR)) {
//                sb.append(thenCalendar.get(Calendar.YEAR)).append('年');
//            }
//            sb.append(mon + 1).append('月').append(day).append('日');
//            if (isHasTime) {
//                sb.append(' ').append(genTimeStr(hour, sh, sm, min));
//            }
//            return sb.toString();
//        }
//    }
//
//    private static String genTimeStr(int h, String sh, String sm, int m) {
//        return genTimeStr(h, sh, sm, m, -1, null);
//    }
//
//    private static String genTimeStr(int h, String sh, String sm, int m, int s, String ss) {
//        String strH;
//        if (h < 6) {
//            strH = sh + h + ":" + sm + m;
//        } else if (h < 12) {
//            strH = sh + h + ":" + sm + m;
//        } else if (h < 13) {
//            strH = h + ":" + sm + m;
//        } else if (h < 19) {
//            strH = h + ":" + sm + m;
//        } else {
//            strH = h + ":" + sm + m;
//        }
//        if (s < 10 && s >= 0) {
//            strH += ":" + ss + s;
//        } else if (s >= 10) {
//            strH += ":" + s;
//        }
//        return strH;
//    }
//
//    /**
//     * 按照最新时间戳规则生成聊天场景日期时间字符串（1.6版本，2015-12-15）
//     *
//     * @param date 1分钟内                      ---->  刚刚
//     *             1小时内                      ---->  xx分钟前
//     *             1天内：时分秒                ----> 12:30:30
//     *             昨天：昨天+时分秒            ---> 昨天 15:32:32
//     *             其他
//     *             跨年：年月日+时分秒      ----> 2014年12月15日 15:32:32
//     *             未跨年：月日+时分秒      ----> 12月15日 15:32:32
//     * @return
//     */
//    public static String getTimeDiffDesc3(Date date) {
//        if (date == null) {
//            return null;
//        }
//
//        // 今天
//        Calendar today = Calendar.getInstance();
//        today.set(Calendar.HOUR_OF_DAY, 0);
//        today.set(Calendar.MINUTE, 0);
//        today.set(Calendar.SECOND, 0);
//
//        // 昨天
//        Calendar yesterday = Calendar.getInstance();
//        yesterday.add(Calendar.DATE, -1);
//        yesterday.set(Calendar.HOUR_OF_DAY, 0);
//        yesterday.set(Calendar.MINUTE, 0);
//        yesterday.set(Calendar.SECOND, 0);
//
//        Calendar thenCalendar = Calendar.getInstance();
//        thenCalendar.setTime(date);
//
//        int mon = thenCalendar.get(Calendar.MONTH);
//        int day = thenCalendar.get(Calendar.DAY_OF_MONTH);
//        int h = thenCalendar.get(Calendar.HOUR_OF_DAY);
//        int m = thenCalendar.get(Calendar.MINUTE);
//        int s = thenCalendar.get(Calendar.SECOND);
//
//        String sh = "", sm = "", ss = "";
//        if (h < 10) {
//            sh = "0";
//        }
//        if (m < 10) {
//            sm = "0";
//        }
//        if (s < 10) {
//            ss = "0";
//        }
//        if (thenCalendar.after(today)) {
//            String strDesc = "";
//            long time = System.currentTimeMillis() - thenCalendar.getTime().getTime();
//            if (time < 60 * 1000) {
//                strDesc = "刚刚";
//            } else if (time < 60 * 60 * 1000) {
//                strDesc = time / 1000 / 60 + "分钟前";
//            } else {
//                strDesc = genTimeStr(h, sh, sm, m, s, ss);
//            }
//            return strDesc;
//        } else if (thenCalendar.before(today) && (thenCalendar.after(yesterday))) {
//            String strDesc = "昨天";
//            return strDesc + ' ' + genTimeStr(h, sh, sm, m, s, ss);
//        } else {
//            StringBuilder sb = new StringBuilder();
//            if (thenCalendar.get(Calendar.YEAR) != today.get(Calendar.YEAR)) {
//                sb.append(thenCalendar.get(Calendar.YEAR)).append('年').append(' ');
//            }
//            sb.append(mon + 1).append('月').append(day).append('日');
//            sb.append(' ').append(genTimeStr(h, sh, sm, m, s, ss));
//            return sb.toString();
//        }
//    }
//
//    /**
//     * 按照最新时间戳规则生成聊天场景日期时间字符串（1.6版本，2015-12-15）
//     *
//     * @param date 1天内：时分秒                   ----> 12:30
//     *             昨天：昨天+时分                 ---> 昨天 15:32
//     *             其他
//     *             跨年：年月日+周几+时分          ----> 2014年12月15日 周一 15:32
//     *             未跨年：月日+周几+时分          ----> 12月15日 周一 15:32
//     * @return
//     */
//    public static String getTimeDiffDesc4(Date date) {
//        if (date == null) {
//            return null;
//        }
//
//        // 今天
//        Calendar today = Calendar.getInstance();
//        today.set(Calendar.HOUR_OF_DAY, 0);
//        today.set(Calendar.MINUTE, 0);
//        today.set(Calendar.SECOND, 0);
//
//        // 昨天
//        Calendar yesterday = Calendar.getInstance();
//        yesterday.add(Calendar.DATE, -1);
//        yesterday.set(Calendar.HOUR_OF_DAY, 0);
//        yesterday.set(Calendar.MINUTE, 0);
//        yesterday.set(Calendar.SECOND, 0);
//
//        Calendar thenCalendar = Calendar.getInstance();
//        thenCalendar.setTime(date);
//
//        int mon = thenCalendar.get(Calendar.MONTH);
//        int day = thenCalendar.get(Calendar.DAY_OF_MONTH);
//        int h = thenCalendar.get(Calendar.HOUR_OF_DAY);
//        int m = thenCalendar.get(Calendar.MINUTE);
//        int s = thenCalendar.get(Calendar.SECOND);
//        int w = thenCalendar.get(Calendar.WEEK_OF_MONTH);
//
//        String sh = "", sm = "", ss = "";
//        if (h < 10) {
//            sh = "0";
//        }
//        if (m < 10) {
//            sm = "0";
//        }
//        if (s < 10) {
//            ss = "0";
//        }
//        if (thenCalendar.after(today)) {
//            String strDesc = "";
//            long time = System.currentTimeMillis() - thenCalendar.getTimeInMillis();
//            strDesc = genTimeStr(h, sh, sm, m);
//            return strDesc;
//        } else if (thenCalendar.before(today) && (thenCalendar.after(yesterday))) {
//            String strDesc = "昨天";
//            return strDesc + ' ' + genTimeStr(h, sh, sm, m);
//        } else {
//            StringBuilder sb = new StringBuilder();
//            if (thenCalendar.get(Calendar.YEAR) != today.get(Calendar.YEAR)) {
//                sb.append(thenCalendar.get(Calendar.YEAR)).append('年').append(' ');
//            }
//            sb.append(mon + 1).append('月').append(day).append('日');
//            sb.append(' ').append(getWeekStr(w));
//            sb.append(' ').append(genTimeStr(h, sh, sm, m));
//            return sb.toString();
//        }
//    }
//
//    public static String format(String date, SimpleDateFormat originalFormat, SimpleDateFormat targetFormat) {
//        if (TextUtils.isEmpty(date)) {
//            return Strings.EMPTY;
//        }
//        try {
//            long time = getTimeStamp(date, originalFormat);
//            return getTimeStr(time, targetFormat);
//        } catch (ParseException e) {
//            Tags.App.e(e, date + " -> " + originalFormat);
//            return Strings.EMPTY;
//        }
//    }
//
//    /**
//     * 时间格式转换 StringToC#DateTime
//     */
//    public static String stringToCCDateTime(String dateTime, boolean isEnd) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        String tmpdatetime = null;
//        if (isEnd) {
//            tmpdatetime = dateTime + " 23:59:59";
//        } else {
//            tmpdatetime = dateTime + " 00:00:00";
//        }
//
//        try {
//            date = dateFormat.parse(tmpdatetime);
//
//            return "/Date(" + date.getTime() + "+0800)/";
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//}
