package shangri.example.com.shangri.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import shangri.example.com.shangri.model.bean.response.WeekAndMonthBean;

/**
 * Created by Administrator on 2018/1/12.
 */

public class TimeUtil {

    public static String getNowTime(String s) {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(s);
        return dateFormat.format(date);
    }


    //得到当前时间 精确到分
    public static long getCurrentTime()
            throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式 精确到分
        String strTime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        Date date = stringToDate(strTime, "yyyy-MM-dd HH:mm"); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }


    //得到当前时间 精确到分
    public static long getCurrentTime1()
            throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式 精确到分
        String strTime = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        Date date = stringToDate(strTime, "yyyy-MM-dd"); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // date类型转换为long类型
    // date要转换的date类型的时间  获取10位的时间戳  因为java 是13 位  php 是10位
    public static long dateToLong(Date date) {
        return date.getTime() / 1000;
    }


    //字符串转指定格式时间
    public static String getMyDate(String str) {
        return StringToDate(str, "yyyy-MM-dd", "MM-dd");
    }

    //字符串转指定格式时间
    public static String getMyDate2(String str) {
        return StringToDate(str, "yyyy-MM-dd", "MM月dd日");
    }

    //字符串转指定格式时间
    public static String getMyDate3(String str) {
        return StringToDate(str, "yyyy-MM-dd", "MM.dd");
    }


    //字符串转指定格式时间
    public static String getMyDate1(String str) {
        return StringToDate(str, "yyyy-MM", "yyyy年MM月");
    }

    //字符串转指定格式时间
    public static String getMyDate(String str, String nowType, String CreateType) {
        return StringToDate(str, nowType, CreateType);
    }


    public static String StringToDate(String dateStr, String dateFormatStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(dateFormatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(formatStr);

        return s.format(date);
    }

    /**
     * 取得今天，昨天，前天，明天，后天...的日期
     *
     * @param sel --- 0->当天    -1->昨天     -2->前天      1->明天      2->后天  ......
     * @return ------- 返回指定日期
     */
    public static String getOurSelData(int sel) {
        String str = "";
        //格式化日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy" + "年" + "MM" + "月" + "dd" + "日");
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, sel);
        str = df.format(calendar.getTime());
        return str;
    }


    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        if (currentTime == 0) {
            return "0";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        Date date = new Date(currentTime * 1000L);
        return sdf.format(date);

    }

    /**
     * 转换时间日期格式字串为long型
     *
     * @param time 格式为：yyyy-MM-dd HH:mm:ss的时间日期类型
     */
    public static Long convertTimeToLong(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d:H:m:s");
            date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 转换时间日期格式字串为long型
     *
     * @param time 格式为：yyyy-MM-dd HH:mm:ss的时间日期类型
     */
    public static Long convertTimeToLong8(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 转换时间日期格式字串为long型
     *
     * @param time 格式为：yyyy-MM-dd HH:mm:ss的时间日期类型
     */
    public static Long convertTimeToLong10(String time, String type) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(type);
            date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }


    /**
     * 转换时间日期格式字串为long型
     *
     * @param time 格式为：yyyy-MM-dd HH:mm:ss的时间日期类型
     */
    public static Long convertTimeToLong1(String time) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    //根据当前时间获取在此时间一个月以后的时间的集合

    public static ArrayList<String> last() {
        ArrayList<String> list = new ArrayList<>();
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(new Date()); //设置时间为当前时间
        ca.add(Calendar.MONTH, +1); //获取一个月或是一年一天之后的现在时间，之前的时间用 "-"号（昨天） 之后的时间用"+"(明天)
        Date lastMonth = ca.getTime(); //结果
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Log.d("Debug", "一个月以后的时间是" + sdf.format(lastMonth));
        List<Date> listDate = getDatesBetweenTwoDate(new Date(), ca.getTime());
        for (int i = 0; i < listDate.size(); i++) {
            Log.d("Debug", "返回的时间为" + sdf.format(listDate.get(i)));
            list.add(sdf.format(listDate.get(i)));
        }
        return list;
    }

    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     *
     * @param beginDate
     * @param endDate
     * @return List
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }

    /**
     * <pre>
     * 根据指定的日期字符串获取星期几
     * </pre>
     *
     * @param strDate 指定的日期字符串(yyyy-MM-dd 或 yyyy/MM/dd)
     * @return week
     * 星期几(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY)
     */
    public static String getWeekByDateStr(String strDate) {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int day = Integer.parseInt(strDate.substring(8, 10));

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);
        switch (weekIndex) {
            case 1:
                week = "日";
//                WeekNumber = "7";
                break;
            case 2:
                week = "一";
//                WeekNumber = "1";
                break;
            case 3:
                week = "二";
//                WeekNumber = "2";
                break;
            case 4:
                week = "三";
//                WeekNumber = "3";
                break;
            case 5:
                week = "四";
//                WeekNumber = "4";
                break;
            case 6:
                week = "五";
//                WeekNumber = "5";
                break;
            case 7:
                week = "六";
//                WeekNumber = "6";
                break;
        }
        return week;
    }


    public static final int SECONDS_IN_DAY = 60 * 60 * 24;
    public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;

    public static boolean isSameDayOfMillis(final long ms1, final long ms2) {
        final long interval = ms1 - ms2;
        return interval < MILLIS_IN_DAY
                && interval > -1L * MILLIS_IN_DAY
                && toDay(ms1) == toDay(ms2);
    }

    private static long toDay(long millis) {
        return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
    }


    /**
     * 返回指定年度的所有周。List中包含的是String[2]对象<br>
     * string[0]本周的开始日期,string[1]是本周的结束日期。<br>
     * 日期的格式为yyyy-MM-dd。<br>
     * 每年的第一个周，必须包含星期一且是完整的七天。<br>
     * 例如：2009年的第一个周开始日期为2009-01-05，结束日期为2009-01-11。 <br>
     * 星期一在哪一年，那么包含这个星期的周就是哪一年的周。<br>
     * 例如：2008-12-29是星期一，2009-01-04是星期日，哪么这个周就是2008年度的最后一个周。<br>
     *
     * @param year 格式 yyyy  ，必须大于1900年度 小于9999年
     * @return
     */
    public static List<WeekAndMonthBean> getWeeksByYear(final int year) {
        if (year < 1900 || year > 9999) {
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }
        //实现思路，首先计算当年有多少个周，然后找到每个周的开始日期和结束日期

//      Calendar calendar = new GregorianCalendar();
//      // 在具有默认语言环境的默认时区内使用当前时间构造一个默认的 GregorianCalendar。
//      calendar.setFirstDayOfWeek(Calendar.MONDAY); //设置每周的第一天为星期一
//      calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //每周从周一开始
//      上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。
//      calendar.setMinimalDaysInFirstWeek(7);  //设置每周最少为7天
//      calendar.set(Calendar.YEAR, year); // 设置年度为指定的年

//      //首先计算当年有多少个周,每年都至少有52个周，个别年度有53个周

        int weeks = getWeekNumByYear(year);


        Calendar calendar = Calendar.getInstance();
        //设置为传来的时间
        calendar.setTime(calendar.getTime());
        //美国默认一周的开始为周日   设置为一周的开始为周一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        //当前的时间
        int Nowyear = calendar.get(Calendar.YEAR);

        Log.d("Debug", "当前的年份是" + Nowyear);
        //传过来的时间在当前属于第几周
        int NowWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR);

//      System.out.println(year+"共有"+weeks+"个周");
        List<WeekAndMonthBean> result = new ArrayList<WeekAndMonthBean>();
        for (int i = weeks; i >= 1; i--) {
            if (Nowyear == year) {
                if (i <= NowWeekNumber) {
                    WeekAndMonthBean tempWeek = new WeekAndMonthBean();
                    tempWeek.setWeek(i + "");
                    tempWeek.StartTime = getYearWeekFirstDay(year, i);
                    tempWeek.EndTime = getYearWeekEndDay(year, i);
                    result.add(tempWeek);
                }
            } else {
                WeekAndMonthBean tempWeek = new WeekAndMonthBean();
                tempWeek.setWeek(i + "");
                tempWeek.StartTime = getYearWeekFirstDay(year, i);
                tempWeek.EndTime = getYearWeekEndDay(year, i);
                result.add(tempWeek);

            }

        }
        return result;
    }

    /**
     * 传过来穿过置顶时间看是第几周
     */
    public static int getWeekNumByTime(String time) {

        Calendar calendar = Calendar.getInstance();
        int NowWeekNumber = 1;
        try {
            Date date = stringToDate(time, "yyyy-MM-dd");
            //设置为传来的时间
            calendar.setTime(date);
            //美国默认一周的开始为周日   设置为一周的开始为周一
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            //传过来的时间在当前属于第几周
            NowWeekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return NowWeekNumber;
    }


    /**
     * 两个年份之前相差年数
     */
    public static int yearChect(String year) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) - Integer.parseInt(year);
    }

    /**
     * 传过来穿过置顶时间看是第几周
     */
    public static int getMonthNumByTime(String time) {

        Calendar calendar = Calendar.getInstance();
        int NowMonthNumber = 1;
        try {
            Date date = stringToDate(time, "yyyy-MM");
            //设置为传来的时间
            calendar.setTime(date);
            //这里要注意，月份是从0开始。
            NowMonthNumber = calendar.get(Calendar.MONTH) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return NowMonthNumber;
    }


    /**
     * 计算指定年度共有多少个周。
     *
     * @param year 格式 yyyy  ，必须大于1900年度 小于9999年
     * @return
     */
    public static int getWeekNumByYear(final int year) {
        if (year < 1900 || year > 9999) {
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }
        int result = 52;//每年至少有52个周 ，最多有53个周。
        String date = getYearWeekFirstDay(year, 53);
        if (date.substring(0, 4).equals(year + "")) { //判断年度是否相符，如果相符说明有53个周。
            result = 53;
        }
        return result;
    }


    /**
     * 计算某年某周的开始日期
     *
     * @param yearNum 格式 yyyy  ，必须大于1900年度 小于9999年
     * @param weekNum 1到52或者53
     * @return 日期，格式为yyyy-MM-dd
     */
    public static String getYearWeekFirstDay(int yearNum, int weekNum) {
        if (yearNum < 1900 || yearNum > 9999) {
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); //设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//每周从周一开始
//       上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。
        cal.setMinimalDaysInFirstWeek(7);  //设置每周最少为7天
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);

        //分别取得当前日期的年、月、日
        return dateToString(cal.getTime(), "yyyy年MM月dd日");
    }

    /**
     * 计算某年某周的结束日期
     *
     * @param yearNum 格式 yyyy  ，必须大于1900年度 小于9999年
     * @param weekNum 1到52或者53
     * @return 日期，格式为yyyy-MM-dd
     */
    public static String getYearWeekEndDay(int yearNum, int weekNum) {
        if (yearNum < 1900 || yearNum > 9999) {
            throw new NullPointerException("年度必须大于等于1900年小于等于9999年");
        }
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY); //设置每周的第一天为星期一
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//每周从周一开始
//       上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。
        cal.setMinimalDaysInFirstWeek(7);  //设置每周最少为7天
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);

        return dateToString(cal.getTime(), "yyyy年MM月dd日");
    }


    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static Long getSpecifiedDayBefore(String specifiedDay) {
//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return convertTimeToLong10(dayBefore, "yyyy-MM-dd");
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static Long getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());


        return convertTimeToLong10(dayAfter, "yyyy-MM-dd");
    }


    public static boolean isSameDay(Date date, Date sameDate) {

        if (null == date || null == sameDate) {

            return false;

        }

        Calendar nowCalendar = Calendar.getInstance();

        nowCalendar.setTime(sameDate);

        Calendar dateCalendar = Calendar.getInstance();

        dateCalendar.setTime(date);

        return nowCalendar.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR)

                && nowCalendar.get(Calendar.MONTH) == dateCalendar.get(Calendar.MONTH)

                && nowCalendar.get(Calendar.DATE) == dateCalendar.get(Calendar.DATE);

    }


    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */

    public static String getOldDate(int distanceDay, String time) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        try {
            beginDate = stringToDate(time, "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtil.d("前7天==" + dft.format(endDate));
        return dft.format(endDate);
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param time         System.currentTimeMillis()
     * @param strDateBegin 开始时间 00:00:00
     * @param strDateEnd   结束时间 00:05:00
     * @return
     */
    public static boolean isInDate(long time, String strDateBegin, String strDateEnd) {
        Calendar calendar = Calendar.getInstance();
        // 处理开始时间
        String[] startTime = strDateBegin.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(startTime[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(startTime[1]));
        calendar.set(Calendar.SECOND, Integer.valueOf(startTime[2]));
        calendar.set(Calendar.MILLISECOND, 0);
        long startTimeL = calendar.getTimeInMillis();
        // 处理结束时间
        String[] endTime = strDateEnd.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(endTime[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(endTime[1]));
        calendar.set(Calendar.SECOND, Integer.valueOf(endTime[2]));
        calendar.set(Calendar.MILLISECOND, 0);
        long endTimeL = calendar.getTimeInMillis();
        return time >= startTimeL && time <= endTimeL;
    }

    public static void main(String[] args) {
        System.out.println(isInDate(System.currentTimeMillis(), "17:00:00", "17:30:00"));
    }


    /**
     * 约聊时间显示
     */

    public static String getTtime(long time) {
        String showTime = "";
        try {
            long currentTime = getCurrentTime();
            //求出当前时间 检出传过来时间的之间的秒数
            long l = currentTime - time;
            //当天的消息
            if (l < 60 * 60 * 24) {
                showTime = longToString(time , "HH:mm");
            } else if (60 * 60 * 24 < l && l < 60 * 60 * 24 * 2) {
//                昨天的消息
                showTime = "昨天  " + longToString(time , "HH:mm");

            } else if (60 * 60 * 24 * 2 < l && l < 60 * 60 * 24 * 7) {
//                消息超过两天、小于1周
                //传过来时间是星期几
                String weekByDateStr = getWeekByDateStr(longToString(time , "yyyy-MM-dd"));

                showTime = "星期" + weekByDateStr + "  " + longToString(time , "HH:mm");

            } else if (l > 60 * 60 * 24 * 7) {
//                消息大于1周
                showTime = longToString(time , "yyyy-MM-dd");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return showTime;
    }


}