package android.yuchang.weathermvp.commom.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yu on 14-1-8.
 */
public class StrUtil {

    public static long getSysCurrentTime() {
        return System.currentTimeMillis();
    }

    @SuppressLint("SimpleDateFormat")
    public static Date getCurrentDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(new Date());
        return sdf.parse(date);
    }

    public static boolean isEmpty(String str) {
        return (str == null) || str.trim().equalsIgnoreCase("null")
                || (str.trim().length() < 1) || str.equals("");
    }


}
