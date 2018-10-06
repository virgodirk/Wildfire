package net.virgodirk.wildfire.util;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * Time Utils
 *
 * @author 李晓勇 on 2018年5月2日 上午19:32:35
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfTime {
    
    /**
     * 短时间长度
     */
    protected static final int TIME_SHORT_SIZE = 6;
    
    /**
     * 短时间格式模板
     */
    protected static final String TIME_SHORT = "HHmmss";
    
    /**
     * 长时间格式模板
     */
    protected static final String TIME_LONG = "HH:mm:ss";
    
    
    /**
     * 短时间格式
     */
    public static final DateTimeFormatter TIME_SHORT_FORMATTER = DateTimeFormatter.ofPattern(TIME_SHORT);
    
    /**
     * 长时间格式
     */
    public static final DateTimeFormatter TIME_LONG_FORMATTER = DateTimeFormatter.ofPattern(TIME_LONG);
    
    
    /**
     * 获取本地当前时间
     * @return 本地当前时间
     */
    public static LocalTime getNow() {
        return LocalTime.now(ZoneId.systemDefault());
    }
    
    /**
     * 获取本地当前时间字符串（短时间格式）
     * @return 本地当前时间字符串（短时间格式），如：08:05:30
     */
    public static String getNowShort() {
        return LocalTime.now(ZoneId.systemDefault()).format(TIME_SHORT_FORMATTER);
    }
    
    /**
     * 获取本地当前时间字符串（长时间格式）
     * @return 本地当前时间字符串（长时间格式），如：080530
     */
    public static String getNowLong() {
        return LocalTime.now(ZoneId.systemDefault()).format(TIME_LONG_FORMATTER);
    }
    
    
    /**
     * 获取本地当前时间的小时数
     * @return 本地当前时间的小时数
     */
    public static int getHour() {
        return LocalTime.now(ZoneId.systemDefault()).getHour();
    }
    
    /**
     * 获取 {@code time} 的小时数
     * @param time 时间字符串，如：080530、08:05:30
     * @return {@code time} 的小时数
     */
    public static int getHour(final String time) {
        final LocalTime localTime = Convert.toLocalTime(time);
        return localTime == null ? 0 : localTime.getHour();
    }
    
    /**
     * 获取本地当前时间的分钟数
     * @return 本地当前时间的分钟数
     */
    public static int getMinute() {
        return LocalTime.now(ZoneId.systemDefault()).getMinute();
    }
    
    /**
     * 获取 {@code time} 的分钟数
     * @param time 时间字符串，如：080530、08:05:30
     * @return {@code time} 的分钟数
     */
    public static int getMinute(final String time) {
        final LocalTime localTime = Convert.toLocalTime(time);
        return localTime == null ? 0 : localTime.getMinute();
    }
    
    /**
     * 获取本地当前时间的秒数
     * @return 本地当前时间的秒数
     */
    public static int getSecond() {
        return LocalTime.now(ZoneId.systemDefault()).getSecond();
    }
    
    /**
     * 获取 {@code time} 的秒数
     * @param time 时间字符串，如：080530、08:05:30
     * @return {@code time} 的秒数
     */
    public static int getSecond(final String time) {
        final LocalTime localTime = Convert.toLocalTime(time);
        return localTime == null ? 0 : localTime.getSecond();
    }
    
    
    /**
     * 将 {@link LocalTime} 按照格式模板进行格式化
     * @param time {@link LocalTime}
     * @param pattern 格式模板，如：HH:mm:ss、HH时mm分ss秒
     * @return 格式化后的时间字符串
     */
    public static String format(final LocalTime time, final String pattern) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return time == null ? "" : time.format(formatter);
        } catch (UnsupportedTemporalTypeException excpt) {
            return "";
        }
    }
    
    /**
     * 将时间字符串按照格式模板进行格式化
     * @param time 时间字符串，如：080530、08:05:30
     * @param pattern 格式模板，如：HH:mm:ss、HH时mm分ss秒
     * @return 格式化后的时间字符串
     */
    public static String format(final String time, final String pattern) {
        return format(Convert.toLocalTime(time), pattern);
    }
    
    /**
     * 将 {@link LocalTime} 格式化为短时间字符串
     * @param time {@link LocalTime}
     * @return 短时间字符串，如：080530
     */
    public static String formatShort(final LocalTime time) {
        return time == null ? "" : time.format(TIME_SHORT_FORMATTER);
    }
    
    /**
     * 将时间字符串格式化为短时间
     * @param time 时间字符串，如：08:05:30
     * @return 短时间字符串，如：080530
     */
    public static String formatShort(final String time) {
        return formatShort(Convert.toLocalTime(time));
    }
    
    /**
     * 将 {@link LocalTime} 格式化为长时间字符串
     * @param time {@link LocalTime}
     * @return 长时间字符串，如：08:05:30
     */
    public static String formatLong(final LocalTime time) {
        return time == null ? "" : time.format(TIME_LONG_FORMATTER);
    }
    
    /**
     * 将时间字符串格式化为长时间
     * @param time 时间字符串，如：080530
     * @return 长时间字符串，如：080530
     */
    public static String formatLong(final String time) {
        return formatLong(Convert.toLocalTime(time));
    }
    

    /**
     * 比较 {@code time} 与本地当前时间在时间上的大小
     * @param time 时间字符串，如：080530、08:05:30
     * @return {@code > 0：time} 大于本地当前时间<br>
     *         {@code < 0：time} 小于本地当前时间<br>
     *         {@code = 0：time} 等于本地当前时间
     */
    public static long compareToNow(final String time) {
        final LocalTime localTime = Convert.toLocalTime(time);
        if (localTime == null) {
            return 0;
        }
        final LocalTime localTimeNow = LocalTime.now(ZoneId.systemDefault());
        return localTime.compareTo(localTimeNow);
    }
    
    /**
     * 比较 {@code timeA} 与 {@code timeB} 在时间上的大小
     * @param timeA 时间字符串A，如：080530、08:05:30
     * @param timeB 时间字符串B，如：080530、08:05:30
     * @return {@code > 0：timeA > timeB}<br>
     *         {@code < 0：timeA < timeB}<br>
     *         {@code = 0：timeA = timeB}
     */
    public static long compare(final String timeA, final String timeB) {
        final LocalTime localTimeA = Convert.toLocalTime(timeA);
        if (localTimeA == null) {
            return 0;
        }
        
        final LocalTime localTimeB = Convert.toLocalTime(timeB);
        if (localTimeB == null) {
            return 0;
        }
        
        return localTimeA.compareTo(localTimeB);
    }
    
    
    /**
     * 比较 {@code time} 是否早于本地当前时间
     * @param time 时间字符串，如：080530、08:05:30
     * @return 返回 {@code true} 表示 {@code time} 早于本地当前时间
     */
    public static boolean isBeforeNow(final String time) {
        final LocalTime localTime = Convert.toLocalTime(time);
        if (localTime == null) {
            return false;
        } 
        final LocalTime localTimeNow = LocalTime.now(ZoneId.systemDefault());
        return localTime.isBefore(localTimeNow);
    }
    
    /**
     * 比较 {@code timeA} 是否早于 {@code timeB}
     * @param timeA 日期时间字符串A，如：080530、08:05:30
     * @param timeB 日期时间字符串B，如：080530、08:05:30
     * @return 返回 {@code true} 表示 {@code timeA} 早于 {@code timeB}
     */
    public static boolean isBefore(final String timeA, final String timeB) {
        final LocalTime localTimeA = Convert.toLocalTime(timeA);
        if (localTimeA == null) {
            return false;
        }
        
        final LocalTime localTimeB = Convert.toLocalTime(timeB);
        if (localTimeB == null) {
            return false;
        }
        
        return localTimeA.isBefore(localTimeB);
    }
    
    
    /**
     *  比较 {@code time} 是否晚于本地当前时间
     * @param time 时间字符串，如：080530、08:05:30
     * @return 返回 {@code true} 表示 {@code time} 晚于本地当前时间
     */
    public static boolean isAfterNow(final String time) {
        final LocalTime localTime = Convert.toLocalTime(time);
        if (localTime == null) {
            return false;
        } 
        final LocalTime localTimeNow = LocalTime.now(ZoneId.systemDefault());
        return localTime.isAfter(localTimeNow);
    } 
    
    /**
     * 比较 {@code timeA} 是否晚于 {@code timeB}
     * @param timeA 日期时间字符串A，如：080530、08:05:30
     * @param timeB 日期时间字符串B，如：080530、08:05:30
     * @return 返回 {@code true} 表示 {@code timeA} 晚于 {@code timeB}
     */
    public static boolean isAfter(final String timeA, final String timeB) {
        final LocalTime localTimeA = Convert.toLocalTime(timeA);
        if (localTimeA == null) {
            return false;
        }
        
        final LocalTime localTimeB = Convert.toLocalTime(timeB);
        if (localTimeB == null) {
            return false;
        }
        
        return localTimeA.isAfter(localTimeB);
    }
    

    /**
     * 给本地当前时间增加指定的秒数
     * @param seconds 要增加的秒数，为负数时表示减去指定秒数
     * @return 增加指定秒数后的时间字符串（短时间格式），如：080530
     */
    public static String addSeconds(final long seconds) {
        final LocalTime localTimeNow = LocalTime.now(ZoneId.systemDefault());
        return localTimeNow.plusSeconds(seconds).format(TIME_SHORT_FORMATTER);
    }
    
    /**
     * 给时间字符串增加指定的秒数
     * @param time 时间字符串，如：080530、08:05:30
     * @param seconds 要增加的秒数，为负数时表示减去指定秒数
     * @return 增加指定秒数后的时间字符串，格式与参数 {@code time} 相同
     */
    public static String addSeconds(final String time, final long seconds) {
        final LocalTime localTime = Convert.toLocalTime(time);
        if (localTime == null) {
            return "";
        }
        
        if (time.length() <= TIME_SHORT_SIZE) {
            return localTime.plusSeconds(seconds).format(TIME_SHORT_FORMATTER);
        }
        return localTime.plusSeconds(seconds).format(TIME_LONG_FORMATTER);
    }
    
    
    /**
     * 给本地当前时间增加指定的分钟数
     * @param minutes 要增加的分钟数，为负数时表示减去指定分钟数
     * @return 增加指定分钟数后的时间字符串（短时间格式），如：080530
     */
    public static String addMinutes(final long minutes) {
        final LocalTime localTimeNow = LocalTime.now(ZoneId.systemDefault());
        return localTimeNow.plusMinutes(minutes).format(TIME_SHORT_FORMATTER);
    }
    
    /**
     * 给时间字符串增加指定的分钟数
     * @param time 时间字符串，如：080530、08:05:30
     * @param hours 要增加的分钟数，为负数时表示减去指定分钟数
     * @return 增加指定分钟数后的时间字符串，格式与参数 {@code time} 相同
     */
    public static String addMinutes(final String time, final long hours) {
        final LocalTime localTime = Convert.toLocalTime(time);
        if (localTime == null) {
            return "";
        }
        
        if (time.length() <= TIME_SHORT_SIZE) {
            return localTime.plusMinutes(hours).format(TIME_SHORT_FORMATTER);
        }
        return localTime.plusMinutes(hours).format(TIME_LONG_FORMATTER);
    }
    
    
    /**
     * 给本地当前时间增加指定的小时数
     * @param hours 要增加的小时数，为负数时表示减去指定小时数
     * @return 增加指定小时数后的时间字符串（短时间格式），如：080530
     */
    public static String addHours(final long hours) {
        final LocalTime localTimeNow = LocalTime.now(ZoneId.systemDefault());
        return localTimeNow.plusHours(hours).format(TIME_SHORT_FORMATTER);
    }
    
    /**
     * 给时间字符串增加指定的小时数
     * @param time 时间字符串，如：080530、08:05:30
     * @param hours 要增加的小时数，为负数时表示减去指定小时数
     * @return 增加指定小时数后的时间字符串，格式与参数 {@code time} 相同
     */
    public static String addHours(final String time, final long hours) {
        final LocalTime localTime = Convert.toLocalTime(time);
        if (localTime == null) {
            return "";
        }
        
        if (time.length() <= TIME_SHORT_SIZE) {
            return localTime.plusHours(hours).format(TIME_SHORT_FORMATTER);
        }
        return localTime.plusHours(hours).format(TIME_LONG_FORMATTER);
    }
    

    /**
     * 获取同一天内 {@code time} 与本地当前时间相差的小时数
     * @param time 时间字符串，如：080530、08:05:30
     * @return {@code time} 与本地当前时间相差的小时数
     */
    public static long getHourSpan(final String time) {
        return until(time, ChronoUnit.HOURS);
    }
    
    /**
     * 获取同一天内两个时间相差的小时数
     * @param startTime 开始时间字符串，如：080530、08:05:30
     * @param endTime 结束时间字符串，如：080530、08:05:30
     * @return 两个时间相差的小时数
     */
    public static long getHourSpan(final String startTime, final String endTime) {
        return until(startTime, endTime, ChronoUnit.HOURS);
    }
 
    
    /**
     * 获取同一天内 {@code time} 与本地当前时间相差的分钟数
     * @param time 时间字符串，如：080530、08:05:30
     * @return {@code time} 与本地当前时间相差的分钟数
     */
    public static long getMinuteSpan(final String time) {
        return until(time, ChronoUnit.MINUTES);
    }
    
    /**
     * 获取同一天内两个时间相差的分钟数
     * @param startTime 开始时间字符串，如：080530、08:05:30
     * @param endTime 结束时间字符串，如：080530、08:05:30
     * @return 两个时间相差的分钟数
     */
    public static long getMinuteSpan(final String startTime, final String endTime) {
        return until(startTime, endTime, ChronoUnit.MINUTES);
    }
 
    
    /**
     * 获取同一天内 {@code time} 与本地当前时间相差的秒数
     * @param time 时间字符串，如：080530、08:05:30
     * @return {@code time} 与本地当前时间相差的秒数
     */
    public static long getSecondSpan(final String time) {
        return until(time, ChronoUnit.SECONDS);
    }
    
    /**
     * 获取同一天内两个时间相差的秒数
     * @param startTime 开始时间字符串，如：080530、08:05:30
     * @param endTime 结束时间字符串，如：080530、08:05:30
     * @return 两个时间相差的秒数
     */
    public static long getSecondSpan(final String startTime, final String endTime) {
        return until(startTime, endTime, ChronoUnit.SECONDS);
    }
    

    /**
     * 计算同一天内 {@code time} 与本地当前时间之差
     * @param time 时间字符串，如：080530、08:05:30
     * @param unit 计时单位
     * @return {@code time} 与本地当前时间之差
     */
    private static long until(final String time, final ChronoUnit unit) {
        final LocalTime localTimeStart = Convert.toLocalTime(time);
        if (localTimeStart == null) {
            return 0L;
        }
        final LocalTime localTimeEnd = LocalTime.now(ZoneId.systemDefault());
        return localTimeStart.until(localTimeEnd, unit);
    }
    
    /**
     * 计算同一天内两个时间之差
     * @param startTime 开始时间字符串，如：080530、08:05:30
     * @param endTime 结束时间字符串，如：080530、08:05:30
     * @param unit 计时单位
     * @return 两个时间之差
     */
    private static long until(final String startTime, final String endTime, final ChronoUnit unit) {
        final LocalTime localTimeStart = Convert.toLocalTime(startTime);
        if (localTimeStart == null) {
            return 0L;
        }
        
        final LocalTime localTimeEnd = Convert.toLocalTime(endTime);
        if (localTimeEnd == null) {
            return 0L;
        }

        return localTimeStart.until(localTimeEnd, unit);
    }
    
    
    /**
     * 时间类型转换器
     *
     * @author 李晓勇 on 2018年5月3日 上午08:40:40
     * @version Version 2.0
     */
    public static class Convert {
        
        /**
         * 将时间字符串转为 {@link LocalTime}
         * @param time 时间字符串，如：080530、08:05:30
         * @return {@link LocalTime}
         */
        public static LocalTime toLocalTime(final String time) {
            if (time == null || time.length() < TIME_SHORT_SIZE) {
                return null;
            }
            
            try {
                if (time.length() == TIME_SHORT_SIZE) {
                    return LocalTime.parse(time, TIME_SHORT_FORMATTER);
                }
                return LocalTime.parse(time, TIME_LONG_FORMATTER);
            } catch (DateTimeException excpt) {
                return null;
            }
        }
        
        /**
         * 将 {@link LocalTime} 转为时间字符串
         * @param time {@link LocalTime}
         * @return 短时间字符串，如：080530
         */
        public static String toString(final LocalTime time) {
            return time == null ? "" : formatShort(time);
        }
  
    }
    
}