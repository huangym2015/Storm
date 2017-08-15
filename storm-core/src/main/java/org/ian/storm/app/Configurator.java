package org.ian.storm.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ian on 2017/8/15.
 */

public class Configurator {

    private static final HashMap<String,Object> STORM_CONFIGS = new HashMap<>();  //存放APP全局的配置信息
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>(); //存放图标字体

    private Configurator(){
        STORM_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<String,Object> getStormConfig(){
        return STORM_CONFIGS;
    }
    private static  class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure(){
        initIcons();
        STORM_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String host){
        STORM_CONFIGS.put(ConfigType.API_HOST.name(),host);
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

    //进行设置检测
    private void checkConfiguration(){
        final boolean isReady = (boolean) STORM_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }


    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) STORM_CONFIGS.get(key);
    }


}
