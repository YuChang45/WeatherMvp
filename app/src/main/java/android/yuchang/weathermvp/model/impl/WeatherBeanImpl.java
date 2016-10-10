package android.yuchang.weathermvp.model.impl;

import android.yuchang.weathermvp.model.IWeatherBean;
import android.yuchang.weathermvp.model.entity.WeatherBean;
import android.yuchang.weathermvp.protocol.RetrofitProxy;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author MrChang45
 * @time 2016/10/9
 * @desc
 */
public class WeatherBeanImpl implements IWeatherBean {

    @Override
    public Subscription GetWeatherInfo(String cityName, Observer<List<WeatherBean>> observable) {
        return RetrofitProxy.getWeatherApi()
                .GetWeatherInfo(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }
}
