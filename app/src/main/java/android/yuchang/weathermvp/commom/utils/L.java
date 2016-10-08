package android.yuchang.weathermvp.commom.utils;

import android.util.Log;

/**
 * @author MrChang
 *         created  at  2015/12/8.
 * @description 日志帮助类
 */
public class L {

    /**
     * 是否开启debug
     */
    public static boolean isDebug = true;


    /**
     * 错误
     * @param clazz
     * @param msg
     */
    public static void e(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.e(clazz.getSimpleName(), msg + "");
        }
    }

    /**
     * 信息
     * @param clazz
     * @param msg
     */
    public static void i(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.i(clazz.getSimpleName(), msg + "");
        }
    }

    /**
     * 警告
     * @param clazz
     * @param msg
     */
    public static void w(Class<?> clazz, String msg) {
        if (isDebug) {
            Log.w(clazz.getSimpleName(), msg + "");
        }
    }

    /**
     * 错误
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg + "");
        }
    }

    /**
     * 信息
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg + "");
        }
    }

    /**
     * 警告
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg + "");
        }
    }


    /**
     * 错误
     * @param msg
     */
    public static void e(String msg) {
        if (isDebug) {
            Log.e(Thread.currentThread().getName(), msg + "");
        }
    }

    /**
     * 信息
     * @param msg
     */
    public static void i(String msg) {
        if (isDebug) {
            Log.i(Thread.currentThread().getName(), msg + "");
        }
    }

    /**
     * 警告
     * @param msg
     */
    public static void w(String msg) {
        if (isDebug) {
            Log.w(Thread.currentThread().getName(), msg + "");
        }
    }
}
