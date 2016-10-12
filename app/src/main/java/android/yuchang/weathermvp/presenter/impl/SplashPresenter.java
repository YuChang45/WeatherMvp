package android.yuchang.weathermvp.presenter.impl;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.yuchang.weathermvp.commom.Const;
import android.yuchang.weathermvp.model.IChosenCityBean;
import android.yuchang.weathermvp.model.db.ChosenCityHelper;
import android.yuchang.weathermvp.model.impl.ChosenCityBeanImpl;
import android.yuchang.weathermvp.presenter.base.BasePresenter;
import android.yuchang.weathermvp.ui.chosencity.ChosenCityActivity;
import android.yuchang.weathermvp.ui.main.MainActivity;
import android.yuchang.weathermvp.ui.start.SplashView;

import java.util.List;

/**
 * @author MrChang45
 * @time 2016/9/20
 * @desc
 */
public class SplashPresenter extends BasePresenter {

    private IChosenCityBean iChosenCityBean;
    private SplashView splashView;
  //  private List<String> chosenCityNamesList;
  //  private ChosenCityHelper chosenCityHelper;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            splashView.TurnToOtherActivityByIntent(mIntent);
        }
    };

    @Override
    public void onStart() {
        splashView = (SplashView) mView;
        iChosenCityBean = new ChosenCityBeanImpl(activity);
        if (iChosenCityBean.IsAllReadyRetainsChosenCity()) {
            mIntent = new Intent(activity, MainActivity.class);
        } else {
            mIntent = new Intent(activity, ChosenCityActivity.class);
            mIntent.putExtra(Const.IS_FRIST, true);
        }
        mHandler.sendEmptyMessageDelayed(0, 2000);

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
