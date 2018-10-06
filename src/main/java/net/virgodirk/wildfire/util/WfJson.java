package net.virgodirk.wildfire.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * JSON Utils
 *
 * <p>基于com.alibaba.fastjson</p>
 *
 * @author 李晓勇 on 2017年8月25日 下午5:09:46
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfJson {
    
    /**
     * 将JavaBean序列化为JSON文本
     * @param object JavaBean
     * @return JSON文本，{@code object} 为 {@code null} 时返回 {}
     */
    public static String toJsonString(final Object object) {
        return object == null ? "{}" : JSON.toJSONString(object);
    }
    
    /**
     * 解析JSON文本为JavaBean
     * @param json JSON文本
     * @param clazz JavaBean类型
     * @param <T> JavaBean类型
     * @return JavaBean，{@code json} 为空时返回 {@code null}
     */
    public static <T> T parseObject(final String json, final Class<T> clazz) {
        if (json == null || json.trim().equals("") || clazz == null) {
            return null;
        }
        return JSON.parseObject(json, clazz);
    }
    
    /**
     * 解析JSON文本为JavaBean集合
     * @param json JSON文本
     * @param clazz JavaBean类型
     * @param <T> JavaBean类型
     * @return JavaBean集合，{@code json} 为空时返回 {@code null}
     */
    public static <T> List<T> parseArray(final String json, final Class<T> clazz) {
        if (json == null || json.trim().equals("") || clazz == null) {
            return null;
        }
        return JSON.parseArray(json, clazz);
    }
}