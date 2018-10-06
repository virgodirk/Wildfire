package net.virgodirk.wildfire.util;

import net.virgodirk.wildfire.util.exception.WfDesException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES Utils
 *
 * <p>加密模式：ECB<br>
 * 填充规则：ZeroPadding</p>
 * 
 * @author 李晓勇 on 2017年8月25日 上午10:20:08
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfDes {
    
    /**
     * DES加密错误
     */
    private static final String DES_ENCRYPT_ERROR = "DES加密错误";
    
    /**
     * DES解密错误
     */
    private static final String DES_DECRYPT_ERROR = "DES解密错误";
    
    
    /**
     * 默认字符集名称
     */
    private static final String DEFAULT_CHARSET = "UTF-8";
    
    /**
     * 密钥长度（64bit）
     */
    private static final int DES_KEY_SIZE = 8;
    
    /**
     * 加密规则（方法/模式/填充规则）
     */
    private static final String CIPHER_RULE = "DES/ECB/NoPadding";
    
    
    /**
     * 设备编码加密
     * <p>仅用于加密/解密移动端设备编码</p>
     * @param deviceId 设备编码
     * @return 加密后的设备编码
     * @throws WfDesException {@link WfDesException} 异常
     */
    public static String encryptDeviceId(final String deviceId) throws WfDesException {
        return encrypt(deviceId, "h2d0t0y9", "GB2312") ;
    }
    
    
    /**
     * DES加密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待加密内容
     * @param key 密钥（8位）
     * @return DES加密结果（Base64格式）
     * @throws WfDesException {@link WfDesException} 异常
     */
    public static String encrypt(final String src, final String key) throws WfDesException {
        return encrypt(src, key, DEFAULT_CHARSET);
    }
    
    /**
     * DES加密
     * @param src 待加密内容
     * @param key 密钥（8位）
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return DES加密结果（Base64格式）
     * @throws WfDesException {@link WfDesException} 异常
     */
    public static String encrypt(final String src, final String key, final String charset) throws WfDesException {
        if (src == null) {
            return null;
        }
        try {
            final byte[] input = src.getBytes(charset);
            final byte[] output = encryptToBytes(input, key, charset);
            return WfBase64.encodeBytes(output);
        } catch (UnsupportedEncodingException excpt) {
            throw new WfDesException(DES_ENCRYPT_ERROR, excpt);
        }
    }
    
    
    /**
     * DES解密
     * <p>使用默认字符集：UTF-8</p>
     * @param src 待解密内容（Base64格式）
     * @param key 密钥（8位）
     * @return DES解密结果
     * @throws WfDesException {@link WfDesException} 异常
     */
    public static String decrypt(final String src, final String key) throws WfDesException {
        return decrypt(src, key, DEFAULT_CHARSET);
    }
    
    /**
     * DES解密
     * @param src 待解密内容（Base64格式）
     * @param key 密钥（8位）
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return DES解密结果
     * @throws WfDesException {@link WfDesException} 异常
     */
    public static String decrypt(final String src, final String key, final String charset) throws WfDesException {
        if (src == null) {
            return null;
        }
        final byte[] input = WfBase64.decodeToBytes(src);
        final byte[] output = decryptToBytes(input, key, charset);
        return WfConvert.bytes2Str(output, charset);
    }
    
    
    /**
     * DES加密
     * @param src 待加密内容
     * @param key 密钥（8位）
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return DES加密结果
     * @throws WfDesException {@link WfDesException} 异常
     */
    public static byte[] encryptToBytes(final byte[] src, final String key, final String charset) throws WfDesException {
        validateDesInput(src, key);
        try {
            // 构造密钥
            final DESKeySpec keySpec = new DESKeySpec(key.getBytes(charset));
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            final SecretKey secretKey = keyFactory.generateSecret(keySpec);

            // 创建密码器并初始化
            final Cipher cipher = Cipher.getInstance(CIPHER_RULE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // 模拟ZeroPadding
            final byte[] input = zeroPadding(src, cipher.getBlockSize());
            
            // 加密
            return cipher.doFinal(input);
        } catch (InvalidKeyException | UnsupportedEncodingException 
                | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException 
                | IllegalBlockSizeException | javax.crypto.BadPaddingException excpt) {
            throw new WfDesException(DES_ENCRYPT_ERROR, excpt);
        }
    }

    /**
     * DES解密
     * @param src 待解密内容
     * @param key 密钥（8位）
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return DES解密结果
     * @throws WfDesException {@link WfDesException} 异常
     */
    public static byte[] decryptToBytes(final byte[] src, final String key, final String charset) throws WfDesException {
        validateDesInput(src, key);
        try {
            // 构造密钥
            final DESKeySpec keySpec = new DESKeySpec(key.getBytes(charset));
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            final SecretKey secretKey = keyFactory.generateSecret(keySpec);

            // 创建解密器并初始化
            final Cipher cipher = Cipher.getInstance(CIPHER_RULE);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // 模拟ZeroPadding
            final byte[] input = zeroPadding(src, cipher.getBlockSize());
            
            // 解密
            return cipher.doFinal(input);
        } catch (InvalidKeyException | UnsupportedEncodingException 
                | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException 
                | IllegalBlockSizeException | javax.crypto.BadPaddingException excpt) {
            throw new WfDesException(DES_DECRYPT_ERROR, excpt);
        }
    }
    
    
    /**
     * DES加密/解密输入验证
     * @param src 待加密/解密内容
     * @param key 密钥（8位）
     * @throws WfDesException {@link WfDesException} 异常
     */
    private static void validateDesInput(final byte[] src, final String key) throws WfDesException {
        if (src == null) {
            throw new WfDesException("待加密/解密内容为空");
        }
        if (key == null) {
            throw new WfDesException("密钥为空");
        }
        if (key.length() != DES_KEY_SIZE) {
            throw new WfDesException("密钥长度错误");
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