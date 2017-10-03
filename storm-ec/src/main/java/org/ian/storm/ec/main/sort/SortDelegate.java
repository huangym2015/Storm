package org.ian.storm.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.ian.storm.delegates.bottom.BottomItemDelegate;
import org.ian.storm.ec.R;
import org.ian.storm.ec.main.sort.content.ContentDelegate;
import org.ian.storm.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by ian on 2017/9/1.
 */

//分类
public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {

        return R.layout.delegate_sort;
    }

    //同类打开时，该页面已经在加载
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    //点击该页面才开始加载
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));


    }
}
