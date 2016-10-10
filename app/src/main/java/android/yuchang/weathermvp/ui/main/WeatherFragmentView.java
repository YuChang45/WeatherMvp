package android.yuchang.weathermvp.ui.main;

import android.yuchang.weathermvp.model.entity.WeatherBean;
import android.yuchang.weathermvp.model.entity.WeatherQulityBean;
import android.yuchang.weathermvp.ui.base.BaseView;

/**
 * @author MrChang45
 * @time 2016/10/10
 * @desc
 */
public interface WeatherFragmentView extends BaseView {

    void FillWeatherInfo(WeatherBean weatherBean);

    void FillWeatherQulity(WeatherQulityBean weatherQulityBean);

}
