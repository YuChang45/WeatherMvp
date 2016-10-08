package android.yuchang.weathermvp.model;

import android.content.Context;

import java.util.List;

/**
 * @author MrChang45
 * @time 2016/9/20
 * @desc
 */
public interface IHotCityBean {
    List<String> GetHotCity(Context context);
}
