package android.yuchang.weathermvp.model.impl;

import android.yuchang.weathermvp.model.IWeatherQulityBean;
import android.yuchang.weathermvp.model.entity.WeatherQulityBean;
import android.yuchang.weathermvp.protocol.RetrofitProxy;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author MrChang45
 * @time 2016/10/10
 * @desc 获取天气质量
 */
public class WeatherQulityBeanImpl implements IWeatherQulityBean {

    @Override
    public Subscription GetWeatherQulityInfo(String cityName, Observer<WeatherQulityBean> observable) {
        return RetrofitProxy.getWeatherQulityApi()
                .GetWeatherQulityInfo(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable);
    }
}
