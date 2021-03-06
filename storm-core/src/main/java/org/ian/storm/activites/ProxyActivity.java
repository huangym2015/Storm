package org.ian.storm.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import org.ian.storm.R;
import org.ian.storm.delegates.StormDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by ian on 2017/8/16.
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract StormDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);

        setContentView(container);

        if(savedInstanceState ==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
