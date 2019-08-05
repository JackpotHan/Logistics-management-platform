package com.lmp.admin.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 日期工具类
 * @date 2019/1/16 17:22
 */
public class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYYMMDDTHHMMSS = "yyyy-MM-dd'T'HH:mm:ss.SSS'+08:00'";

    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public static String YYYYMMDDHHMMSS_ = "yyyy/MM/dd HH:mm:ss";

    /**
     * yyyyMMddHHmmss
     */
    public static String YYYYMMDDHHMMSS_STRING = "yyyyMMddHHmmss";

    /**
     * yyyy-MM-dd
     */
    public static String YYYYMMDD = "yyyy-MM-dd";

    /**
     * YYYYMMdd
     */
    public static String YYYYMMDD_STRING = "yyyyMMdd";

    /**
     * yyyy-MM-dd 00:00:00
     */
    public static String YYYYMMDD_DAY_START = "yyyy-MM-dd 00:00:00";

    /**
     * yyyy-MM-dd 23:59:59
     */
    public static String YYYYMMDD_DAY_END = "yyyy-MM-dd 23:59:59";

    /**
     * yyyy-MM
     */
    public static String YYYYMM = "yyyy-MM";

    /**
     * yyyyMM
     */
    public static String YYYYMM_STRING = "yyyyMM";

    /**
     * yyyy
     */
    public static String YYYY = "yyyy";

    /**
     * dd
     */
    public static String DD = "dd";

    /**
     * MM
     */
    public static String MM = "MM";

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        return toDateTime(formatNow(YYYYMMDDHHMMSS));
    }

    /**
     * 格式化时间
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null)
            return "";
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 格式化时间
     *
     * @param date 日期
     * @return
     */
    public static String formatDate(String date) {
        if (date == null)
            return "";
        if (date.indexOf(':') < 0) {
            date += " 00:00:00";
        }
        return format(toDate(date, YYYYMMDDHHMMSS), YYYYMMDDHHMMSS_);
    }

    /**
     * 格式化时间
     */
    public static Date formatToDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isEmpty(pattern)) {
            pattern = YYYYMMDDHHMMSS;
        }
        return toDate(new SimpleDateFormat(pattern).format(date), pattern);
    }

    /**
     * 设置基于当前时间的某天
     *
     * @param day
     * @param addMonth
     * @return
     */
    public static Date setDay(int day, int addMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, day);
        c.add(Calendar.MONTH, addMonth);
        return c.getTime();
    }

    /**
     * 格式化当前时间
     *
     * @param pattern
     * @return
     */
    public static String formatNow(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 格式化当前时间-yyMMdd
     *
     * @return
     */
    public static String formatTody() {
        return formatNow("yyMMdd");
    }

    /**
     * 格式化当前时间-yyyyMMddHHmmss
     *
     * @return
     */
    public static String formatNowTime() {
        return formatNow("yyyyMMddHHmmss");
    }

    /**
     * 格式化当前时间-yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String formatTime() {
        return formatNow("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化当前时间-yyyyMMddHHmmssSSSS
     *
     * @return
     */
    public static String formatNowTimeStamp() {
        return formatNow("yyyyMMddHHmmssSSSS");
    }

    public static String formatNowTimeStampAndTimeZone(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));// 这里一定要设置GMT时区
        return df.format(new Date());
    }

    /**
     * 字符串转换为日期
     *
     * @param dateString
     * @return
     */
    public static Date toDate(String dateString) {
        return toDate(dateString, "yyyy-MM-dd");
    }

    /**
     * 获取当天0点日期
     *
     * @return
     */
    public static Date getCurrentZeroDate() {
        return toDate(format(new Date(), "yyyy-MM-dd") + " 00:00:00", YYYYMMDDHHMMSS);
    }

    /**
     * 获取某天最小的时间点：yyyyMMdd 00:00:00
     */
    public static Date getDayFirst(Date date) {
        return DateUtil.toDate(DateUtil.format(date, DateUtil.YYYYMMDD) + " 00:00:00",
                DateUtil.YYYYMMDDHHMMSS);
    }

    public static Date getDayFirst(String dateStr){
        return toDate(dateStr + " 00:00:00", YYYYMMDDHHMMSS);
    }

    public static Date getDayLast(String dateStr){
        LOGGER.info("日期格式字符串:{}", dateStr);
        return toDate(dateStr + " 23:59:59", YYYYMMDDHHMMSS);
    }

    /**
     * 获取某天最后的时间点：yyyyMMdd 23:59:59
     */
    public static Date getDayLast(Date date) {
        return DateUtil.toDate(DateUtil.format(date, DateUtil.YYYYMMDD) + " 23:59:59",
                DateUtil.YYYYMMDDHHMMSS);
    }

    /**
     * 字符串转换为 年月日 时分秒
     *
     * @param dateString
     * @return
     */
    public static Date toDateTime(String dateString) {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        return toDate(dateString, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date toDate(String dateString, String format) {
        if (StringUtils.isEmpty(dateString))
            return null;
        DateFormat df = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = df.parse(dateString);
        } catch (Exception e) {
            LOGGER.error("日期转换错误[{}][{}]", dateString, format);
        }
        return d;
    }

    /**
     * 获取昨天日期
     *
     * @return
     */
    public static Date getYeserday() {
        return new Date(DateUtil.getCurrentDate().getTime() - 24 * 60 * 60 * 1000);
    }

    public static String getYesterday() {
        Date yeserday = getYeserday();
        return format(yeserday, "yyyy-MM-dd");
    }

    /**
     * 获取指定几天前的最早日期时间
     *
     * @return
     */
    public static Date getDateOfAgoDay(int agoDays) {
        return toDate(format(getDateOfAgoDays(-agoDays), "yyyy-MM-dd") + " 00:00:00",
                YYYYMMDDHHMMSS);
    }

    /**
     * 获取指定几天后的最早日期时间
     *
     * @return
     */
    public static Date getDateOfLaterDay(int agoDays) {
        return toDate(format(getDateOfAgoDays(agoDays), "yyyy-MM-dd") + " 00:00:00", YYYYMMDDHHMMSS);
    }

    /**
     * 获取指定几天前的日期时间
     *
     * @return
     */
    public static Date getDateOfAgoDays(int agoDays) {
        Date date = getCurrentDate();
        return getDateOfAgoDays(agoDays, date);
    }

    /**
     * 获取指定几天前的日期时间
     *
     * @param agoDays
     * @param date
     * @return
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static Date getDateOfAgoDays(int agoDays, Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, agoDays);
        return c.getTime();
    }

    /**
     * 获取前一个月日期
     *
     * @return
     */
    public static Date getOneMonthAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String dateString = format.format(m);
        return toDateTime(dateString);
    }

    /**
     * 获取指定日期前一个月日期
     *
     * @param date
     * @return
     */
    public static Date getOneMonthAgoOfDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String dateString = format.format(m);
        return toDateTime(dateString);
    }

    /**
     * 获取前一年日期
     *
     * @return
     */
    public static Date getOneYearAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date m = c.getTime();
        String dateString = format.format(m);
        return toDateTime(dateString);
    }

    /**
     * 获取指定日期前一年日期
     *
     * @param date
     * @return
     */
    public static Date getOneYearAgoOfDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        c.add(Calendar.YEAR, -1);
        Date m = c.getTime();
        String dateString = format.format(m);
        return toDateTime(dateString);
    }

    /**
     * 获取当前月开始日期
     *
     * @return
     */
    public static Date getFirstDayOfCurrentMonth() {
        return toDate(format(new Date(), "yyyy-MM") + "-01 00:00:00", YYYYMMDDHHMMSS);
    }

    /**
     * 获取指定月份第一天
     */
    public static Date getFirstDayOfMonth(Date date) {

        if (date == null)
            return null;
        return toDate(format(date, "yyyy-MM") + "-01 00:00:00", YYYYMMDDHHMMSS);
    }

    /**
     * 获取指定月份最后一天
     */
    public static Date getLastDayOfMonth(Date date) {
        if (date == null)
            return null;
        date = getFirstDayOfMonth(date);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return toDate(format(c.getTime(), "yyyy-MM-dd") + " 23:59:59", YYYYMMDDHHMMSS);
    }

    /**
     * 获取本周星期一的日期
     */
    public static Date getFirstDayOfCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取前8天日期
     *
     * @return
     */
    public static Date getOneWeekAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.DATE, -8);
        Date d = c.getTime();
        String dateString = format.format(d);
        dateString = dateString.substring(0, dateString.indexOf(" ")) + " 00:00:00";
        return toDateTime(dateString);
    }

    /**
     * 获取前一个星期日期
     *
     * @return
     */
    public static Date getOneOfWeekAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        c.setTime(new Date());
        c.add(Calendar.DATE, -7);
        Date d = c.getTime();
        String dateString = format.format(d);
        dateString = dateString.substring(0, dateString.indexOf(" ")) + " 00:00:00";
        return toDateTime(dateString);
    }

    public static String getYeserdayDate() {
        return format(getYeserday(), "yyyyMMdd");
    }

    /**
     * 日期比较,获取较大时间
     *
     * @param one
     * @param two
     * @return
     */
    public static Date maxDate(Date one, Date two) {
        return one.getTime() > two.getTime() ? one : two;
    }

    /**
     * 日期比较,获取较小时间
     *
     * @param one
     * @param two
     * @return
     */
    public static Date minDate(Date one, Date two) {
        return one.getTime() > two.getTime() ? two : one;
    }

    /**
     * 毫秒 转 日期
     *
     * @param s 毫秒
     * @return
     */
    public static Date timestampString2Date(String s) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.valueOf(s));
        return c.getTime();
    }

    /**
     * 毫秒 转 日期字符串
     *
     * @param s 毫秒
     * @return
     */
    public static String timestampString2DateStr(String s) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.valueOf(s));
        return format(c.getTime(), YYYYMMDDHHMMSS);
    }

    /**
     * 日期字符串 转 毫秒
     *
     * @param pattern    格式
     * @param dateString 日期字符串
     * @return
     */
    public static Long dateString2millisecond(String pattern, String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date = sdf.parse(dateString);
            long time = date.getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date now() {
        return new Date();
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static long millisecondsBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = time2 - time1;

        return between_days;
    }

    /**
     * 日期与当前日期比较，时间差大于time毫秒 返回 true
     *
     * @param srcDate 日期
     * @param time    时间差毫秒
     * @return
     */
    public static boolean dateCompare(Date srcDate, long time) throws ParseException {
        long milliseconds = DateUtil.millisecondsBetween(srcDate, new Date());
        return milliseconds > time;
    }

    /**
     * 获取前几秒钟的时间
     *
     * @return
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static Date minSecondsAgoDate(int minSeconds) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        int now = calendar.get(Calendar.SECOND);
        calendar.set(Calendar.SECOND, now - minSeconds);
        String format = df.format(calendar.getTime());
        return toDateTime(format);
    }

    /**
     * 获取前几分钟的时间
     *
     * @return
     * @author sunxiaolu
     * @since JDK 1.8
     */
    public static Date minPointsAgoDate(int minPoints) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        int now = calendar.get(Calendar.MINUTE);
        calendar.set(Calendar.MINUTE, now - minPoints);
        String format = df.format(calendar.getTime());
        return toDateTime(format);
    }

    public static String stringToDate(String time) {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            time = formatter1.format(formatter2.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static long getCurrentTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date = format.parse(formatNow("HH:mm:ss"));
            return date.getTime() / 1000;
        } catch (ParseException e) {

        }
        return 0L;
    }

    /**
     * add月数
     *
     * @return yyyyMMdd
     */
    public static Date addMonths(Date date, int nums) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, nums);
        return formatToDate(c.getTime(), YYYYMMDD);
    }

    /**
     * add年数
     *
     * @return yyyyMMdd
     */
    public static Date addYears(Date date, int nums) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, nums);
        return formatToDate(c.getTime(), YYYYMMDD);
    }

    /**
     * add天数
     */
    public static Date addDays(Date date, int nums) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, nums);
        return formatToDate(c.getTime(), YYYYMMDD);
    }

    /**
     * add分钟
     */
    public static Date addMinute(Date date, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return formatToDate(c.getTime(), YYYYMMDDHHMMSS);
    }

    /**
     * 获取当前 UTC 时间
     *
     * @param date
     * @return
     */
    public static String getISO8601Timestamp(Date date) {
        TimeZone tz = TimeZone.getTimeZone("GMT");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }

    /**
     * 获取指定月份后的日期
     *
     * @param inputDate null 默认当前日期
     * @param number    月数
     * @return
     */
    public static Date getAfterMonth(String inputDate, int number) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            if (StringUtils.isEmpty(inputDate)) {
                date = formatToDate(new Date(), YYYYMMDD);
            } else {
                date = sdf.parse(inputDate);//初始日期
            }
        } catch (Exception e) {

        }
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH, number);//
        //        String strDate = sdf.format(c.getTime());//
        return c.getTime();
    }

    /**
     * 获取两个时间段的每天时期，只保留到天 返回字符串
     *
     * @param startDate
     * @param endDate
     * @param endDate   pattern：yyyyMMdd,yyyy-MM-dd
     * @return
     */
    public static List<String> daysByTimes(Date startDate, Date endDate, String pattern) {
        // 返回的日期集合
        List<String> days = new ArrayList<>();

        Date start = DateUtil.formatToDate(startDate, DateUtil.YYYYMMDD);
        Date end = DateUtil.formatToDate(endDate, DateUtil.YYYYMMDD);

        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        // 日期加1(包含结束)
        tempEnd.add(Calendar.DATE, 1);
        while (tempStart.before(tempEnd)) {
            days.add(DateUtil.format(tempStart.getTime(), pattern));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return days;
    }

    /**
     * 获取两个时间段的每天时期，只保留到天 返回时间
     *
     * @param startDate
     * @param endDate
     * @param endDate   pattern：yyyyMMdd,yyyy-MM-dd
     * @return
     */
    public static List<Date> daysByDates(Date startDate, Date endDate, String pattern) {
        // 返回的日期集合
        List<Date> days = new ArrayList<>();

        Date start = DateUtil.formatToDate(startDate, DateUtil.YYYYMMDD);
        Date end = DateUtil.formatToDate(endDate, DateUtil.YYYYMMDD);

        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        // 日期加1(包含结束)
        tempEnd.add(Calendar.DATE, 1);
        while (tempStart.before(tempEnd)) {
            days.add(DateUtil.formatToDate(tempStart.getTime(), pattern));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return days;
    }

    /**
     * 获取当前时间半点时间戳，00-30 返回00 时间戳 30-60 返回30的时间戳
     *
     * @return
     */
    public static String getHalfTimestampNow() {
        return getHalfTimestamp(new Date());
    }

    /**
     * 获取时间半点时间戳，00-30 返回00 时间戳 30-60 返回30的时间戳
     *
     * @return
     */
    public static String getHalfTimestamp(Date time) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(time);
        c2.setTime(time);
        c1.set(Calendar.MINUTE, 30);
        c1.set(Calendar.SECOND, 00);
        if (c2.before(c1)) {
            c1.set(Calendar.MINUTE, 00);
        }
        return c1.getTimeInMillis() / 1000 + "000";
    }

    /**
     * 获取一周的星期一 日期
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return formatToDate(calendar.getTime(), YYYYMMDD);
    }

    /**
     * 获取一周的星期日 日期
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.add(Calendar.DATE, 6);
        return formatToDate(calendar.getTime(), YYYYMMDD);
    }

    /**
     * 获取一天的开始时间
     *
     * @param date
     * @return
     */
    public static Date getFirstTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        return calendar.getTime();
    }

    /**
     * 获取一天的结束时间
     *
     * @param date
     * @return
     */
    public static Date getLastTimeOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    //    public static void main(String[] args) {
    //        Date date = toDate("201809", YYYYMM_STRING);
    //        System.out.println(format(getFirstDayOfMonth(date), YYYYMMDD));
    //        System.out.println(getAfterMonth(null,12));
    //        System.out.println(minPointsAgoDate(60));

    //    }

}
