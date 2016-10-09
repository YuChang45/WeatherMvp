package android.yuchang.weathermvp.presenter.impl;

import android.content.Intent;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.model.IHotCityBean;
import android.yuchang.weathermvp.model.db.ChosenCityHelper;
import android.yuchang.weathermvp.model.impl.HotCityBeanImpl;
import android.yuchang.weathermvp.presenter.base.BasePresenter;
import android.yuchang.weathermvp.ui.addmorecity.AddMoreCityActivity;
import android.yuchang.weathermvp.ui.chosencity.ChosenCityView;
import android.yuchang.weathermvp.widget.sweetdialog.SweetAlertDialog;

import java.util.List;

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
    private ChosenCityHelper chosenCityHelper;

    public void ConvertClick(int action) {
        switch (action) {
            //退出
            case R.id.iv_back:
                //判断是否至少存在一个城市，不存在不允许退出
                if (chosenCityHelper.hasSelectedCity()) {
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



    @Override
    public void onStart() {
        iHotCityBean = new HotCityBeanImpl();
        data = iHotCityBean.GetHotCity(activity);
        chosenCityView = (ChosenCityView) mView;
        chosenCityView.FillHotCityRecyclerView(data);
        chosenCityHelper = new ChosenCityHelper(activity);
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
