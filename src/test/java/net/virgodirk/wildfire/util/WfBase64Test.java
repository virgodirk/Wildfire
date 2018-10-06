package net.virgodirk.wildfire.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for WfBase64
 *
 * @author 李晓勇 on 2017年8月24日 下午2:58:31
 * @version Version 3.0
 */
public class WfBase64Test {
    
    @Test
    public void testEncodeString() {
        assertEquals("", WfBase64.encode(null));
        assertEquals("", WfBase64.encode(""));
        assertEquals("IA==", WfBase64.encode(" "));
        assertEquals("MTIzYWJj5rWL6K+V", WfBase64.encode("123abc测试"));
    }
    
    @Test
    public void testEncodeBytes() throws Exception {
        String src = "123abc测试";
        byte[] srcBytes = src.getBytes("UTF-8");
        
        String dest = "MTIzYWJj5rWL6K+V";
        
        assertEquals(dest, WfBase64.encodeBytes(srcBytes));
        assertEquals("", WfBase64.encodeBytes(null));
        assertEquals("", WfBase64.encodeBytes("".getBytes("UTF-8")));
    }
    
    @Test
    public void testEncodeToBytes() throws Exception {
        String src = "123abc测试";
        byte[] srcBytes = src.getBytes("UTF-8");
        
        String dest = "MTIzYWJj5rWL6K+V";
        byte[] destBytes = dest.getBytes("UTF-8");
        
        assertArrayEquals(destBytes, WfBase64.encodeToBytes(srcBytes));
        assertNull(WfBase64.encodeToBytes(null));
        assertArrayEquals(new byte[] {}, WfBase64.encodeToBytes("".getBytes("UTF-8")));
    }

    
    @Test
    public void testDecodeString() {
        String src = "123abc测试";
        String dest = "MTIzYWJj5rWL6K+V";
        
        assertEquals("", WfBase64.decode(null));
        assertEquals("", WfBase64.decode(""));
        assertEquals(" ", WfBase64.decode("IA=="));
        assertEquals(src, WfBase64.decode(dest));
    }

    @Test
    public void testDecodeBytes() throws Exception {
        String src = "123abc测试";
        String dest = "MTIzYWJj5rWL6K+V";
        byte[] srcBytes = src.getBytes("UTF-8");
        byte[] destBytes = dest.getBytes("UTF-8");
        
        assertNull(WfBase64.decodeBytes(null));
        assertArrayEquals(new byte[] {}, WfBase64.decodeBytes("".getBytes("UTF-8")));
        assertArrayEquals(srcBytes, WfBase64.decodeBytes(destBytes));
    }

    @Test
    public void testDecodeToBytes() throws Exception {
        String src = "123abc测试";
        byte[] srcBytes = src.getBytes("UTF-8");
        
        String dest = "MTIzYWJj5rWL6K+V";
        
        assertArrayEquals(null, WfBase64.decodeToBytes(null));
        assertArrayEquals(new byte[] {}, WfBase64.decodeToBytes(""));
        assertArrayEquals(srcBytes, WfBase64.decodeToBytes(dest));
    }
    
    
    @Test
    public void testUrlEncodeString() {
        assertEquals("", WfBase64.urlEncode(null));
        assertEquals("", WfBase64.urlEncode(""));
        assertEquals("IA==", WfBase64.urlEncode(" "));
        assertEquals("MTIzYWJj5rWL6K-V", WfBase64.urlEncode("123abc测试"));
    }
    
    @Test
    public void testUrlEncodeBytes() throws Exception {
        String src = "123abc测试";
        byte[] srcBytes = src.getBytes("UTF-8");
        
        String dest = "MTIzYWJj5rWL6K-V";
        
        assertEquals(dest, WfBase64.urlEncodeBytes(srcBytes));
        assertEquals("", WfBase64.urlEncodeBytes(null));
        assertEquals("", WfBase64.urlEncodeBytes("".getBytes("UTF-8")));
    }
    
    @Test
    public void testUrlEncodeToBytes() throws Exception {
        String src = "123abc测试";
        byte[] srcBytes = src.getBytes("UTF-8");
        
        String dest = "MTIzYWJj5rWL6K-V";
        byte[] destBytes = dest.getBytes("UTF-8");
        
        assertArrayEquals(destBytes, WfBase64.urlEncodeToBytes(srcBytes));
        assertNull(WfBase64.urlEncodeToBytes(null));
        assertArrayEquals(new byte[] {}, WfBase64.urlEncodeToBytes("".getBytes("UTF-8")));
    }
    
    
    @Test
    public void testUrlDecodeString() {
        String src = "123abc测试";
        String dest = "MTIzYWJj5rWL6K-V";
        
        assertEquals("", WfBase64.urlDecode(null));
        assertEquals("", WfBase64.urlDecode(""));
        assertEquals(" ", WfBase64.urlDecode("IA=="));
        assertEquals(src, WfBase64.urlDecode(dest));
    }

    @Test
    public void testUrlDecodeBytes() throws Exception {
        String src = "123abc测试";
        String dest = "MTIzYWJj5rWL6K-V";
        byte[] srcBytes = src.getBytes("UTF-8");
        byte[] destBytes = dest.getBytes("UTF-8");
        
        assertNull(WfBase64.urlDecodeBytes(null));
        assertArrayEquals(new byte[] {}, WfBase64.urlDecodeBytes("".getBytes("UTF-8")));
        assertArrayEquals(srcBytes, WfBase64.urlDecodeBytes(destBytes));
    }

    @Test
    public void testUrlDecodeToBytes() throws Exception {
        String src = "123abc测试";
        byte[] srcBytes = src.getBytes("UTF-8");
        
        String dest = "MTIzYWJj5rWL6K-V";
        
        assertArrayEquals(null, WfBase64.urlDecodeToBytes(null));
        assertArrayEquals(new byte[] {}, WfBase64.urlDecodeToBytes(""));
        assertArrayEquals(srcBytes, WfBase64.urlDecodeToBytes(dest));
    }
}