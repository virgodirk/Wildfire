package net.virgodirk.wildfire.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for WfDate
 *
 * @author 李晓勇 on 2018年4月28日 上午09:35:03
 * @version Version 3.0
 */
public class WfDateTest {

    private String dateError = "20181340";
    private String dateShort = WfDate.getNowShort();
    
    @Test
    public void testGetNow() {
        System.out.println("本地当前日期：" + WfDate.getNow());
        System.out.println("本地当前日期：" + WfDate.getNowLong());
        System.out.println("本地当前日期：" + WfDate.getNowShort());
    }
    
    @Test
    public void testGetYear() {
        Assert.assertEquals(0, WfDate.getYear(null));
        Assert.assertEquals(0, WfDate.getYear(""));
        Assert.assertEquals(0, WfDate.getYear(" "));
        Assert.assertEquals(0, WfDate.getYear(dateError));
        Assert.assertEquals(2018, WfDate.getYear("20180101"));
        Assert.assertEquals(2018, WfDate.getYear("2018-01-01"));
    }
    
    @Test
    public void testGetMonth() {
        Assert.assertEquals(0, WfDate.getMonth(null));
        Assert.assertEquals(0, WfDate.getMonth(""));
        Assert.assertEquals(0, WfDate.getMonth(" "));
        Assert.assertEquals(0, WfDate.getMonth(dateError));
        Assert.assertEquals(1, WfDate.getMonth("20180101"));
        Assert.assertEquals(1, WfDate.getMonth("2018-01-01"));
    }
    
    @Test
    public void testGetWeek() {
        Assert.assertEquals(0, WfDate.getWeek(null));
        Assert.assertEquals(0, WfDate.getWeek(""));
        Assert.assertEquals(0, WfDate.getWeek(" "));
        Assert.assertEquals(0, WfDate.getWeek(dateError));
        Assert.assertEquals(7, WfDate.getWeek("20180401"));
        Assert.assertEquals(1, WfDate.getWeek("2018-04-02"));
    }
    
    @Test
    public void testFormat() {
        String formatStr = "yyyy年MM月dd日";
        
        Assert.assertEquals("", WfDate.format("", formatStr));
        Assert.assertEquals("", WfDate.format(" ", formatStr));
        Assert.assertEquals("", WfDate.format("2018-13-40", formatStr));
        Assert.assertEquals("2018年01月01日", WfDate.format("20180101", formatStr));
        Assert.assertEquals("2018年01月01日", WfDate.format("2018-01-01", formatStr));
        
        Assert.assertEquals("", WfDate.formatShort(""));
        Assert.assertEquals("", WfDate.formatShort(" "));
        Assert.assertEquals("", WfDate.formatShort(dateError));
        Assert.assertEquals("20180101", WfDate.formatShort("20180101"));
        Assert.assertEquals("20180101", WfDate.formatShort("2018-01-01"));
        
        Assert.assertEquals("", WfDate.formatLong(""));
        Assert.assertEquals("", WfDate.formatLong(" "));
        Assert.assertEquals("", WfDate.formatLong(dateError));
        Assert.assertEquals("2018-01-01", WfDate.formatLong("20180101"));
        Assert.assertEquals("2018-01-01", WfDate.formatLong("2018-01-01"));
    }
    
    @Test
    public void testCompare() {
        Assert.assertEquals(WfDate.compareToNow(null), 0);
        Assert.assertEquals(WfDate.compareToNow(""), 0);
        Assert.assertEquals(WfDate.compareToNow(" "), 0);
        Assert.assertTrue(WfDate.compareToNow("20180101") < 0);
        Assert.assertTrue(WfDate.compareToNow("21001231") > 0);
        Assert.assertEquals(WfDate.compareToNow(WfDate.getNowShort()), 0);
        
        Assert.assertEquals(WfDate.compare(null, null), 0);
        Assert.assertEquals(WfDate.compare("", ""), 0);
        Assert.assertEquals(WfDate.compare(" ", " "), 0);
        Assert.assertTrue(WfDate.compare("20180101", "20180428") < 0);
        Assert.assertTrue(WfDate.compare("21001231", "20180428") > 0);
        Assert.assertEquals(WfDate.compare(dateShort, dateShort), 0);
        
        Assert.assertTrue(!WfDate.isBeforeNow(null));
        Assert.assertTrue(!WfDate.isBeforeNow(""));
        Assert.assertTrue(!WfDate.isBeforeNow(" "));
        Assert.assertTrue(WfDate.isBeforeNow("20180101"));
        Assert.assertTrue(!WfDate.isBeforeNow("21001231"));
        
        Assert.assertTrue(!WfDate.isBefore(null, null));
        Assert.assertTrue(!WfDate.isBefore("", ""));
        Assert.assertTrue(!WfDate.isBefore(" ", " "));
        Assert.assertTrue(WfDate.isBefore("20180101", "20180428"));
        Assert.assertTrue(!WfDate.isBefore("21001231", "20180428"));
        
        Assert.assertTrue(!WfDate.isAfterNow(null));
        Assert.assertTrue(!WfDate.isAfterNow(""));
        Assert.assertTrue(!WfDate.isAfterNow(" "));
        Assert.assertTrue(!WfDate.isAfterNow("20180101"));
        Assert.assertTrue(WfDate.isAfterNow("21001231"));
        
        Assert.assertTrue(!WfDate.isAfter(null, null));
        Assert.assertTrue(!WfDate.isAfter("", ""));
        Assert.assertTrue(!WfDate.isAfter(" ", " "));
        Assert.assertTrue(!WfDate.isAfter("20180101", "20180428"));
        Assert.assertTrue(WfDate.isAfter("21001231", "20180428"));
    }

    @Test
    public void testAdd() {
        Assert.assertEquals("", WfDate.addDays(null, 1));
        Assert.assertEquals("", WfDate.addDays("", 1));
        Assert.assertEquals("", WfDate.addDays(" ", 1));
        Assert.assertEquals("20180301", WfDate.addDays("20180230", 1));
        Assert.assertEquals("20180201", WfDate.addDays("20180131", 1));
        Assert.assertEquals("2018-02-01", WfDate.addDays("2018-01-31", 1));
        Assert.assertEquals("20190101", WfDate.addDays("20181231", 1));
        Assert.assertEquals("2019-01-01", WfDate.addDays("2018-12-31", 1));
        Assert.assertEquals("20160229", WfDate.addDays("20160301", -1));
        Assert.assertEquals("2016-02-29", WfDate.addDays("2016-03-01", -1));
        Assert.assertEquals("20180228", WfDate.addDays("20180301", -1));
        Assert.assertEquals("2018-02-28", WfDate.addDays("2018-03-01", -1));
        Assert.assertEquals("20180310", WfDate.addDays("20180320", -10));
        
        Assert.assertEquals("", WfDate.addWeeks(null, 1));
        Assert.assertEquals("", WfDate.addWeeks("", 1));
        Assert.assertEquals("", WfDate.addWeeks(" ", 1));
        Assert.assertEquals("", WfDate.addWeeks("20181340", 1));
        Assert.assertEquals("20180307", WfDate.addWeeks("20180230", 1));
        Assert.assertEquals("20180228", WfDate.addWeeks("20180307", -1));
        Assert.assertEquals("2016-02-29", WfDate.addWeeks("2016-03-07", -1));
        
        Assert.assertEquals("", WfDate.addMonths(null, 1));
        Assert.assertEquals("", WfDate.addMonths("", 1));
        Assert.assertEquals("", WfDate.addMonths(" ", 1));
        Assert.assertEquals("", WfDate.addMonths("20181340", 1));
        Assert.assertEquals("20180328", WfDate.addMonths("20180230", 1));
        Assert.assertEquals("20180101", WfDate.addMonths("20171201", 1));
        Assert.assertEquals("2017-12-01", WfDate.addMonths("2018-01-01", -1));
        
        Assert.assertEquals("", WfDate.addYears(null, 1));
        Assert.assertEquals("", WfDate.addYears("", 1));
        Assert.assertEquals("", WfDate.addYears(" ", 1));
        Assert.assertEquals("", WfDate.addYears("20181340", 1));
        Assert.assertEquals("20170228", WfDate.addYears("20160229", 1));
        Assert.assertEquals("20190101", WfDate.addYears("20180101", 1));
        Assert.assertEquals("2016-01-01", WfDate.addYears("2017-01-01", -1));
        
        System.out.println("明天是：" + WfDate.addDays(1));
        System.out.println("下周是：" + WfDate.addWeeks(1));
        System.out.println("下月是：" + WfDate.addMonths(1));
        System.out.println("明年是：" + WfDate.addYears(1));
    }
    
    @Test
    public void testGetDaySpan() {
        Assert.assertEquals(0, WfDate.getDaySpan(null));
        Assert.assertEquals(0, WfDate.getDaySpan(""));
        Assert.assertEquals(0, WfDate.getDaySpan(" "));
        Assert.assertEquals(0, WfDate.getDaySpan(dateError));
        
        Assert.assertEquals(0, WfDate.getDaySpan(null, null));
        Assert.assertEquals(0, WfDate.getDaySpan("", ""));
        Assert.assertEquals(0, WfDate.getDaySpan(" ", " "));
        Assert.assertEquals(5, WfDate.getDaySpan(WfDate.getNowShort(), WfDate.addDays(5)));
        Assert.assertEquals(-5, WfDate.getDaySpan(WfDate.getNowShort(), WfDate.addDays(-5)));
    }
    
    @Test
    public void testGetWeekSpan() {
        Assert.assertEquals(0, WfDate.getWeekSpan(null));
        Assert.assertEquals(0, WfDate.getWeekSpan(""));
        Assert.assertEquals(0, WfDate.getWeekSpan(" "));
        Assert.assertEquals(0, WfDate.getWeekSpan(dateError));
        
        Assert.assertEquals(0, WfDate.getWeekSpan(null, null));
        Assert.assertEquals(0, WfDate.getWeekSpan("", ""));
        Assert.assertEquals(0, WfDate.getWeekSpan(" ", " "));
        Assert.assertEquals(5, WfDate.getWeekSpan(WfDate.getNowShort(), WfDate.addWeeks(5)));
        Assert.assertEquals(-5, WfDate.getWeekSpan(WfDate.getNowShort(), WfDate.addWeeks(-5)));
    }
    
    @Test
    public void testGetMonthSpan() {
        Assert.assertEquals(0, WfDate.getMonthSpan(null));
        Assert.assertEquals(0, WfDate.getMonthSpan(""));
        Assert.assertEquals(0, WfDate.getMonthSpan(" "));
        Assert.assertEquals(0, WfDate.getMonthSpan(dateError));
        
        Assert.assertEquals(0, WfDate.getMonthSpan(null, null));
        Assert.assertEquals(0, WfDate.getMonthSpan("", ""));
        Assert.assertEquals(0, WfDate.getMonthSpan(" ", " "));
        Assert.assertEquals(5, WfDate.getMonthSpan(WfDate.getNowShort(), WfDate.addMonths(5)));
        Assert.assertEquals(-5, WfDate.getMonthSpan(WfDate.getNowShort(), WfDate.addMonths(-5)));
    }
    
    @Test
    public void testGetYearSpan() {
        Assert.assertEquals(0, WfDate.getYearSpan(null));
        Assert.assertEquals(0, WfDate.getYearSpan(""));
        Assert.assertEquals(0, WfDate.getYearSpan(" "));
        Assert.assertEquals(0, WfDate.getYearSpan(dateError));
        
        Assert.assertEquals(0, WfDate.getYearSpan(null, null));
        Assert.assertEquals(0, WfDate.getYearSpan("", ""));
        Assert.assertEquals(0, WfDate.getYearSpan(" ", " "));
        Assert.assertEquals(5, WfDate.getYearSpan(WfDate.getNowShort(), WfDate.addYears(5)));
        Assert.assertEquals(-5, WfDate.getYearSpan(WfDate.getNowShort(), WfDate.addYears(-5)));
    }

    @Test
    public void testGetLength() {
        Assert.assertEquals(0, WfDate.lengthOfYear(0));
        Assert.assertEquals(366, WfDate.lengthOfYear(2016));
        Assert.assertEquals(365, WfDate.lengthOfYear(2018));
        
        Assert.assertEquals(0, WfDate.lengthOfMonth(0, 0));
        Assert.assertEquals(0, WfDate.lengthOfMonth(0, 13));
        Assert.assertEquals(0, WfDate.lengthOfMonth(0, -1));
        Assert.assertEquals(29, WfDate.lengthOfMonth(2016, 2));
        Assert.assertEquals(28, WfDate.lengthOfMonth(2018, 2));
        Assert.assertEquals(31, WfDate.lengthOfMonth(2018, 1));
        Assert.assertEquals(30, WfDate.lengthOfMonth(2018, 4));
        
        System.out.println(WfDate.getYear() + "年有 " + WfDate.lengthOfYear() + " 天");
        System.out.println("今年" + WfDate.getMonth() + "月有 " + WfDate.lengthOfMonth() + " 天");
    }
    
    @Test
    public void testValidate() {
        Assert.assertTrue(WfDate.isLeapYear(0));
        Assert.assertTrue(WfDate.isLeapYear(2016));
        Assert.assertTrue(!WfDate.isLeapYear(2018));
        
        Assert.assertTrue(WfDate.isLunarMonth(4));
        Assert.assertTrue(!WfDate.isLunarMonth(3));
        Assert.assertTrue(!WfDate.isLunarMonth(0));
        
        Assert.assertTrue(WfDate.isYear(1984));
        Assert.assertTrue(!WfDate.isYear(998));
        
        Assert.assertTrue(!WfDate.isMonth(-1));
        Assert.assertTrue(!WfDate.isMonth(0));
        Assert.assertTrue(!WfDate.isMonth(13));
        Assert.assertTrue(WfDate.isMonth(12));
    }
    
    @Test
    public void testConverter() {
        Assert.assertNull(WfDate.Convert.toLocalDate(null));
        Assert.assertNull(WfDate.Convert.toLocalDate(""));
        Assert.assertNull(WfDate.Convert.toLocalDate(" "));
        Assert.assertNull(WfDate.Convert.toLocalDate("20181340"));
        Assert.assertEquals("2018-02-28", WfDate.Convert.toLocalDate("20180230").toString());
        Assert.assertEquals("2018-01-01", WfDate.Convert.toLocalDate("2018-01-01").toString());
        
        Assert.assertEquals("", WfDate.Convert.toString(null));
        Assert.assertEquals(WfDate.getNowShort(), WfDate.Convert.toString(LocalDate.now(ZoneId.systemDefault())));
    }
    
    @Test
    public void testGetPeriod() {
        Assert.assertEquals(Period.ZERO, WfDate.getPeriod(null));
        Assert.assertEquals(Period.ZERO, WfDate.getPeriod(""));
        Assert.assertEquals(Period.ZERO, WfDate.getPeriod(" "));
        Assert.assertEquals(Period.ZERO, WfDate.getPeriod("20181340"));
        
        Assert.assertEquals(Period.ZERO, WfDate.getPeriod(null, "20180428"));
        Assert.assertEquals(Period.ZERO, WfDate.getPeriod("", "20180428"));
        Assert.assertEquals(Period.ZERO, WfDate.getPeriod(" ", "20180428"));
        Assert.assertEquals(Period.ZERO, WfDate.getPeriod("20181340", "20180428"));
        Assert.assertEquals("P2Y1M30D", WfDate.getPeriod("20160229", "20180428").toString());
        Assert.assertEquals("P-2Y-1M-28D", WfDate.getPeriod("2018-04-28", "2016-02-29").toString());
        
        Period periodNow = WfDate.getPeriod("20160229", WfDate.getNowShort());
        System.out.print("2016年02月29日距今天已" + periodNow.getYears() + "年" + periodNow.getMonths() + "个月" + periodNow.getDays() + "天");
    }
    
    @Test
    public void getWorkDaysOfMonth() {
        Assert.assertEquals(23, WfDate.getWorkDaysOfMonth(2018, 5));
    }
}