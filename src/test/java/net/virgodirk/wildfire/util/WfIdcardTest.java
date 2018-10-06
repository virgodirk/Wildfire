package net.virgodirk.wildfire.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for WfIdcard
 *
 * @author 李晓勇 on 2017年8月25日 下午2:29:44
 * @version Version 3.0
 */
public class WfIdcardTest {
    
    @Test
    public void testCheck() {
        Assert.assertTrue(WfIdcard.check("150102197407011020"));
        Assert.assertTrue(WfIdcard.check("150102196009170531"));
        Assert.assertTrue(WfIdcard.check("622426199010210022"));
        Assert.assertTrue(WfIdcard.check("622624199105050373"));
        Assert.assertTrue(WfIdcard.check("150102197001254014"));
        Assert.assertTrue(WfIdcard.check("650107196812200015"));
        Assert.assertTrue(WfIdcard.check("150102196401242026"));
        Assert.assertTrue(WfIdcard.check("350500197310115057"));
        Assert.assertTrue(WfIdcard.check("410101199211275928"));
        Assert.assertTrue(WfIdcard.check("150102195912151519"));
        Assert.assertTrue(WfIdcard.check("15010219580128401X"));
    }

    @Test
    public void testParser() {
        String cardId = "15010219580128401X";
        WfIdcard.ParseResult ret = WfIdcard.parser(cardId);
        Assert.assertEquals("150102", ret.getRegion());
        Assert.assertEquals("19580128", ret.getBirthDate());
        Assert.assertEquals("1", ret.getGender());
    }

    @Test
    public void testToCardId18() {
        Assert.assertEquals("15010219580128401X", WfIdcard.toCardId18("150102580128401"));
    }
    
    @Test
    public void testGetEndDate() {
        String startDate = "20180101";
        String nowDate = WfDate.getNowShort();
        // 15周岁生日
        String birthDate15 = WfDate.addYears(nowDate, -15);
        // 16周岁生日
        String birthDate16 = WfDate.addYears(nowDate, -16);
        // 25周岁生日
        String birthDate25 = WfDate.addYears(nowDate, -25);
        // 26周岁生日
        String birthDate26 = WfDate.addYears(nowDate, -26);
        // 45周岁生日
        String birthDate45 = WfDate.addYears(nowDate, -45);
        // 46周岁申日
        String birthDate46 = WfDate.addYears(nowDate, -46);
        
        // 46周岁及以上
        Assert.assertEquals("长期", WfIdcard.getEndDate(birthDate46, startDate));
        // 26-45周岁
        Assert.assertEquals("20380101", WfIdcard.getEndDate(birthDate26, startDate));
        Assert.assertEquals("20380101", WfIdcard.getEndDate(birthDate45, startDate));
        // 16-25周岁
        Assert.assertEquals("20280101", WfIdcard.getEndDate(birthDate25, startDate));
        Assert.assertEquals("20280101", WfIdcard.getEndDate(birthDate16, startDate));
        // 15周岁及以下
        Assert.assertEquals("20230101", WfIdcard.getEndDate(birthDate15, startDate));
        
        // 起始日期为闰年，结束日期也为闰年
        Assert.assertEquals("19280229", WfIdcard.getEndDate(birthDate26, "19080229"));
        Assert.assertEquals("19280229", WfIdcard.getEndDate(birthDate26, "19080229"));
        // 起始日期为闰年，结束日期为平年
        Assert.assertEquals("20210301", WfIdcard.getEndDate(birthDate15, "2016-02-29"));
        Assert.assertEquals("20210301", WfIdcard.getEndDate(birthDate15, "2016-02-29"));
    }
}