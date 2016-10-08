package android.yuchang.weathermvp.model.impl;

import android.content.Context;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.model.IHotCityBean;

import java.util.Arrays;
import java.util.List;

/**
 * @author MrChang45
 * @time 2016/9/20
 * @desc
 */
public class HotCityBeanImpl implements IHotCityBean {

    @Override
    public List<String> GetHotCity(Context context) {
        return Arrays.asList(context.getResources().getStringArray(R.array.hot_city_ch));
    }

}
