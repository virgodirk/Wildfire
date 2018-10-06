package net.virgodirk.wildfire.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Convert
 *
 * @author 李晓勇 on 2017年8月22日 下午2:35:06
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfConvert {
    
    /**
     * {@code String} 转为 {@code int}
     * @param str 待转换字符串
     * @param defVal 默认值，若转换失败则返回该值
     * @return 参数转为 {@code int} 类型后的值
     */
    public static int str2Int(final String str, final int defVal) {
        try {
            return str == null ? defVal : Integer.parseInt(str);
        } catch (NumberFormatException excpt) {
            return defVal;
        }
    }
    
    /**
     * {@code String} 转为 {@code long}
     * @param str 待转换字符串
     * @param defVal 默认值，若转换失败则返回该值
     * @return 参数转为 {@code long} 类型后的值
     */
    public static long str2Long(final String str, final long defVal) {
        try {
            return str == null ? defVal : Long.parseLong(str);
        } catch (NumberFormatException excpt) {
            return defVal;
        }
    }

    /**
     * {@code String} 转为 {@code float}
     * @param str 待转换字符串
     * @param defVal 默认值，若转换失败则返回该值
     * @return 参数转为 {@code float} 类型后的值
     */
    public static float str2Float(final String str, final float defVal) {
        try {
            return str == null ? defVal : Float.parseFloat(str);
        } catch (NumberFormatException excpt) {
            return defVal;
        }
    }

    /**
     * {@code String} 转为 {@code double}
     * @param str 待转换字符串
     * @param defVal 默认值，若转换失败则返回该值
     * @return 参数转为 {@code double} 类型后的值
     */
    public static double str2Double(final String str, final double defVal) {
        try {
            return str == null ? defVal : Double.parseDouble(str);
        } catch (NumberFormatException excpt) {
            return defVal;
        }
    }
    
    
    /**
     * {@code byte[]} 转为 {@code String}
     * @param bytes byte数组
     * @param charset 字符集，如：UTF-8、GB2312等
     * @return 参数转为 {@code String} 类型后的值，转换失败返回 {@code ""}
     */
    public static String bytes2Str(final byte[] bytes, final String charset) {
        if (bytes == null || bytes.length <= 0) {
            return "";
        }

        try {
            return new String(bytes, charset).replace("\0", "");
        } catch (UnsupportedEncodingException excpt) {
            excpt.printStackTrace();
            return "";
        }
    }
    
    /**
     * {@code byte[]} 转为 {@code String}
     * 
     * <p>默认字符集：UTF-8</p>
     * 
     * @param bytes {@code byte} 类型数组
     * @return 参数转为 {@code String} 类型后的值，转换失败返回 {@code ""}
     */
    public static String bytes2Str(final byte[] bytes) {
        return bytes2Str(bytes, "UTF-8");
    }

    
    /**
     * {@code byte[]} 转为十六进制字符串
     * @param bytes {@code byte} 类型数组
     * @return 参数转为十六进制字符串后的值，如：0f210c0d
     */
    public static String bytes2HexStr(final byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return "";
        }

        String hexStr;
        final StringBuilder hexStrBuilder = new StringBuilder(32);
        for (final byte b : bytes) {
            hexStr = Integer.toHexString(b & 0xff);
            if (hexStr.length() == 1) {
                hexStrBuilder.append('0');
            }
            hexStrBuilder.append(hexStr);
        }
        return hexStrBuilder.toString();
    }

    /**
     * 十六进制字符串转为 {@code byte[]}
     * @param hexStr 十六进制字符串，如：0f210c0d
     * @return 参数转为 {@code byte[]} 后的值
     */
    public static byte[] hexStr2Bytes(final String hexStr) {
        if (hexStr == null || hexStr.trim().equals("")) {
            return null;
        }

        final int hexStrLength = hexStr.length();
        final int retLength = hexStrLength / 2;
        if (retLength <= 0) {
            return null;
        }

        int charOffset;
        byte[] result = new byte[retLength];
        for (int i = 0; i < retLength; i++) {
            charOffset = i * 2;
            result[i] = uniteBytes(hexStr.charAt(charOffset), hexStr.charAt(charOffset + 1));
        }
        return result;
    }
    
    
    /**
     * {@code float} 转为百分比
     * <p>float2Percent(0.25678F, 2) = 25.68%</p>
     * @param floatVal {@code float} 类型数字
     * @param decimal 小数点后位数
     * @return 百分比
     */
    public static String toPercent(final float floatVal, final int decimal) {
        if (decimal <= 0) {
            return String.format("%.2f%%", floatVal * 100);
        }
        return String.format("%." + decimal + "f%%", floatVal * 100);
    }
    
    /**
     * {@code double} 转为百分比
     * <p>float2Percent(0.25678, 2) = 25.68%</p>
     * @param doubleVal {@code double} 类型数字
     * @param decimal 小数点后位数
     * @return 百分比
     */
    public static String toPercent(final double doubleVal, final int decimal) {
        if (decimal <= 0) {
            return String.format("%.2f%%", doubleVal * 100);
        }
        return String.format("%." + decimal + "f%%", doubleVal * 100);
    }
    
    
    /**
     * {@code InputStream} 转为 {@code byte[]}
     * @param input {@link InputStream}
     * @return {@link byte[]}
     */
    public static byte[] input2Byte(final InputStream input) {
        int readBytes;
        final byte[] buff = new byte[1024];
        try (final ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            while ((readBytes = input.read(buff, 0, 1024)) > 0) {
                output.write(buff, 0, readBytes);
            }
            return output.toByteArray();
        } catch (IOException excpt) {
            return null;
        }
    }
    
    /**
     * {@code byte[]} 转为 {@code InputStream}
     * @param bytes {@link byte[]}
     * @return {@link InputStream}
     */
    public static InputStream byte2Input(final byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }
    
    
    /**
     * 根据高4位与低4位的值来组合byte
     * @param h4Bit 高4位
     * @param l4Bit 低4位
     * @return 根据高4位与低4位组合出来的byte值
     */
    private static byte uniteBytes(final char h4Bit, final char l4Bit) {
        final byte high = Byte.decode("0x" + h4Bit);
        final byte low = Byte.decode("0x" + l4Bit);
        return ((byte) ((high << 4) | low));
    }
}