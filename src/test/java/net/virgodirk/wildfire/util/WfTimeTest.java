package net.virgodirk.wildfire.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for WfTime
 *
 * @author 李晓勇 on 2018年5月3日 上午08:40:40
 * @version Version 3.0
 */
public class WfTimeTest {
    
    private String timeError = "257080";
    private String timeShort = WfTime.getNowShort();
    private String timeLong = WfTime.getNowLong();
    
    @Test
    public void testGetNow() {
        System.out.println("本地当前时间：" + WfTime.getNow().toString());
        System.out.println("本地当前时间：" + WfTime.getNowShort());
        System.out.println("本地当前时间：" + WfTime.getNowLong());
    }
    
    @Test
    public void testGetHour() {
        Assert.assertEquals(0, WfTime.getHour(null));
        Assert.assertEquals(0, WfTime.getHour(""));
        Assert.assertEquals(0, WfTime.getHour(" "));
        Assert.assertEquals(0, WfTime.getHour(timeError));
        Assert.assertEquals(8, WfTime.getHour("080530"));
        Assert.assertEquals(8, WfTime.getHour("08:05:30"));
    }
    
    @Test
    public void testGetMunite() {
        Assert.assertEquals(0, WfTime.getMinute(null));
        Assert.assertEquals(0, WfTime.getMinute(""));
        Assert.assertEquals(0, WfTime.getMinute(" "));
        Assert.assertEquals(0, WfTime.getMinute(timeError));
        Assert.assertEquals(5, WfTime.getMinute("080530"));
        Assert.assertEquals(5, WfTime.getMinute("08:05:30"));
    }
    
    @Test
    public void testGetSecond() {
        Assert.assertEquals(0, WfTime.getSecond(null));
        Assert.assertEquals(0, WfTime.getSecond(""));
        Assert.assertEquals(0, WfTime.getSecond(" "));
        Assert.assertEquals(0, WfTime.getSecond(timeError));
        Assert.assertEquals(30, WfTime.getSecond("080530"));
        Assert.assertEquals(30, WfTime.getSecond("08:05:30"));
    }
    
    @Test
    public void testFormat() {
        String pattern = "HH时mm分ss秒";
        
        Assert.assertEquals("", WfTime.format("", pattern));
        Assert.assertEquals("", WfTime.format(" ", pattern));
        Assert.assertEquals("", WfTime.format(timeError, pattern));
        Assert.assertEquals("08时05分30秒", WfTime.format("080530", pattern));
        
        Assert.assertEquals("", WfTime.formatShort(""));
        Assert.assertEquals("", WfTime.formatShort(" "));
        Assert.assertEquals("", WfTime.formatShort(timeError));
        Assert.assertEquals("080530", WfTime.formatShort("080530"));
        Assert.assertEquals("080530", WfTime.formatShort("08:05:30"));
        
        Assert.assertEquals("", WfTime.formatLong(""));
        Assert.assertEquals("", WfTime.formatLong(" "));
        Assert.assertEquals("", WfTime.formatLong(timeError));
        Assert.assertEquals("08:05:30", WfTime.formatLong("080530"));
        Assert.assertEquals("08:05:30", WfTime.formatLong("08:05:30"));
    }
    
    @Test
    public void testCompare() {
        Assert.assertEquals(WfTime.compareToNow(null), 0);
        Assert.assertEquals(WfTime.compareToNow(""), 0);
        Assert.assertEquals(WfTime.compareToNow(" "), 0);
        Assert.assertTrue(WfTime.compareToNow(WfTime.addHours(timeShort, 1)) > 0);
        Assert.assertTrue(WfTime.compareToNow(WfTime.addHours(timeLong, -1)) < 0);
        
        Assert.assertEquals(WfTime.compare(null, null), 0);
        Assert.assertEquals(WfTime.compare("", ""), 0);
        Assert.assertEquals(WfTime.compare(" ", " "), 0);
        Assert.assertTrue(WfTime.compare(WfTime.addHours(timeShort, 1), timeShort) > 0);
        Assert.assertTrue(WfTime.compare(WfTime.addHours(timeLong, -1), timeLong) < 0);
        Assert.assertEquals(WfTime.compare(timeShort, timeShort), 0);
        
        Assert.assertTrue(!WfTime.isAfterNow(null));
        Assert.assertTrue(!WfTime.isAfterNow(""));
        Assert.assertTrue(!WfTime.isAfterNow(" "));
        Assert.assertTrue(!WfTime.isAfterNow(WfTime.addHours(timeShort, -1)));
        Assert.assertTrue(WfTime.isAfterNow(WfTime.addHours(timeShort, 1)));
        
        Assert.assertTrue(!WfTime.isAfter(null, null));
        Assert.assertTrue(!WfTime.isAfter("", ""));
        Assert.assertTrue(!WfTime.isAfter(" ", " "));
        Assert.assertTrue(!WfTime.isAfter(timeShort, timeLong));
        Assert.assertTrue(!WfTime.isAfter(timeShort, WfTime.addHours(timeShort, 1)));
        Assert.assertTrue(WfTime.isAfter(timeLong, WfTime.addHours(timeLong, -1)));
        
        Assert.assertTrue(!WfTime.isBeforeNow(null));
        Assert.assertTrue(!WfTime.isBeforeNow(""));
        Assert.assertTrue(!WfTime.isBeforeNow(" "));
        Assert.assertTrue(!WfTime.isBeforeNow(WfTime.addHours(timeShort, 1)));
        Assert.assertTrue(WfTime.isBeforeNow(WfTime.addHours(timeShort, -1)));
        
        Assert.assertTrue(!WfTime.isBefore(null, null));
        Assert.assertTrue(!WfTime.isBefore("", ""));
        Assert.assertTrue(!WfTime.isBefore(" ", " "));
        Assert.assertTrue(!WfTime.isBefore(timeShort, timeLong));
        Assert.assertTrue(!WfTime.isBefore(timeShort, WfTime.addHours(timeShort, -1)));
        Assert.assertTrue(WfTime.isBefore(timeLong, WfTime.addHours(timeLong, 1)));
    }
    
    @Test
    public void testAdd() {
        Assert.assertEquals("", WfTime.addHours(null, 1));
        Assert.assertEquals("", WfTime.addHours("", 1));
        Assert.assertEquals("", WfTime.addHours(" ", 1));
        Assert.assertEquals("", WfTime.addHours(timeError, 1));
        Assert.assertEquals("130530", WfTime.addHours("080530", 5));
        Assert.assertEquals("03:05:30", WfTime.addHours("08:05:30", -5));
        
        Assert.assertEquals("", WfTime.addMinutes(null, 1));
        Assert.assertEquals("", WfTime.addMinutes("", 1));
        Assert.assertEquals("", WfTime.addMinutes(" ", 1));
        Assert.assertEquals("", WfTime.addMinutes(timeError, 1));
        Assert.assertEquals("090330", WfTime.addMinutes("085830", 5));
        Assert.assertEquals("07:59:30", WfTime.addMinutes("08:04:30", -5));
        
        Assert.assertEquals("", WfTime.addSeconds(null, 1));
        Assert.assertEquals("", WfTime.addSeconds("", 1));
        Assert.assertEquals("", WfTime.addSeconds(" ", 1));
        Assert.assertEquals("", WfTime.addSeconds(timeError, 1));
        Assert.assertEquals("090003", WfTime.addSeconds("085958", 5));
        Assert.assertEquals("07:59:59", WfTime.addSeconds("08:00:04", -5));
    }
    
    @Test
    public void testGetHourSpan() {
        Assert.assertEquals(0, WfTime.getHourSpan(null));
        Assert.assertEquals(0, WfTime.getHourSpan(""));
        Assert.assertEquals(0, WfTime.getHourSpan(" "));
        Assert.assertEquals(0, WfTime.getHourSpan(timeError));
        
        Assert.assertEquals(0, WfTime.getHourSpan(null, null));
        Assert.assertEquals(0, WfTime.getHourSpan("", ""));
        Assert.assertEquals(0, WfTime.getHourSpan(" ", " "));
        Assert.assertEquals(0, WfTime.getHourSpan(timeError, timeError));
        Assert.assertEquals(1, WfTime.getHourSpan(timeShort, WfTime.addHours(timeShort, 1)));
        Assert.assertEquals(-1, WfTime.getHourSpan(timeLong, WfTime.addHours(timeLong, -1)));
    }
    
    @Test
    public void testGetMinuteSpan() {
        Assert.assertEquals(0, WfTime.getMinuteSpan(null));
        Assert.assertEquals(0, WfTime.getMinuteSpan(""));
        Assert.assertEquals(0, WfTime.getMinuteSpan(" "));
        Assert.assertEquals(0, WfTime.getMinuteSpan(timeError));
        
        Assert.assertEquals(0, WfTime.getMinuteSpan(null, null));
        Assert.assertEquals(0, WfTime.getMinuteSpan("", ""));
        Assert.assertEquals(0, WfTime.getMinuteSpan(" ", " "));
        Assert.assertEquals(0, WfTime.getMinuteSpan(timeError, timeError));
        Assert.assertEquals(5, WfTime.getMinuteSpan(timeShort, WfTime.addMinutes(timeShort, 5)));
        Assert.assertEquals(-5, WfTime.getMinuteSpan(timeLong, WfTime.addMinutes(timeLong, -5)));
    }
    
    @Test
    public void testGetSecondSpan() {
        Assert.assertEquals(0, WfTime.getSecondSpan(null));
        Assert.assertEquals(0, WfTime.getSecondSpan(""));
        Assert.assertEquals(0, WfTime.getSecondSpan(" "));
        Assert.assertEquals(0, WfTime.getSecondSpan(timeError));
        
        Assert.assertEquals(0, WfTime.getSecondSpan(null, null));
        Assert.assertEquals(0, WfTime.getSecondSpan("", ""));
        Assert.assertEquals(0, WfTime.getSecondSpan(" ", " "));
        Assert.assertEquals(0, WfTime.getSecondSpan(timeError, timeError));
        Assert.assertEquals(5, WfTime.getSecondSpan(timeShort, WfTime.addSeconds(timeShort, 5)));
        Assert.assertEquals(-5, WfTime.getSecondSpan(timeLong, WfTime.addSeconds(timeLong, -5)));
    }
    
    @Test
    public void testConverter() {
        Assert.assertNull(WfTime.Convert.toLocalTime(null));
        Assert.assertNull(WfTime.Convert.toLocalTime(""));
        Assert.assertNull(WfTime.Convert.toLocalTime(" "));
        Assert.assertNull(WfTime.Convert.toLocalTime(timeError));
        Assert.assertEquals("08:05:30", WfTime.Convert.toLocalTime("080530").toString());
        Assert.assertEquals("08:05:30", WfTime.Convert.toLocalTime("08:05:30").toString());
        
        Assert.assertEquals("", WfTime.Convert.toString(null));
        Assert.assertEquals(timeShort, WfTime.Convert.toString(WfTime.Convert.toLocalTime(timeShort)));
    }
    
}
