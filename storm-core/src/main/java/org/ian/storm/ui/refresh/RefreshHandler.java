package org.ian.storm.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import org.ian.storm.app.Storm;

/**
 * Created by ian on 2017/9/5.
 */

//刷新
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final  SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        REFRESH_LAYOUT.setOnRefreshListener(this); //监听滑动事件
    }

    private  void refesh(){
        REFRESH_LAYOUT.setRefreshing(true); //开始加载
        Storm.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 * 此处进行某些网络请求，将下面方法放入请求的回调函数中
                 */
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }
    @Override
    public void onRefresh() {
        refesh();
    }
}
