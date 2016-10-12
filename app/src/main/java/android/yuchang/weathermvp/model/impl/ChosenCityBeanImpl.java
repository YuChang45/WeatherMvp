package android.yuchang.weathermvp.model.impl;

import android.content.Context;
import android.yuchang.weathermvp.model.IChosenCityBean;
import android.yuchang.weathermvp.model.db.ChosenCityHelper;
import android.yuchang.weathermvp.model.entity.ChosenCityBean;

import java.util.List;

/**
 * @author MrChang45
 * @time 2016/9/20
 * @desc
 */
public class ChosenCityBeanImpl implements IChosenCityBean {

    private Context context;
    private ChosenCityHelper chosenCityHelper;

    public ChosenCityBeanImpl(Context context) {
        this.context = context;
        chosenCityHelper = new ChosenCityHelper(context);
    }

    @Override
    public Boolean IsAllReadyRetainsChosenCity() {
        ChosenCityHelper chosenCityHelper = new ChosenCityHelper(context);
        return chosenCityHelper.hasSelectedCity();
    }

    @Override
    public boolean hasSelectedCity() {
        return chosenCityHelper.hasSelectedCity();
    }

    @Override
    public boolean hasOnlyOneSelectedCity() {
        return chosenCityHelper.hasOnlyOneSelectedCity();
    }

    @Override
    public boolean hasRemianByCityName(String cityName) {
        return chosenCityHelper.hasRemianByCityName(cityName);
    }

    @Override
    public List<ChosenCityBean> getSelectorBean() {
        return chosenCityHelper.getSelectorBean();
    }

    @Override
    public List<String> getRemainsCityName() {
        return chosenCityHelper.getRemainsCityName();
    }

    @Override
    public int storeWeatherInfo(ChosenCityBean bean) {
        return chosenCityHelper.storeWeatherInfo(bean);
    }

    @Override
    public boolean IsRemainsByCityName(String cityName) {
        return chosenCityHelper.IsRemainsByCityName(cityName);
    }

    @Override
    public void UpdateInfo(String selectedCityName) {
        chosenCityHelper.UpdateInfo(selectedCityName);
    }

    @Override
    public int DeletedByCityName(String cityName) {
        return chosenCityHelper.DeletedByCityName(cityName);
    }
}
