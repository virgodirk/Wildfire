package net.virgodirk.wildfire.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for WfSha
 *
 * @author 李晓勇 on 2017年8月24日 下午5:41:21
 * @version Version 3.0
 */
public class WfShaTest {

    @Test
    public void testSha1() {
        String text = "123-abc_测试+/";
        byte[] bytes = text.getBytes();
        
        assertEquals("", WfSha.sha1(null));
        assertEquals("", WfSha.sha1(""));
        assertEquals("b858cb282617fb0956d960215c8e84d1ccf909c6", WfSha.sha1(" "));
        assertEquals("d0bf332f05b541c163a5c70d654e7177f3a4dc16", WfSha.sha1(text));
        assertEquals("d0bf332f05b541c163a5c70d654e7177f3a4dc16", WfSha.sha1Bytes(bytes));
    }

    @Test
    public void testSha256() {
        String text = "123-abc_测试+/";
        byte[] bytes = text.getBytes();
        
        assertEquals("", WfSha.sha256(null));
        assertEquals("", WfSha.sha256(""));
        assertEquals("36a9e7f1c95b82ffb99743e0c5c4ce95d83c9a430aac59f84ef3cbfab6145068", WfSha.sha256(" "));
        assertEquals("77bf9db6f7ffd20dbedac1de6bb354e0c4305c77a92eee125371e55e15a281e4", WfSha.sha256(text));
        assertEquals("77bf9db6f7ffd20dbedac1de6bb354e0c4305c77a92eee125371e55e15a281e4", WfSha.sha256Bytes(bytes));
    }

    @Test
    public void testSha512() {
        String text = "123-abc_测试+/";
        byte[] bytes = text.getBytes();
        
        assertEquals("", WfSha.sha512(null));
        assertEquals("", WfSha.sha512(""));
        assertEquals("f90ddd77e400dfe6a3fcf479b00b1ee29e7015c5bb8cd70f5f15b4886cc339275ff553fc8a053f8ddc7324f45168cffaf81f8c3ac93996f6536eef38e5e40768", WfSha.sha512(" "));
        assertEquals("79c1f405b74e27889b70893a741257537027ace7d5bf4627de5dddb4b6af177b924900f5b7c2897d343e51098843dd392d6418cd6a883eb1d77799d7fcdf2f5b", WfSha.sha512(text));
        assertEquals("79c1f405b74e27889b70893a741257537027ace7d5bf4627de5dddb4b6af177b924900f5b7c2897d343e51098843dd392d6418cd6a883eb1d77799d7fcdf2f5b", WfSha.sha512Bytes(bytes));
    }
    
}
