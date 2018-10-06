package net.virgodirk.wildfire.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for WfDateTime
 *
 * @author 李晓勇 on 2018年4月28日 上午09:35:03
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfDateTimeTest {

    private String dateTimeError = "20180101256060";
    private String dateTimeShort = WfDateTime.getNowShort();
    private String dateTimeLong = WfDateTime.getNowLong();
    
    @Test
    public void testGetNow() {
        System.out.println("本地当前日期时间：" + WfDateTime.getNow());
        System.out.println("本地当前日期时间：" + WfDateTime.getNowLong());
        System.out.println("本地当前日期时间：" + WfDateTime.getNowShort());
    }
    
    @Test
    public void testGetYear() {
        Assert.assertEquals(0, WfDateTime.getYear(null));
        Assert.assertEquals(0, WfDateTime.getYear(""));
        Assert.assertEquals(0, WfDateTime.getYear(" "));
        Assert.assertEquals(WfDate.getYear(), WfDateTime.getYear(dateTimeShort));
        Assert.assertEquals(WfDate.getYear(), WfDateTime.getYear(dateTimeLong));
    }
    
    @Test
    public void testGetMonth() {
        Assert.assertEquals(0, WfDateTime.getMonth(null));
        Assert.assertEquals(0, WfDateTime.getMonth(""));
        Assert.assertEquals(0, WfDateTime.getMonth(" "));
        Assert.assertEquals(WfDate.getMonth(), WfDateTime.getMonth(dateTimeShort));
        Assert.assertEquals(WfDate.getMonth(), WfDateTime.getMonth(dateTimeLong));
    }
    
    @Test
    public void testGetWeek() {
        Assert.assertEquals(0, WfDateTime.getWeek(null));
        Assert.assertEquals(0, WfDateTime.getWeek(""));
        Assert.assertEquals(0, WfDateTime.getWeek(" "));
        Assert.assertEquals(WfDate.getWeek(), WfDateTime.getWeek(dateTimeShort));
        Assert.assertEquals(WfDate.getWeek(), WfDateTime.getWeek(dateTimeLong));
    }
    
    @Test
    public void testGetHour() {
        Assert.assertEquals(0, WfDateTime.getHour(null));
        Assert.assertEquals(0, WfDateTime.getHour(""));
        Assert.assertEquals(0, WfDateTime.getHour(" "));
        Assert.assertEquals(LocalDateTime.now(ZoneId.systemDefault()).getHour(), WfDateTime.getHour(dateTimeShort));
        Assert.assertEquals(LocalDateTime.now(ZoneId.systemDefault()).getHour(), WfDateTime.getHour(dateTimeLong));
    }
    
    @Test
    public void testGetMinute() {
        Assert.assertEquals(0, WfDateTime.getHour(null));
        Assert.assertEquals(0, WfDateTime.getHour(""));
        Assert.assertEquals(0, WfDateTime.getHour(" "));
        Assert.assertEquals(LocalDateTime.now(ZoneId.systemDefault()).getMinute(), WfDateTime.getMinute(dateTimeShort));
        Assert.assertEquals(LocalDateTime.now(ZoneId.systemDefault()).getMinute(), WfDateTime.getMinute(dateTimeLong));
    }
    
    @Test
    public void testGetSecond() {
        Assert.assertEquals(0, WfDateTime.getSecond(null));
        Assert.assertEquals(0, WfDateTime.getSecond(""));
        Assert.assertEquals(0, WfDateTime.getSecond(" "));
        Assert.assertEquals(WfDateTime.Convert.toLocalTime(dateTimeShort).getSecond(), WfDateTime.getSecond(dateTimeShort));
        Assert.assertEquals(WfDateTime.Convert.toLocalTime(dateTimeShort).getSecond(), WfDateTime.getSecond(dateTimeLong));
    }
    
    @Test
    public void testFormat() {
        String formatStr = "yyyy年MM月dd日 HH时mm分ss秒";
        
        // 自定义格式化
        Assert.assertEquals("", WfDateTime.format("", formatStr));
        Assert.assertEquals("", WfDateTime.format(" ", formatStr));
        Assert.assertEquals("", WfDateTime.format(dateTimeError, formatStr));
        Assert.assertEquals("2018年01月01日 08时00分01秒", WfDateTime.format("20180101080001", formatStr));
        
        // 短日期格式化
        Assert.assertEquals("", WfDateTime.formatShort(""));
        Assert.assertEquals("", WfDateTime.formatShort(" "));
        Assert.assertEquals("", WfDateTime.formatShort(dateTimeError));
        Assert.assertEquals(dateTimeShort, WfDateTime.formatShort(dateTimeShort));
        Assert.assertEquals(dateTimeShort, WfDateTime.formatShort(dateTimeLong));
        
        // 长日期格式化
        Assert.assertEquals("", WfDateTime.formatLong(""));
        Assert.assertEquals("", WfDateTime.formatLong(" "));
        Assert.assertEquals("", WfDateTime.formatLong(dateTimeError));
        Assert.assertEquals(dateTimeLong, WfDateTime.formatLong(dateTimeLong));
        Assert.assertEquals(dateTimeLong, WfDateTime.formatLong(dateTimeShort));
    }
    
    @Test
    public void testCompare() {
        Assert.assertEquals(WfDateTime.compareToNow(null), 0);
        Assert.assertEquals(WfDateTime.compareToNow(""), 0);
        Assert.assertEquals(WfDateTime.compareToNow(" "), 0);
        Assert.assertEquals(WfDateTime.compareToNow(dateTimeError), 0);
        Assert.assertTrue(WfDateTime.compareToNow(WfDateTime.addHours(dateTimeShort, -1)) < 0);
        Assert.assertTrue(WfDateTime.compareToNow(WfDateTime.addHours(dateTimeShort, 1)) > 0);
        
        Assert.assertEquals(WfDateTime.compare(null, null), 0);
        Assert.assertEquals(WfDateTime.compare("", ""), 0);
        Assert.assertEquals(WfDateTime.compare(" ", " "), 0);
        Assert.assertEquals(WfDateTime.compare("dateTimeError", "dateTimeError"), 0);
        Assert.assertTrue(WfDateTime.compare(WfDateTime.addHours(dateTimeShort, -1), WfDateTime.addHours(dateTimeShort, 1)) < 0);
        Assert.assertTrue(WfDateTime.compare(WfDateTime.addHours(dateTimeShort, 1), WfDateTime.addHours(dateTimeShort, -1)) > 0);
        Assert.assertEquals(WfDateTime.compare(dateTimeLong, dateTimeLong), 0);
        
        Assert.assertTrue(!WfDateTime.isBeforeNow(null));
        Assert.assertTrue(!WfDateTime.isBeforeNow(""));
        Assert.assertTrue(!WfDateTime.isBeforeNow(" "));
        Assert.assertTrue(!WfDateTime.isBeforeNow("dateTimeError"));
        Assert.assertTrue(!WfDateTime.isBeforeNow(WfDateTime.addHours(dateTimeShort, 1)));
        Assert.assertTrue(WfDateTime.isBeforeNow(WfDateTime.addHours(dateTimeShort, -1)));
        
        Assert.assertTrue(!WfDateTime.isBefore(null, null));
        Assert.assertTrue(!WfDateTime.isBefore("", ""));
        Assert.assertTrue(!WfDateTime.isBefore(" ", " "));
        Assert.assertTrue(!WfDateTime.isBefore(dateTimeError, dateTimeShort));
        Assert.assertTrue(!WfDateTime.isBefore(dateTimeLong, dateTimeLong));
        Assert.assertTrue(!WfDateTime.isBefore(WfDateTime.addHours(dateTimeShort, 1), dateTimeShort));
        Assert.assertTrue(WfDateTime.isBefore(WfDateTime.addHours(dateTimeShort, -1), dateTimeShort));
        
        Assert.assertTrue(!WfDateTime.isAfterNow(null));
        Assert.assertTrue(!WfDateTime.isAfterNow(""));
        Assert.assertTrue(!WfDateTime.isAfterNow(" "));
        Assert.assertTrue(!WfDateTime.isAfterNow(dateTimeError));
        Assert.assertTrue(!WfDateTime.isAfterNow(WfDateTime.addHours(dateTimeShort, -1)));
        Assert.assertTrue(WfDateTime.isAfterNow(WfDateTime.addHours(dateTimeShort, 1)));
        
        Assert.assertTrue(!WfDateTime.isAfter(null, null));
        Assert.assertTrue(!WfDateTime.isAfter("", ""));
        Assert.assertTrue(!WfDateTime.isAfter(" ", " "));
        Assert.assertTrue(!WfDateTime.isAfter(dateTimeError, dateTimeShort));
        Assert.assertTrue(!WfDateTime.isAfter(dateTimeLong, dateTimeLong));
        Assert.assertTrue(!WfDateTime.isAfter(WfDateTime.addHours(dateTimeShort, -1), dateTimeShort));
        Assert.assertTrue(WfDateTime.isAfter(WfDateTime.addHours(dateTimeShort, 1), dateTimeShort));
    }

    @Test
    public void testAdd() {
        String dateTimeShort = WfDateTime.getNowShort();
        
        Assert.assertEquals("", WfDateTime.addYears(null, 1));
        Assert.assertEquals("", WfDateTime.addYears("", 1));
        Assert.assertEquals("", WfDateTime.addYears(" ", 1));
        Assert.assertEquals(WfDateTime.addYears(1), WfDateTime.addYears(dateTimeShort, 1));
        
        Assert.assertEquals("", WfDateTime.addMonths(null, 1));
        Assert.assertEquals("", WfDateTime.addMonths("", 1));
        Assert.assertEquals("", WfDateTime.addMonths(" ", 1));
        Assert.assertEquals(WfDateTime.addMonths(1), WfDateTime.addMonths(dateTimeShort, 1));
        
        Assert.assertEquals("", WfDateTime.addWeeks(null, 1));
        Assert.assertEquals("", WfDateTime.addWeeks("", 1));
        Assert.assertEquals("", WfDateTime.addWeeks(" ", 1));
        Assert.assertEquals(WfDateTime.addWeeks(1), WfDateTime.addWeeks(dateTimeShort, 1));
        
        Assert.assertEquals("", WfDateTime.addDays(null, 1));
        Assert.assertEquals("", WfDateTime.addDays("", 1));
        Assert.assertEquals("", WfDateTime.addDays(" ", 1));
        Assert.assertEquals(WfDateTime.addDays(1), WfDateTime.addDays(dateTimeShort, 1));
        
        Assert.assertEquals("", WfDateTime.addHours(null, 1));
        Assert.assertEquals("", WfDateTime.addHours("", 1));
        Assert.assertEquals("", WfDateTime.addHours(" ", 1));
        Assert.assertEquals(WfDateTime.addHours(1), WfDateTime.addHours(dateTimeShort, 1));
        
        Assert.assertEquals("", WfDateTime.addMinutes(null, 1));
        Assert.assertEquals("", WfDateTime.addMinutes("", 1));
        Assert.assertEquals("", WfDateTime.addMinutes(" ", 1));
        Assert.assertEquals(WfDateTime.addMinutes(1), WfDateTime.addMinutes(dateTimeShort, 1));
        
        Assert.assertEquals("", WfDateTime.addSeconds(null, 1));
        Assert.assertEquals("", WfDateTime.addSeconds("", 1));
        Assert.assertEquals("", WfDateTime.addSeconds(" ", 1));
        Assert.assertEquals(WfDateTime.addSeconds(1), WfDateTime.addSeconds(dateTimeShort, 1));
    }
    
    @Test
    public void testGetSecondSpan() {
        Assert.assertEquals(0, WfDateTime.getSecondSpan(null));
        Assert.assertEquals(0, WfDateTime.getSecondSpan(""));
        Assert.assertEquals(0, WfDateTime.getSecondSpan(" "));
        Assert.assertEquals(0, WfDateTime.getSecondSpan(dateTimeError));
        Assert.assertEquals(5, WfDateTime.getSecondSpan(WfDateTime.addSeconds(-5)));
        
        Assert.assertEquals(0, WfDateTime.getSecondSpan(null, null));
        Assert.assertEquals(0, WfDateTime.getSecondSpan("", ""));
        Assert.assertEquals(0, WfDateTime.getSecondSpan(" ", " "));
        Assert.assertEquals(0, WfDateTime.getSecondSpan(dateTimeError, dateTimeError));
        Assert.assertEquals(5, WfDateTime.getSecondSpan(dateTimeShort, WfDateTime.addSeconds(5)));
    }
    
    @Test
    public void testGetMinuteSpan() {
        Assert.assertEquals(0, WfDateTime.getMinuteSpan(null));
        Assert.assertEquals(0, WfDateTime.getMinuteSpan(""));
        Assert.assertEquals(0, WfDateTime.getMinuteSpan(" "));
        Assert.assertEquals(0, WfDateTime.getMinuteSpan(dateTimeError));
        Assert.assertEquals(5, WfDateTime.getMinuteSpan(WfDateTime.addMinutes(-5)));
        
        Assert.assertEquals(0, WfDateTime.getMinuteSpan(null, null));
        Assert.assertEquals(0, WfDateTime.getMinuteSpan("", ""));
        Assert.assertEquals(0, WfDateTime.getMinuteSpan(" ", " "));
        Assert.assertEquals(0, WfDateTime.getMinuteSpan(dateTimeError, dateTimeError));
        Assert.assertEquals(5, WfDateTime.getMinuteSpan(dateTimeShort, WfDateTime.addMinutes(5)));
    }
    
    @Test
    public void testGetHourSpan() {
        Assert.assertEquals(0, WfDateTime.getHourSpan(null));
        Assert.assertEquals(0, WfDateTime.getHourSpan(""));
        Assert.assertEquals(0, WfDateTime.getHourSpan(" "));
        Assert.assertEquals(0, WfDateTime.getHourSpan(dateTimeError));
        Assert.assertEquals(5, WfDateTime.getHourSpan(WfDateTime.addHours(-5)));
        
        Assert.assertEquals(0, WfDateTime.getHourSpan(null, null));
        Assert.assertEquals(0, WfDateTime.getHourSpan("", ""));
        Assert.assertEquals(0, WfDateTime.getHourSpan(" ", " "));
        Assert.assertEquals(0, WfDateTime.getHourSpan(dateTimeError, dateTimeError));
        Assert.assertEquals(5, WfDateTime.getHourSpan(dateTimeShort, WfDateTime.addHours(5)));
    }
    
    @Test
    public void testGetDaySpan() {
        Assert.assertEquals(0, WfDateTime.getDaySpan(null));
        Assert.assertEquals(0, WfDateTime.getDaySpan(""));
        Assert.assertEquals(0, WfDateTime.getDaySpan(" "));
        Assert.assertEquals(0, WfDateTime.getDaySpan(dateTimeError));
        Assert.assertEquals(5, WfDateTime.getDaySpan(WfDateTime.addDays(-5)));
        
        Assert.assertEquals(0, WfDateTime.getDaySpan(null, null));
        Assert.assertEquals(0, WfDateTime.getDaySpan("", ""));
        Assert.assertEquals(0, WfDateTime.getDaySpan(" ", " "));
        Assert.assertEquals(0, WfDateTime.getDaySpan(dateTimeError, dateTimeError));
        Assert.assertEquals(5, WfDateTime.getDaySpan(dateTimeShort, WfDateTime.addDays(5)));
    }
    
    @Test
    public void testGetWeekSpan() {
        Assert.assertEquals(0, WfDateTime.getWeekSpan(null));
        Assert.assertEquals(0, WfDateTime.getWeekSpan(""));
        Assert.assertEquals(0, WfDateTime.getWeekSpan(" "));
        Assert.assertEquals(0, WfDateTime.getWeekSpan(dateTimeError));
        Assert.assertEquals(5, WfDateTime.getWeekSpan(WfDateTime.addWeeks(-5)));
        
        Assert.assertEquals(0, WfDateTime.getWeekSpan(null, null));
        Assert.assertEquals(0, WfDateTime.getWeekSpan("", ""));
        Assert.assertEquals(0, WfDateTime.getWeekSpan(" ", " "));
        Assert.assertEquals(0, WfDateTime.getWeekSpan(dateTimeError, dateTimeError));
        Assert.assertEquals(5, WfDateTime.getWeekSpan(dateTimeShort, WfDateTime.addWeeks(5)));
    }
    
    @Test
    public void testGetMonthSpan() {
        Assert.assertEquals(0, WfDateTime.getMonthSpan(null));
        Assert.assertEquals(0, WfDateTime.getMonthSpan(""));
        Assert.assertEquals(0, WfDateTime.getMonthSpan(" "));
        Assert.assertEquals(0, WfDateTime.getMonthSpan(dateTimeError));
        Assert.assertEquals(5, WfDateTime.getMonthSpan(WfDateTime.addMonths(-5)));
        
        Assert.assertEquals(0, WfDateTime.getMonthSpan(null, null));
        Assert.assertEquals(0, WfDateTime.getMonthSpan("", ""));
        Assert.assertEquals(0, WfDateTime.getMonthSpan(" ", " "));
        Assert.assertEquals(0, WfDateTime.getMonthSpan(dateTimeError, dateTimeError));
        Assert.assertEquals(5, WfDateTime.getMonthSpan(dateTimeShort, WfDateTime.addMonths(5)));
    }
    
    @Test
    public void testGetYearSpan() {
        Assert.assertEquals(0, WfDateTime.getYearSpan(null));
        Assert.assertEquals(0, WfDateTime.getYearSpan(""));
        Assert.assertEquals(0, WfDateTime.getYearSpan(" "));
        Assert.assertEquals(0, WfDateTime.getYearSpan(dateTimeError));
        Assert.assertEquals(5, WfDateTime.getYearSpan(WfDateTime.addYears(-5)));
        
        Assert.assertEquals(0, WfDateTime.getYearSpan(null, null));
        Assert.assertEquals(0, WfDateTime.getYearSpan("", ""));
        Assert.assertEquals(0, WfDateTime.getYearSpan(" ", " "));
        Assert.assertEquals(0, WfDateTime.getYearSpan(dateTimeError, dateTimeError));
        Assert.assertEquals(5, WfDateTime.getYearSpan(dateTimeShort, WfDateTime.addYears(5)));
    }

    @Test
    public void testGetLocalDate() {
        Assert.assertEquals("", WfDateTime.getLocalDate(null));
        Assert.assertEquals("", WfDateTime.getLocalDate(""));
        Assert.assertEquals("", WfDateTime.getLocalDate(" "));
        Assert.assertEquals(WfDate.getNowShort(), WfDateTime.getLocalDate(WfDateTime.getNowShort()));
        Assert.assertEquals(WfDate.getNowShort(), WfDateTime.getLocalDate(WfDateTime.getNowLong()));
    }
    
    @Test
    public void testGetLocalTime() {
        Assert.assertEquals("", WfDateTime.getLocalTime(null));
        Assert.assertEquals("", WfDateTime.getLocalTime(""));
        Assert.assertEquals("", WfDateTime.getLocalTime(" "));
        Assert.assertEquals(dateTimeShort.substring(8, 14), WfDateTime.getLocalTime(dateTimeShort));
        Assert.assertEquals(dateTimeShort.substring(8, 14), WfDateTime.getLocalTime(dateTimeLong));
    }
   
    @Test
    public void testConverter() {
        Assert.assertNull(WfDateTime.Convert.toLocalDateTime(null));
        Assert.assertNull(WfDateTime.Convert.toLocalDateTime(""));
        Assert.assertNull(WfDateTime.Convert.toLocalDateTime(" "));
        Assert.assertNull(WfDateTime.Convert.toLocalDateTime(dateTimeError));
        Assert.assertEquals("2018-01-01T08:00:01", WfDateTime.Convert.toLocalDateTime("20180101080001").toString());
        Assert.assertEquals("2018-01-01T08:00:01", WfDateTime.Convert.toLocalDateTime("2018-01-01 08:00:01").toString());
        
        Assert.assertEquals("2018-05-03T11:28:05", WfDateTime.Convert.fromTimestamp(1525318085L).toString());
        Assert.assertEquals("2018-05-03T11:58:50.790", WfDateTime.Convert.fromTimestamp(1525319930790L).toString());
        
        Assert.assertNull(WfDateTime.Convert.toLocalTime(null));
        Assert.assertNull(WfDateTime.Convert.toLocalTime(""));
        Assert.assertNull(WfDateTime.Convert.toLocalTime(" "));
        Assert.assertNull(WfDateTime.Convert.toLocalTime(dateTimeError));
        Assert.assertEquals("08:00:01", WfDateTime.Convert.toLocalTime("20180101080001").toString());
        Assert.assertEquals("08:00:01", WfDateTime.Convert.toLocalTime("2018-01-01 08:00:01").toString());
        
        Assert.assertNull(WfDateTime.Convert.toLocalDate(null));
        Assert.assertNull(WfDateTime.Convert.toLocalDate(""));
        Assert.assertNull(WfDateTime.Convert.toLocalDate(" "));
        Assert.assertNull(WfDateTime.Convert.toLocalDate(dateTimeError));
        Assert.assertEquals("2018-01-01", WfDateTime.Convert.toLocalDate("20180101080001").toString());
        Assert.assertEquals("2018-01-01", WfDateTime.Convert.toLocalDate("2018-01-01 08:00:01").toString());
        
        Assert.assertEquals("", WfDateTime.Convert.toString(null));
        Assert.assertEquals(dateTimeShort, WfDateTime.Convert.toString(WfDateTime.Convert.toLocalDateTime(dateTimeShort)));
    }
}