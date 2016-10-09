package android.yuchang.weathermvp.ui.addmorecity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.yuchang.weathermvp.R;
import android.yuchang.weathermvp.model.entity.AddMoreCityBean;
import android.yuchang.weathermvp.ui.chosencity.HotCityRecyclerViewAdaptor;

import java.util.ArrayList;
import java.util.List;


/**
 * @author MrChang
 *         created  at  2016/3/8.
 * @description
 */
public class AddMoreCityListRecyclerViewAdapotr extends RecyclerView.Adapter<AddMoreCityListRecyclerViewAdapotr.MyViewHolder> implements View.OnClickListener {

    public List<AddMoreCityBean> getData() {
        return data;
    }

    public void setData(List<AddMoreCityBean> data) {
        this.data = data;
    }

    private List<AddMoreCityBean> data = new ArrayList<>();
    private Context context;
    private HotCityRecyclerViewAdaptor.RecyclerViewListenr recyclerViewListenr;

    private int layoutId = 0;

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        v.setTag(data.get(position));
        recyclerViewListenr.onViewClick(v, position);
    }

    public AddMoreCityListRecyclerViewAdapotr(Context context, HotCityRecyclerViewAdaptor.RecyclerViewListenr recyclerViewListenr, int layoutId) {
        this.context = context;
        this.recyclerViewListenr = recyclerViewListenr;
        this.layoutId = layoutId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(layoutId, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(data.get(position).name);
        holder.ll_bg.setEnabled(data.get(position).enable);
        holder.ll_bg.setOnClickListener(this);
        holder.ll_bg.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_bg;
        TextView tv_name;

        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_01);
            ll_bg = (LinearLayout) view.findViewById(R.id.ll_01);
        }
    }
}
