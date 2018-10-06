package net.virgodirk.wildfire.util;

import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for WfXml
 *
 * @author 李晓勇 on 2017年8月28日 上午9:23:07
 * @version Version 3.0
 */
public class WfXmlTest {

    @Test
    public void test() {
        URL url = this.getClass().getResource("/test.xml");
        WfXml xml = new WfXml(url);
        
        WfXml.XmlNode node = xml.selectSingleNode("items/item[@key='03']");
        Assert.assertEquals("03", node.getAttribute("key"));
        Assert.assertEquals("item-3", node.getAttribute("value"));
        Assert.assertEquals("Item No.3", node.getText());
        
        List<WfXml.XmlNode> nodes = xml.selectNodes("/items/item");
        node = nodes.get(nodes.size() - 1);
        Assert.assertEquals("05", node.getAttribute("key"));
        Assert.assertEquals("item-5", node.getAttribute("value"));
        Assert.assertEquals("Item No.5", node.getText());
    }
}