package org.ian.storm.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ian.storm.activites.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by ian on 2017/8/16.
 */

public abstract class BaseDelegate extends SwipeBackFragment {



    @SuppressWarnings("SpellCheckingInspection")
    public Unbinder mUnbinder = null;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = null;
        final View rootView;
        if(setLayout() instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if (setLayout() instanceof View){
            rootView = (View) setLayout();
        }else {
            //如果不是int或view会抛出异常
            throw new ClassCastException("setLayout() type must be int or View");
        }
        mUnbinder = ButterKnife.bind(this,rootView);
        onBindView(savedInstanceState,rootView);

        return rootView;
    }


    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder !=null){
            mUnbinder.unbind();
        }
    }
}
