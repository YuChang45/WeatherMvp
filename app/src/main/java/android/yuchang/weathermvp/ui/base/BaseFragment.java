package android.yuchang.weathermvp.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.yuchang.weathermvp.commom.utils.TUtil;
import android.yuchang.weathermvp.presenter.base.BasePresenter;

import rx.subscriptions.CompositeSubscription;

/**
 * @author MrChang45
 * @time 2016/10/10
 * @desc
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected Intent mIntent;
    public T mPresenter;
    //管理异步处理与Activity生命周期,避免出现内存泄漏
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(initContentView(savedInstanceState), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        if (mPresenter == null) {
            throw new IllegalArgumentException("Presenter can not be null ");
        }
        if (this instanceof BaseView) {
            mPresenter.setView(this, getActivity(), mCompositeSubscription);
        } else {
            throw new IllegalArgumentException("Fragment must implemt BaseView ");
        }
        setUpViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpLisener();
    }


    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onDestroy() {
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     *
     * @param resId
     */
    public void showLongToast(int resId) {
        Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 短暂显示Toast提示(来自res)
     *
     * @param resId
     */
    protected void showShortToast(int resId) {
        Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     *
     * @param text
     */
    protected void showShortToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * setContentView
     *
     * @param savedInstanceState
     */
    protected abstract int initContentView(Bundle savedInstanceState);

    /***
     * 初始化试图
     */
    public abstract void setUpViews(View view);

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
