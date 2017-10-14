package org.ian.myec.example;

import android.app.Application;
import android.support.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.ian.myec.example.event.TestEvent;
import org.ian.storm.app.Storm;
import org.ian.storm.ec.database.DatabaseManager;
import org.ian.storm.ec.icon.FontEcModule;
import org.ian.storm.net.interceptors.DebugInterceptor;
import org.ian.storm.net.rx.AddCookieInterceptor;
import org.ian.storm.util.callback.CallbackManager;
import org.ian.storm.util.callback.CallbackType;
import org.ian.storm.util.callback.IGlobalCallback;

import cn.jpush.android.api.JPushInterface;

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
                //.withApiHost("http://192.168.8.112:8080/json/")
                .withApiHost("http://192.168.1.199:8080/json/")
               // .withApiHost("http://172.16.22.3:8080/json/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test)) //拦截器必须添加，否则会报错！这个是个坑！
                .withWeChatAppId("") //微信appid
                .withWeChatAppSecret("")//微信appsecret
                .withJavascriptInterface("storm")
                .withWebEvent("test",new TestEvent())
                //添加Cookie同步拦截器
                .withWebHost("http://www.baidu.com/")
                .withInterceptor(new AddCookieInterceptor())
                .configure();
        //initStetho();
        DatabaseManager.getInstance().init(this);//

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Storm.getApplicationContext())){
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(ExampleApp.this);
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Storm.getApplicationContext())){
                            //停止极光推送
                            JPushInterface.stopPush(ExampleApp.this);
                        }

                    }
                });

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














