package android.yuchang.weathermvp.presenter.base;

import android.app.Activity;
import android.content.Intent;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author MrChang45
 * @time 2016/8/24
 * @desc
 */
public abstract class BasePresenter<T> {

    public Intent mIntent;
    public Activity activity;
    //视图层引用
    public T mView;
    //在activity中或者fragment中构建的
    private CompositeSubscription mCompositeSubscription;

    public void setView(T v, Activity activity, CompositeSubscription mCompositeSubscription) {

        this.activity = activity;
        this.mView = v;
        this.mCompositeSubscription = mCompositeSubscription;

    }

    protected void addSubscription(Subscription s) {
        this.mCompositeSubscription.add(s);
    }

    public abstract void onStart();
    public abstract void onResume();
    public abstract void onPause();
    public abstract void onDestory();

}
