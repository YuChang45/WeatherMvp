package android.yuchang.weathermvp.ui.managercity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.model.db.ChosenCityHelper;
import android.yuchang.weathermvp.model.entity.ChosenCityBean;
import android.yuchang.weathermvp.presenter.impl.ManagerCityPresenter;
import android.yuchang.weathermvp.ui.base.BaseActivity;
import android.yuchang.weathermvp.ui.base.BaseView;

import java.util.ArrayList;
import java.util.List;

public class ManagerCityActivity extends BaseActivity<ManagerCityPresenter> implements BaseView {

    private ImageView iv_back;
    private TextView tv_title;
    private ImageView iv_header_edit;
    private RecyclerView recyclerView;
    private ManagerCityRecyclerViewAdaptor managerCityRecyclerViewAdaptor;
    private List<ChosenCityBean> selectedBeanList = null;
    private ChosenCityHelper chosenCityHelper;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_manager_city);
    }

    @Override
    public void setUpViews() {

        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_header_edit = (ImageView) findViewById(R.id.iv_header_edit);
        tv_title.setText(getResources().getString(R.string.city_manager));
        if (null == chosenCityHelper) {
            chosenCityHelper = new ChosenCityHelper(this);
        }
        if (null == selectedBeanList) {
            selectedBeanList = new ArrayList<>();
        }
        selectedBeanList = chosenCityHelper.getSelectorBean();
        managerCityRecyclerViewAdaptor = new ManagerCityRecyclerViewAdaptor(new ManagerCityRecyclerViewAdaptor.RecyclerViewListenr() {
            @Override
            public void onViewClick(View v, int position) {
                   mPresenter.IsAddOrChosen(chosenCityHelper,editView,iv_header_edit,v,position);
            }
        }, selectedBeanList, this, new ManagerCityRecyclerViewAdaptor.MyCloseInterface() {
            @Override
            public void deleteByCityName(String cityName) {
                mPresenter.DeletedCity(chosenCityHelper,cityName,managerCityRecyclerViewAdaptor);
            }
        });
        recyclerView.setAdapter(managerCityRecyclerViewAdaptor);
    }

    @Override
    public void setUpLisener() {
        iv_back.setOnClickListener(this);
        iv_header_edit.setOnClickListener(this);
    }

    //编辑模式
    private boolean editView = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_header_edit:
                editView = !editView;
                managerCityRecyclerViewAdaptor.setShowClose(editView);
                if (editView) {
                    //进入编辑模式
                    iv_header_edit.setBackgroundResource(R.drawable.cross_selector);
                } else {
                    //推出编辑模式
                    iv_header_edit.setBackgroundResource(R.drawable.edit_selector);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.RefrushData(managerCityRecyclerViewAdaptor, chosenCityHelper);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (editView) {
                editView = !editView;
                managerCityRecyclerViewAdaptor.setShowClose(editView);
                iv_header_edit.setBackgroundResource(R.drawable.edit_selector);
                return false;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
