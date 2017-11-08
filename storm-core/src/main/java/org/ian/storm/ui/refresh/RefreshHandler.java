package org.ian.storm.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.ian.storm.app.Storm;
import org.ian.storm.net.RestClient;
import org.ian.storm.net.callback.IError;
import org.ian.storm.net.callback.IFailure;
import org.ian.storm.net.callback.ISuccess;
import org.ian.storm.ui.recycler.DataConverter;
import org.ian.storm.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by ian on 2017/9/5.
 */

//刷新
public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener{

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView,
                          DataConverter converter, PagingBean pagingBean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = pagingBean;
        REFRESH_LAYOUT.setOnRefreshListener(this); //监听滑动事件
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView,
                                        DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());

    }

    private void refesh() {
        REFRESH_LAYOUT.setRefreshing(true); //开始加载
        Storm.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 * 此处进行某些网络请求，将下面方法放入请求的回调函数中
                 */
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }


    //取得首页展示的数据
    public void firstPage(String url) {
        BEAN.setDelayed(1000);

        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("Ian", "success");
                        final JSONObject object = JSON.parseObject(response);
                        Log.e("Ian",""+object.getInteger("total")+":"+object.getInteger("page_size"));
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.e("Ian", "failure");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        Log.e("Ian", "error");
                    }
                })
                .build()
                .get();
    }


    private void paging(final String url){
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();

        if (mAdapter.getData().size()<pageSize||currentCount>=total){
            mAdapter.loadMoreEnd(true);
        }else{
            Storm.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url(url+index)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                }
                            })
                            .build()
                            .get();
                }
            },1000);
        }
    }


    @Override
    public void onRefresh() {
        refesh();
    }

    @Override
    public void onLoadMoreRequested() {
        paging("JsonServlet?action=index_2_data&index=");

    }
}
