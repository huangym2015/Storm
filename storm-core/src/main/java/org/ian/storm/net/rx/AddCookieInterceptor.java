package org.ian.storm.net.rx;

import org.ian.storm.util.storage.StormPreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ian on 2017/9/28.
 */

public  final class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(StormPreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String cookie) throws Exception {
                        //  给原生API请求附带上WebView拦截下来的Cookie
                        builder.addHeader("Cookie",cookie);

                    }
                });
        return chain.proceed(builder.build());
    }
}














