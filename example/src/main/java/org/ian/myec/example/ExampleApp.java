package org.ian.myec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.ian.myec.example.event.TestEvent;
import org.ian.storm.app.Storm;
import org.ian.storm.ec.database.DatabaseManager;
import org.ian.storm.ec.icon.FontEcModule;
import org.ian.storm.net.interceptors.DebugInterceptor;

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
                .withApiHost("http://192.168.1.199:8080/json/")
               // .withApiHost("http://172.16.22.3:8080/json/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test)) //拦截器必须添加，否则会报错！这个是个坑！
                .withWeChatAppId("") //微信appid
                .withWeChatAppSecret("")//微信appsecret
                .withJavascriptInterface("storm")
                .withWebEvent("test",new TestEvent())
                .configure();
        //initStetho();
        DatabaseManager.getInstance().init(this);//

    }

    //原生渲染成dom
    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        );
    }
}














