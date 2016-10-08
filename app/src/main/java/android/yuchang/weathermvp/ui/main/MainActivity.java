package android.yuchang.weathermvp.ui.main;

import android.os.Bundle;
import android.view.View;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.presenter.base.BasePresenter;
import android.yuchang.weathermvp.presenter.impl.EmptyPresenter;
import android.yuchang.weathermvp.ui.base.BaseActivity;
import android.yuchang.weathermvp.ui.base.BaseView;

public class MainActivity extends BaseActivity<EmptyPresenter> implements BaseView {

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
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
}
