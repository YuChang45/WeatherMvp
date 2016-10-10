package android.yuchang.weathermvp.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * @author MrChang
 *         created  at  2016/1/19.
 * @description
 */
public class YCViewPager extends ViewPager {

    private YCCircleFlowIndicator ycCircleFlowIndicator;

    public YCViewPager(Context context) {
        super(context);
    }

    public YCViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*
    * @param l Current horizontal scroll origin. 水平滚动原点
    * @param t Current vertical scroll origin.
    * @param oldl Previous horizontal scroll origin.
    * @param oldt Previous vertical scroll origin. 以前的水平滚动原点
    * */
    private int preIndex = 1;
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(preIndex != (getCurrentItem()+1))
        {
            preIndex = (getCurrentItem()+1);
        }
        int hPerceived = l + (preIndex - (getCurrentItem()+1))
                * getWidth();
        ycCircleFlowIndicator.onScrolled(hPerceived, t, oldl, oldt);
    }

    public YCCircleFlowIndicator getYcCircleFlowIndicator() {
        return ycCircleFlowIndicator;
    }

    public void setYcCircleFlowIndicator(YCCircleFlowIndicator ycCircleFlowIndicator) {
        this.ycCircleFlowIndicator = ycCircleFlowIndicator;
        ycCircleFlowIndicator.setViewFlow(this);
    }

}
