package android.yuchang.weathermvp.ui.addmorecity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.presenter.impl.AddMoreCityPresenter;
import android.yuchang.weathermvp.ui.base.BaseActivity;

/***
 * 添加更多
 */
public class AddMoreCityActivity extends BaseActivity<AddMoreCityPresenter> implements AddMoreCityView {

    private ImageView iv_back;
    private TextView tv_tile;
    private ImageView iv_edit;
    private RecyclerView recyclerViewProvince;
    private RecyclerView recyclerViewCity;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_more_city);
    }

    @Override
    public void setUpViews() {
        recyclerViewProvince = (RecyclerView) findViewById(R.id.id_recyclerview);
        recyclerViewCity = (RecyclerView) findViewById(R.id.id_recyclerview2);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_tile = (TextView) findViewById(R.id.tv_title);
        iv_edit = (ImageView) findViewById(R.id.iv_header_edit);
        iv_edit.setVisibility(View.GONE);
        tv_tile.setText(R.string.more_city);
        mPresenter.buildProvinceRecyclerView(recyclerViewProvince);
        mPresenter.buildCityRecyclerView(recyclerViewCity);
    }

    @Override
    public void setUpLisener() {
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
