package net.virgodirk.wildfire.util;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * HTTP Utils
 *
 * @author 李晓勇 on 2017年9月7日 上午11:46:35
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfHttp {
    
    /**
     * 默认字符集名称
     */
    protected static final String DEFAULT_CHARSET = "UTF-8";
    
    
    /**
     * 从HTTP请求中获客户端真实IP地址
     * @param request HTTP请求对象
     * @return 客户端真实IP地址
     */
    public static String getRemoteAddr(final HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        
        String ipAddr = request.getHeader("x-forwarded-for");
        if (isEmptyStr(ipAddr)) {
            ipAddr = request.getHeader("Proxy-Client-IP");
        }
        if (isEmptyStr(ipAddr)) {
            ipAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isEmptyStr(ipAddr)) {
            ipAddr = request.getRemoteAddr();
        }
        return ipAddr == null || ipAddr.contains(":") ? getLocalAddr() : ipAddr;
    }
    
    /**
     * 获取本机IP地址
     * @return 本机IP地址
     */
    public static String getLocalAddr() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException excpt) {
            return "";
        }
    }
    
    /**
     * URL编码
     * <p>使用默认字符集：UTF-8</p>
     * @param text 待编码文本
     * @return URL编码结果，解码失败返回空字符串 {@code ""}
     */
    public static String urlEncode(final String text) {
        try {
            return isEmptyStr(text) ? "" : URLEncoder.encode(text, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException excpt) {
            return "";
        }
    }
    
    /**
     * URL编码
     * @param text 待编码文本
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return URL编码结果，解码失败返回空字符串 {@code ""}
     */
    public static String urlEncode(final String text, final String charset) {
        try {
            return isEmptyStr(text) ? "" : URLEncoder.encode(text, charset);
        } catch (UnsupportedEncodingException excpt) {
            return "";
        }
    }
    
    /**
     * URL解码
     * <p>使用默认字符集：UTF-8</p>
     * @param text 待解码文本
     * @return URL解码结果，解码失败返回空字符串 {@code ""}
     */
    public static String urlDecode(final String text) {
        try {
            return isEmptyStr(text) ? "" : URLDecoder.decode(text, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException excpt) {
            return "";
        }
    }
    
    /**
     * URL解码
     * @param text 待解码文本
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return URL解码结果，解码失败返回空字符串 {@code ""}
     */
    public static String urlDecode(final String text, final String charset) {
        try {
            return isEmptyStr(text) ? "" : URLDecoder.decode(text, charset);
        } catch (UnsupportedEncodingException excpt) {
            return "";
        }
    }
    
    
    /**
     * 获取 {@link WfHttpRequest.WfHttpRequestBuilder}
     * @return {@link WfHttpRequest.WfHttpRequestBuilder}
     */
    public static WfHttpRequest.WfHttpRequestBuilder request() {
        return WfHttpRequest.builder();
    }
    
    
    /**
     * 检查字符串是否是空字符串
     * <p>{@code unknown} 也视为空字符串</p>
     * @param str 待检查字符串
     * @return {@code true} 字符串为空
     *         {@code false} 字符串不为空
     */
    private static boolean isEmptyStr(final String str) {
        return str == null || str.trim().length() <= 0 || str.equalsIgnoreCase("unknown");
    }
}