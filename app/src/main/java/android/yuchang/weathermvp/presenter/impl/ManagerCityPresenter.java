package android.yuchang.weathermvp.presenter.impl;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.commom.Const;
import android.yuchang.weathermvp.model.IChosenCityBean;
import android.yuchang.weathermvp.model.entity.ChosenCityBean;
import android.yuchang.weathermvp.presenter.base.BasePresenter;
import android.yuchang.weathermvp.ui.chosencity.ChosenCityActivity;
import android.yuchang.weathermvp.ui.main.MainActivity;
import android.yuchang.weathermvp.ui.managercity.ManagerCityRecyclerViewAdaptor;
import android.yuchang.weathermvp.widget.sweetdialog.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MrChang45
 * @time 2016/10/10
 * @desc
 */
public class ManagerCityPresenter extends BasePresenter {

    private List<ChosenCityBean> selectedBeanList = null;


    public void IsAddOrChosen(IChosenCityBean chosenCityHelper, Boolean editView, ImageView iv_header_edit, View view, int position) {
        if (!editView) {
            iv_header_edit.setBackgroundResource(R.drawable.edit_selector);
            ImageView iv = (ImageView) view.findViewById(R.id.iv_add);
            if (iv.getVisibility() == View.VISIBLE) {
                mIntent = new Intent(activity, ChosenCityActivity.class);
                activity.startActivity(mIntent);
            } else {
                ChosenCityBean selectedBean = selectedBeanList.get(position);
                chosenCityHelper.UpdateInfo(selectedBean.getCityName());
                mIntent = new Intent(activity, MainActivity.class);
                activity.startActivity(mIntent);
                activity.finish();
            }
            iv = null;
        }
    }

    public void DeletedCity(IChosenCityBean chosenCityHelper, String cityName, ManagerCityRecyclerViewAdaptor managerCityRecyclerViewAdaptor) {

        if (!chosenCityHelper.hasOnlyOneSelectedCity()) {

            if (chosenCityHelper.DeletedByCityName(cityName) == Const.DELETE_ERRO) {

                Toast.makeText(activity, "删除失败!请重试!", Toast.LENGTH_SHORT).show();

            } else {

                ChosenCityBean selectedBean = new ChosenCityBean();
                selectedBean.setCityName(cityName);
                int position = selectedBeanList.indexOf(selectedBean);
                managerCityRecyclerViewAdaptor.removeData(position);

            }

        } else {

            //只剩最后一个城市，不得删除，业务逻辑为 至少保留一个城市
            new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(activity.getResources().getString(R.string.notice_str))
                    .setContentText(activity.getResources().getString(R.string.at_leaste_save_one_city))
                    .setConfirmText(activity.getResources().getString(R.string.i_know_str))
                    .showCancelButton(true)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
        }
    }


    /***
     * 刷新数据
     */
    public void RefrushData(ManagerCityRecyclerViewAdaptor managerCityRecyclerViewAdaptor, IChosenCityBean chosenCityHelper) {

        if (null == selectedBeanList) {
            selectedBeanList = new ArrayList<>();
        }
        selectedBeanList = chosenCityHelper.getSelectorBean();
        managerCityRecyclerViewAdaptor.setData(selectedBeanList);
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
