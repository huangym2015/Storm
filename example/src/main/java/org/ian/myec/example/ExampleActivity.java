package org.ian.myec.example;

import org.ian.storm.activites.ProxyActivity;
import org.ian.storm.delegates.StormDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public StormDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
