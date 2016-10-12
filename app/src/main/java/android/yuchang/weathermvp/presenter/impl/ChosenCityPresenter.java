package android.yuchang.weathermvp.presenter.impl;

import android.content.Intent;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.model.IChosenCityBean;
import android.yuchang.weathermvp.model.IHotCityBean;
import android.yuchang.weathermvp.model.IWeatherBean;
import android.yuchang.weathermvp.model.db.ChosenCityHelper;
import android.yuchang.weathermvp.model.entity.ChosenCityBean;
import android.yuchang.weathermvp.model.entity.WeatherBean;
import android.yuchang.weathermvp.model.impl.ChosenCityBeanImpl;
import android.yuchang.weathermvp.model.impl.HotCityBeanImpl;
import android.yuchang.weathermvp.model.impl.WeatherBeanImpl;
import android.yuchang.weathermvp.presenter.base.BasePresenter;
import android.yuchang.weathermvp.ui.addmorecity.AddMoreCityActivity;
import android.yuchang.weathermvp.ui.chosencity.ChosenCityView;
import android.yuchang.weathermvp.ui.main.MainActivity;
import android.yuchang.weathermvp.ui.managercity.ManagerCityActivity;
import android.yuchang.weathermvp.widget.sweetdialog.SweetAlertDialog;

import java.util.List;

import rx.Observer;

/**
 * @author MrChang45
 * @time 2016/9/20
 * @desc
 */
public class ChosenCityPresenter extends BasePresenter {

    private List<String> data;
    private IHotCityBean iHotCityBean;
    private ChosenCityView chosenCityView;
    private SweetAlertDialog sweetAlertDialog;
    private IChosenCityBean iChosenCityBean;

    private IWeatherBean iWeatherBean;
    private ChosenCityBean chosenCityBean;

    public void ConvertClick(int action) {
        switch (action) {
            //退出
            case R.id.iv_back:
                //判断是否至少存在一个城市，不存在不允许退出
                if (iChosenCityBean.hasSelectedCity()) {
                    activity.finish();
                } else {
                    sweetAlertDialog = new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(activity.getResources().getString(R.string.notice_str))
                            .setContentText(activity.getResources().getString(R.string.at_leaste_select_one_city))
                            .setConfirmText(activity.getResources().getString(R.string.i_know_str))
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                }
                            });
                    sweetAlertDialog.show();
                }
                break;
            //添加更多
            case R.id.tv_01:
                mIntent = new Intent(activity, AddMoreCityActivity.class);
                activity.startActivity(mIntent);
                break;
        }
    }

    public void GetWeather(final String cityName) {
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
                iChosenCityBean.storeWeatherInfo(chosenCityBean);
                mIntent = new Intent(activity, MainActivity.class);
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
                iChosenCityBean.storeWeatherInfo(chosenCityBean);

                mIntent = new Intent(activity, MainActivity.class);
                activity.startActivity(mIntent);
                activity.finish();
            }
        });
    }

    @Override
    public void onStart() {
        iHotCityBean = new HotCityBeanImpl();
        iWeatherBean = new WeatherBeanImpl();
        data = iHotCityBean.GetHotCity(activity);
        chosenCityView = (ChosenCityView) mView;
        chosenCityView.FillHotCityRecyclerView(data);
        iChosenCityBean = new ChosenCityBeanImpl(activity);
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
