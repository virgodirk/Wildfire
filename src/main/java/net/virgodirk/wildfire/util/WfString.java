package net.virgodirk.wildfire.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * String Utils
 * 
 * <p>基于org.apache.commons.lang3.StringUtils</p>
 * 
 * @author 李晓勇 on 2017年8月22日 下午2:12:22
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfString extends StringUtils {

    /**
     * 检查字符串是否全部为数字
     */
    private static final Pattern ALL_NUMBER_PATTERN = Pattern.compile("^[\\d]+$");
    
    /**
     * 检查字符串是否为数值字符串
     */
    private static final Pattern IS_NUMERIC_PATTERN = 
            Pattern.compile("^[\\\\+|\\-]?[\\d]+.[\\d]+$|^[\\\\+|\\-]?[\\d]+$");
    
    /**
     * 检查字符串是否是电话号码
     */
    private static final Pattern IS_PHONE_NUMBER_PATTERN = 
            Pattern.compile("^([\\d]{6,12})$|^(\\d{3,4}-)?(\\d{7,8})(-(\\d{3,4}))?$");
    
    /**
     * 检查字符串是否是Email地址
     */
    private static final Pattern IS_EMAIL_PATTERN = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
    
    /**
     * IP段数字
     */
    private static final String IP_NUM = "((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])";
    
    /**
     * 检查字符串是否是IPV4地址
     */
    private static final Pattern IS_IP_ADDRESS_PATTERN = 
            Pattern.compile(String.format("\\b%s\\.%s\\.%s\\.%s\\b", IP_NUM, IP_NUM, IP_NUM, IP_NUM));
    
    /**
     * 检查字符串是否是邮政编码
     */
    private static final Pattern IS_POSTCODE_PATTERN = Pattern.compile("^\\d{6}$");
    
    /**
     * 检查字符串是否全部是中文（汉字）
     */
    private static final Pattern IS_CHINESE_PATTERN = Pattern.compile("^[\u4e00-\u9fa5]+$");
    
    /**
     * 检查字符串中是否包含中文（汉字）
     */
    private static final Pattern CONTAIN_CHINESE_PATTERN = Pattern.compile(".*[\u4e00-\u9fa5]+.*");
    
    /**
     * 检查字符串是否全部是宽字符
     */
    private static final Pattern IS_WIDE_CHAR_PATTERN = Pattern.compile("^[^\\x00-\\xff]+$");
    
    /**
     * 检查字符串是否包含宽字符
     */
    private static final Pattern CONTAIN_WIDE_CHAR_PATTERN = Pattern.compile("^.*[^\\x00-\\xff]+.*$");
    
    /**
     * 检查字符串是否为中文姓名
     */
    private static final Pattern IS_CHINESE_NAME_PATTERN = 
            Pattern.compile("^[\u4e00-\u9fa5]+([·][\u4e00-\u9fa5]+)*[\u4e00-\u9fa5]*$");
    
    /**
     * 用于生成随机字符串的基础字符串
     */
    private static final String RANDOM_BASE_STRING = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /**
     * 用于生成随机字符串的基础字符串长度
     */
    private static final int RANDOM_BASE_STRING_SIZE = 62;


    /**
     * 检查字符串是否全部为数字
     * <p>不包括正负号和小数点</p>
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串全部是数字<br>
     *         {@code false} 待检查字符串不全是数字
     */
    public static boolean allNumber(final String str) {
        return !isEmpty(str) && ALL_NUMBER_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串是否为数值字符串
     * <p>数值包括：整数、小数、 正数、 负数</p>
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串是数值字符串<br>
     *         {@code false} 待检查字符串不是数值字符串
     */
    public static boolean isNumeric(final String str) {
        return !isEmpty(str) && IS_NUMERIC_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串是否是电话号码
     * <p>支持手机号码和固话号码，如：0471-4391327-203、15947418715</p>
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串是电话号<br>
     *         {@code false} 待检查字符串不是电话号
     */
    public static boolean isPhoneNumber(final String str) {
        return !isEmpty(str) && IS_PHONE_NUMBER_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串是否是Email地址
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串是Email地址<br>
     *         {@code false} 待检查字符串不是Email地址
     */
    public static boolean isEmail(final String str) {
        return !isEmpty(str) && !containWideChar(str) && IS_EMAIL_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串是否是IPV4地址
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串是IPV4地址<br>
     *         {@code false} 待检查字符串不是IPV4地址
     */
    public static boolean isIpAddress(final String str) {
        return !isEmpty(str) && IS_IP_ADDRESS_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串是否是邮政编码
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串是邮政编码<br>
     *         {@code false} 待检查字符串不是邮政编码
     */
    public static boolean isPostcode(final String str) {
        return !isEmpty(str) && IS_POSTCODE_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串是否全部是中文（汉字）
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串全部是中文<br>
     *         {@code false} 待检查字符串不全是中文
     */
    public static boolean isChinese(final String str) {
        return !isEmpty(str) && IS_CHINESE_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串中是否包含中文（汉字）
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串包含中文<br>
     *         {@code false} 待检查字符串不包含中文
     */
    public static boolean containChinese(final String str) {
        return !isEmpty(str) && CONTAIN_CHINESE_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串是否全部是宽字符
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串全部是宽字符<br>
     *         {@code false} 待检查字符串不全是宽字符
     */
    public static boolean isWideChar(final String str) {
        return !isEmpty(str) && IS_WIDE_CHAR_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串是否包含宽字符
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串包含宽字符<br>
     *         {@code false} 待检查字符串不包含宽字符
     */
    public static boolean containWideChar(final String str) {
        return !isEmpty(str) && CONTAIN_WIDE_CHAR_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串是否为中文姓名
     * <p>中文姓名如：李四、阿·买买提·古丽等</p>
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串是中文姓名<br>
     *         {@code false} 待检查字符串不是中文姓名
     */
    public static boolean isChineseName(final String str) {
        return !isEmpty(str) && IS_CHINESE_NAME_PATTERN.matcher(str).matches();
    }

    /**
     * 检查字符串是否是日期时间 
     * <p>兼容长日期格式与短日期格式</p>
     * @param str 待检查字符串，如：20170101010100、20170101、2017-01-01 01:01:00、2017-01-01
     * @return {@code true} 待检查字符串是有效的日期时间<br>
     *         {@code false} 待检查字符串不是有效的日期时间
     */
    public static boolean isDateTime(final String str) {
        if (isEmpty(str)) {
            return false;
        }
        
        String dateTimeStr = str;
        dateTimeStr = dateTimeStr.replace("-", "");
        dateTimeStr = dateTimeStr.replace(":", "");
        dateTimeStr = dateTimeStr.replace(" ", "");
        if (!allNumber(dateTimeStr)) {
            return false;
        }
        dateTimeStr = rightPad(dateTimeStr, 14, '0');
        
        // 日期验证
        final int year = Integer.parseInt(dateTimeStr.substring(0, 4));
        final int month = Integer.parseInt(dateTimeStr.substring(4, 6));
        final int day = Integer.parseInt(dateTimeStr.substring(6, 8));
        if (year < 1900 || !isValidDay(year, month, day)) {
            return false;
        }
 
        // 时间验证
        final int hours = Integer.parseInt(dateTimeStr.substring(8, 10));
        final int minutes = Integer.parseInt(dateTimeStr.substring(10, 12));
        final int seconds = Integer.parseInt(dateTimeStr.substring(12, 14));
        return hours >= 0 && hours <= 23 && minutes >= 0 && minutes < 60 && seconds >= 0 && seconds < 60;
    }
    
    
    /**
     * 从字符串首部移除指定字符
     * @param src 原字符串
     * @param character 要移除的字符
     * @return 移除首部字符后的子串
     */
    public static String trimStart(final String src, final char character) {
        if (isEmpty(src) || !src.startsWith(String.valueOf(character))) {
            return src;
        }
        
        int beginIndex = 0;
        final int length = src.length();
        for (; beginIndex < length; beginIndex++) {
            if (src.charAt(beginIndex) != character) {
                break;
            }
        }
        return src.substring(beginIndex, length);
    }
    
    /**
     * 从字符串末尾移除指定字符
     * @param src 原字符串
     * @param character 要移除的字符
     * @return 移除末尾字符后的子串
     */
    public static String trimEnd(final String src, final char character) {
        if (isEmpty(src) || !src.endsWith(String.valueOf(character))) {
            return src;
        }
        
        int endIndex = src.length() - 1;
        for (; endIndex >= 0; endIndex--) {
            if (src.charAt(endIndex) != character) {
                break;
            }
        }
        return src.substring(0, endIndex + 1);
    }
    
    /**
     * 根据当前序号获取下一个序号
     * <p>如：getNextOrderId("2", 4)结果为"0003"</p>
     * @param currentId 当前序号，必须为整数字符串
     * @param width 序号位数，位数不足时前面补0
     * @return 下一个序号
     */
    public static String nextId(final String currentId, final int width) {
        if (isEmpty(currentId) || width <= 0 || !allNumber(currentId)) {
            return "";
        }
        return String.format("%0" + width + "d", Integer.parseInt(currentId) + 1);
    }

    /**
     * 获取分隔符左侧的子字符串
     * @param str 原字符串
     * @param separator 分隔符
     * @return 返回分隔符左侧的子串，不含分隔符时返回原字符串
     */
    public static String leftSubStr(final String str, final String separator) {
        if (isEmpty(str)) {
            return "";
        }

        final int index = str.indexOf(separator);
        if (index < 0) {
            return str; // 字符串中不含分割符，直接返回原字符串
        }
        return str.substring(0, index);
    }

    /**
     * 获取空格左侧的子字符串
     * @param str 原字符串
     * @return 返回空格左侧的子串，不含空格时返回原字符串
     */
    public static String leftSubStr(final String str) {
        if (isEmpty(str)) {
            return "";
        }

        final int index = str.indexOf(' ');
        if (index < 0) {
            return str; // 字符串中不含空格，直接返回原字符串
        }
        return str.substring(0, index);
    }

    /**
     * 获取分隔符右侧的子字符串
     * @param str 原字符串
     * @param separator 分隔符
     * @return 返回分隔符右侧的子串，不含分隔符时返回原字符串
     */
    public static String rightSubStr(final String str, final String separator) {
        if (isEmpty(str)) {
            return "";
        }

        final int index = str.indexOf(separator);
        if (index < 0) {
            return str;  // 字符串中不含分割符，直接返回原字符串
        }
        return str.substring(index + separator.length());
    }

    /**
     * 获取空格右侧的子字符串
     * @param str 原字符串
     * @return 返回空格右侧的子串，不含空格时返回原字符串
     */
    public static String rightSubStr(final String str) {
        if (isEmpty(str)) {
            return "";
        }

        final int index = str.indexOf(' ');
        if (index < 0) {
            return str;  // 字符串中不含空格，直接返回原字符串
        }
        return str.substring(index + 1);
    }

    /**
     * 将字符串按分隔符分割
     * @param str 待分割字符串
     * @param separator 分隔符
     * @return 分割结果, {@code str} 为 {@code null} 或 {@code ""} 时返回 {@code null}
     */
    public static List<String> splitToList(final String str, final String separator) {
        if (isEmpty(str)) {
            return null;
        }
        final String[] strArray = str.split(separator);
        return Arrays.asList(strArray);
    }

    /**
     * 比较两个版本号的大小
     * <p>可比较两个长度不等的版本号，如：1.0.2大于1.0.1.9</p>
     * @param newVersion 新版本号，如：1.0.1.1
     * @param curVersion 当前版本号，如：1.0.1.0
     * @return 大于0 新版本号大于当前版本号（有新版本）<br>
     *         小于0 新版本号小于当前版本号（无新版本）<br>
     *         等于0 新版本号等于当前版本号（无新版本）
     */
    public static int cmpVersions(final String newVersion, final String curVersion) {
        if (newVersion.equals(curVersion)) {
            return 0;
        }

        // 分解当前版本号
        final String[] curVersions = curVersion.split("\\.");
        final int curVersionLen = curVersions.length;

        // 分解最新版本号
        final String[] newVersions = newVersion.split("\\.");
        final int newVersionLen = newVersions.length;

        // 比较版本号
        int curSubVersion; // 当前版本号中的子版本号
        int newSubVersion; // 新版本号中的子版本号
        int subVersionDiff = 0; // 子版本号差
        final int versionLen = curVersionLen > newVersionLen ? curVersionLen : newVersionLen;
        for (int i = 0; i < versionLen; i++) {
            if (curVersionLen <= i) { // 当前版本号较短
                curSubVersion = 0;
                newSubVersion = WfConvert.str2Int(newVersions[i], 0);
            } else if (newVersionLen <= i) { // 新版本号较短
                newSubVersion = 0;
                curSubVersion = WfConvert.str2Int(curVersions[i], 0);
            } else {
                curSubVersion = WfConvert.str2Int(curVersions[i], 0);
                newSubVersion = WfConvert.str2Int(newVersions[i], 0);
            }
            subVersionDiff = newSubVersion - curSubVersion;
            if (subVersionDiff != 0) {
                break;
            }
        }
        return subVersionDiff;
    }
    
    
    /**
     * 手机号码脱敏
     * @param phoneNum 手机号码
     * @return 脱敏后的手机号码，如：159****8715
     */
    public static String maskPhoneNum(final String phoneNum) {
        if (isEmpty(phoneNum) || phoneNum.length() < 7) {
            return phoneNum;
        }
        return String.format("%s****%s", phoneNum.substring(0, 3), phoneNum.substring(phoneNum.length() - 4));
    }
    
    /**
     * 身份证号脱敏
     * @param cardId 身份证号（18位）
     * @return 脱敏后的身份证号，如：150*************12
     */
    public static String maskCardId(final String cardId) {
        if (isEmpty(cardId) || cardId.length() != 18) {
            return cardId;
        }
        return String.format("%s*************%s", cardId.substring(0, 3), cardId.substring(cardId.length() - 2));
    }
    
    /**
     * 姓名脱敏
     * @param name 姓名
     * @return 脱敏后的姓名，如：李*、张*丰、欧**强
     */
    public static String maskName(final String name) {
        if (name == null || name.length() <= 1) {
            return name;
        }
        
        final int length = name.length();
        if (length == 2) {
            return String.format("%s*", name.substring(0, 1));
        }
        return String.format("%s*%s", rightPad(name.substring(0, 1), length - 2, '*'), name.substring(length - 1));
    }
    
    
    /**
     * 生成UUID
     * @return 32位小写UUID
     */
    public static String getUuid32() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase(Locale.ENGLISH);
    }
    
    /**
     * 生成UUID
     * @return 36位小写UUID
     */
    public static String getUuid36() {
        return UUID.randomUUID().toString().toLowerCase(Locale.ENGLISH);
    }

    /**
     * 获取指定位数的随机字符串
     * @param length 随机字符串位数
     * @return 随机字符串
     */
    public static String getRandomStr(final int length) {
        final Random random = new Random();
        final StringBuilder randomStrBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomStrBuilder.append(RANDOM_BASE_STRING.charAt(random.nextInt(RANDOM_BASE_STRING_SIZE)));
        }
        return randomStrBuilder.toString();
    }
    
    /**
     * 获取指定位数的随机数字
     * @param length 随机数字位数
     * @return 随机数字
     */
    public static String getRandomNum(final int length) {
        final Random random = new Random();
        final StringBuilder randomStrBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomStrBuilder.append("1234567890".charAt(random.nextInt(10)));
        }
        return randomStrBuilder.toString();
    }
    
    
    /**
     * 检查日期是否有效
     * @param year 所属年份
     * @param month 所属月份
     * @param day 待检查日期
     * @return {@code true} 日期有效
     *         {@code false} 日期无效
     */
    private static boolean isValidDay(final int year, final int month, final int day) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) { // 小月
            return day >= 1 && day <= 30;
        }
        if (month == 2) { // 二月
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) { // 闰年
                return day >= 1 && day <= 29;
            }
            return day >= 1 && day <= 28;
        }
        return day >= 1 && day <= 31; // 大月
    }
}