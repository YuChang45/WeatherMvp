package android.yuchang.weathermvp.protocol;

import android.yuchang.weathermvp.commom.utils.L;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.zip.GZIPInputStream;

/**
 * Created by MrChang45 on 2016/4/25.
 * 日志记录
 */
public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder()
                .method(request.method(), request.body());
        L.e("net", "request:" + request.toString());

        //返回最准确的可用系统计时器的当前值，以毫微秒为单位。
        //此方法只能用于测量已过的时间，与系统或钟表时间的其他任何时间概念无关。
        //返回值表示从某一固定但任意的时间算起的毫微秒数（或许从以后算起，所以该值可能为负）。
        //此方法提供毫微秒的精度，但不是必要的毫微秒的准确度。它对于值的更改频率没有作出保证。
        long t1 = System.nanoTime();
        Response response = chain.proceed(requestBuilder.build());

        long t2 = System.nanoTime();
        L.e("net", String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        MediaType mediaType = response.body().contentType();
        byte[] content = response.body().bytes();
        if (response.headers().get("Content-Encoding") != null) {
            L.e("net", "response body:" + getJsonStringFromGZIP(content));
        } else {
            L.e("net", "response body:" + new String(content));
        }
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }

    public static String getJsonStringFromGZIP(byte[] content) {
        String jsonString = null;
        try {
            InputStream is = new ByteArrayInputStream(content);
            BufferedInputStream bis = new BufferedInputStream(is);
            bis.mark(2);
            // 取前两个字节
            byte[] header = new byte[2];
            int result = bis.read(header);
            // reset输入流到开始位置
            bis.reset();
            if (result != -1) {
                is = new GZIPInputStream(bis);
            } else {
                is = bis;
            }
            InputStreamReader reader = new InputStreamReader(is, "utf-8");
            char[] data = new char[100];
            int readSize;
            StringBuffer sb = new StringBuffer();
            while ((readSize = reader.read(data)) > 0) {
                sb.append(data, 0, readSize);
            }
            jsonString = sb.toString();
            bis.close();
            reader.close();
        } catch (Exception e) {
            return "解压失败!";
        }

        return jsonString;
    }
}