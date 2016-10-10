package android.yuchang.weathermvp.presenter.impl;

import android.support.v4.app.FragmentManager;
import android.yuchang.weathermvp.model.db.ChosenCityHelper;
import android.yuchang.weathermvp.presenter.base.BasePresenter;
import android.yuchang.weathermvp.ui.main.MyFragmentPagerAdapter;
import android.yuchang.weathermvp.ui.main.WeatherFragment;
import android.yuchang.weathermvp.widget.YCCircleFlowIndicator;
import android.yuchang.weathermvp.widget.YCViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MrChang45
 * @time 2016/10/10
 * @desc
 */
public class MainPresenter extends BasePresenter {

    private MyFragmentPagerAdapter myFragmentPagerDaptor;
    private List<String> data;
    private ChosenCityHelper chosenCityHelper;

    public void BuilderWeatherFragmentView(FragmentManager fragmentManager, YCViewPager ycViewPager, YCCircleFlowIndicator ycCircleFlowIndicator) {
        if (chosenCityHelper == null) {
            chosenCityHelper = new ChosenCityHelper(activity);
        }
        myFragmentPagerDaptor = new MyFragmentPagerAdapter(fragmentManager);

        data = chosenCityHelper.getRemainsCityName();
        List<WeatherFragment> weatherFragmentList = new ArrayList<>();
        WeatherFragment weatherFragment;
        for (String cityName : data) {
            weatherFragment = WeatherFragment.newInstance(cityName);
            weatherFragmentList.add(weatherFragment);
        }
        myFragmentPagerDaptor.setList(weatherFragmentList);
        ycViewPager.setAdapter(myFragmentPagerDaptor);
        ycViewPager.setYcCircleFlowIndicator(ycCircleFlowIndicator);

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
