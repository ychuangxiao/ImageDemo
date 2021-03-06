package com.sb.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by banditcat-pc on 2017/7/1.
 */

public class TimeUtils {


    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * <p>在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类，所以日志格式主要使用 SimpleDateFormat的定义格式.</p>
     * 格式的意义如下： 日期和时间模式 <br>
     * <p>日期和时间格式由日期和时间模式字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
     * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
     * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
     * </p>
     * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
     * <table border="1" cellspacing="1" cellpadding="1" summary="Chart shows pattern letters, date/time component,
     * presentation, and examples.">
     * <tr>
     * <th align="left">字母</th>
     * <th align="left">日期或时间元素</th>
     * <th align="left">表示</th>
     * <th align="left">示例</th>
     * </tr>
     * <tr>
     * <td><code>G</code></td>
     * <td>Era 标志符</td>
     * <td>Text</td>
     * <td><code>AD</code></td>
     * </tr>
     * <tr>
     * <td><code>y</code> </td>
     * <td>年 </td>
     * <td>Year </td>
     * <td><code>1996</code>; <code>96</code> </td>
     * </tr>
     * <tr>
     * <td><code>M</code> </td>
     * <td>年中的月份 </td>
     * <td>Month </td>
     * <td><code>July</code>; <code>Jul</code>; <code>07</code> </td>
     * </tr>
     * <tr>
     * <td><code>w</code> </td>
     * <td>年中的周数 </td>
     * <td>Number </td>
     * <td><code>27</code> </td>
     * </tr>
     * <tr>
     * <td><code>W</code> </td>
     * <td>月份中的周数 </td>
     * <td>Number </td>
     * <td><code>2</code> </td>
     * </tr>
     * <tr>
     * <td><code>D</code> </td>
     * <td>年中的天数 </td>
     * <td>Number </td>
     * <td><code>189</code> </td>
     * </tr>
     * <tr>
     * <td><code>d</code> </td>
     * <td>月份中的天数 </td>
     * <td>Number </td>
     * <td><code>10</code> </td>
     * </tr>
     * <tr>
     * <td><code>F</code> </td>
     * <td>月份中的星期 </td>
     * <td>Number </td>
     * <td><code>2</code> </td>
     * </tr>
     * <tr>
     * <td><code>E</code> </td>
     * <td>星期中的天数 </td>
     * <td>Text </td>
     * <td><code>Tuesday</code>; <code>Tue</code> </td>
     * </tr>
     * <tr>
     * <td><code>a</code> </td>
     * <td>Am/pm 标记 </td>
     * <td>Text </td>
     * <td><code>PM</code> </td>
     * </tr>
     * <tr>
     * <td><code>H</code> </td>
     * <td>一天中的小时数（0-23） </td>
     * <td>Number </td>
     * <td><code>0</code> </td>
     * </tr>
     * <tr>
     * <td><code>k</code> </td>
     * <td>一天中的小时数（1-24） </td>
     * <td>Number </td>
     * <td><code>24</code> </td>
     * </tr>
     * <tr>
     * <td><code>K</code> </td>
     * <td>am/pm 中的小时数（0-11） </td>
     * <td>Number </td>
     * <td><code>0</code> </td>
     * </tr>
     * <tr>
     * <td><code>h</code> </td>
     * <td>am/pm 中的小时数（1-12） </td>
     * <td>Number </td>
     * <td><code>12</code> </td>
     * </tr>
     * <tr>
     * <td><code>m</code> </td>
     * <td>小时中的分钟数 </td>
     * <td>Number </td>
     * <td><code>30</code> </td>
     * </tr>
     * <tr>
     * <td><code>s</code> </td>
     * <td>分钟中的秒数 </td>
     * <td>Number </td>
     * <td><code>55</code> </td>
     * </tr>
     * <tr>
     * <td><code>S</code> </td>
     * <td>毫秒数 </td>
     * <td>Number </td>
     * <td><code>978</code> </td>
     * </tr>
     * <tr>
     * <td><code>z</code> </td>
     * <td>时区 </td>
     * <td>General time zone </td>
     * <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code> </td>
     * </tr>
     * <tr>
     * <td><code>Z</code> </td>
     * <td>时区 </td>
     * <td>RFC 822 time zone </td>
     * <td><code>-0800</code> </td>
     * </tr>
     * </table>
     * <pre>
     *                          HH:mm    15:44
     *                         h:mm a    3:44 下午
     *                        HH:mm z    15:44 CST
     *                        HH:mm Z    15:44 +0800
     *                     HH:mm zzzz    15:44 中国标准时间
     *                       HH:mm:ss    15:44:40
     *                     yyyy-MM-dd    2016-08-12
     *               yyyy-MM-dd HH:mm    2016-08-12 15:44
     *            yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
     *       yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
     *  EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
     *       yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     *   yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
     *                         K:mm a    3:44 下午
     *               EEE, MMM d, ''yy    星期五, 八月 12, '16
     *          hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
     *   yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
     *     EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
     *                  yyMMddHHmmssZ    160812154440+0800
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     * EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
     * </pre>
     * 注意：SimpleDateFormat不是线程安全的，线程安全需用{@code ThreadLocal<SimpleDateFormat>}
     */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_PATTERN_1 = "yyyyMMdd";
    public static final String DEFAULT_PATTERN_2 = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_PATTERN_3 = "MM-dd HH:mm";
    public static final String DEFAULT_PATTERN_4 = "HH:mm";

    public static final String DEFAULT_PATTERN_4_1 = "hh:mm";

    public static final String DEFAULT_PATTERN_5 = "yyyy-MM-dd";

    public static final String DEFAULT_PATTERN_6 = "yyyy-MM-dd hh:mm:ss";

    public static final String DEFAULT_PATTERN_7 = "yyyyMMddHHmmss";


    public static final String DEFAULT_PATTERN_8 = "yyyy年MM月dd日 HH:mm";

    public static final String DEFAULT_PATTERN_9 = "yyyy年MM月dd日 HH:mm:ss:SSS";


    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param millis 毫秒时间戳
     * @return 时间字符串
     */
    public static String millis2String(long millis) {
        return new SimpleDateFormat(DEFAULT_PATTERN, Locale.getDefault()).format(new Date(millis));
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为pattern</p>
     *
     * @param millis  毫秒时间戳
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String millis2String(long millis, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date(millis));
    }

    public static long millis2millis(long millis, String pattern) {
        String value = millis2String(millis);

        return string2Millis(value, pattern);
    }


    /**
     * 将Date类型转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param date Date类型时间
     * @return 时间字符串
     */
    public static String date2String(Date date) {
        return date2String(date, DEFAULT_PATTERN);
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为pattern</p>
     *
     * @param date    Date类型时间
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date date, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }

    /**
     * 将Date类型转为时间戳
     *
     * @param date Date类型时间
     * @return 毫秒时间戳
     */
    public static long date2Millis(Date date) {
        return date.getTime();
    }

    /**
     * 将时间戳转为Date类型
     *
     * @param millis 毫秒时间戳
     * @return Date类型时间
     */
    public static Date millis2Date(long millis) {
        return new Date(millis);
    }

    public static String addHour(int hour, long millis) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(millis);
        now.add(Calendar.HOUR_OF_DAY, hour);

        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN_3);

        return sdf.format(now.getTimeInMillis());
    }


    public static Long addHour(int hour, long millis, String pattern) {
        Calendar now = Calendar.getInstance();

        now.setTimeInMillis(TimeUtils.millis2millis(millis, pattern));
        now.add(Calendar.HOUR_OF_DAY, hour);



        return now.getTimeInMillis();
    }

    public static Long addHour2(int hour, long millis) {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(millis);
        now.add(Calendar.HOUR_OF_DAY, hour);


        return now.getTimeInMillis();
    }


    /**
     * 将时间字符串转为时间戳
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Millis(String time) {
        return string2Millis(time, DEFAULT_PATTERN);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Millis(String time, String pattern) {
        try {
            return new SimpleDateFormat(pattern, Locale.getDefault()).parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 两个时间戳之间的天数
     */
    public static int daysBetween(Long startMillis, Long endMillis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startMillis);
        Long time1 = cal.getTimeInMillis();
        cal.setTimeInMillis(endMillis);
        Long time2 = cal.getTimeInMillis();
        Long between_days = (time2 - time1) / 1000;//(1000 * 3600 * 24);

        between_days = between_days * 3600 * 1;

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 两个时间的分钟数
     *
     * @param startMillis 开始时间
     * @param endMillis   结束时间
     * @param minute      分钟数
     * @return
     */
    public static int minuteBetween(Long startMillis, Long endMillis, int minute) {


        Calendar cal = Calendar.getInstance();


        cal.setTimeInMillis(startMillis);
        Long time1 = cal.getTimeInMillis();
        cal.setTimeInMillis(endMillis);
        Long time2 = cal.getTimeInMillis();
        Long between_days = (time2 - time1) / 1000;


        //一分钟 = 60秒

        between_days = between_days * 60 * minute;

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String getLastDateTime(Long startMillis, Long endMillis) {
        return "";

       /* Calendar cal = Calendar.getInstance();

        //昨天时间
        Long yesterdayTime, currentDateTime;
        cal.setTimeInMillis(TimeUtils.millis2millis(startMillis, TimeUtils.DEFAULT_PATTERN_5));

        yesterdayTime = cal.getTimeInMillis();

        cal.setTimeInMillis(TimeUtils.millis2millis(endMillis, TimeUtils.DEFAULT_PATTERN_5));
        currentDateTime = cal.getTimeInMillis();

        cal.setTimeInMillis(startMillis);
        Long time1 = cal.getTimeInMillis();

        cal.setTimeInMillis(endMillis);
        Long time2 = cal.getTimeInMillis();


        //两周内 显示 星期几 HH:mm 昨天显示 显示：昨天 HH:mm 今天显示： HH:mm ，同时 只显示20分钟以内的

        //(1000 * 3600 * 24)

        cal.setTimeInMillis(TimeUtils.millis2millis(System.currentTimeMillis(), TimeUtils.DEFAULT_PATTERN_5));
        long taday = cal.getTimeInMillis();

        //分钟

        long between_days;

        //今天
        if (taday == currentDateTime) {
            between_days = (time2 - time1) / 1000; //(1000 * 60 * 20);//这个值看看是否是大于20分钟

            if (between_days > 60 * 20) {
                return TimeUtils.millis2String(endMillis, DEFAULT_PATTERN_4);
            } else {
                return "";
            }
        } else {

            //第一步 判断是否是两周外
              between_days = (taday - currentDateTime) / 1000;//(1000 * 3600 * 24);


            if (between_days == 24 * 3600) {
                between_days = (time2 - time1) / 1000; //(1000 * 60 * 20);//这个值看看是否是大于20分钟
                if (between_days > 60 * 20) {
                    return "昨天 " + TimeUtils.millis2String(endMillis, DEFAULT_PATTERN_4);
                } else {
                    return "";
                }
            } else if (between_days >= 24 * 3600 * 2 && between_days < 24 * 3600 * 14) {

                between_days = (time2 - time1) / 1000; //(1000 * 60 * 20);//这个值看看是否是大于20分钟
                if (between_days > 60 * 20) {
                    return getWeekOfDate(endMillis) + " " + TimeUtils.millis2String(endMillis, DEFAULT_PATTERN_4);
                } else {
                    return "";
                }
            } else {
                between_days = (time2 - yesterdayTime) / 1000; //(1000 * 60 * 20);//这个值看看是否是大于20分钟
                if (between_days > 60 * 20) {
                    return TimeUtils.millis2String(endMillis, DEFAULT_PATTERN_8);
                } else {
                    return "";
                }
            }
        }
*/

    }


    public static String getFirstDateTime(Long startMillis, Long endMillis) {


        Calendar cal = Calendar.getInstance();


        //昨天时间
        Long yesterdayTime;
        cal.setTimeInMillis(TimeUtils.millis2millis(startMillis, TimeUtils.DEFAULT_PATTERN_5));

        yesterdayTime = cal.getTimeInMillis();

        cal.setTimeInMillis(endMillis);
        Long time2 = cal.getTimeInMillis();


        //两周内 显示 星期几 HH:mm 昨天显示 显示：昨天 HH:mm 今天显示： HH:mm ，同时 只显示20分钟以内的

        //(1000 * 3600 * 24)

        //分钟
        Long between_days = (time2 - yesterdayTime) / (1000 * 3600 * 24);

        if (between_days > 14) {
            return TimeUtils.millis2String(startMillis, DEFAULT_PATTERN_8);
        } else if (between_days <= 14 && between_days > 1) {
            return getWeekOfDate(startMillis) + " " + TimeUtils.millis2String(startMillis, DEFAULT_PATTERN_4);
        }
        if (between_days == 1) {
            return "昨天 " + TimeUtils.millis2String(startMillis, DEFAULT_PATTERN_4);
        } else {
            return TimeUtils.millis2String(startMillis, DEFAULT_PATTERN_4);
        }
    }

    public static String getWeekOfDate(Long millis) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    public static Long comperHour(Long startMillis, Long endMillis) {


        Calendar cal = Calendar.getInstance();


        //昨天时间
        Long yesterdayTime;
        cal.setTimeInMillis(TimeUtils.millis2millis(startMillis, TimeUtils.DEFAULT_PATTERN_2));

        yesterdayTime = cal.getTimeInMillis();

        cal.setTimeInMillis(TimeUtils.millis2millis(endMillis, TimeUtils.DEFAULT_PATTERN_2));

        Long time2 = cal.getTimeInMillis();


        //小时
        Long betweenSecond = (time2 - yesterdayTime) / (1000);


        return betweenSecond;
    }


    public static Long comperMillis(Long startMillis, Long endMillis) {


        Calendar cal = Calendar.getInstance();


        //昨天时间
        Long yesterdayTime;
        cal.setTimeInMillis(TimeUtils.millis2millis(startMillis, TimeUtils.DEFAULT_PATTERN_5));

        yesterdayTime = cal.getTimeInMillis();

        cal.setTimeInMillis(TimeUtils.millis2millis(endMillis, TimeUtils.DEFAULT_PATTERN_5));

        Long time2 = cal.getTimeInMillis();


        //小时
        Long betweenSecond = (time2 - yesterdayTime) / (1000);


        return betweenSecond;
    }
}

