package android.yuchang.weathermvp.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.presenter.impl.SplashPresenter;
import android.yuchang.weathermvp.ui.base.BaseActivity;


/***
 * 启动页
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void setUpViews() {

    }

    @Override
    public void setUpLisener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void TurnToOtherActivityByIntent(Intent intent) {
        startActivity(intent);
        finish();
    }

}
