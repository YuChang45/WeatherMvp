package android.yuchang.weathermvp.ui.chosencity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.yuchang.weathermvp.R;

import java.util.List;


/**
 * @author MrChang
 *         created  at  2016/3/9.
 * @description
 */
public class HotCityRecyclerViewAdaptor extends RecyclerView.Adapter<HotCityRecyclerViewAdaptor.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private RecyclerViewListenr recyclerViewListenr;
    private List<String> data;

    public HotCityRecyclerViewAdaptor(Context context, RecyclerViewListenr recyclerViewListenr) {
        this.context = context;
        this.recyclerViewListenr = recyclerViewListenr;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        v.setTag(data.get(position));
        this.recyclerViewListenr.onViewClick(v, position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.hot_city_grid_view_itmes, null));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_cityName.setText(data.get(position));
        holder.ll_01.setOnClickListener(this);
        holder.ll_01.setTag(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface RecyclerViewListenr {

        void onViewClick(View v, int position);
    }

    static final class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_cityName;
        LinearLayout ll_01;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_cityName = (TextView) itemView.findViewById(R.id.tv_01);
            ll_01 = (LinearLayout) itemView.findViewById(R.id.ll_01);
        }

    }

}
