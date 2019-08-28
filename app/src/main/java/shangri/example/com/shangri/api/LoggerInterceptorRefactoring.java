package shangri.example.com.shangri.api;

import android.util.Log;


import shangri.example.com.shangri.util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by zhy on 16/3/1.
 */
public class LoggerInterceptorRefactoring implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return logForResponse(request,response);
    }
    private Response logForResponse(Request request, Response response)
    {
        try
        {
            String url = request.url().toString();

            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            ResponseBody body = clone.body();
            String resp = body.string();

            Log.e("requestBody" ,url+"   "+bodyToString(request));
            LogUtil.e("responseBody",resp);

            MediaType mediaType = body.contentType();
            body = ResponseBody.create(mediaType, resp);
            return response.newBuilder().body(body).build();

    } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    private String bodyToString(final Request request)
    {
        try
        {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e)
        {
            return "something error when show requestBody.";
        }
    }
}
