package org.ian.storm.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.delegates.bottom.BottomItemDelegate;
import org.ian.storm.delegates.web.WebDelegate;
import org.ian.storm.delegates.web.WebDelegateImpl;
import org.ian.storm.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ian on 2017/9/21.
 */

public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("index.html");
        loadRootFragment(R.id.web_discovery_container,delegate);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator(); //切换fragment时横向动画
    }
}
