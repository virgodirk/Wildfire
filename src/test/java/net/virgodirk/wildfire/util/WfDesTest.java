package net.virgodirk.wildfire.util;

import net.virgodirk.wildfire.util.exception.WfDesException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for WfDes
 *
 * @author 李晓勇 on 2017年8月25日 上午11:02:12
 * @version Version 3.0
 */
public class WfDesTest {
    
    @Test
    public void testEncryptDeviceId() throws WfDesException {
        String src = "5359763980";
        String ret = "6OFbefQrI9gN+ZAnLLTbVA==";
        
        Assert.assertEquals(ret, WfDes.encryptDeviceId(src));
        Assert.assertEquals(src, WfDes.decrypt(ret, "h2d0t0y9", "GB2312"));
    }
}