package android.yuchang.weathermvp.commom.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public static String changStr(String dateStr)
    {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sd2 = new SimpleDateFormat("M/d");
        try {
            Date date =sd.parse(dateStr);
            return sd2.format(date).toString();
        } catch (ParseException e) {
            return "";
        }
    }
    public static String changStr2(String dateStr)
    {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateStr));
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            switch (dayOfWeek) {
                case 1:
                    return "周日";
                case 2:
                    return "周一";
                case 3:
                    return "周二";
                case 4:
                    return "周三";
                case 5:
                    return "周四";
                case 6:
                    return "周五";
                case 7:
                    return "周六";
            }
        } catch (ParseException e) {
        }
        return "";
    }

    public static String changStr3(String dateStr)
    {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sd2 = new SimpleDateFormat("HH:mm");
        try {
            Date date =sd.parse(dateStr);
            return sd2.format(date).toString();
        } catch (ParseException e) {
            return "";
        }
    }

}
