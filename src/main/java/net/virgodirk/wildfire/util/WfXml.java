package net.virgodirk.wildfire.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.virgodirk.wildfire.util.exception.WfFileException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * XML文件解析器
 *
 * <p>基于dom4j，SAX方式</p>
 * 
 * @author 李晓勇 on 2017年8月26日 上午11:21:19
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfXml {
    
    /**
     * XML文档对象
     */
    private transient Document doc;
    
    
    /**
     * 构造 {@link WfXml}
     * @param xmlFileName XML文件名（含路径）
     * @throws WfFileException {@link WfFileException} 异常
     */
    public WfXml(final String xmlFileName) throws WfFileException {
        final File file = new File(xmlFileName);
        if (!file.exists()) {
            throw new WfFileException("未找到文件：" + xmlFileName);
        }
        
        final SAXReader reader = new SAXReader();
        try {
            doc = reader.read(file);
        } catch (DocumentException excpt) {
            throw new WfFileException("解析XML文件：" + xmlFileName + "时发生错误", excpt);
        }
    }
    
    /**
     * 构造 {@link WfXml}
     * @param url XML文件URL
     * @throws WfFileException {@link WfFileException} 异常
     */
    public WfXml(final URL url) throws WfFileException {
        final SAXReader reader = new SAXReader();
        try {
            doc = reader.read(url);
        } catch (DocumentException excpt) {
            throw new WfFileException("解析XML文件：" + url + "时发生错误", excpt);
        }
    }
    
    /**
     * 选择多个XML节点
     * @param xpath XPath
     * @return XML节点集
     */
    @SuppressWarnings("unchecked")
    public List<XmlNode> selectNodes(final String xpath) {
        if (doc == null || xpath == null || xpath.trim().equals("")) {
            return null;
        }
        
        // 查询Node节点
        final List<Node> nodes = doc.selectNodes(xpath);
        if (nodes == null || nodes.isEmpty()) {
            return null;
        }
        
        // 将Node转换为XmlNode
        final List<XmlNode> xmlNodes = new ArrayList<>(nodes.size());
        for (final Node node : nodes) {
            xmlNodes.add(new XmlNode(node));
        }
        return xmlNodes;
    }
    
    /**
     * 选择单个XML节点
     * @param xpath XPath
     * @return 单个XML节点
     */
    public XmlNode selectSingleNode(final String xpath) {
        if (doc == null || xpath == null || xpath.trim().equals("")) {
            return null;
        }
        final Node node = doc.selectSingleNode(xpath);
        return node == null ? null : new XmlNode(node);
    }
    
    
    /**
     * XML节点
     * 
     * @author 李晓勇 on 2018年4月26日 下午19:31:06
     * @version Version 2.0
     */
    public class XmlNode {
        
        /**
         * {@link Node} 节点
         */
        private Node node;
        
        
        /**
         *  构造 {@link XmlNode}
         * @param node Node节点
         */
        public XmlNode(final Node node) {
            this.node = node;
        }
        
        /**
         * 获取节点文本
         * @return 节点文本
         */
        public String getText() {
            return node.getText();
        }
        
        /**
         * 获取节点属性值
         * @param attrName 属性名称
         * @return 属性值
         */
        public String getAttribute(final String attrName) {
            return  ((Element) node).attributeValue(attrName);
        }

        
        /**
         * 获取 {@link Node} 节点
         * @return {@link Node} 节点
         */
        public Node getNode() {
            return node;
        }
        
        /**
         * 设置 {@link Node} 节点
         * @param node {@link Node} 节点
         */
        public void setNode(final Node node) {
            this.node = node;
        }
        
    }
}