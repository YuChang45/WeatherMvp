package android.yuchang.weathermvp.model;

import android.yuchang.weathermvp.model.entity.WeatherBean;

import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * @author MrChang45
 * @time 2016/10/9
 * @desc
 */
public interface IWeatherBean {
    Subscription GetWeatherInfo(String cityName, Observer<List<WeatherBean>> subscriber);
}
