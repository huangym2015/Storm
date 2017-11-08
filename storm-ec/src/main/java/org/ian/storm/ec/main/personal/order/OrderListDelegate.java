package org.ian.storm.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.ec.R;
import org.ian.storm.ec.R2;
import org.ian.storm.ec.main.personal.PersonalDelegate;
import org.ian.storm.net.RestClient;
import org.ian.storm.net.callback.ISuccess;
import org.ian.storm.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ian on 2017/10/10.
 */

public class OrderListDelegate extends StormDelegate {

    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView =null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        RestClient.builder()
                .loader(getContext())
                .url("JsonServlet?action=order_list")
                .params("type",mType)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(response).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));

                    }
                })
                .build()
                .get();
    }
}
