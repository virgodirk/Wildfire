package net.virgodirk.wildfire.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 Utils
 *
 * @author 李晓勇 on 2017年8月24日 下午5:16:32
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfMd5 {
    
    /**
     * 默认字符集名称
     */
    private static final String DEFAULT_CHARSET = "UTF-8";


    /**
     * MD5加密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待加密内容
     * @return MD5加密结果（32位小写）
     */
    public static String encrypt32(final String src) {
        return encrypt(src, DEFAULT_CHARSET);
    }
    
    /**
     * MD5加密
     * @param src 待加密内容
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return MD5加密结果（32位小写）
     */
    public static String encrypt32(final String src, final String charset) {
        return encrypt(src, charset);
    }
    
    
    /**
     * MD5加密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待加密内容
     * @return MD5加密结果（16位小写）
     */
    public static String encrypt16(final String src) {
        final String md5Str = encrypt(src, DEFAULT_CHARSET);
        if (md5Str.length() != 32) {
            return "";
        }
        return encrypt(src, DEFAULT_CHARSET).substring(8, 24);
    }
    
    /**
     * MD5加密
     * @param src 待加密内容
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return MD5加密结果（16位小写）
     */
    public static String encrypt16(final String src, final String charset) {
        final String md5Str = encrypt(src, charset);
        if (32 != md5Str.length()) {
            return "";
        }
        return encrypt(src, charset).substring(8, 24);
    }
    
    
    /**
     * MD5加密
     * @param src 待加密内容
     * @return MD5加密结果（32位小写）
     */
    public static String encryptBytes(final byte[] src) {
        try {
            return encrypt(src);
        } catch (NoSuchAlgorithmException excpt) {
            excpt.printStackTrace();
            return "";
        }
    }

    
    /**
     * MD5加密
     * @param src 待加密内容
     * @param charset 字符集名称
     * @return MD5加密结果（32位小写）
     */
    private static String encrypt(final String src, final String charset) {
        if (src == null || src.length() <= 0) {
            return "";
        }

        try {
            return encrypt(src.getBytes(charset));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException excpt) {
            excpt.printStackTrace();
            return "";
        }
    }
    
    /**
     * MD5加密
     * @param src 待加密内容
     * @return MD5加密结果（32位小写）
     * @throws NoSuchAlgorithmException {@link NoSuchAlgorithmException} 异常
     */
    private static String encrypt(final byte[] src) throws NoSuchAlgorithmException {
        if (src == null || src.length <= 0) {
            return "";
        }
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(src);
        final byte[] output = messageDigest.digest();
        return WfConvert.bytes2HexStr(output);
    }
}