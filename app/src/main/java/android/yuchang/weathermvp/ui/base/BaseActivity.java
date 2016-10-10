package android.yuchang.weathermvp.ui.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;
import android.yuchang.weathermvp.commom.utils.TUtil;
import android.yuchang.weathermvp.presenter.base.BasePresenter;

import rx.subscriptions.CompositeSubscription;

/***
 * @author MrChang
 *         create at 2015-12-08
 * @description 公共activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends FragmentActivity implements View.OnClickListener {

    protected Intent mIntent;
    public T mPresenter;
    //管理异步处理与Activity生命周期,避免出现内存泄漏
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter == null) {
            throw new IllegalArgumentException("Presenter can not be null ");
        }
        if (this instanceof BaseView) {
            mPresenter.setView(this, this, mCompositeSubscription);
        } else {
            throw new IllegalArgumentException("Activity must implemt BaseView ");
        }
        initContentView(savedInstanceState);
        //强制设置为全局竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setUpViews();
        setUpLisener();
        getDataOnCreate();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
        mPresenter.onDestory();
    }


    /**
     * 长时间显示Toast提示(来自String)
     *
     * @param message
     */
    public void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     *
     * @param resId
     */
    public void showLongToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 短暂显示Toast提示(来自res)
     *
     * @param resId
     */
    protected void showShortToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     *
     * @param text
     */
    protected void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * setContentView
     *
     * @param savedInstanceState
     */
    protected abstract void initContentView(Bundle savedInstanceState);

    /***
     * 初始化试图
     */
    public abstract void setUpViews();

    /***
     * 注册事件
     */
    public abstract void setUpLisener();

    /**
     * 加载数据
     */
    public void getDataOnCreate() {

    }
}
