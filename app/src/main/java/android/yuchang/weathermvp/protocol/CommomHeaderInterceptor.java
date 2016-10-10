package android.yuchang.weathermvp.protocol;

import android.yuchang.weathermvp.commom.url.ApiUrl;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

/**
 * @author MrChang45
 * @time 2016/10/10
 * @desc
 */
public class CommomHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder()
                .method(request.method(), request.body());
        requestBuilder.addHeader("apikey", ApiUrl.SECRETE_STR);
        Response response = chain.proceed(requestBuilder.build());
        MediaType mediaType = response.body().contentType();
        byte[] content = response.body().bytes();
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }
}
