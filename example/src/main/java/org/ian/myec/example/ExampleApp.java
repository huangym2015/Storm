package org.ian.myec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.ian.storm.app.Storm;
import org.ian.storm.ec.icon.FontEcModule;
import org.ian.storm.net.interceptors.DebugInteceptor;

/**
 * Created by ian on 2017/8/15.
 */

public class ExampleApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Storm.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInteceptor("index",R.raw.test)) //拦截器必须添加，否则会报错！这个是个坑！
                .configure();
    }


}














