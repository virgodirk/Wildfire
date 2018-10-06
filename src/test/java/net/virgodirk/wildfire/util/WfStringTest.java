package net.virgodirk.wildfire.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for WfString
 *
 * @author 李晓勇 on 2017年8月23日 上午10:53:07
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfStringTest {
    
    @Test
    public void testAllNumber() {
        assertFalse(WfString.allNumber(null));
        assertFalse(WfString.allNumber(""));
        assertFalse(WfString.allNumber(" "));
        assertFalse(WfString.allNumber("abc"));
        assertFalse(WfString.allNumber("123abc"));
        assertFalse(WfString.allNumber("123 "));
        assertFalse(WfString.allNumber("123.00"));
        assertTrue(WfString.allNumber("1"));
        assertTrue(WfString.allNumber("00001"));
        assertTrue(WfString.allNumber("1234567890"));
    }

    @Test
    public void testIsNumeric() {
        assertFalse(WfString.isNumeric(null));
        assertFalse(WfString.isNumeric(""));
        assertFalse(WfString.isNumeric(" "));
        assertFalse(WfString.isNumeric("123abc"));
        assertTrue(WfString.isNumeric("123"));
        assertTrue(WfString.isNumeric("+123"));
        assertTrue(WfString.isNumeric("-123"));
        assertTrue(WfString.isNumeric("+123.123"));
        assertTrue(WfString.isNumeric("123.123"));
        assertTrue(WfString.isNumeric("-123.123"));
    }
    
    @Test
    public void testIsPhoneNumber() {
        assertFalse(WfString.isPhoneNumber(null));
        assertFalse(WfString.isPhoneNumber(""));
        assertFalse(WfString.isPhoneNumber(" "));
        assertFalse(WfString.isPhoneNumber("1594741871a"));
        assertTrue(WfString.isPhoneNumber("4391327"));
        assertTrue(WfString.isPhoneNumber("15947418715"));
        assertTrue(WfString.isPhoneNumber("0471-4391327"));
        assertTrue(WfString.isPhoneNumber("0471-4391327"));
        assertTrue(WfString.isPhoneNumber("0471-43913270"));
        assertTrue(WfString.isPhoneNumber("0471-4391327-203"));
        assertTrue(WfString.isPhoneNumber("0471-4391327-0203"));
    }

    @Test
    public void testIsEmail() {
        assertFalse(WfString.isEmail(null));
        assertFalse(WfString.isEmail(""));
        assertFalse(WfString.isEmail(" "));
        assertFalse(WfString.isEmail("lisi@"));
        assertFalse(WfString.isEmail("@163.com"));
        assertTrue(WfString.isEmail("lisi@163.com"));
        assertTrue(WfString.isEmail("lisi_163@163.com"));
        assertTrue(WfString.isEmail("lisi-163@163.com"));
    }

    @Test
    public void testIsIpAddress() {
        assertFalse(WfString.isIpAddress(null));
        assertFalse(WfString.isIpAddress(""));
        assertFalse(WfString.isIpAddress(" "));
        assertFalse(WfString.isIpAddress("1234657890"));
        assertFalse(WfString.isIpAddress("abcABC"));
        assertFalse(WfString.isIpAddress("256.256.256.256"));
        assertFalse(WfString.isIpAddress("0.0.0.0.0"));
        assertFalse(WfString.isIpAddress("192.168.10.256"));
        assertFalse(WfString.isIpAddress("192.168.10"));
        assertFalse(WfString.isIpAddress("192.168.0"));
        assertFalse(WfString.isIpAddress("192.168.10.240.100"));
        assertTrue(WfString.isIpAddress("172.16.60.240"));
    }
    
    @Test
    public void testIsPostcode() {
        assertFalse(WfString.isPostcode(null));
        assertFalse(WfString.isPostcode(""));
        assertFalse(WfString.isPostcode(" "));
        assertFalse(WfString.isPostcode("1324567890"));
        assertFalse(WfString.isPostcode("123abc"));
        assertTrue(WfString.isPostcode("123456"));
        assertTrue(WfString.isPostcode("000000"));
        assertTrue(WfString.isPostcode("024428"));
    }
    
    @Test
    public void testIsChinese() {
        assertFalse(WfString.isChinese(null));
        assertFalse(WfString.isChinese(""));
        assertFalse(WfString.isChinese(" "));
        assertFalse(WfString.isChinese("1234567890"));
        assertFalse(WfString.isChinese("abcABC"));
        assertFalse(WfString.isChinese("123abcABC"));
        assertFalse(WfString.isChinese("!@#$%^&*()"));
        assertFalse(WfString.isChinese("Hello中文"));
        assertFalse(WfString.isChinese("ｈｅｌｌｏ中文"));
        assertTrue(WfString.isChinese("您好中文"));
    }

    @Test
    public void testContainChinese() {
        assertFalse(WfString.containChinese(null));
        assertFalse(WfString.containChinese(""));
        assertFalse(WfString.containChinese(" "));
        assertFalse(WfString.containChinese("1234567890"));
        assertFalse(WfString.containChinese("abcABC"));
        assertFalse(WfString.containChinese("123abcABC"));
        assertFalse(WfString.containChinese("!@#$%^&*()"));
        assertTrue(WfString.containChinese("Hello中文"));
        assertTrue(WfString.containChinese("Hello中文123abc"));
        assertTrue(WfString.containChinese("ｈｅｌｌｏ中文"));
        assertTrue(WfString.containChinese("您好中文"));
    }

    @Test
    public void testIsWideChar() {
        assertFalse(WfString.isWideChar(null));
        assertFalse(WfString.isWideChar(""));
        assertFalse(WfString.isWideChar(" "));
        assertFalse(WfString.isWideChar("1234567890"));
        assertFalse(WfString.isWideChar("abcABC"));
        assertFalse(WfString.isWideChar("123abcABC"));
        assertFalse(WfString.isWideChar("!@#$%^&*()"));
        assertFalse(WfString.isWideChar("Hello中文"));
        assertFalse(WfString.isWideChar("Hello中文123abc"));
        assertTrue(WfString.isWideChar("ｈｅｌｌｏ中文"));
        assertTrue(WfString.isWideChar("ｈｅｌｌｏ"));
        assertTrue(WfString.isWideChar("您好中文"));
    }
    
    @Test
    public void testContainWideChar() {
        assertFalse(WfString.containWideChar(null));
        assertFalse(WfString.containWideChar(""));
        assertFalse(WfString.containWideChar(" "));
        assertFalse(WfString.containWideChar("1234567890"));
        assertFalse(WfString.containWideChar("abcABC"));
        assertFalse(WfString.containWideChar("123abcABC"));
        assertFalse(WfString.containWideChar("!@#$%^&*()"));
        assertTrue(WfString.containWideChar("Hello中文"));
        assertTrue(WfString.containWideChar("Hello中文123abc"));
        assertTrue(WfString.containWideChar("ｈｅｌｌｏ中文"));
        assertTrue(WfString.containWideChar("ｈｅｌｌｏ"));
        assertTrue(WfString.containWideChar("您好中文"));
    }

    @Test
    public void testIsChineseName() {
        assertFalse(WfString.isChineseName(null));
        assertFalse(WfString.isChineseName(""));
        assertFalse(WfString.isChineseName(" "));
        assertFalse(WfString.isChineseName("1234567890"));
        assertFalse(WfString.isChineseName("abcABC"));
        assertFalse(WfString.isChineseName("123abcABC"));
        assertFalse(WfString.isChineseName("!@#$%%^&*()"));
        assertFalse(WfString.isChineseName("牛2"));
        assertFalse(WfString.isChineseName("阿·买买提·"));
        assertFalse(WfString.isChineseName("·买买提·古丽"));
        assertTrue(WfString.isChineseName("龙"));
        assertTrue(WfString.isChineseName("李雷"));
        assertTrue(WfString.isChineseName("韩梅梅"));
        assertTrue(WfString.isChineseName("阿·买买提·古丽"));
        assertTrue(WfString.isChineseName("阿·买买提·古丽·晋美"));
    }

    @Test
    public void testIsDateTime() {
        assertFalse(WfString.isDateTime(null));
        assertFalse(WfString.isDateTime(""));
        assertFalse(WfString.isDateTime(" "));
        assertFalse(WfString.isDateTime("1234567890"));
        assertFalse(WfString.isDateTime("123abcABC"));
        assertFalse(WfString.isDateTime("20171310"));
        assertFalse(WfString.isDateTime("20171232"));
        assertFalse(WfString.isDateTime("20170229"));
        assertFalse(WfString.isDateTime("20170431"));
        assertFalse(WfString.isDateTime("20170612245900"));
        assertFalse(WfString.isDateTime("20170612236000"));
        assertFalse(WfString.isDateTime("20170612235960"));
        assertTrue(WfString.isDateTime("20170612"));
        assertTrue(WfString.isDateTime("2017-06-12"));
        assertTrue(WfString.isDateTime("2017061216"));
        assertTrue(WfString.isDateTime("2017-06-12 16"));
        assertTrue(WfString.isDateTime("201706121602"));
        assertTrue(WfString.isDateTime("2017-06-12 16:02"));
        assertTrue(WfString.isDateTime("20170612160210"));
        assertTrue(WfString.isDateTime("2017-06-12 16:02:10"));
    }

    @Test
    public void testTrimStart() {
        assertNull(WfString.trimStart(null, ' '));
        assertEquals("", WfString.trimStart("", ' '));
        assertEquals("", WfString.trimStart("    ", ' '));
        assertEquals("", WfString.trimStart("0", '0'));
        assertEquals("007", WfString.trimStart("007", '2'));
        assertEquals("150000", WfString.trimStart("00150000", '0'));
        assertEquals("100150000", WfString.trimStart("0000100150000", '0'));
    }

    @Test
    public void testTrimEnd() {
        assertNull(WfString.trimEnd(null, ' '));
        assertEquals("", WfString.trimEnd("", ' '));
        assertEquals("", WfString.trimEnd("    ", ' '));
        assertEquals("", WfString.trimEnd("0", '0'));
        assertEquals("150000", WfString.trimEnd("150000", '2'));
        assertEquals("15", WfString.trimEnd("150000", '0'));
        assertEquals("0015", WfString.trimEnd("00150000", '0'));
        assertEquals("1501", WfString.trimEnd("150100", '0'));
        assertEquals("001501", WfString.trimEnd("00150100", '0'));
    }

    @Test
    public void testNextId() {
        String currentId = "10";
        int width = 4;
        assertEquals("0011", WfString.nextId(currentId, width));
    }
    
    @Test
    public void testLeftSubStr() {
        String str = "abc***ABC";
        assertEquals("abc", WfString.leftSubStr(str, "***"));
        assertEquals("abc", WfString.leftSubStr(str, "*"));
        assertEquals("abc", WfString.leftSubStr("abc ABC"));
        assertEquals("abcABC", WfString.leftSubStr("abcABC"));
        
        str = "abc ABC";
        assertEquals("abc", WfString.leftSubStr(str));
    }
    
    @Test
    public void testRightSubStr() {
        String str = "abc***ABC";
        assertEquals("ABC", WfString.rightSubStr(str, "***"));
        assertEquals("**ABC", WfString.rightSubStr(str, "*"));
        assertEquals("ABC", WfString.rightSubStr("abc ABC"));
        assertEquals("abcABC", WfString.rightSubStr("abcABC"));
        
        str = "abc ABC";
        assertEquals("ABC", WfString.rightSubStr(str));
    }

    @Test
    public void testsplitToList() {
        assertNull(WfString.splitToList(null, ","));
        assertNull(WfString.splitToList("", ","));
        assertEquals("abcd", WfString.splitToList("abcd", "$").get(0));
        assertEquals("a", WfString.splitToList("a,b,c,d", ",").get(0));
        assertEquals("c", WfString.splitToList("a b c d", " ").get(2));
    }

    @Test
    public void testCmpVersions() {
        assertTrue(WfString.cmpVersions("1.0.1","1.0.1") == 0);
        assertTrue(WfString.cmpVersions("1.0","1.0.0") == 0);

        assertTrue(WfString.cmpVersions("1.0.1","1.0.0") > 0);
        assertTrue(WfString.cmpVersions("1.1.0","1.0.9") > 0);
        assertTrue(WfString.cmpVersions("2.0","1.8.9") > 0);
        assertTrue(WfString.cmpVersions("1.0.2","1.0.1.9") > 0);

        assertTrue(WfString.cmpVersions("1.0.0","1.0.1") < 0);
        assertTrue(WfString.cmpVersions("1.0.9","1.1.0") < 0);
        assertTrue(WfString.cmpVersions("1.8.9","2.0") < 0);
        assertTrue(WfString.cmpVersions("1.0.1.9","1.0.2") < 0);
    }

    @Test
    public void testMaskPhoneNum() {
        assertNull(WfString.maskPhoneNum(null));
        assertEquals("", WfString.maskPhoneNum(""));
        assertEquals(" ", WfString.maskPhoneNum(" "));
        assertEquals("123456", WfString.maskPhoneNum("123456"));
        assertEquals("159****8715", WfString.maskPhoneNum("15947418715"));
    }
    
    @Test
    public void testMaskCardId() {
        assertNull(WfString.maskCardId(null));
        assertEquals("", WfString.maskCardId(""));
        assertEquals(" ", WfString.maskCardId(" "));
        assertEquals("123456", WfString.maskCardId("123456"));
        assertEquals("150*************24", WfString.maskCardId("150430199201013424"));
    }
    
    @Test
    public void testMaskName() {
        assertNull(WfString.maskName(null));
        assertEquals("", WfString.maskName(""));
        assertEquals(" ", WfString.maskName(" "));
        assertEquals("李", WfString.maskName("李"));
        assertEquals("李*", WfString.maskName("李四"));
        assertEquals("张*丰", WfString.maskName("张三丰"));
        assertEquals("欧**强", WfString.maskName("欧阳奋强"));
        assertEquals("爱***匡", WfString.maskName("爱新觉罗匡"));
    }

    @Test
    public void testGetUuid32() {
        System.out.println("UUID-32=" + WfString.getUuid32());
    }

    @Test
    public void testGetUuid36() {
        System.out.println("UUID-36=" + WfString.getUuid36());
    }

    @Test
    public void testGetRandomStr() {
        System.out.println("随机字符串：" + WfString.getRandomStr(6));
    }

    @Test
    public void testGetRandomNum() {
        System.out.println("随机数字：" + WfString.getRandomNum(6));
    }
}
