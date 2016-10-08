package android.yuchang.weathermvp.ui.chosencity;

import android.yuchang.weathermvp.ui.base.BaseView;

import java.util.List;

/**
 * @author MrChang45
 * @time 2016/9/20
 * @desc
 */
public interface ChosenCityView extends BaseView {

    void FillHotCityRecyclerView(List<String> data);

}
