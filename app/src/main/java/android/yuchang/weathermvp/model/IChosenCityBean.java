package android.yuchang.weathermvp.model;

import android.yuchang.weathermvp.model.entity.ChosenCityBean;

import java.util.List;

/**
 * @author MrChang45
 * @time 2016/9/20
 * @desc
 */
public interface IChosenCityBean {

    Boolean IsAllReadyRetainsChosenCity();

    boolean hasSelectedCity();

    boolean hasOnlyOneSelectedCity();

    boolean hasRemianByCityName(String cityName);

    List<ChosenCityBean> getSelectorBean();

    List<String> getRemainsCityName();

    int storeWeatherInfo(ChosenCityBean bean);

    boolean IsRemainsByCityName(String cityName);

    void UpdateInfo(String selectedCityName);

    int DeletedByCityName(String cityName);

}
