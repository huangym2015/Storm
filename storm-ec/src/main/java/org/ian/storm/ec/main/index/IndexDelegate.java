package org.ian.storm.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.ian.storm.delegates.bottom.BottomItemDelegate;
import org.ian.storm.ec.R;

/**
 * Created by ian on 2017/9/1.
 */

//首页
public class IndexDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
