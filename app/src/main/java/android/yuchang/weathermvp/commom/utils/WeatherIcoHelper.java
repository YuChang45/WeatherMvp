package android.yuchang.weathermvp.commom.utils;


import android.yuchang.weathermvp.R;

/**
 * @author MrChang
 *         created  at  2016/1/22.
 * @description
 */
public class WeatherIcoHelper {

    public static int changeWeatherCodeToIcoResourceId(String code, boolean isNight) {
        int resourceId = -1;
        switch (code) {
            case "100":
                if (isNight) {
                    resourceId = R.mipmap.weathericon_graph_night_100;
                } else {
                    resourceId = R.mipmap.weathericon_graph_100;
                }
                break;
            case "101":
            case "102":
                if (isNight) {
                    resourceId = R.mipmap.weathericon_graph_night_101_102_103_104;
                } else {
                    resourceId = R.mipmap.weathericon_graph_101_102;
                }
                break;
            case "103":
                if (isNight) {
                    resourceId = R.mipmap.weathericon_graph_night_101_102_103_104;
                } else {
                    resourceId = R.mipmap.weathericon_graph_103;
                }
                break;
            case "104":
                if (isNight) {
                    resourceId = R.mipmap.weathericon_graph_night_101_102_103_104;
                } else {
                    resourceId = R.mipmap.weathericon_graph_104;
                }
                break;
            case "300":
            case "301":
                resourceId = R.mipmap.weathericon_graph_300_301;
                break;
            case "302":
            case "303":
                resourceId = R.mipmap.weathericon_graph_302_303;
                break;
            case "400":
            case "401":
            case "407":
                resourceId = R.mipmap.weathericon_graph_400_401_407;
                break;
            case "402":
            case "403":
                resourceId = R.mipmap.weathericon_graph_402_403;
                break;
            case "404":
            case "405":
                resourceId = R.mipmap.weathericon_graph_404_405;
                break;
            case "500":
            case "501":
                resourceId = R.mipmap.weathericon_graph_500_501;
                break;
            case "502":
                resourceId = R.mipmap.weathericon_graph_502;
                break;
            case "503":
            case "504":
            case "507":
            case "508":
                resourceId = R.mipmap.weathericon_graph_503_504_507_508;
                break;
            case "305":
            case "306":
            case "308":
            case "309":
                resourceId = R.mipmap.weathericon_graph__305_306_308_309;
                break;
            case "307":
            case "310":
            case "311":
            case "312":
                resourceId = R.mipmap.weathericon_graph__307_310_311_312;
                break;
            case "313":
                resourceId = R.mipmap.weathericon_graph__313;
                break;
        }
        return resourceId;
    }
}
