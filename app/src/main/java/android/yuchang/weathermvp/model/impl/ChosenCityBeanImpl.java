package android.yuchang.weathermvp.model.impl;

import android.content.Context;
import android.yuchang.weathermvp.model.IChosenCityBean;
import android.yuchang.weathermvp.model.db.ChosenCityHelper;

/**
 * @author MrChang45
 * @time 2016/9/20
 * @desc
 */
public class ChosenCityBeanImpl implements IChosenCityBean {

    @Override
    public Boolean IsAllReadyRetainsChosenCity(Context context) {
        ChosenCityHelper chosenCityHelper = new ChosenCityHelper(context);
        return chosenCityHelper.hasSelectedCity();
    }
}
