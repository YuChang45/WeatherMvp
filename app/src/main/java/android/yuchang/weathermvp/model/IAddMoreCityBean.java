package android.yuchang.weathermvp.model;

import android.yuchang.weathermvp.model.entity.AddMoreCityBean;

import java.util.List;

/**
 * @author MrChang45
 * @time 2016/10/11
 * @desc
 */
public interface IAddMoreCityBean {

    List<AddMoreCityBean> getData(String inParam);

    List<AddMoreCityBean> getProvinces();

    List<AddMoreCityBean> getCityNames(String code);

    List<AddMoreCityBean> getCountryNames(String code);
}
