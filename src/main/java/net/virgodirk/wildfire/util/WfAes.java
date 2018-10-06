package net.virgodirk.wildfire.util;

import net.virgodirk.wildfire.util.exception.WfAesException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES Utils 
 * 
 * <p>加密模式：CBC<br>
 * 填充规则：ZeroPadding<br>
 * 默认向量：WfMd5.encrypt16(key)，key为密钥</p>
 * 
 * @author 李晓勇 on 2017年8月24日 下午5:47:30
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfAes {

    /**
     * AES加密错误
     */
    private static final String AES_ENCRYPT_ERROR = "AES加密错误";

    /**
     * AES解密错误
     */
    private static final String AES_DECRYPT_ERROR = "AES解密错误";

    /**
     * 默认字符集名称
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 密钥长度（128bit）
     */
    private static final int AES_KEY_SIZE = 16;

    /**
     * 加密规则（方法/模式/填充规则）
     * <p>使用NoPadding是为了兼容C#/JS/PHP的ZeroPadding</p>
     */
    private static final String CIPHER_RULE = "AES/CBC/NoPadding";

    
    /**
     * AES加密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待加密内容
     * @param password 密码（任意长度）
     * @return AES加密结果（Base64格式）
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static String encryptAnyKey(final String src, final String password) throws WfAesException {
        final String key = WfMd5.encrypt16(password);
        return encryptEx(src, key.toLowerCase(Locale.ENGLISH), key.toUpperCase(Locale.ENGLISH));
    }

    /**
     * AES解密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待解密内容（Base64格式）
     * @param password 密码（任意长度）
     * @return AES解密结果
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static String decryptAnyKey(final String src, final String password) throws WfAesException {
        final String key = WfMd5.encrypt16(password);
        return decryptEx(src, key.toLowerCase(Locale.ENGLISH), key.toUpperCase(Locale.ENGLISH));
    }

    
    /**
     * AES加密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待加密内容
     * @param key 密钥（16位）
     * @return AES加密结果（Base64格式）
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static String encrypt(final String src, final String key) throws WfAesException {
        return encryptEx(src, key, null, DEFAULT_CHARSET);
    }

    /**
     * AES加密
     * @param src 待加密内容
     * @param key 密钥（16位）
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return AES加密结果（Base64格式）
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static String encrypt(final String src, final String key, final String charset) throws WfAesException {
        return encryptEx(src, key, null, DEFAULT_CHARSET);
    }
    
    /**
     * AES加密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待加密内容
     * @param key 密钥（16位）
     * @param keyIv 密钥向量（16位）
     * @return AES加密结果（Base64格式）
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static String encryptEx(final String src, final String key, final String keyIv) throws WfAesException {
        return encryptEx(src, key, keyIv, DEFAULT_CHARSET);
    }
    
    /**
     * AES加密
     * @param src 待加密内容
     * @param key 密钥（16位）
     * @param keyIv 密钥向量（16位）
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return AES加密结果（Base64格式）
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static String encryptEx(final String src, 
            final String key, final String keyIv, final String charset) throws WfAesException {
        if (src == null) {
            return "";
        }

        byte[] input;
        try {
            input = src.getBytes(charset);
        } catch (UnsupportedEncodingException excpt) {
            throw new WfAesException(AES_ENCRYPT_ERROR, excpt);
        }

        final byte[] result = encrypt(input, key, keyIv, charset);
        return result == null ? "" : WfBase64.encodeBytes(result);
    }

    
    /**
     * AES解密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待解密内容（Base64格式）
     * @param key 密钥（16位）
     * @return AES解密结果
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static String decrypt(final String src, final String key) throws WfAesException {
        return decryptEx(src, key, null, DEFAULT_CHARSET);
    }
    
    /**
     * AES解密
     * @param src 待解密内容（Base64格式）
     * @param key 密钥（16位）
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return AES解密结果
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static String decrypt(final String src, final String key, final String charset) throws WfAesException {
        return decryptEx(src, key, null, charset);
    }

    /**
     * AES解密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待解密内容（Base64格式）
     * @param key 密钥（16位）
     * @param keyIv 密钥向量（16位）
     * @return AES解密结果
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static String decryptEx(final String src, final String key, final String keyIv) throws WfAesException {
        return decryptEx(src, key, keyIv, DEFAULT_CHARSET);
    }
    
    /**
     * AES解密
     * @param src 待解密内容（Base64格式）
     * @param key 密钥（16位）
     * @param keyIv 密钥向量（16位）
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return AES解密结果
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static String decryptEx(final String src, 
            final String key, final String keyIv, final String charset) throws WfAesException {
        if (src == null) {
            return "";
        }

        final byte[] input = WfBase64.decodeToBytes(src);
        if (input == null) {
            throw new WfAesException("解密内容格式错误");
        }

        final byte[] result = decrypt(input, key, keyIv, charset);
        return WfConvert.bytes2Str(result, charset);
    }


    /**
     * AES加密
     * @param src 待加密数据
     * @param key 密钥（16位）
     * @param keyIv 密钥向量（16位）
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return AES加密结果
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static byte[] encrypt(final byte[] src,
            final String key, final String keyIv, final String charset) throws WfAesException {
        validateAesInput(src, key, keyIv);
        try {
            // 创建密钥
            final SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(charset), "AES");

            // 创建密钥向量
            String ivValue = keyIv;
            if (ivValue == null) {
                // 默认密钥向量
                ivValue = WfMd5.encrypt16(key);
            }
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(ivValue.getBytes());

            // 创建并初始化密码器
            final Cipher cipher = Cipher.getInstance(CIPHER_RULE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            // 模拟ZeroPadding
            final byte[] input = zeroPadding(src, cipher.getBlockSize());

            // 加密
            return cipher.doFinal(input);
        } catch (InvalidKeyException | UnsupportedEncodingException | NoSuchPaddingException 
                | NoSuchAlgorithmException | InvalidAlgorithmParameterException 
                | BadPaddingException | IllegalBlockSizeException  excpt) {
            throw new WfAesException(AES_ENCRYPT_ERROR, excpt);
        }
    }

    /**
     * AES解密
     * @param src 待解密数据
     * @param key 密钥（16位）
     * @param keyIv 密钥向量（16位）
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return AES解密结果
     * @throws WfAesException {@link WfAesException} 异常
     */
    public static byte[] decrypt(final byte[] src,
            final String key, final String keyIv, final String charset) throws WfAesException {
        validateAesInput(src, key, keyIv);
        try {
            // 创建密钥
            final SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(charset), "AES");

            // 创建密钥向量
            String ivValue = keyIv;
            if (WfString.isEmpty(ivValue)) {
                // 默认密钥向量
                ivValue = WfMd5.encrypt16(key);
            }
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(ivValue.getBytes(charset));

            // 创建并初始化密码器
            final Cipher cipher = Cipher.getInstance(CIPHER_RULE);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            // 解密
            return cipher.doFinal(src);
        } catch (InvalidKeyException | UnsupportedEncodingException | NoSuchPaddingException 
                | NoSuchAlgorithmException | InvalidAlgorithmParameterException 
                | BadPaddingException | IllegalBlockSizeException  excpt) {
            throw new WfAesException(AES_DECRYPT_ERROR, excpt);
        }
    }

    
    /**
     * AES加密/解密输入验证
     * @param src 待加密/解密内容
     * @param key 密钥（16位）
     * @param keyIv 密钥向量（16位）
     * @throws WfAesException {@link WfAesException} 异常
     */
    private static void validateAesInput(final byte[] src, final String key, final String keyIv) throws WfAesException {
        if (src == null) {
            throw new WfAesException("待加密/解密内容为空");
        }
        if (key == null) {
            throw new WfAesException("密钥为空");
        }
        if (key.length() != AES_KEY_SIZE) {
            throw new WfAesException("密钥长度错误");
        }
        if (keyIv != null && keyIv.trim().length() != AES_KEY_SIZE) {
            throw new WfAesException("密钥向量长度错误");
        }
    }
    
    /**
     * ZeroPadding
     * <p>为兼容JS、C#、PHP等，模拟ZeroPadding效果</p>
     * @param data 待加密数据
     * @param blockSize 加密数据块大小
     * @return ZeroPadding后的待加密数据
     */
    private static byte[] zeroPadding(final byte[] data, final int blockSize) {
        if (data == null) {
            return null;
        }

        int dataLen = data.length;
        final int missingNum = dataLen % blockSize;
        if (missingNum != 0) {
            dataLen += (blockSize - missingNum);
        }

        final byte[] result = new byte[dataLen];
        System.arraycopy(data, 0, result, 0, data.length);
        return result;
    }
}