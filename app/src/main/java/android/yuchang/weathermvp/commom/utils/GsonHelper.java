package android.yuchang.weathermvp.commom.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/***
 * Gson帮助类
 *
 * @author 喻畅 2015-01-20
 */
public class GsonHelper {
    public static final String EMPTY = "";
    /**
     * 空的 JSON 数据
     */
    public static final String EMPTY_JSON = "{}";
    /**
     * 空的 JSON 数组(集合)数据
     */
    public static final String EMPTY_JSON_ARRAY = "[]";
    /**
     * 默认的 JSON 日期/时间字段的格式化模式。
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";
    /**
     * Google Gson 的注解常用的版本号常量 - 1.0。
     */
    public static final Double SINCE_VERSION_10 = 1.0d;
    /**
     * Google Gson 的 注解常用的版本号常量 - 1.1。
     */
    public static final Double SINCE_VERSION_11 = 1.1d;
    /**
     * Google Gson的注解常用的版本号常量 - 1.2。
     */
    public static final Double SINCE_VERSION_12 = 1.2d;

    /**
     * 将给定的目标对象根据指定的条件参数转换成 JSON格式的字符串。 该方法转换发生错误时，不会抛出任何异常。若发生错误时，曾通对象返回 ；
     * 集合或数组对象返回
     *
     * @param target                      目标对象。
     * @param targetType                  目标对象的类型。
     * @param isSerializeNulls            是否序列化null 值字段。
     * @param version                     字段的版本号注解。
     * @param datePattern                 日期字段的格式化模式。
     * @param excludesFieldsWithoutExpose 是否排除未标注 注解的字段。
     * @return 目标对象的 JSON 格式的字符串。
     */
    public static String toJson(Object target, Type targetType,
                                boolean isSerializeNulls, Double version, String datePattern,
                                boolean excludesFieldsWithoutExpose) {
        if (target == null)
            return EMPTY_JSON;
        GsonBuilder builder = new GsonBuilder();
        if (isSerializeNulls)
            builder.serializeNulls();
        if (version != null)
            builder.setVersion(version.doubleValue());
        if (isEmpty(datePattern))
            datePattern = DEFAULT_DATE_PATTERN;
        builder.setDateFormat(datePattern);
        if (excludesFieldsWithoutExpose)
            builder.excludeFieldsWithoutExposeAnnotation();
        String result = EMPTY;
        Gson gson = builder.create();
        try {
            if (targetType != null) {
                result = gson.toJson(target, targetType);
            } else {
                result = gson.toJson(target);
            }
        } catch (Exception ex) {
            Log.e("xxx", "目标对象 " + target.getClass().getName() + " 转换 JSON 字符串时，发生异常！" + ex.getMessage()
            );
            if (target instanceof Collection || target instanceof Iterator
                    || target instanceof Enumeration
                    || target.getClass().isArray()) {
                result = EMPTY_JSON_ARRAY;
            } else
                result = EMPTY_JSON;
        }
        return result;
    }

    /**
     * 将给定的目标对象转换成 JSON格式的字符串。此方法只用来转换普通的 JavaBean 对象。 该方法只会转换标有 注解的字段； 该方法不会转换
     * null值字段； 该方法会转换所有未标注或已标注 的字段； 该方法转换时使用默认的 日期/时间 格式化模式 - yyyy-MM-dd
     * HH:mm:ss SSS；
     *
     * @param target 要转换成 JSON的目标对象。
     * @return 目标对象的 JSON格式的字符串。
     */
    public static String toJson(Object target) {
        return toJson(target, null, false, null, null, true);
    }

    /**
     * 将给定的目标对象转换成 JSON格式的字符串。此方法只用来转换普通的JavaBean 对象。 该方法只会转换标有 注解的字段； 该方法不会转换
     * null值字段； 该方法会转换所有未标注或已标注 的字段；
     *
     * @param target      要转换成 JSON的目标对象。
     * @param datePattern 日期字段的格式化模式。
     * @return 目标对象的JSON格式的字符串。
     */
    public static String toJson(Object target, String datePattern) {
        return toJson(target, null, false, null, datePattern, true);
    }

    /**
     * 将给定的目标对象转换成 JSON 格式的字符串。此方法只用来转换普通的JavaBean 对象。 该方法只会转换标有 注解的字段； 该方法不会转换
     * null值字段； 该方法转换时使用默认的 日期/时间 格式化模式 - yyyy-MM-dd HH:mm:ss SSS；
     *
     * @param target  要转换成 JSON的目标对象。
     * @param version 字段的版本号注解
     * @return 目标对象的 JSON格式的字符串。
     */
    public static String toJson(Object target, Double version) {
        return toJson(target, null, false, version, null, true);
    }

    /**
     * 将给定的目标对象转换成JSON格式的字符串。此方法只用来转换普通的 JavaBean 对象。 该方法不会转换 null值字段；
     * 该方法会转换所有未标注或已标注 的字段； 该方法转换时使用默认的 日期/时间 格式化模式 - yyyy-MM-dd HH:mm:ss SSS；
     *
     * @param target                      要转换成 JSON的目标对象。
     * @param excludesFieldsWithoutExpose 是否排除未标注 注解的字段。
     * @return 目标对象的 JSON 格式的字符串。
     */
    public static String toJson(Object target,
                                boolean excludesFieldsWithoutExpose) {
        return toJson(target, null, false, null, null,
                excludesFieldsWithoutExpose);
    }

    /**
     * 将给定的目标对象转换成 JSON格式的字符串。此方法只用来转换普通的 JavaBean 对象。 该方法不会转换 null值字段；
     * 该方法转换时使用默认的 日期/时间 格式化模式 - yyyy-MM-dd HH:mm:ss SSS；
     *
     * @param target                      要转换成 JSON 的目标对象。
     * @param version                     字段的版本号注解。
     * @param excludesFieldsWithoutExpose 是否排除未标注 注解的字段。
     * @return 目标对象的 JSON格式的字符串。
     */
    public static String toJson(Object target, Double version,
                                boolean excludesFieldsWithoutExpose) {
        return toJson(target, null, false, version, null,
                excludesFieldsWithoutExpose);
    }

    /**
     * 将给定的目标对象转换成JSON格式的字符串。此方法通常用来转换使用泛型的对象。 该方法只会转换标有 注解的字段； 该方法不会转换 null值字段；
     * 该方法会转换所有未标注或已标注 的字段； 该方法转换时使用默认的 日期/时间 格式化模式 - yyyy-MM-dd HH:mm:ss SSSS；
     *
     * @param target     要转换成 JSON的目标对象。
     * @param targetType 目标对象的类型。
     * @return 目标对象的 JSON格式的字符串。
     */
    public static String toJson(Object target, Type targetType) {
        return toJson(target, targetType, false, null, null, true);
    }

    /**
     * 将给定的目标对象转换成 JSON 格式的字符串。此方法通常用来转换使用泛型的对象。 该方法只会转换标有 注解的字段； 该方法不会转换
     * null值字段； 该方法转换时使用默认的 日期/时间 格式化模式 - yyyy-MM-dd HH:mm:ss SSSS；
     *
     * @param target     要转换成 JSON的目标对象。
     * @param targetType 目标对象的类型。
     * @param version    字段的版本号注解。
     * @return 目标对象的 JSON 格式的字符串。
     */
    public static String toJson(Object target, Type targetType, Double version) {
        return toJson(target, targetType, false, version, null, true);
    }

    /**
     * 将给定的目标对象转换成 JSON格式的字符串。此方法通常用来转换使用泛型的对象。 该方法不会转换 null值字段； 该方法会转换所有未标注或已标注
     * 的字段； 该方法转换时使用默认的 日期/时间 格式化模式 - yyyy-MM-dd HH:mm:ss SSS；
     *
     * @param target                      要转换成 JSON的目标对象。
     * @param targetType                  目标对象的类型。
     * @param excludesFieldsWithoutExpose 是否排除未标注注解的字段。
     * @return 目标对象的 JSON格式的字符串。
     */
    public static String toJson(Object target, Type targetType,
                                boolean excludesFieldsWithoutExpose) {
        return toJson(target, targetType, false, null, null,
                excludesFieldsWithoutExpose);
    }

    /**
     * 将给定的目标对象转换成 JSON格式的字符串。此方法通常用来转换使用泛型的对象。 该方法不会转换 null 值字段； 该方法转换时使用默认的
     * 日期/时间 格式化模式 - yyyy-MM-dd HH:mm:ss SSS；
     *
     * @param target                      要转换成JSON的目标对象。
     * @param targetType                  目标对象的类型。
     * @param version                     字段的版本号注解。
     * @param excludesFieldsWithoutExpose 是否排除未标注 注解的字段。
     * @return 目标对象的 JSON格式的字符串。
     */
    public static String toJson(Object target, Type targetType, Double version,
                                boolean excludesFieldsWithoutExpose) {
        return toJson(target, targetType, false, version, null,
                excludesFieldsWithoutExpose);
    }

    /**
     * 将给定的 JSON字符串转换成指定的类型对象。
     *
     * @param <T>         要转换的目标类型。
     * @param json        给定的 JSON字符串。
     * @param token       com.google.gson.reflect.TypeToken 的类型指示类对象。
     * @param datePattern 日期格式模式。
     * @return 给定的 JSON 字符串表示的指定的类型对象。
     */
    public static <T> T fromJson(String json, TypeToken<T> token,
                                 String datePattern) {
        if (isEmpty(json)) {
            return null;
        }
        GsonBuilder builder = new GsonBuilder();
        if (isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        Gson gson = builder.create();
        try {
            return gson.fromJson(json, token.getType());
        } catch (Exception ex) {
            Log.e("xxx",json + " 无法转换为 " + token.getRawType().getName() + " 对象!"+
                    ex.getMessage());
            return null;
        }
    }

    /**
     * 将给定的JSON字符串转换成指定的类型对象。
     *
     * @param <T>   要转换的目标类型。
     * @param json  给定的 JSON字符串。
     * @param token com.google.gson.reflect.TypeToken的类型指示类对象。
     * @return 给定的 JSON字符串表示的指定的类型对象。
     */
    public static <T> T fromJson(String json, TypeToken<T> token) {
        return fromJson(json, token, null);
    }

    /**
     * 将给定的 JSON字符串转换成指定的类型对象。此方法通常用来转换普通的 JavaBean 对象。
     *
     * @param <T>         要转换的目标类型。
     * @param json        给定的 JSON 字符串。
     * @param clazz       要转换的目标类。
     * @param datePattern 日期格式模式。
     * @return 给定的 JSON字符串表示的指定的类型对象。
     */
    public static <T> T fromJson(String json, Class<T> clazz, String datePattern) {
        if (isEmpty(json)) {
            return null;
        }
        GsonBuilder builder = new GsonBuilder();
        if (isEmpty(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        Gson gson = builder.create();
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception ex) {
            Log.e("xxx",json + " 无法转换为 " + clazz.getName() + " 对象!"+ ex.getMessage());
            return null;
        }
    }

    /**
     * 将给定的 JSON 字符串转换成指定的类型对象。此方法通常用来转换普通的 JavaBean 对象。
     *
     * @param <T>   要转换的目标类型。
     * @param json  给定的 JSON 字符串。
     * @param clazz 要转换的目标类。
     * @return 给定的 JSON字符串表示的指定的类型对象。
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return fromJson(json, clazz, null);
    }

    public static boolean isEmpty(String inStr) {
        boolean reTag = false;
        if (inStr == null || "".equals(inStr)) {
            reTag = true;
        }
        return reTag;
    }
}