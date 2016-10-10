package android.yuchang.weathermvp.presenter.impl;

import android.yuchang.weathermvp.model.IWeatherBean;
import android.yuchang.weathermvp.model.IWeatherQulityBean;
import android.yuchang.weathermvp.model.entity.WeatherBean;
import android.yuchang.weathermvp.model.entity.WeatherQulityBean;
import android.yuchang.weathermvp.model.impl.WeatherBeanImpl;
import android.yuchang.weathermvp.model.impl.WeatherQulityBeanImpl;
import android.yuchang.weathermvp.presenter.base.BasePresenter;
import android.yuchang.weathermvp.ui.main.WeatherFragmentView;

import java.util.List;

import rx.Observer;

/**
 * @author MrChang45
 * @time 2016/10/10
 * @desc
 */
public class WeatherPresenter extends BasePresenter {


    private WeatherBean weatherBean;
    private WeatherQulityBean weatherQulityBean;
    private IWeatherQulityBean iWeatherQulityBean;
    private IWeatherBean iWeatherBean;
    private WeatherFragmentView weatherFragmentView;


    public void FetchWeatherQulity(String cityName) {

        if (iWeatherQulityBean == null) {
            iWeatherQulityBean = new WeatherQulityBeanImpl();
        }
        weatherFragmentView = (WeatherFragmentView) mView;
        iWeatherQulityBean.GetWeatherQulityInfo(cityName, new Observer<WeatherQulityBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WeatherQulityBean weatherQulityBean) {
                weatherFragmentView.FillWeatherQulity(weatherQulityBean);
            }
        });

    }

    public void FetchWeather(String cityName) {

        if (iWeatherBean == null) {
            iWeatherBean = new WeatherBeanImpl();
        }
        weatherFragmentView = (WeatherFragmentView) mView;
        iWeatherBean.GetWeatherInfo(cityName, new Observer<List<WeatherBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<WeatherBean> weatherBeen) {
                weatherFragmentView.FillWeatherInfo(weatherBeen.get(0));
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestory() {

    }
}
