package org.ian.storm.app;

import android.app.Activity;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by ian on 2017/8/15.
 */

public class Configurator {

    private static final HashMap<Object,Object> STORM_CONFIGS = new HashMap<>();  //存放APP全局的配置信息
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>(); //存放图标字体

    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();  //拦截器

    private Configurator(){
        STORM_CONFIGS.put(ConfigKeys.CONFIG_READY,false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<Object,Object> getStormConfig(){
        return STORM_CONFIGS;
    }
    private static  class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure(){
        initIcons();
        STORM_CONFIGS.put(ConfigKeys.CONFIG_READY,true);
    }

    public final Configurator withApiHost(String host){
        STORM_CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }

    private void initIcons(){
        if(ICONS.size() >0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i= 1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }


    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        STORM_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        STORM_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }


    public final Configurator withWeChatAppId(String appId){
        STORM_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID,appId);
        return this;
    }
    public final Configurator withWeChatAppSecret(String appSecret){
        STORM_CONFIGS.put(ConfigKeys.WA_CHAT_APP_SECRET,appSecret);
        return this;
    }
    public final Configurator withActivity(Activity  activity){
        STORM_CONFIGS.put(ConfigKeys.ACTIVITY,activity);
        return this;
    }

    //进行设置检测
    private void checkConfiguration(){
        final boolean isReady = (boolean) STORM_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

//    该方法不够通用
//    @SuppressWarnings("unchecked")
//    final <T> T getConfiguration(Enum<ConfigKeys> key){
//        checkConfiguration();
//        return (T) STORM_CONFIGS.get(key);
//    }

    @SuppressWarnings("unused")
    final <T> T getConfiguration(Object key){
        checkConfiguration();
        final Object value = STORM_CONFIGS.get(key);
        if (value ==null){
            throw new NullPointerException(key.toString()+" IS NULL");
        }
        return (T) STORM_CONFIGS.get(key);
    }

}
