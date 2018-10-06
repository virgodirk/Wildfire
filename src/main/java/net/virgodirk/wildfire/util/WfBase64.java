package net.virgodirk.wildfire.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base64 Utils
 *
 * @author 李晓勇 on 2017年8月24日 下午2:05:28
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfBase64 {
    
    /**
     * 默认字符集名称
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    
    /**
     * Base64编码
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待编码内容
     * @return {@code String} 类型编码结果
     */
    public static String encode(final String src) {
        return encode(src, DEFAULT_CHARSET);
    }
    
    /**
     * Base64编码
     * @param src 待编码内容
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return {@code String} 类型编码结果
     */
    public static String encode(final String src, final String charset) {
        try {
            return src == null ? "" : Base64.getEncoder().encodeToString(src.getBytes(charset));
        } catch (UnsupportedEncodingException excpt) {
            excpt.printStackTrace();
            return "";
        }
    }

    /**
     * Base64编码
     * @param src 待编码内容
     * @return {@code String} 类型编码结果
     */
    public static String encodeBytes(final byte[] src) {
        return src == null ? "" : Base64.getEncoder().encodeToString(src);
    }
    
    /**
     * Base64编码
     * @param src 待编码内容
     * @return {@code byte[]} 类型编码结果
     */
    public static byte[] encodeToBytes(final byte[] src) {
        return src == null ? null : Base64.getEncoder().encode(src);
    }

    
    /**
     * Base64解码
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待解码内容
     * @return {@code String} 类型解码结果
     */
    public static String decode(final String src) {
        return decode(src, DEFAULT_CHARSET);
    }
    
    /**
     * Base64解码
     * @param src 待解码内容
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return {@code String} 类型解码结果
     */
    public static String decode(final String src, final String charset) {
        return src == null ? "" : WfConvert.bytes2Str(Base64.getDecoder().decode(src), charset);
    }

    /**
     * Base64解码
     * @param src 待解码内容
     * @return {@code byte[]} 类型解码结果
     */
    public static byte[] decodeToBytes(final String src) {
        return src == null ? null : Base64.getDecoder().decode(src);
    }

    /**
     * Base64解码
     * @param src 待解码内容
     * @return {@code byte[]} 类型解码结果
     */
    public static byte[] decodeBytes(final byte[] src) {
        return src == null ? null : Base64.getDecoder().decode(src);
    }
    
    
    /**
     * URL安全Base64编码
     * <p>{@code '/'} 替换为 {@code '_'}，{@code '+'} 替换为 {@code '-'}</p>
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待编码内容
     * @return {@code String} 类型编码结果
     */
    public static String urlEncode(final String src) {
        return urlEncode(src, DEFAULT_CHARSET);
    }

    /**
     * URL安全Base64编码
     * <p>{@code '/'} 替换为 {@code '_'}，{@code '+'} 替换为 {@code '-'}</p>
     * @param src 待编码内容
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return {@code String} 类型编码结果
     */
    public static String urlEncode(final String src, final String charset) {
        try {
            return src == null ? "" : Base64.getUrlEncoder().encodeToString(src.getBytes(charset));
        } catch (UnsupportedEncodingException excpt) {
            excpt.printStackTrace();
            return "";
        }
    }
    
    /**
     * URL安全Base64编码
     * <p>{@code '/'} 替换为 {@code '_'}，{@code '+'} 替换为 {@code '-'}</p>
     * @param src 待编码内容
     * @return {@code String} 类型编码结果
     */
    public static String urlEncodeBytes(final byte[] src) {
        return src == null ? "" : Base64.getUrlEncoder().encodeToString(src);
    }
    
    /**
     * URL安全Base64编码
     * <p>{@code '/'} 替换为 {@code '_'}，{@code '+'} 替换为 {@code '-'}</p>
     * @param src 待编码内容
     * @return {@code byte[]} 类型编码结果
     */
    public static byte[] urlEncodeToBytes(final byte[] src) {
        return src == null ? null : Base64.getUrlEncoder().encode(src);
    }

    
    /**
     * URL安全Bae64解码
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待解码内容
     * @return {@code String} 类型解码结果
     */
    public static String urlDecode(final String src) {
        return urlDecode(src, DEFAULT_CHARSET);
    }
    
    /**
     * URL安全Bae64解码
     * @param src 待解码内容
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return {@code String} 类型解码结果
     */
    public static String urlDecode(final String src, final String charset) {
        return src == null ? "" : WfConvert.bytes2Str(Base64.getUrlDecoder().decode(src), charset);
    }
    
    /**
     * URL安全Base64解码
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待解码内容
     * @return {@code byte[]} 类型解码结果
     */
    public static byte[] urlDecodeToBytes(final String src) {
        return src == null ? null : Base64.getUrlDecoder().decode(src);
    }
  
    /**
     * URL安全Base64解码
     * @param src 待解码内容
     * @return {@code byte[]} 类型解码结果
     */
    public static byte[] urlDecodeBytes(final byte[] src) {
        return src == null ? null : Base64.getUrlDecoder().decode(src);
    }
}