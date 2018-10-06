package net.virgodirk.wildfire.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA Utils
 *
 * @author 李晓勇 on 2017年8月24日 下午5:31:42
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfSha {

    /**
     * 默认字符集名称
     */
    private static final String DEFAULT_CHARSET = "UTF-8";


    /**
     * SHA1加密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待加密内容
     * @return 加密结果，40位小写
     */
    public static String sha1(final String src) {
        return sha(src, "SHA1", DEFAULT_CHARSET);
    }
    
    /**
     * SHA1加密
     * @param src 待加密内容
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return 加密结果，40位小写
     */
    public static String sha1(final String src, final String charset) {
        return sha(src, "SHA1", charset);
    }
    
    /**
     * SHA1加密
     * @param src 待加密内容
     * @return 加密结果，40位小写
     */
    public static String sha1Bytes(final byte[] src) {
        try {
            return sha(src, "SHA1");
        } catch (NoSuchAlgorithmException excpt) {
            excpt.printStackTrace();
            return "";
        }
    }

    
    /**
     * SHA256加密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待加密内容
     * @return 加密结果，64位小写
     */
    public static String sha256(final String src) {
        return sha(src, "SHA-256", DEFAULT_CHARSET);
    }
    
    /**
     * SHA256加密
     * @param src 待加密内容
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return 加密结果，64位小写
     */
    public static String sha256(final String src, final String charset) {
        return sha(src, "SHA-256", charset);
    }
    
    /**
     * SHA256加密
     * @param src 待加密内容
     * @return 加密结果，64位小写
     */
    public static String sha256Bytes(final byte[] src) {
        try {
            return sha(src, "SHA-256");
        } catch (NoSuchAlgorithmException excpt) {
            excpt.printStackTrace();
            return "";
        }
    }

    
    /**
     * SHA512加密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待加密内容
     * @return 加密结果，128位小写
     */
    public static String sha512(final String src) {
        return sha(src, "SHA-512", DEFAULT_CHARSET);
    }
    
    /**
     * SHA512加密
     * @param src 待加密内容
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return 加密结果，128位小写
     */
    public static String sha512(final String src, final String charset) {
        return sha(src, "SHA-512", charset);
    }
    
    /**
     * SHA512加密
     * @param src 待加密内容
     * @return 加密结果，128位小写
     */
    public static String sha512Bytes(final byte[] src) {
        try {
            return sha(src, "SHA-512");
        } catch (NoSuchAlgorithmException excpt) {
            excpt.printStackTrace();
            return "";
        }
    }


    /**
     * SHA加密
     * @param src 待加密文本
     * @param algorithm 加密算法
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return SHA加密结果
     */
    private static String sha(final String src, final String algorithm, final String charset) {
        if (src == null || src.length() == 0) {
            return "";
        }

        try {
            return sha(src.getBytes(charset), algorithm);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException excpt) {
            excpt.printStackTrace();
            return "";
        }
    }
    
    /**
     * SHA加密
     * @param src 待加密数据
     * @param algorithm 加密算法
     * @return SHA加密结果
     * @throws NoSuchAlgorithmException {@link NoSuchAlgorithmException} 异常
     */
    private static String sha(final byte[] src, final String algorithm) throws NoSuchAlgorithmException {
        if (src == null || src.length <= 0) {
            return "";
        }
        final MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(src);
        final byte[] output = messageDigest.digest();
        return WfConvert.bytes2HexStr(output);
    }
}