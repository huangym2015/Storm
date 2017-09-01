package org.ian.storm.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.ian.storm.delegates.bottom.BottomItemDelegate;
import org.ian.storm.ec.R;

/**
 * Created by ian on 2017/9/1.
 */

//首页
public class CartDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
