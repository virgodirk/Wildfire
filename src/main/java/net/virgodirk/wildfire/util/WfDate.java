package net.virgodirk.wildfire.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Locale;

/**
 * Date Utils
 *
 * @author 李晓勇 on 2017年8月23日 上午11:00:51
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfDate {
    
    /**
     * 短日期长度
     */
    protected static final int DATE_SHORT_SIZE = 8;
    
    /**
     * 短日期格式模板
     */
    protected static final String DATE_SHORT = "yyyyMMdd";
    
    /**
     * 长日期格式模板
     */
    protected static final String DATE_LONG = "yyyy-MM-dd";
    
    
    /**
     * 短日期格式器
     */
    public static final DateTimeFormatter DATE_SHORT_FORMATTER = DateTimeFormatter.ofPattern(DATE_SHORT);
    
    /**
     * 长日期格式器
     */
    public static final DateTimeFormatter DATE_LONG_FORMATTER = DateTimeFormatter.ofPattern(DATE_LONG);
    
    
    /**
     * 获取本地当前日期
     * @return 本地当前日期
     */
    public static LocalDate getNow() {
        return LocalDate.now(ZoneId.systemDefault());
    }
    
    /**
     * 获取本地当前日期字符串（短日期格式）
     * @return 本地当前日期字符串（短日期格式），如：20180101
     */
    public static String getNowShort() {
        return LocalDate.now(ZoneId.systemDefault()).format(DATE_SHORT_FORMATTER);
    }
    
    /**
     * 获取本地当前日期字符串（长日期格式）
     * @return 本地当前日期字符串（长日期格式），如：2018-01-01
     */
    public static String getNowLong() {
        return LocalDate.now(ZoneId.systemDefault()).format(DATE_LONG_FORMATTER);
    }
    
    
    /**
     * 获取本地当前年份
     * @return 本地当前年份
     */
    public static int getYear() {
        return LocalDate.now(ZoneId.systemDefault()).getYear();
    }
    
    /**
     * 获取 {@code date} 的年份
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return {@code date} 的年份
     */
    public static int getYear(final String date) {
        final LocalDate localDate = Convert.toLocalDate(date);
        return localDate == null ? 0 : localDate.getYear();
    }
    
    /**
     * 获取本地当前月份
     * @return 本地当前月份
     */
    public static int getMonth() {
        return LocalDate.now(ZoneId.systemDefault()).getMonthValue();
    }
    
    /**
     * 获取 {@code date} 的月份
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return {@code date} 的月份
     */
    public static int getMonth(final String date) {
        final LocalDate localDate = Convert.toLocalDate(date);
        return localDate == null ? 0 : localDate.getMonthValue();
    }

    /**
     * 获取今天是星期几
     * @return 1 星期一<br>
     *         2 星期二<br>
     *         3 星期三<br>
     *         4 星期四<br>
     *         5 星期五<br>
     *         6 星期六<br>
     *         7 星期日
     */
    public static int getWeek() {
        return LocalDate.now(ZoneId.systemDefault()).getDayOfWeek().getValue();
    }
    
    /**
     * 获取给定日期是星期几
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return 0 {@code date} 日期字符串无效<br>
     *         1 星期一<br>
     *         2 星期二<br>
     *         3 星期三<br>
     *         4 星期四<br>
     *         5 星期五<br>
     *         6 星期六<br>
     *         7 星期日
     */
    public static int getWeek(final String date) {
        final LocalDate localDate = Convert.toLocalDate(date);
        return localDate == null ? 0 : localDate.getDayOfWeek().getValue();
    }
    
    
    /**
     * 将 {@link LocalDate} 按照格式模板进行格式化
     * @param date {@link LocalDate}
     * @param pattern 格式模板，如：yyyy-MM-dd、yyyy年MM月dd日
     * @return 格式化后的日期字符串
     */
    public static String format(final LocalDate date, final String pattern) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return date == null ? "" : date.format(formatter);
        } catch (UnsupportedTemporalTypeException excpt) {
            return "";
        }
    }
    
    /**
     * 将日期字符串按照格式模板进行格式化
     * @param date 日期字符串，如：20180101、2018-01-01
     * @param pattern 格式模板，如：yyyy-MM-dd、yyyy年MM月dd日
     * @return 格式化后的日期字符串
     */
    public static String format(final String date, final String pattern) {
        return format(Convert.toLocalDate(date), pattern);
    }
    
    /**
     * 将 {@link LocalDate} 格式化为短日期字符串
     * @param date {@link LocalDate}
     * @return 短日期字符串，如：20180101
     */
    public static String formatShort(final LocalDate date) {
        return date == null ? "" : date.format(DATE_SHORT_FORMATTER);
    }
    
    /**
     * 将日期字符串格式化为短日期
     * @param date 日期字符串，如：2018-01-01
     * @return 短日期字符串，如：20180101
     */
    public static String formatShort(final String date) {
        return formatShort(Convert.toLocalDate(date));
    }
    
    /**
     * 将 {@link LocalDate} 格式化为长日期字符串
     * @param date {@link LocalDate}
     * @return 长日期字符串，如：2018-01-01
     */
    public static String formatLong(final LocalDate date) {
        return date == null ? "" : date.format(DATE_LONG_FORMATTER);
    }
    
    /**
     * 将日期字符串格式化为长日期
     * @param date 日期字符串，如：20180101
     * @return 长日期字符串，如：2018-01-01
     */
    public static String formatLong(final String date) {
        return formatLong(Convert.toLocalDate(date));
    }
    
 
    /**
     * 比较 {@code date} 与本地当前日期在日期上的大小
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return {@code > 0：date} 大于本地当前日期<br>
     *         {@code < 0：date} 小于本地当前日期<br>
     *         {@code = 0：date} 等于本地当前日期
     */
    public static long compareToNow(final String date) {
        final LocalDate localDate = Convert.toLocalDate(date);
        if (localDate == null) {
            return 0;
        }
        final LocalDate localDateNow = LocalDate.now(ZoneId.systemDefault());
        return localDate.compareTo(localDateNow);
    }
    
    /**
     * 比较 {@code dateA} 与 {@code dateB} 在日期上的大小
     * @param dateA 日期字符串A，如：20180101、2018-01-01
     * @param dateB 日期字符串B，如：20180101、2018-01-01
     * @return {@code > 0：dataA > dataB}<br>
     *         {@code < 0：dataA < dataB}<br>
     *         {@code = 0：dataA = dataB}
     */
    public static long compare(final String dateA, final String dateB) {
        final LocalDate localDateA = Convert.toLocalDate(dateA);
        if (localDateA == null) {
            return 0;
        }
        
        final LocalDate localDateB = Convert.toLocalDate(dateB);
        if (localDateB == null) {
            return 0;
        }
        
        return localDateA.compareTo(localDateB);
    }
    
    
    /**
     * 比较 {@code date} 是否早于本地当前日期
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return 返回 {@code true} 表示 {@code date} 早于本地当前日期
     */
    public static boolean isBeforeNow(final String date) {
        final LocalDate localDate = Convert.toLocalDate(date);
        if (localDate == null) {
            return false;
        } 
        final LocalDate localDateNow = LocalDate.now(ZoneId.systemDefault());
        return localDate.isBefore(localDateNow);
    }
    
    /**
     * 比较 {@code dateA} 是否早于 {@code dateB}
     * @param dateA 日期字符串A，如：20180101、2018-01-01
     * @param dateB 日期字符串B，如：20180101、2018-01-01
     * @return 返回 {@code true} 表示 {@code dateA} 早于 {@code dateB}
     */
    public static boolean isBefore(final String dateA, final String dateB) {
        final LocalDate localDateA = Convert.toLocalDate(dateA);
        if (localDateA == null) {
            return false;
        }
        
        final LocalDate localDateB = Convert.toLocalDate(dateB);
        if (localDateB == null) {
            return false;
        }
        
        return localDateA.isBefore(localDateB);
    }
    
    
    /**
     *  比较 {@code date} 是否晚于本地当前日期
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return 返回 {@code true} 表示 {@code date} 晚于本地当前日期
     */
    public static boolean isAfterNow(final String date) {
        final LocalDate localDate = Convert.toLocalDate(date);
        if (localDate == null) {
            return false;
        } 
        final LocalDate localDateNow = LocalDate.now(ZoneId.systemDefault());
        return localDate.isAfter(localDateNow);
    } 
    
    /**
     * 比较 {@code dateA} 是否晚于 {@code dateB}
     * @param dateA 日期字符串A，如：20180101、2018-01-01
     * @param dateB 日期字符串B，如：20180101、2018-01-01
     * @return 返回 {@code true} 表示 {@code dateA} 晚于 {@code dateB}
     */
    public static boolean isAfter(final String dateA, final String dateB) {
        final LocalDate localDateA = Convert.toLocalDate(dateA);
        if (localDateA == null) {
            return false;
        }
        
        final LocalDate localDateB = Convert.toLocalDate(dateB);
        if (localDateB == null) {
            return false;
        }
        
        return localDateA.isAfter(localDateB);
    }
    
    
    /**
     * 给本地当前日期增加指定的天数
     * @param days 要增加的天数，为负数时表示减去指定天数
     * @return 增加指定天数后的日期字符串（短日期格式），如：20180101
     */
    public static String addDays(final int days) {
        final LocalDate localDateNow = LocalDate.now(ZoneId.systemDefault());
        return localDateNow.plusDays(days).format(DATE_SHORT_FORMATTER);
    }
    
    /**
     * 给日期字符串增加指定的天数
     * @param date 日期字符串，如：20180101、2018-01-01
     * @param days 要增加的天数，为负数时表示减去指定天数
     * @return 增加指定天数后的日期字符串，格式与参数 {@code date} 相同
     */
    public static String addDays(final String date, final int days) {
        final LocalDate localDate = Convert.toLocalDate(date);
        if (localDate == null) {
            return "";
        }
        
        if (date.length() == DATE_SHORT_SIZE) {
            return localDate.plusDays(days).format(DATE_SHORT_FORMATTER);
        }
        return localDate.plusDays(days).format(DATE_LONG_FORMATTER);
    }
    
    
    /**
     * 给本地当前日期增加指定的周数
     * @param weeks 要增加的周数，为负数时表示减去指定周数
     * @return 增加指定周数后的日期字符串（短日期格式），如：20180101
     */
    public static String addWeeks(final int weeks) {
        final LocalDate localDateNow = LocalDate.now(ZoneId.systemDefault());
        return localDateNow.plusWeeks(weeks).format(DATE_SHORT_FORMATTER);
    }
    
    /**
     * 给日期字符串增加指定的周数
     * @param date 日期字符串，如：20180101、2018-01-01
     * @param weeks 要增加的周数，为负数时表示减去指定周数
     * @return 增加指定周数后的日期字符串，格式与参数 {@code date} 相同
     */
    public static String addWeeks(final String date, final int weeks) {
        final LocalDate localDate = Convert.toLocalDate(date);
        if (localDate == null) {
            return "";
        }
        
        if (date.length() == DATE_SHORT_SIZE) {
            return localDate.plusWeeks(weeks).format(DATE_SHORT_FORMATTER);
        }
        return localDate.plusWeeks(weeks).format(DATE_LONG_FORMATTER);
    }
    
    
    /**
     * 给本地当前日期增加指定的月数
     * @param months 要增加的月数，为负数时表示减去指定月数
     * @return 增加指定月数后的日期字符串（短日期格式），如：20180101
     */
    public static String addMonths(final int months) {
        final LocalDate localDateNow = LocalDate.now(ZoneId.systemDefault());
        return localDateNow.plusMonths(months).format(DATE_SHORT_FORMATTER);
    }
    
    /**
     * 给日期字符串增加指定的月数
     * @param date 日期字符串，如：20180101、2018-01-01
     * @param months 要增加的月数，为负数时表示减去指定月数
     * @return 增加指定月数后的日期字符串，格式与参数 {@code date} 相同
     */
    public static String addMonths(final String date, final int months) {
        final LocalDate localDate = Convert.toLocalDate(date);
        if (localDate == null) {
            return "";
        }
        
        if (date.length() == DATE_SHORT_SIZE) {
            return localDate.plusMonths(months).format(DATE_SHORT_FORMATTER);
        }
        return localDate.plusMonths(months).format(DATE_LONG_FORMATTER);
    }

    
    /**
     * 给本地当前日期增加指定的年数
     * @param years 要增加的年数，为负数时表示减去指定年数
     * @return 增加指定年数后的日期字符串（短日期格式），如：20180101
     */
    public static String addYears(final int years) {
        final LocalDate localDateNow = LocalDate.now(ZoneId.systemDefault());
        return localDateNow.plusYears(years).format(DATE_SHORT_FORMATTER);
    }
    
    /**
     * 给日期字符串增加指定的年数
     * @param date 日期字符串，如：20180101、2018-01-01
     * @param years 要增加的年数，为负数时表示减去指定年数
     * @return 增加指定年数后的日期字符串，格式与参数 {@code date} 相同
     */
    public static String addYears(final String date, final int years) {
        final LocalDate localDate = Convert.toLocalDate(date);
        if (localDate == null) {
            return "";
        }
        
        if (date.length() == DATE_SHORT_SIZE) {
            return localDate.plusYears(years).format(DATE_SHORT_FORMATTER);
        }
        return localDate.plusYears(years).format(DATE_LONG_FORMATTER);
    }
    
    
    /**
     * 获取 {@code date} 与本地当前日期之间的周期
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return {@code date} 与本地当前日期之间的周期（由年数、月数和天数组成）
     */
    public static Period getPeriod(final String date) {
        final LocalDate localDate = Convert.toLocalDate(date);
        return localDate == null ? Period.ZERO : Period.between(localDate, LocalDate.now(ZoneId.systemDefault()));
    }
    
    /**
     * 获取由两个日期之间的年数、月数和天数组成的周期
     * @param startDate 开始日期字符串，如：20180101、2018-01-01
     * @param endDate 结束日期字符串，如：20180101、2018-01-01
     * @return 由两个日期之间的年数、月数和天数组成的周期
     */
    public static Period getPeriod(final String startDate, final String endDate) {
        final LocalDate localDateStart = Convert.toLocalDate(startDate);
        if (localDateStart == null) {
            return Period.ZERO;
        }
        
        final LocalDate localDateEnd = Convert.toLocalDate(endDate);
        if (localDateEnd == null) {
            return Period.ZERO;
        }
        
        return Period.between(localDateStart, localDateEnd);
    }
    
    
    /**
     * 获取 {@code date} 与本地当前日期相差的年数
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return {@code date} 与本地当前日期相差的年数
     */
    public static long getYearSpan(final String date) {
        return until(date, ChronoUnit.YEARS);
    }
    
    /**
     * 获取两个日期相差的年数
     * @param startDate 开始日期字符串，如：20180101、2018-01-01
     * @param endDate 结束日期字符串，如：20180101、2018-01-01
     * @return 两个日期相差的年数
     */
    public static long getYearSpan(final String startDate, final String endDate) {
        return until(startDate, endDate, ChronoUnit.YEARS);
    }
    
    /**
     * 获取 {@code date} 与本地当前日期相差的月数
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return {@code date} 与本地当前日期相差的月数
     */
    public static long getMonthSpan(final String date) {
        return until(date, ChronoUnit.MONTHS);
    }
    
    /**
     * 获取两个日期相差的月数
     * @param startDate 开始日期字符串，如：20180101、2018-01-01
     * @param endDate 结束日期字符串，如：20180101、2018-01-01
     * @return 两个日期相差的月数
     */
    public static long getMonthSpan(final String startDate, final String endDate) {
        return until(startDate, endDate, ChronoUnit.MONTHS);
    }
    
    /**
     * 获取 {@code date} 与本地当前日期相差的周数
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return {@code date} 与本地当前日期相差的周数
     */
    public static long getWeekSpan(final String date) {
        return until(date, ChronoUnit.WEEKS);
    }
    
    /**
     * 获取两个日期相差的周数
     * @param startDate 开始日期字符串，如：20180101、2018-01-01
     * @param endDate 结束日期字符串，如：20180101、2018-01-01
     * @return 两个日期相差的周数
     */
    public static long getWeekSpan(final String startDate, final String endDate) {
        return until(startDate, endDate, ChronoUnit.WEEKS);
    }
    
    /**
     * 获取 {@code date} 与本地当前日期相差的天数
     * @param date 日期字符串，如：20180101、2018-01-01
     * @return {@code date} 与本地当前日期相差的天数
     */
    public static long getDaySpan(final String date) {
        return until(date, ChronoUnit.DAYS);
    }
    
    /**
     * 获取两个日期相差的天数
     * @param startDate 开始日期字符串，如：20180101、2018-01-01
     * @param endDate 结束日期字符串，如：20180101、2018-01-01
     * @return 两个日期相差的天数
     */
    public static long getDaySpan(final String startDate, final String endDate) {
        return until(startDate, endDate, ChronoUnit.DAYS);
    }
    
    
    /**
     * 获取本地当前年份有多少天
     * @return 本地当前年份天数
     */
    public static int lengthOfYear() {
        return LocalDate.now(ZoneId.systemDefault()).lengthOfYear();
    }
    
    /**
     * 根据年份获取该年有多少天
     * @param year 年份
     * @return 该年份天数
     */
    public static int lengthOfYear(final int year) {
        final LocalDate localDate = Convert.toLocalDate(String.format("%d0101", year));
        return localDate == null ? 0 : localDate.lengthOfYear();
    }
    
    /**
     * 获取本地当前月份有多少天
     * @return 本地当前月份天数
     */
    public static int lengthOfMonth() {
        return LocalDate.now(ZoneId.systemDefault()).lengthOfMonth();
    }
    
    /**
     * 根据月份获取该月有多少天
     * @param year 年份
     * @param month 月份
     * @return 该月份天数
     */
    public static int lengthOfMonth(final int year, final int month) {
        if (month < 1 || month > 12) {
            return 0;
        }
        final LocalDate localDate = Convert.toLocalDate(String.format("%d%02d01", year, month));
        return localDate == null ? 0 : localDate.lengthOfMonth();
    }
    
    
    /**
     * 获取给定月份的工作日总数
     * @param year 年份
     * @param month 月份
     * @return 工作日总数（除去双休日）
     */
    public static int getWorkDaysOfMonth(final int year, final int month) {
        final String date = String.format(Locale.ENGLISH, "%d%02d01", year, month);
        final LocalDate localDate = Convert.toLocalDate(date);
        if (localDate == null) {
            return 0;
        }
        
        int totalHoliday = 0;
        final int totalDays = localDate.lengthOfMonth();
        for (int i = 1; i <= totalDays; i++) {
            if (getWeek(String.format(Locale.ENGLISH, "%04d%02d%02d", year, month, i)) >= 6) {
                totalHoliday++;
            }
        }
        return totalDays - totalHoliday;
    }
    
    /**
     * 计算周岁年龄
     * @param birthDate 出生日期，如：19800101、1980-01-01
     * @return 周岁年龄，出生日期无效返回-1
     */
    public static int getAge(final String birthDate) {
        final LocalDate localDateBirth = Convert.toLocalDate(birthDate);
        if (localDateBirth == null) {
            // 出生日期无效
            return -1;
        }
        
        final LocalDate localDateNow = LocalDate.now(ZoneId.systemDefault());
        if (localDateBirth.isAfter(localDateNow)) {
            // 出生日期还没有到
            return -1;
        }
        
        // 计算年龄
        int age = (int) localDateBirth.until(localDateNow, ChronoUnit.YEARS);
        if (localDateBirth.withYear(getYear()).isBefore(localDateNow)) {
            age -= 1; // 尚未过生日，年龄减1
        }
        return age;
    }
    
    
    /**
     * 检查是否为闰年
     * @param year 待检查年份
     * @return {@code true} 是闰年<br>
     *         {@code false} 不是闰年
     */
    public static boolean isLeapYear(final int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 检查是否为小月（不包含二月）
     * @param month 待检查月份（1-12）
     * @return {@code true} 是小月
     *         {@code false} 不是小月
     */
    public static boolean isLunarMonth(final int month) {
        return month == 4 || month == 6 || month == 9 || month == 11;
    }

    /**
     * 检查年份是否有效
     * @param year 年份
     * @return {@code true} 年份有效
     *         {@code false} 年份无效
     */
    public static boolean isYear(final int year) {
        return year >= 1000;
    }
    
    /**
     * 检查月份是否有效
     * @param month 月份
     * @return {@code true} 月份有效
     *         {@code false} 月份无效
     */
    public static boolean isMonth(final int month) {
        return month >= 1 && month <= 12;
    }
    
    
    /**
     * 计算 {@code date} 与本地当前日期之差
     * @param date 日期字符串，如：20180101、2018-01-01
     * @param unit 计时单位
     * @return {@code date} 与本地当前日期之差
     */
    private static long until(final String date, final ChronoUnit unit) {
        final LocalDate localDateStart = Convert.toLocalDate(date);
        if (localDateStart == null) {
            return 0L;
        }
        final LocalDate localDateEnd = LocalDate.now(ZoneId.systemDefault());
        return localDateStart.until(localDateEnd, unit);
    }
    
    /**
     * 计算两个日期之差
     * @param startDate 开始日期字符串，如：20180101、2018-01-01
     * @param endDate 结束日期字符串，如：20180101、2018-01-01
     * @param unit 计时单位
     * @return 两个日期之差
     */
    private static long until(final String startDate, final String endDate, final ChronoUnit unit) {
        final LocalDate localDateStart = Convert.toLocalDate(startDate);
        if (localDateStart == null) {
            return 0L;
        }
        
        final LocalDate localDateEnd = Convert.toLocalDate(endDate);
        if (localDateEnd == null) {
            return 0L;
        }
        
        return localDateStart.until(localDateEnd, unit);
    }
    

    /**
     * 日期类型转换器
     *
     * @author 李晓勇 on 2018年4月27日 上午10:36:37
     * @version Version 2.0
     */
    public static class Convert {
        
        /**
         * 将日期字符串转为 {@link LocalDate}
         * @param date 日期字符串，如：20180101、2018-01-01
         * @return {@link LocalDate}
         */
        public static LocalDate toLocalDate(final String date) {
            if (date == null || date.length() < DATE_SHORT_SIZE) {
                return null;
            }
            
            try {
                if (date.length() <= DATE_SHORT_SIZE) {
                    return LocalDate.parse(date, DATE_SHORT_FORMATTER);
                }
                return LocalDate.parse(date, DATE_LONG_FORMATTER);
            } catch (DateTimeException excpt) {
                return null;
            }
        }
        
        /**
         * 将 {@link LocalDate} 转为日期字符串
         * @param date {@link LocalDate}
         * @return 短日期字符串，如：20180101
         */
        public static String toString(final LocalDate date) {
            return date == null ? "" : formatShort(date);
        }
        
    }

}