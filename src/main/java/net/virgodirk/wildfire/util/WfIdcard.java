package net.virgodirk.wildfire.util;

import java.util.Locale;

/**
 * IDCard Utils
 *
 * @author 李晓勇 on 2017年8月25日 上午11:04:05
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfIdcard {

    /**
     * 身份证号码各位上的加权因子，用于计算校验位
     */
    private static final int[] WEIGHT = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
    
    
    /**
     * 验证身份证号码（高级验证）
     * @param cardId 待验证身份证号码
     *@return {@code true} 验证通过<br>
     *        {@code false} 验证未通过
     */
    public static boolean check(final String cardId) {
        return check(cardId, CheckLevelEnum.HIG);
    }
    
    /**
     * 验证身份证号码
     * @param cardId 待验证身份证号码
     * @param checkLevel 验证级别
     * @return {@code true} 验证通过<br>
     *         {@code false} 验证未通过
     */
    public static boolean check(final String cardId, final CheckLevelEnum checkLevel) {
        if (WfString.isEmpty(cardId)) {
            return false;
        }

        // 是否类似于身份证号码
        if (!isLike(cardId)) {
            return false;
        }

        // 身份证号码长度
        if (!isLength(cardId)) {
            return false;
        }

        // 低级验证
        if (checkLevel == CheckLevelEnum.LOW) {
            return true;
        }

        // 身份证号码格式
        final String cardIdUpperCase = cardId.toUpperCase(Locale.ENGLISH);
        if (!isFormat(cardIdUpperCase)) {
            return false;
        }

        // 中级验证
        if (checkLevel == CheckLevelEnum.MID || cardIdUpperCase.length() == 15) {
            return true;
        }

        // 18位身份证号码校验位（高级验证）
        final String checkNum = getCheckNum(cardIdUpperCase.substring(0, 17));
        return checkNum.equals(cardIdUpperCase.substring(17));
    }

    /**
     * 解析身份证号码
     * @param cardId 待解析身份证号码
     * @return 解析结果
     */
    public static ParseResult parser(final String cardId) {
        final ParseResult result = new ParseResult();
        if (!check(cardId, CheckLevelEnum.LOW)) {
            return result;
        }

        // 户籍地代码
        result.setRegion(cardId.substring(0, 6));

        // 出生日期
        final int length = cardId.length();
        if (length == 18) {
            result.setBirthDate(cardId.substring(6, 14));
            result.setGender(cardId.substring(16, 17));
        } else if (length == 15) {
            result.setBirthDate("19" + cardId.substring(6,12));
            result.setGender(cardId.substring(14, 15));
        }

        // 性别
        if (Integer.parseInt(result.getGender()) % 2 == 0) {
            result.setGender("2"); // 女性
        } else {
            result.setGender("1"); // 男性
        }
        return result;
    }

    /**
     * 将15位身份证号码转为18位
     * @param cardId15 15位身份证号码
     * @return 返回18位身份证号码，身份证号码无效时原样返回
     */
    public static String toCardId18(final String cardId15) {
        if (cardId15.trim().length() != 15 || !check(cardId15, CheckLevelEnum.HIG)) {
            return cardId15;
        }

        final StringBuilder cardIdStrBuild = new StringBuilder(cardId15);
        cardIdStrBuild.insert(6, "19");
        cardIdStrBuild.append(getCheckNum(cardIdStrBuild.toString()));
        return cardIdStrBuild.toString();
    }
    
    /**
     * 根据出生日期与有效期起始日期计算有效期截止日期
     * @param birthDate 出生日期，格式：yyyyMMdd或yyyy-MM-dd
     * @param startDate 有效期起始日期，格式：yyyyMMdd或yyyy-MM-dd
     * @return 有效期截止日期，格式：yyyyMMdd，或“长期”
     */
    public static String getEndDate(final String birthDate, final String startDate) {
        final String birthDateShort = WfDate.formatShort(birthDate);
        final int age = WfDate.getAge(birthDateShort);
        if (age > 45) {
            return "长期";
        }
        
        String endDate;
        final String startDateShort = WfDate.formatShort(startDate);
        if (age > 25) {
            endDate = WfDate.addYears(startDateShort, 20);
        } else if (age >= 16) {
            endDate = WfDate.addYears(startDateShort, 10);
        } else {
            endDate = WfDate.addYears(startDateShort, 5);
        }
        
        /* 若给2016-02-29加1年后为2016-02-28，这种情况需要加1天 
         * 若给2016-02-29加4年后为2020-02-29，这种情况不需要加1天 */
        final int endYear = Integer.parseInt(endDate.substring(0, 4));
        final int startYear = Integer.parseInt(startDateShort.substring(0, 4));
        if (WfDate.isLeapYear(startYear) && !WfDate.isLeapYear(endYear)
                && "0229".equals(startDateShort.substring(4, 8))) {
            return WfDate.addDays(endDate, 1);
        }
        return endDate;
    }

    /**
     * 获取18位份证号码校验位
     * @param cardId17 身份证号码前17位
     * @return 校验位
     */
    public static String getCheckNum(final String cardId17) {
        if (cardId17 == null || cardId17.trim().length() != 17) {
            return "";
        }

        // 计算各位的加权和
        int num;
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            num = Integer.parseInt(cardId17.substring(i, i + 1));
            sum += num * WEIGHT[i];
        }

        // 计算校验位
        final int checkNum = 12 - (sum % 11);
        if (checkNum == 10) {
            return "X";
        }
        if (checkNum == 11) {
            return "0";
        }
        if (checkNum == 12) {
            return "1";
        }
        return String.valueOf(checkNum);
    }
    

    /**
     * 检查身份证号码格式是否正确
     * @param cardId 身份证号码
     * @return {@code true} 格式正确<br>
     *         {@code false} 格式不正确
     */
    private static boolean isFormat(final String cardId) {
        // 前六位行政区划代码不能全部相同
        final String regionId = cardId.substring(0, 6);
        if (regionId.matches("^[0]{6}|[1]{6}|[2]{6}|[3]{6}|[4]{6}|[5]{6}|[6]{6}|[7]{6}|[8]{6}|[9]{6}$")) {
            return false;
        }

        // 出生日期
        final int len = cardId.length();
        final String birthDate = (len == 15 ? ("19" + cardId.substring(6, 12)) : cardId.substring(6, 14));
        return WfString.isDateTime(birthDate);
    }

    /**
     * 检查身份证号码长度是否正确
     * @param cardId 身份证号码
     * @return {@code true} 长度正确<br>
     *         {@code false} 长度不正确
     */
    private static boolean isLength(final String cardId) {
        final int length = cardId.trim().length();
        return length == 15 || length == 18;
    }

    /**
     * 检查字符串是否类似于身份证号码
     * @param str 待检查字符串
     * @return {@code true} 待检查字符串类似于身份证号码<br>
     *         {@code false} 待检查字符串不类似于身份证号码
     */
    private static boolean isLike(final String str) {
        final String strUpperCase = str.toUpperCase(Locale.ENGLISH);
        return strUpperCase.matches("^[\\d]{15}([\\d]{2}[\\d|X])?$");
    }


    /**
     * 身份证号码验证级别
     */
    public enum CheckLevelEnum {

        /**
         * 低级验证：只验证位数(15位或18位)
         */
        LOW,

        /**
         * 中级验证：验证除校验位外的所有条件
         */
        MID,

        /**
         * 高级验证：验证包括校验位在内的所有条件
         */
        HIG
    }

    /**
     * 身份证号码解析结果
     */
    public static final class ParseResult {

        /**
         * 户籍地编码
         */
        private String region;
        
        /**
         * 性别代码（1男；2女）
         */
        private String gender;
        
        /**
         * 出生日期（格式：yyyyMMdd）
         */
        private String birthDate;

        
        /**
         * 构造 {@link ParseResult}
         */
        public ParseResult() {
            region = "";
            gender = "";
            birthDate = "";
        }
        
        /**
         * 获取户籍地代码
         * @return 户籍地代码
         */
        public String getRegion() {
            return region;
        }

        /**
         * 设置户籍地代码
         * @param region 户籍地代码
         */
        public void setRegion(final String region) {
            this.region = region;
        }

        /**
         * 获取性别代码
         * @return 性别代码
         */
        public String getGender() {
            return gender;
        }

        /**
         * 设置性别代码
         * @param gender 性别代码
         */
        public void setGender(final String gender) {
            this.gender = gender;
        }

        /**
         * 获取出生日期
         * @return 出生日期（格式：yyyyMMdd）
         */
        public String getBirthDate() {
            return birthDate;
        }

        /**
         * 设置出生日期
         * @param birthDate 出生日期（格式：yyyyMMdd）
         */
        public void setBirthDate(final String birthDate) {
            this.birthDate = birthDate;
        }
        
    }
}