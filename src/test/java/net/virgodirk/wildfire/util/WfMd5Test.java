package net.virgodirk.wildfire.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for WfMd5
 *
 * @author 李晓勇 on 2017年8月24日 下午5:27:10
 * @version Version 3.0
 */
public class WfMd5Test {
    
    @Test
    public void testEncrypt32() {
        String src = "123-abc_测试+/";
        assertEquals("", WfMd5.encrypt32(null));
        assertEquals("", WfMd5.encrypt32(""));
        assertEquals("7215ee9c7d9dc229d2921a40e899ec5f", WfMd5.encrypt32(" "));
        assertEquals("ee0b3cfe36a31a8eaef5b191f3b4f072", WfMd5.encrypt32(src));
    }

    @Test
    public void testEncrypt16() {
        String text = "123-abc_测试+/";
        assertEquals("", WfMd5.encrypt16(null));
        assertEquals("", WfMd5.encrypt16(""));
        assertEquals("7d9dc229d2921a40", WfMd5.encrypt16(" "));
        assertEquals("36a31a8eaef5b191", WfMd5.encrypt16(text));
    }
    
    @Test
    public void testEncryptBytes() {
        String text = "123-abc_测试+/";
        byte[] bytes = text.getBytes();
        assertEquals("", WfMd5.encryptBytes(null));
        assertEquals("ee0b3cfe36a31a8eaef5b191f3b4f072", WfMd5.encryptBytes(bytes));
    }
}