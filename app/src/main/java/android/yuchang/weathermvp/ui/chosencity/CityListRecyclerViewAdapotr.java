package android.yuchang.weathermvp.ui.chosencity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.yuchang.weathermvp.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author MrChang
 *         created  at  2016/3/8.
 * @description
 */
public class CityListRecyclerViewAdapotr extends RecyclerView.Adapter<CityListRecyclerViewAdapotr.MyViewHolder> implements View.OnClickListener {

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    private List<String> data = new ArrayList<>();
    private Context context;
    private HotCityRecyclerViewAdaptor.RecyclerViewListenr recyclerViewListenr;

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        v.setTag(data.get(position));
        recyclerViewListenr.onViewClick(v, position);
    }


    public CityListRecyclerViewAdapotr(Context context, HotCityRecyclerViewAdaptor.RecyclerViewListenr recyclerViewListenr) {
        this.context = context;
        this.recyclerViewListenr = recyclerViewListenr;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_list_view_city, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(data.get(position));
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
