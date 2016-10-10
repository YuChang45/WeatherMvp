package android.yuchang.weathermvp.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * @author MrChang
 *         created  at  2016/1/21.
 * @description
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<WeatherFragment> list;

    public List<WeatherFragment> getList() {
        return list;
    }

    public void setList(List<WeatherFragment> list) {
        this.list = list;
    }

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        int hsCode = list.get(position).hashCode();
        return hsCode;
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }

}
