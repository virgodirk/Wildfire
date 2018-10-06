package net.virgodirk.wildfire.util;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * Date Time Utils
 *
 * @author 李晓勇 on 2018年4月28日 下午14:33:03
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfDateTime {

    /**
     * 短日期时间长度
     */
    protected static final int DATE_TIME_SHORT_SIZE = 14;
    
    /**
     * 短日期时间格式模板
     */
    protected static final String DATE_TIME_SHORT = "yyyyMMddHHmmss";
    
    /**
     * 长日期时间格式模板
     */
    protected static final String DATE_TIME_LONG = "yyyy-MM-dd HH:mm:ss";
    
    
    /**
     * 短日期时间格式器
     */
    public static final DateTimeFormatter DATE_TIME_SHORT_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_SHORT);
    
    /**
     * 长日期时间格式器
     */
    public static final DateTimeFormatter DATE_TIME_LONG_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_LONG);
    
    
    /**
     * 获取本地当前日期时间
     * @return 本地当前日期时间
     */
    public static LocalDateTime getNow() {
        return LocalDateTime.now(ZoneId.systemDefault());
    }
    
    /**
     * 获取本地当前日期时间字符串（短日期时间格式）
     * @return 本地当前日期时间字符串（短日期时间格式），如：20180101080001
     */
    public static String getNowShort() {
        return LocalDateTime.now(ZoneId.systemDefault()).format(DATE_TIME_SHORT_FORMATTER);
    }
    
    /**
     * 获取本地当前日期时间字符串（长日期时间格式）
     * @return 本地当前日期字符串（长日期时间格式），如：2018-01-01 08:00:01
     */
    public static String getNowLong() {
        return LocalDateTime.now(ZoneId.systemDefault()).format(DATE_TIME_LONG_FORMATTER);
    }
    
    
    /**
     * 获取本地当前时间戳
     * @return 本地当前时间戳（13位）
     */
    public static long getTimestamp() {
        return Instant.now().toEpochMilli();
    }
    
    /**
     * 获取本地当前UNIX时间戳
     * @return 本地当前UNIX时间戳（10位）
     */
    public static long getTimestampUnix() {
        return Instant.now().toEpochMilli() / 1000L;
    }
    

    /**
     * 获取本地当前年份
     * @return 本地当前年份
     */
    public static int getYear() {
        return LocalDate.now(ZoneId.systemDefault()).getYear();
    }
    
    /**
     * 获取 {@code dateTime} 的年份
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 的年份
     */
    public static int getYear(final String dateTime) {
        final LocalDate localDate = Convert.toLocalDate(dateTime);
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
     * 获取 {@code dateTime} 的月份
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 的月份
     */
    public static int getMonth(final String dateTime) {
        final LocalDate localDate = Convert.toLocalDate(dateTime);
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
     * 获取给定日期时间是星期几
     * @param dateTime 日期时间字符串，如：201801010800、2018-01-01 08:00:01
     * @return 0 {@code dateTime} 日期字符串无效<br>
     *         1 星期一<br>
     *         2 星期二<br>
     *         3 星期三<br>
     *         4 星期四<br>
     *         5 星期五<br>
     *         6 星期六<br>
     *         7 星期日
     */
    public static int getWeek(final String dateTime) {
        final LocalDate localDate = Convert.toLocalDate(dateTime);
        return localDate == null ? 0 : localDate.getDayOfWeek().getValue();
    }
    
    /**
     * 获取本地当前小时
     * @return 本地当前小时（24小时制）
     */
    public static int getHour() {
        return LocalDateTime.now(ZoneId.systemDefault()).getHour();
    }
    
    /**
     * 获取 {@code dateTime} 的小时
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 的小时（24小时制）
     */
    public static int getHour(final String dateTime) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        return localDateTime == null ? 0 : localDateTime.getHour();
    }
    
    /**
     * 获取本地当前分钟
     * @return 本地当前分钟
     */
    public static int getMinute() {
        return LocalDateTime.now(ZoneId.systemDefault()).getMinute();
    }
    
    /**
     * 获取 {@code dateTime} 的分钟
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 的分钟
     */
    public static int getMinute(final String dateTime) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        return localDateTime == null ? 0 : localDateTime.getMinute();
    }
    
    /**
     * 获取本地当前秒钟
     * @return 本地当前秒钟
     */
    public static int getSecond() {
        return LocalDateTime.now(ZoneId.systemDefault()).getSecond();
    }
    
    /**
     * 获取 {@code dateTime} 的秒钟
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 的秒钟
     */
    public static int getSecond(final String dateTime) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        return localDateTime == null ? 0 : localDateTime.getSecond();
    }
    
    
    /**
     * 将 {@link LocalDateTime} 按照格式模板进行格式化
     * @param dateTime {@link LocalDateTime}
     * @param pattern 格式模板，如：yyyy-MM-dd HH:mm:ss、yyyy年MM月dd日 HH时mm分ss秒
     * @return 格式化后的日期时间字符串
     */
    public static String format(final LocalDateTime dateTime, final String pattern) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return dateTime == null ? "" : dateTime.format(formatter);
        } catch (UnsupportedTemporalTypeException excpt) {
            return "";
        }
    }
    
    /**
     * 将日期时间字符串按照格式模板进行格式化
     * @param dateTime 日期时间字符串，如：20180101、2018-01-01
     * @param pattern 格式模板，如：yyyy-MM-dd HH:mm:ss、yyyy年MM月dd日 HH时mm分ss秒
     * @return 格式化后的日期时间字符串
     */
    public static String format(final String dateTime, final String pattern) {
        return format(Convert.toLocalDateTime(dateTime), pattern);
    }
    
    /**
     * 将 {@link LocalDateTime} 格式化为短日期时间字符串
     * @param dateTime {@link LocalDateTime}
     * @return 短日期时间字符串，如：20180101080001
     */
    public static String formatShort(final LocalDateTime dateTime) {
        return dateTime == null ? "" : dateTime.format(DATE_TIME_SHORT_FORMATTER);
    }
    
    /**
     * 将日期时间字符串格式化为短日期时间
     * @param dateTime 日期时间字符串，如：2018-01-01 08:00:01
     * @return 短日期时间字符串，如：20180101080001
     */
    public static String formatShort(final String dateTime) {
        return formatShort(Convert.toLocalDateTime(dateTime));
    }
    
    /**
     * 将 {@link LocalDateTime} 格式化为长日期时间字符串
     * @param dateTime {@link LocalDateTime}
     * @return 长日期时间字符串，如：2018-01-01 08:00:01
     */
    public static String formatLong(final LocalDateTime dateTime) {
        return dateTime == null ? "" : dateTime.format(DATE_TIME_LONG_FORMATTER);
    }
    
    /**
     * 将日期时间字符串格式化为长日期时间
     * @param dateTime 日期时间字符串，如：20180101080001
     * @return 长日期时间字符串，如：2018-01-01 08:00:01
     */
    public static String formatLong(final String dateTime) {
        return formatLong(Convert.toLocalDateTime(dateTime));
    }
    
 
    /**
     * 比较 {@code dateTime} 与本地当前日期时间在日期时间上的大小
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code > 0：dateTime} 大于本地当前日期时间<br>
     *         {@code < 0：dateTime} 小于本地当前日期时间<br>
     *         {@code = 0：dateTime} 等于本地当前日期时间
     */
    public static long compareToNow(final String dateTime) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        if (localDateTime == null) {
            return 0;
        }
        final LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTime.compareTo(localDateTimeNow);
    }
    
    /**
     * 比较 {@code dateTimeA} 与 {@code dateTimeB} 在日期时间上的大小
     * @param dateTimeA 日期时间字符串A，如：20180101080001、2018-01-01 08:00:01
     * @param dateTimeB 日期时间字符串B，如：20180101080001、2018-01-01 08:00:01
     * @return {@code > 0：dateTimeA > dateTimeB}<br>
     *         {@code < 0：dateTimeA < dateTimeB}<br>
     *         {@code = 0：dateTimeA = dateTimeB}
     */
    public static long compare(final String dateTimeA, final String dateTimeB) {
        final LocalDateTime localDateTimeA = Convert.toLocalDateTime(dateTimeA);
        if (localDateTimeA == null) {
            return 0;
        }
        
        final LocalDateTime localDateTimeB = Convert.toLocalDateTime(dateTimeB);
        if (localDateTimeB == null) {
            return 0;
        }
        
        return localDateTimeA.compareTo(localDateTimeB);
    }
    
    
    /**
     * 比较 {@code dateTime} 是否早于本地当前日期时间
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 返回 {@code true} 表示 {@code dateTime} 早于本地当前日期时间
     */
    public static boolean isBeforeNow(final String dateTime) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        if (localDateTime == null) {
            return false;
        } 
        final LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTime.isBefore(localDateTimeNow);
    }
    
    /**
     * 比较 {@code dateTimeA} 是否早于 {@code dateTimeB}
     * @param dateTimeA 日期时间字符串A，如：20180101080001、2018-01-01 08:00:01
     * @param dateTimeB 日期时间字符串B，如：20180101080001、2018-01-01 08:00:01
     * @return 返回 {@code true} 表示 {@code dateTimeA} 早于 {@code dateTimeB}
     */
    public static boolean isBefore(final String dateTimeA, final String dateTimeB) {
        final LocalDateTime localDateTimeA = Convert.toLocalDateTime(dateTimeA);
        if (localDateTimeA == null) {
            return false;
        }
        
        final LocalDateTime localDateTimeB = Convert.toLocalDateTime(dateTimeB);
        if (localDateTimeB == null) {
            return false;
        }
        
        return localDateTimeA.isBefore(localDateTimeB);
    }
    
    
    /**
     *  比较 {@code dateTime} 是否晚于本地当前日期时间
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 返回 {@code true} 表示 {@code dateTime} 晚于本地当前日期时间
     */
    public static boolean isAfterNow(final String dateTime) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        if (localDateTime == null) {
            return false;
        } 
        final LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTime.isAfter(localDateTimeNow);
    } 
    
    /**
     * 比较 {@code dateTimeA} 是否晚于 {@code dateTimeB}
     * @param dateTimeA 日期时间字符串A，如：20180101080001、2018-01-01 08:00:01
     * @param dateTimeB 日期时间字符串B，如：20180101080001、2018-01-01 08:00:01
     * @return 返回 {@code true} 表示 {@code dateTimeA} 晚于 {@code dateTimeB}
     */
    public static boolean isAfter(final String dateTimeA, final String dateTimeB) {
        final LocalDateTime localDateTimeA = Convert.toLocalDateTime(dateTimeA);
        if (localDateTimeA == null) {
            return false;
        }
        
        final LocalDateTime localDateTimeB = Convert.toLocalDateTime(dateTimeB);
        if (localDateTimeB == null) {
            return false;
        }
        
        return localDateTimeA.isAfter(localDateTimeB);
    }
    
    
    /**
     * 给本地当前日期时间增加指定的秒数
     * @param seconds 要增加的秒数，为负数时表示减去指定秒数
     * @return 增加指定秒数后的日期时间字符串（短日期时间格式），如：20180101080001
     */
    public static String addSeconds(final long seconds) {
        final LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTimeNow.plusSeconds(seconds).format(DATE_TIME_SHORT_FORMATTER);
    }
    
    /**
     * 给日期时间字符串增加指定的秒数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param seconds 要增加的秒数，为负数时表示减去指定秒数
     * @return 增加指定秒数后的日期时间字符串，格式与参数 {@code dateTime} 相同
     */
    public static String addSeconds(final String dateTime, final long seconds) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        if (localDateTime == null) {
            return "";
        }
        
        if (dateTime.length() <= DATE_TIME_SHORT_SIZE) {
            return localDateTime.plusSeconds(seconds).format(DATE_TIME_SHORT_FORMATTER);
        }
        return localDateTime.plusSeconds(seconds).format(DATE_TIME_LONG_FORMATTER);
    }
    
    
    /**
     * 给本地当前日期时间增加指定的分钟数
     * @param minutes 要增加的分钟数，为负数时表示减去指定分钟数
     * @return 增加指定分钟数后的日期时间字符串（短日期时间格式），如：20180101080001
     */
    public static String addMinutes(final long minutes) {
        final LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTimeNow.plusMinutes(minutes).format(DATE_TIME_SHORT_FORMATTER);
    }
    
    /**
     * 给日期时间字符串增加指定的分钟数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param hours 要增加的分钟数，为负数时表示减去指定分钟数
     * @return 增加指定分钟数后的日期时间字符串，格式与参数 {@code dateTime} 相同
     */
    public static String addMinutes(final String dateTime, final long hours) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        if (localDateTime == null) {
            return "";
        }
        
        if (dateTime.length() <= DATE_TIME_SHORT_SIZE) {
            return localDateTime.plusMinutes(hours).format(DATE_TIME_SHORT_FORMATTER);
        }
        return localDateTime.plusMinutes(hours).format(DATE_TIME_LONG_FORMATTER);
    }
    
    
    /**
     * 给本地当前日期时间增加指定的小时数
     * @param hours 要增加的小时数，为负数时表示减去指定小时数
     * @return 增加指定小时数后的日期时间字符串（短日期时间格式），如：20180101080001
     */
    public static String addHours(final long hours) {
        final LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTimeNow.plusHours(hours).format(DATE_TIME_SHORT_FORMATTER);
    }
    
    /**
     * 给日期时间字符串增加指定的小时数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param hours 要增加的小时数，为负数时表示减去指定小时数
     * @return 增加指定小时数后的日期时间字符串，格式与参数 {@code dateTime} 相同
     */
    public static String addHours(final String dateTime, final long hours) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        if (localDateTime == null) {
            return "";
        }
        
        if (dateTime.length() <= DATE_TIME_SHORT_SIZE) {
            return localDateTime.plusHours(hours).format(DATE_TIME_SHORT_FORMATTER);
        }
        return localDateTime.plusHours(hours).format(DATE_TIME_LONG_FORMATTER);
    }
    
    
    /**
     * 给本地当前日期时间增加指定的天数
     * @param days 要增加的天数，为负数时表示减去指定天数
     * @return 增加指定天数后的日期时间字符串（短日期时间格式），如：20180101
     */
    public static String addDays(final int days) {
        final LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTimeNow.plusDays(days).format(DATE_TIME_SHORT_FORMATTER);
    }
    
    /**
     * 给日期时间字符串增加指定的天数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param days 要增加的天数，为负数时表示减去指定天数
     * @return 增加指定天数后的日期时间字符串，格式与参数 {@code dateTime} 相同
     */
    public static String addDays(final String dateTime, final int days) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        if (localDateTime == null) {
            return "";
        }
        
        if (dateTime.length() <= DATE_TIME_SHORT_SIZE) {
            return localDateTime.plusDays(days).format(DATE_TIME_SHORT_FORMATTER);
        }
        return localDateTime.plusDays(days).format(DATE_TIME_LONG_FORMATTER);
    }
    
    
    /**
     * 给本地当前日期时间增加指定的周数
     * @param weeks 要增加的周数，为负数时表示减去指定周数
     * @return 增加指定周数后的日期时间字符串（短日期时间格式），如：20180101080001
     */
    public static String addWeeks(final int weeks) {
        final LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTimeNow.plusWeeks(weeks).format(DATE_TIME_SHORT_FORMATTER);
    }
    
    /**
     * 给日期时间字符串增加指定的周数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param weeks 要增加的周数，为负数时表示减去指定周数
     * @return 增加指定周数后的日期时间字符串，格式与参数 {@code dateTime} 相同
     */
    public static String addWeeks(final String dateTime, final int weeks) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        if (localDateTime == null) {
            return "";
        }
        
        if (dateTime.length() <= DATE_TIME_SHORT_SIZE) {
            return localDateTime.plusWeeks(weeks).format(DATE_TIME_SHORT_FORMATTER);
        }
        return localDateTime.plusWeeks(weeks).format(DATE_TIME_LONG_FORMATTER);
    }
    
    
    /**
     * 给本地当前日期时间增加指定的月数
     * @param months 要增加的月数，为负数时表示减去指定月数
     * @return 增加指定月数后的日期时间字符串（短日期时间格式），如：20180101080001
     */
    public static String addMonths(final int months) {
        final LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTimeNow.plusMonths(months).format(DATE_TIME_SHORT_FORMATTER);
    }
    
    /**
     * 给日期时间字符串增加指定的月数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param months 要增加的月数，为负数时表示减去指定月数
     * @return 增加指定月数后的日期时间字符串，格式与参数 {@code dateTime} 相同
     */
    public static String addMonths(final String dateTime, final int months) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        if (localDateTime == null) {
            return "";
        }
        
        if (dateTime.length() <= DATE_TIME_SHORT_SIZE) {
            return localDateTime.plusMonths(months).format(DATE_TIME_SHORT_FORMATTER);
        }
        return localDateTime.plusMonths(months).format(DATE_TIME_LONG_FORMATTER);
    }

    
    /**
     * 给本地当前日期时间增加指定的年数
     * @param years 要增加的年数，为负数时表示减去指定年数
     * @return 增加指定年数后的日期时间字符串（短日期时间格式），如：20180101080001
     */
    public static String addYears(final int years) {
        final LocalDateTime localDateTimeNow = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTimeNow.plusYears(years).format(DATE_TIME_SHORT_FORMATTER);
    }
    
    /**
     * 给日期时间字符串增加指定的年数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param years 要增加的年数，为负数时表示减去指定年数
     * @return 增加指定年数后的日期时间字符串，格式与参数 {@code dateTime} 相同
     */
    public static String addYears(final String dateTime, final int years) {
        final LocalDateTime localDateTime = Convert.toLocalDateTime(dateTime);
        if (localDateTime == null) {
            return "";
        }
        
        if (dateTime.length() <= DATE_TIME_SHORT_SIZE) {
            return localDateTime.plusYears(years).format(DATE_TIME_SHORT_FORMATTER);
        }
        return localDateTime.plusYears(years).format(DATE_TIME_LONG_FORMATTER);
    }
    
    
    /**
     * 获取 {@code dateTime} 与本地当前日期时间相差的年数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 与本地当前日期时间相差的年数
     */
    public static long getYearSpan(final String dateTime) {
        return until(dateTime, ChronoUnit.YEARS);
    }
    
    /**
     * 获取两个日期时间相差的年数
     * @param startDateTime 开始日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param endDateTime 结束日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 两个日期时间相差的年数
     */
    public static long getYearSpan(final String startDateTime, final String endDateTime) {
        return until(startDateTime, endDateTime, ChronoUnit.YEARS);
    }
    
    
    /**
     * 获取 {@code dateTime} 与本地当前日期时间相差的月数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 与本地当前日期时间相差的月数
     */
    public static long getMonthSpan(final String dateTime) {
        return until(dateTime, ChronoUnit.MONTHS);
    }
    
    /**
     * 获取两个日期时间相差的月数
     * @param startDateTime 开始日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param endDateTime 结束日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 两个日期时间相差的月数
     */
    public static long getMonthSpan(final String startDateTime, final String endDateTime) {
        return until(startDateTime, endDateTime, ChronoUnit.MONTHS);
    }
    
    
    /**
     * 获取 {@code dateTime} 与本地当前日期时间相差的周数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 与本地当前日期时间相差的周数
     */
    public static long getWeekSpan(final String dateTime) {
        return until(dateTime, ChronoUnit.WEEKS);
    }
    
    /**
     * 获取两个日期时间相差的周数
     * @param startDateTime 开始日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param endDateTime 结束日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 两个日期时间相差的周数
     */
    public static long getWeekSpan(final String startDateTime, final String endDateTime) {
        return until(startDateTime, endDateTime, ChronoUnit.WEEKS);
    }
    
    
    /**
     * 获取 {@code dateTime} 与本地当前日期时间相差的天数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 与本地当前日期时间相差的天数
     */
    public static long getDaySpan(final String dateTime) {
        return until(dateTime, ChronoUnit.DAYS);
    }
    
    /**
     * 获取两个日期时间相差的天数
     * @param startDateTime 开始日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param endDateTime 结束日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 两个日期时间相差的天数
     */
    public static long getDaySpan(final String startDateTime, final String endDateTime) {
        return until(startDateTime, endDateTime, ChronoUnit.DAYS);
    }
    
    
    /**
     * 获取 {@code dateTime} 与本地当前日期时间相差的小时数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 与本地当前日期时间相差的小时数
     */
    public static long getHourSpan(final String dateTime) {
        return until(dateTime, ChronoUnit.HOURS);
    }
    
    /**
     * 获取两个日期时间相差的小时数
     * @param startDateTime 开始日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param endDateTime 结束日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 两个日期时间相差的小时数
     */
    public static long getHourSpan(final String startDateTime, final String endDateTime) {
        return until(startDateTime, endDateTime, ChronoUnit.HOURS);
    }
 
    
    /**
     * 获取 {@code dateTime} 与本地当前日期时间相差的分钟数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 与本地当前日期时间相差的分钟数
     */
    public static long getMinuteSpan(final String dateTime) {
        return until(dateTime, ChronoUnit.MINUTES);
    }
    
    /**
     * 获取两个日期时间相差的分钟数
     * @param startDateTime 开始日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param endDateTime 结束日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 两个日期时间相差的分钟数
     */
    public static long getMinuteSpan(final String startDateTime, final String endDateTime) {
        return until(startDateTime, endDateTime, ChronoUnit.MINUTES);
    }
 
    
    /**
     * 获取 {@code dateTime} 与本地当前日期时间相差的秒数
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return {@code dateTime} 与本地当前日期时间相差的秒数
     */
    public static long getSecondSpan(final String dateTime) {
        return until(dateTime, ChronoUnit.SECONDS);
    }
    
    /**
     * 获取两个日期时间相差的秒数
     * @param startDateTime 开始日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param endDateTime 结束日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 两个日期时间相差的秒数
     */
    public static long getSecondSpan(final String startDateTime, final String endDateTime) {
        return until(startDateTime, endDateTime, ChronoUnit.SECONDS);
    }
    
    
    /**
     * 获取日期时间字符串的日期部分
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 日期字符串，如：20180101
     */
    public static String getLocalDate(final String dateTime) {
        final LocalDate localDate = Convert.toLocalDate(dateTime);
        return localDate == null ? "" : localDate.format(WfDate.DATE_SHORT_FORMATTER);
    }
  
    /**
     * 获取日期时间字符串的时间部分
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @return 时间字符串，如：080001
     */
    public static String getLocalTime(final String dateTime) {
        final  LocalTime localTime = Convert.toLocalTime(dateTime);
        return localTime == null ? "" : localTime.format(WfTime.TIME_SHORT_FORMATTER);
    }
    
    
    /**
     * 计算 {@code dateTime} 与本地当前日期时间之差
     * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param unit 计时单位
     * @return {@code dateTime} 与本地当前日期时间之差
     */
    private static long until(final String dateTime, final ChronoUnit unit) {
        final LocalDateTime localDateTimeStart = Convert.toLocalDateTime(dateTime);
        if (localDateTimeStart == null) {
            return 0L;
        }
        final LocalDateTime localDateTimeEnd = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTimeStart.until(localDateTimeEnd, unit);
    }
    
    /**
     * 计算两个日期时间之差
     * @param startDateTime 开始日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param endDateTime 结束日期时间字符串，如：20180101080001、2018-01-01 08:00:01
     * @param unit 计时单位
     * @return 两个日期时间之差
     */
    private static long until(final String startDateTime, final String endDateTime, final ChronoUnit unit) {
        final LocalDateTime localDateTimeStart = Convert.toLocalDateTime(startDateTime);
        if (localDateTimeStart == null) {
            return 0L;
        }
        
        final LocalDateTime localDateTimeEnd = Convert.toLocalDateTime(endDateTime);
        if (localDateTimeEnd == null) {
            return 0L;
        }
        
        return localDateTimeStart.until(localDateTimeEnd, unit);
    }
    

    /**
     * 日期时间类型转换器
     *
     * @author 李晓勇 on 2018年4月27日 上午10:36:37
     * @version Version 2.0
     */
    public static class Convert {
        
        /**
         * 将日期时间字符串转为 {@link LocalDateTime}
         * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
         * @return {@link LocalDateTime}
         */
        public static LocalDateTime toLocalDateTime(final String dateTime) {
            if (dateTime == null || dateTime.length() < DATE_TIME_SHORT_SIZE) {
                return null;
            }
            
            try {
                if (dateTime.length() == DATE_TIME_SHORT_SIZE) {
                    return LocalDateTime.parse(dateTime, DATE_TIME_SHORT_FORMATTER);
                }
                return LocalDateTime.parse(dateTime, DATE_TIME_LONG_FORMATTER);
            } catch (DateTimeException excpt) {
                return null;
            }
        }
        
        /**
         * 将时间戳转为 {@link LocalDateTime}
         * @param timestamp 时间戳
         * @return {@link LocalDateTime}
         */
        public static LocalDateTime fromTimestamp(final long timestamp) {
            if (timestamp < 1000000000000L) {
                // UNIX时间戳需要乘以1000
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp * 1000), ZoneId.systemDefault());
            }
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        }
        
        /**
         * 将日期时间字符串转为 {@link LocalDate}
         * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
         * @return {@link LocalDate}
         */
        public static LocalDate toLocalDate(final String dateTime) {
            final LocalDateTime localDateTime = toLocalDateTime(dateTime);
            return localDateTime == null ? null : localDateTime.toLocalDate();
        }
        
        /**
         * 将日期时间字符串转为 {@link LocalTime}
         * @param dateTime 日期时间字符串，如：20180101080001、2018-01-01 08:00:01
         * @return {@link LocalTime}
         */
        public static LocalTime toLocalTime(final String dateTime) {
            final LocalDateTime localDateTime = toLocalDateTime(dateTime);
            return localDateTime == null ? null : localDateTime.toLocalTime();
        }
        
        
        /**
         * 将 {@link LocalDateTime} 转为日期时间字符串
         * @param dateTime {@link LocalDateTime}
         * @return 短日期时间字符串，如：20180101080001
         */
        public static String toString(final LocalDateTime dateTime) {
            return dateTime == null ? "" : formatShort(dateTime);
        }
        
    }
    
}