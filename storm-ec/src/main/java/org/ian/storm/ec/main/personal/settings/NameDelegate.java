package org.ian.storm.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.ec.R;

/**
 * Created by ian on 2017/10/10.
 */

public class NameDelegate  extends StormDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
