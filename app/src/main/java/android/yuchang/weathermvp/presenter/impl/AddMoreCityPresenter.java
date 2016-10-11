package android.yuchang.weathermvp.presenter.impl;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.model.IWeatherBean;
import android.yuchang.weathermvp.model.db.ChosenCityHelper;
import android.yuchang.weathermvp.model.db.CityHelper;
import android.yuchang.weathermvp.model.entity.AddMoreCityBean;
import android.yuchang.weathermvp.model.entity.ChosenCityBean;
import android.yuchang.weathermvp.model.entity.WeatherBean;
import android.yuchang.weathermvp.model.impl.WeatherBeanImpl;
import android.yuchang.weathermvp.presenter.base.BasePresenter;
import android.yuchang.weathermvp.ui.addmorecity.AddMoreCityListRecyclerViewAdapotr;
import android.yuchang.weathermvp.ui.chosencity.HotCityRecyclerViewAdaptor;
import android.yuchang.weathermvp.ui.managercity.ManagerCityActivity;
import android.yuchang.weathermvp.widget.decoretion.MyWhiteDecoration;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

/**
 * @author MrChang45
 * @time 2016/10/8
 * @desc
 */
public class AddMoreCityPresenter extends BasePresenter {


    private IWeatherBean iWeatherBean;
    private CityHelper cityHelper;
    private List<AddMoreCityBean> addMoreCityBeanListProvince = new ArrayList<>();
    private List<AddMoreCityBean> addMoreCityBeanListCity = new ArrayList<>();
    private AddMoreCityListRecyclerViewAdapotr addMoreCityListRecyclerViewAdapotrProvince;
    private AddMoreCityListRecyclerViewAdapotr addMoreCityListRecyclerViewAdapotrCity;

    private ChosenCityHelper chosenCityHelper;
    private ChosenCityBean chosenCityBean;

    public void buildProvinceRecyclerView(RecyclerView recyclerViewProvince) {
        cityHelper = new CityHelper(activity);
        addMoreCityListRecyclerViewAdapotrProvince = new AddMoreCityListRecyclerViewAdapotr(activity, new HotCityRecyclerViewAdaptor.RecyclerViewListenr() {
            @Override
            public void onViewClick(View v, int position) {
                //刷新本级数据 刷新下级数据
                RefreshProvince(position);
                RefreshCity(position);
            }
        }, R.layout.item_list_view_province);
        addMoreCityBeanListProvince = cityHelper.getProvinces();
        //默认选中第一个
        addMoreCityBeanListProvince.get(0).enable = false;
        addMoreCityListRecyclerViewAdapotrProvince.setData(addMoreCityBeanListProvince);
        //设置布局管理器
        recyclerViewProvince.setLayoutManager(new LinearLayoutManager(activity));
        //添加分割线
        recyclerViewProvince.addItemDecoration(new MyWhiteDecoration(activity, LinearLayoutManager.VERTICAL));
        //设置Item增加、移除动画
        recyclerViewProvince.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProvince.setAdapter(addMoreCityListRecyclerViewAdapotrProvince);
    }

    private void RefreshCity(int position) {
        for (AddMoreCityBean addMoreCityBean :
                addMoreCityBeanListProvince) {
            if (!addMoreCityBean.enable) {
                addMoreCityBean.enable = true;
                addMoreCityBeanListProvince.get(position).enable = false;
                addMoreCityListRecyclerViewAdapotrProvince.notifyDataSetChanged();
                break;
            }

        }
    }

    private void RefreshProvince(int position) {
        addMoreCityBeanListCity = cityHelper.getData(addMoreCityBeanListProvince.get(position).name);
        addMoreCityListRecyclerViewAdapotrCity.setData(addMoreCityBeanListCity);
        addMoreCityListRecyclerViewAdapotrCity.notifyDataSetChanged();
    }

    public void buildCityRecyclerView(RecyclerView recyclerViewCity) {

        iWeatherBean = new WeatherBeanImpl();
        chosenCityHelper = new ChosenCityHelper(activity);

        addMoreCityListRecyclerViewAdapotrCity = new AddMoreCityListRecyclerViewAdapotr(activity, new HotCityRecyclerViewAdaptor.RecyclerViewListenr() {
            @Override
            public void onViewClick(View v, int position) {
                //获取天气
                final String cityName = v.getTag().toString();
                iWeatherBean.GetWeatherInfo(cityName, new Observer<List<WeatherBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        chosenCityBean = new ChosenCityBean();
                        chosenCityBean.setCityName(cityName);
                        chosenCityBean.setMaxTemperature("获取失败");
                        chosenCityBean.setMinTemperature("获取失败");
                        chosenCityBean.setTemperatureCode(100);
                        chosenCityBean.setTemperatureStr("获取失败");
                        chosenCityBean.setSelectedFlag(1);
                        chosenCityHelper.storeWeatherInfo(chosenCityBean);

                        mIntent = new Intent(activity, ManagerCityActivity.class);
                        activity.startActivity(mIntent);
                        activity.finish();
                    }

                    @Override
                    public void onNext(List<WeatherBean> weatherBean) {
                        chosenCityBean = new ChosenCityBean();
                        chosenCityBean.setCityName(cityName);
                        chosenCityBean.setMaxTemperature(weatherBean.get(0).getDaily_forecast().get(0).getTmp().getMax());
                        chosenCityBean.setMinTemperature(weatherBean.get(0).getDaily_forecast().get(0).getTmp().getMin());
                        chosenCityBean.setTemperatureCode(Integer.parseInt(weatherBean.get(0).getNow().getCond().getCode()));
                        chosenCityBean.setTemperatureStr(weatherBean.get(0).getNow().getCond().getTxt());
                        chosenCityBean.setSelectedFlag(1);
                        chosenCityHelper.storeWeatherInfo(chosenCityBean);

                        mIntent = new Intent(activity, ManagerCityActivity.class);
                        activity.startActivity(mIntent);
                        activity.finish();
                    }
                });

            }
        }, R.layout.item_list_view_city);
        addMoreCityBeanListCity = cityHelper.getData(addMoreCityBeanListProvince.get(0).name);
        addMoreCityListRecyclerViewAdapotrCity.setData(addMoreCityBeanListCity);
        //设置布局管理器
        recyclerViewCity.setLayoutManager(new LinearLayoutManager(activity));
        //添加分割线
        recyclerViewCity.addItemDecoration(new MyWhiteDecoration(activity, LinearLayoutManager.VERTICAL));
        //设置Item增加、移除动画
        recyclerViewCity.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCity.setAdapter(addMoreCityListRecyclerViewAdapotrCity);
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
