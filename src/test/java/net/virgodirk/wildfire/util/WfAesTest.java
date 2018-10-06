package net.virgodirk.wildfire.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for WfAes
 *
 * @author 李晓勇 on 2017年8月25日 上午10:02:49
 * @version Version 3.0
 */
public class WfAesTest {

    @Test
    public void testEncryptAnyKey() {
        String src = "^123-测试-abcABC_@#$";
        String pwd = "1324567890abcdefghij";
        System.out.println(WfAes.encryptAnyKey(src, pwd));
        assertEquals(src, WfAes.decryptAnyKey(WfAes.encryptAnyKey(src, pwd), pwd));
    }

    @Test
    public void testDecryptAnyKey() {
        String src = "^123-测试-abcABC_@#$";
        String pwd = "1324567890abcdefghij";
        assertEquals(src, WfAes.decryptAnyKey(WfAes.encryptAnyKey(src, pwd), pwd));
    }

    @Test
    public void testAes() {
        String src = "^123-测试-abcABC_@#$";
        String key = "1234abcdDCBA4321";
        assertEquals(src, WfAes.decrypt(WfAes.encrypt(src, key), key));
    }
    
    @Test
    public void testAesEx() {
        String src = "^123-测试-abcABC_@#$";
        String key = "1234abcdDCBA4321";
        String keyIv = "1234ABCDabcd4321";
        
        System.out.println(WfAes.encryptEx(src, key, keyIv));
        
        assertEquals(src, WfAes.decryptEx(WfAes.encryptEx(src, key, keyIv), key, keyIv));
    }
}