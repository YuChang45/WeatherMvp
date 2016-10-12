package android.yuchang.weathermvp.protocol;

import android.yuchang.weathermvp.commom.Const;
import android.yuchang.weathermvp.commom.url.ApiUrl;
import android.yuchang.weathermvp.commom.utils.GsonHelper;
import android.yuchang.weathermvp.model.entity.WeatherBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * @author MrChang45
 * @time 2016/10/10
 * @desc
 */
public class DealHistoryProblemInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder()
                .method(request.method(), request.body());
        requestBuilder.addHeader("apikey", ApiUrl.SECRETE_STR);
        Response response = chain.proceed(requestBuilder.build());
        MediaType mediaType = response.body().contentType();
        byte[] content = response.body().bytes();
        //处理 HeWeather data service 3.0,接口历史问题
        JSONObject jb = null;
        List<WeatherBean> weatherBean = null;
        try {
            jb = new JSONObject(new String(content));
            weatherBean = GsonHelper.fromJson(jb.optString(Const.JSON_OBJ_FIRST), new TypeToken<List<WeatherBean>>() {
            });
            if(null != weatherBean) {
                Gson gson = new Gson();
                content = gson.toJson(weatherBean).getBytes();
            }
        } catch (JSONException e) {

        }

        return response.newBuilder()
                .body(ResponseBody.create(mediaType,content) )
                .build();
    }
}
