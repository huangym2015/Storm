package org.ian.storm.net.interceptors;

import android.support.annotation.RawRes;
import android.util.Log;

import org.ian.storm.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ian on 2017/8/22.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawid) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawid;
    }


    //请求的返回方法
    private Response getResponse(Chain chain, String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }


    private Response debugResponse(Chain chain,@RawRes int rawId){ //使用注解 检查类型
        final String json = FileUtil.getRawFile(rawId);
        return  getResponse(chain,json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        Log.e("Ian",url);
        if (url.contains(DEBUG_URL)) {  //如果包含关键字，返回相应的数据
            return debugResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());  //不包含关键字，原样返回
    }
}
















