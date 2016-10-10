package android.yuchang.weathermvp.model;

import android.yuchang.weathermvp.model.entity.WeatherQulityBean;

import rx.Observer;
import rx.Subscription;

/**
 * @author MrChang45
 * @time 2016/10/10
 * @desc
 */
public interface IWeatherQulityBean {
    Subscription GetWeatherQulityInfo(String cityName, Observer<WeatherQulityBean> subscriber);
}
