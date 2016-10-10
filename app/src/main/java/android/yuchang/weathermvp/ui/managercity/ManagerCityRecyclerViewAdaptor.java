package android.yuchang.weathermvp.ui.managercity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.commom.Const;
import android.yuchang.weathermvp.commom.url.ApiUrl;
import android.yuchang.weathermvp.commom.utils.WeatherIcoHelper;
import android.yuchang.weathermvp.model.entity.ChosenCityBean;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * @author MrChang
 *         created  at  2016/3/9.
 * @description
 */
public class ManagerCityRecyclerViewAdaptor extends RecyclerView.Adapter<ManagerCityRecyclerViewAdaptor.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private List<ChosenCityBean> data;
    private RecyclerViewListenr recyclerViewListenr;

    @Override
    public void onClick(View v) {
        recyclerViewListenr.onViewClick(v, (Integer) v.getTag());
    }

    public void removeData(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public List<ChosenCityBean> getData() {
        return data;
    }

    public void setData(List<ChosenCityBean> data) {

        if (null == data || data.size() < Const.MAX_CITYS) {
            ChosenCityBean selectedBean = new ChosenCityBean();
            selectedBean.setAdd(true);
            data.add(selectedBean);
        }
        this.data = data;
        notifyDataSetChanged();
    }

    private ChosenCityBean selectedBean = null;
    private boolean isShowClose = false;

    private MyCloseInterface myCloseInterface;

    public interface MyCloseInterface {
        void deleteByCityName(String cityName);
    }
    public interface RecyclerViewListenr {
        void onViewClick(View v,int position);
    }

    public ManagerCityRecyclerViewAdaptor(RecyclerViewListenr recyclerViewListenr, List<ChosenCityBean> insertData, Context context, MyCloseInterface myCloseInterface) {
        if (null == insertData) {
            insertData = new ArrayList<>();
            ChosenCityBean selectedBean = new ChosenCityBean();
            selectedBean.setAdd(true);
            insertData.add(selectedBean);
        } else {
            if (insertData.size() < Const.MAX_CITYS) {
                ChosenCityBean selectedBean = new ChosenCityBean();
                selectedBean.setAdd(true);
                insertData.add(selectedBean);
            }
        }
        data = insertData;
        this.context = context;
        this.myCloseInterface = myCloseInterface;
        this.recyclerViewListenr = recyclerViewListenr;
    }

    @Override
    public ManagerCityRecyclerViewAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ManagerCityRecyclerViewAdaptor.MyViewHolder holder = new ManagerCityRecyclerViewAdaptor.MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.items_city_gridview, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ManagerCityRecyclerViewAdaptor.MyViewHolder viewHolder, int position) {

        if (data == null) {
            selectedBean = null;
        } else {
            selectedBean = data.get(position);
        }

        viewHolder.iv_close.setTag(selectedBean.getCityName());
        viewHolder.ll_01.setOnClickListener(this);
        viewHolder.ll_01.setTag(position);
        if (data != null) {
            if (selectedBean.isAdd()) {
                //显示add
                viewHolder.fl_01.setVisibility(View.GONE);
                viewHolder.iv_add.setVisibility(View.VISIBLE);

            } else {

                viewHolder.fl_01.setVisibility(View.VISIBLE);
                viewHolder.iv_add.setVisibility(View.GONE);
                if (isShowClose) {
                    //显示叉
                    viewHolder.iv_close.setVisibility(View.VISIBLE);
                    viewHolder.iv_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myCloseInterface.deleteByCityName(v.getTag().toString());
                        }
                    });
                } else {
                    //不显示叉
                    viewHolder.iv_close.setVisibility(View.GONE);
                }
                //第一个默认为选中项
                if (position == 0) {
                    viewHolder.iv_selected.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.iv_selected.setVisibility(View.INVISIBLE);
                }

                int weatherIcoResourceId = WeatherIcoHelper.changeWeatherCodeToIcoResourceId(selectedBean.getTemperatureCode() + "", false);
                if (weatherIcoResourceId != -1) {
                    viewHolder.iv_weather_ico.setBackgroundResource(weatherIcoResourceId);
                } else {
                    Picasso.with(context).load(ApiUrl.IMAGE_URL_PRE + selectedBean.getTemperatureCode() + Const.IMAGE_HZ)
                            .into(viewHolder.iv_weather_ico);
                }

                viewHolder.tv_city_name.setText(selectedBean.getCityName());
                viewHolder.tv_dec.setText(selectedBean.getTemperatureStr());
                viewHolder.tv_max.setText(selectedBean.getMaxTemperature() + Const.TEMPRATURE_SUFFIXS);
                viewHolder.tv_min.setText(selectedBean.getMinTemperature() + Const.TEMPRATURE_SUFFIXS);
            }
        } else {
            //显示add
            if (position < 5) {
                viewHolder.fl_01.setVisibility(View.GONE);
                viewHolder.iv_add.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public boolean isShowClose() {
        return isShowClose;
    }

    public void setShowClose(boolean showClose) {
        isShowClose = showClose;
        notifyDataSetChanged();
    }

    static final class MyViewHolder extends RecyclerView.ViewHolder {
        FrameLayout fl_01;
        ImageView iv_selected;
        ImageView iv_weather_ico;
        TextView tv_max;
        TextView tv_min;
        TextView tv_dec;
        TextView tv_city_name;
        ImageView iv_add;
        ImageView iv_close;

        LinearLayout ll_01;

        public MyViewHolder(View view) {
            super(view);
            ll_01 = (LinearLayout) view.findViewById(R.id.ll_01);
            fl_01 = (FrameLayout) view.findViewById(R.id.fl_01);
            iv_selected = (ImageView) view.findViewById(R.id.iv_03);
            iv_weather_ico = (ImageView) view.findViewById(R.id.iv_01);
            tv_max = (TextView) view.findViewById(R.id.tv_01);
            tv_min = (TextView) view.findViewById(R.id.tv_02);
            tv_dec = (TextView) view.findViewById(R.id.tv_03);
            tv_city_name = (TextView) view.findViewById(R.id.tv_04);
            iv_add = (ImageView) view.findViewById(R.id.iv_add);
            iv_close = (ImageView) view.findViewById(R.id.iv_02);
        }
    }
}
