package org.ian.storm.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.ec.detail.GoodsDetailDelegate;

/**
 * Created by ian on 2017/9/12.
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final StormDelegate DELEGATE;

    private IndexItemClickListener(StormDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(StormDelegate delegate){
        return new IndexItemClickListener(delegate);
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create();
        DELEGATE.start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
