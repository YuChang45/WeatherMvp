package android.yuchang.weathermvp.ui.chosencity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.presenter.impl.ChosenCityPresenter;
import android.yuchang.weathermvp.ui.base.BaseActivity;

import java.util.List;

/***
 * 选择城市
 */
public class ChosenCityActivity extends BaseActivity<ChosenCityPresenter> implements ChosenCityView, HotCityRecyclerViewAdaptor.RecyclerViewListenr {

    private ImageView iv_back;
    private ImageView iv_edit;
    private TextView tv_title;
    private RecyclerView recyclerView;
    private TextView tv_add_more;
    private HotCityRecyclerViewAdaptor hotCityRecyclerViewAdaptor;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_city);
    }

    @Override
    public void setUpViews() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_edit = (ImageView) findViewById(R.id.iv_header_edit);
        tv_title = (TextView) findViewById(R.id.tv_title);
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        tv_add_more = (TextView) findViewById(R.id.tv_01);
        tv_title.setText(getResources().getString(R.string.choose_city));
        iv_edit.setVisibility(View.GONE);
        hotCityRecyclerViewAdaptor = new HotCityRecyclerViewAdaptor(this, this);
        recyclerView.setAdapter(hotCityRecyclerViewAdaptor);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @Override
    public void setUpLisener() {
        iv_back.setOnClickListener(this);
        tv_add_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mPresenter.ConvertClick(v.getId());
    }

    @Override
    public void onViewClick(View v, int position) {
        showShortToast(v.getTag().toString());
    }

    @Override
    public void FillHotCityRecyclerView(List<String> data) {
        hotCityRecyclerViewAdaptor.setData(data);
    }


}
