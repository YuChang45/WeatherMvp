package android.yuchang.weathermvp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.presenter.impl.EmptyPresenter;
import android.yuchang.weathermvp.presenter.impl.MainPresenter;
import android.yuchang.weathermvp.ui.base.BaseActivity;
import android.yuchang.weathermvp.ui.base.BaseView;
import android.yuchang.weathermvp.ui.managercity.ManagerCityActivity;
import android.yuchang.weathermvp.widget.YCCircleFlowIndicator;
import android.yuchang.weathermvp.widget.YCViewPager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity<MainPresenter> implements BaseView {


    private YCViewPager viewPager;
    private ImageView iv_add;
    private YCCircleFlowIndicator ycCircleFlowIndicator;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setUpViews() {
        viewPager = (YCViewPager) findViewById(R.id.viewPager);
        iv_add = (ImageView) findViewById(R.id.iv_01);
        ycCircleFlowIndicator = (YCCircleFlowIndicator) findViewById(R.id.ycCircleFlowIndicator);
        iv_add.setOnClickListener(this);
        mPresenter.BuilderWeatherFragmentView(getSupportFragmentManager(),viewPager,ycCircleFlowIndicator);
    }

    @Override
    public void setUpLisener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_01:
                 mIntent = new Intent(this, ManagerCityActivity.class);
                 startActivity(mIntent);
                break;
        }
    }

    private boolean isExit = false;
    private TimerTask timeTask = null;
    private Timer timer = null;

    @Override
    public void onBackPressed() {
        if (isExit) {
            finish();
        } else {
            isExit = true;
            if (timer == null) {
                timer = new Timer();
            }
            Toast.makeText(this, R.string.exit_again, Toast.LENGTH_SHORT)
                    .show();
            timeTask = new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            };
            timer.schedule(timeTask, 2000);
        }
    }
}
