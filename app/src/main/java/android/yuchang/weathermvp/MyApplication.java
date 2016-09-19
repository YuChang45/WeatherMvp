package android.yuchang.weathermvp;

import android.app.Application;

/**
 * @author MrChang
 *         created  at  2016/1/12.
 * @description
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static MyApplication GetInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
