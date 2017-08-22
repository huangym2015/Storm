package org.ian.storm.app;

import android.content.Context;

/**
 * Created by ian on 2017/8/15.
 */

public final class Storm {
    public static Configurator init(Context context){
        //getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        Configurator.getInstance().getStormConfig().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

//    public static HashMap<Object,Object> getConfigurations(){
//        return Configurator.getInstance().getStormConfig();
//    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext(){
        //return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}





















