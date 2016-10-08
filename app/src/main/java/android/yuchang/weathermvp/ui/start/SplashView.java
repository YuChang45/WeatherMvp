package android.yuchang.weathermvp.ui.start;

import android.content.Intent;
import android.yuchang.weathermvp.ui.base.BaseView;

/**
 * @author MrChang45
 * @time 2016/9/20
 * @desc
 */
public interface SplashView extends BaseView {
    void TurnToOtherActivityByIntent(Intent intent);
}
