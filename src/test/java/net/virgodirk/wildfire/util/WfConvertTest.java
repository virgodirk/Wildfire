package net.virgodirk.wildfire.util;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for WfConvert
 *
 * @author 李晓勇 on 2017年8月23日 上午10:56:48
 * @version Version 3.0
 */
public class WfConvertTest {
    
    @Test
    public void testStr2Int() {
        assertEquals(0, WfConvert.str2Int(null, 0));
        assertEquals(1, WfConvert.str2Int("", 1));
        assertEquals(2, WfConvert.str2Int(" ", 2));
        assertEquals(3, WfConvert.str2Int("123abc", 3));
        assertEquals(4, WfConvert.str2Int("123.5", 4));
        assertEquals(123, WfConvert.str2Int("123", 5));
    }

    @Test
    public void testStr2Long() {
        assertEquals(1, WfConvert.str2Long(null,1L));
        assertEquals(2, WfConvert.str2Long("",2L));
        assertEquals(3, WfConvert.str2Long(" ",3L));
        assertEquals(4, WfConvert.str2Long("123abc",4L));
        assertEquals(5, WfConvert.str2Long("123.5",5L));
        assertEquals(123, WfConvert.str2Long("123",0L));
    }

    @Test
    public void testStr2Float() {
        assertEquals(1.0F, WfConvert.str2Float(null, 1.0F), 0.0);
        assertEquals(2.0F, WfConvert.str2Float("", 2.0F), 0.0);
        assertEquals(3.0F, WfConvert.str2Float(" ", 3.0F), 0.0);
        assertEquals(4.0F, WfConvert.str2Float("123abc", 4.0F), 0.0);
        assertEquals(123.555F, WfConvert.str2Float("123.555", 5.0F), 0.0);
        assertEquals(123F, WfConvert.str2Float("123", 6.0F), 0.0);
    }

    @Test
    public void testStr2Double() {
        assertEquals(1.0, WfConvert.str2Double(null, 1.0), 0.0);
        assertEquals(2.0, WfConvert.str2Double("", 2.0), 0.0);
        assertEquals(3.0, WfConvert.str2Double(" ", 3.0), 0.0);
        assertEquals(4.0, WfConvert.str2Double("123abc", 4.0), 0.0);
        assertEquals(123.555, WfConvert.str2Double("123.555", 0), 0.0);
        assertEquals(123, WfConvert.str2Double("123", 0), 0.0);
    }
    
    @Test
    public void testBytes2Str() throws Exception {
        String str = "123abc测试";
        String charset = "UTF-8";
        byte[] bytes = str.getBytes(charset);
        assertEquals(str, WfConvert.bytes2Str(bytes));
        assertEquals(str, WfConvert.bytes2Str(bytes, charset));
    }

    @Test
    public void testBytes2HexStr() {
        byte[] bytes = {10, 20, 30, 40, 50};
        assertEquals("0a141e2832", WfConvert.bytes2HexStr(bytes));
    }

    @Test
    public void testHexStr2Bytes() {
        Assert.assertNull(WfConvert.hexStr2Bytes(null));
        Assert.assertNull(WfConvert.hexStr2Bytes(""));
        Assert.assertNull(WfConvert.hexStr2Bytes(" "));
        Assert.assertNull(WfConvert.hexStr2Bytes("        "));
        
        String hexStr = "0a141e2832";
        byte[] bytes = WfConvert.hexStr2Bytes(hexStr);
        Assert.assertArrayEquals(new byte[] {10, 20, 30, 40, 50}, bytes);
    }
   
    @Test
    public void testToPercent() {
        Assert.assertEquals("0.00%", WfConvert.toPercent(0, 2));
        Assert.assertEquals("25.68%", WfConvert.toPercent(0.25678, 2));
        Assert.assertEquals("25.6780%", WfConvert.toPercent(0.25678, 4));
    }
}