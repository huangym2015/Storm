package org.ian.storm.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.ec.R;

/**
 * Created by ian on 2017/9/21.
 */

public class DiscoverDelegate extends StormDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
