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

                WeatherBean.AqiEntity aqiEntity = new WeatherBean.AqiEntity();
                WeatherBean.AqiEntity.CityEntity cityEntity = new WeatherBean.AqiEntity.CityEntity();
                cityEntity.setAqi(weatherQulityBean.getRetData().getAqi() + "");
                cityEntity.setQlty(weatherQulityBean.getRetData().getLevel());

                aqiEntity.setCity(cityEntity);
                weatherFragmentView.FillWeatherQulity(aqiEntity);

            }
        });

    }

    public void FetchWeather(final String cityName) {

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
                if (null != weatherBeen.get(0).getAqi()) {
                    weatherFragmentView.FillWeatherQulity(weatherBeen.get(0).getAqi());
                } else {
                    FetchWeatherQulity(cityName);
                }

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
