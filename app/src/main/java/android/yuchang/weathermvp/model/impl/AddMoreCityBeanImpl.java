package android.yuchang.weathermvp.model.impl;

import android.content.Context;
import android.yuchang.weathermvp.model.IAddMoreCityBean;
import android.yuchang.weathermvp.model.db.CityHelper;
import android.yuchang.weathermvp.model.entity.AddMoreCityBean;

import java.util.List;

/**
 * @author MrChang45
 * @time 2016/10/11
 * @desc
 */
public class AddMoreCityBeanImpl implements IAddMoreCityBean {

    private CityHelper cityHelper;

    public AddMoreCityBeanImpl(Context context) {
        cityHelper = new CityHelper(context);
    }

    @Override
    public List<AddMoreCityBean> getData(String inParam) {
        return cityHelper.getData(inParam);
    }

    @Override
    public List<AddMoreCityBean> getProvinces() {
        return cityHelper.getProvinces();
    }

    @Override
    public List<AddMoreCityBean> getCityNames(String code) {
        return cityHelper.getCityNames(code);
    }

    @Override
    public List<AddMoreCityBean> getCountryNames(String code) {
        return cityHelper.getCountryNames(code);
    }
}
