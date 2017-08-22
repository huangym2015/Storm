package org.ian.storm.net;


import org.ian.storm.app.ConfigKeys;
import org.ian.storm.app.Storm;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ian on 2017/8/19.
 */

public class RestCreator {

    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static final WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }


    //构建全局retrof
    private static final class RetrofitHolder {
        private static final String BASE_URL = Storm.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        //取得拦截器，若不定义，此处会抛 interceptor is NULL的异常
        private static final ArrayList<Interceptor> INTERCEPTORS = Storm.getConfiguration(ConfigKeys.INTERCEPTOR);

        //添加初始化拦截器
        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);

                }
            }
            return BUILDER;
        }


        // private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder() 可以直接使用已生成的client
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


}





