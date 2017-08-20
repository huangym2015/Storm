package org.ian.myec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.ian.storm.app.Storm;
import org.ian.storm.ec.icon.FontEcModule;

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
                .configure();
    }


}
