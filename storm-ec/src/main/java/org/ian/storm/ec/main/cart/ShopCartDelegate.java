package org.ian.storm.ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.joanzapata.iconify.widget.IconTextView;

import org.ian.storm.delegates.bottom.BottomItemDelegate;
import org.ian.storm.ec.R;
import org.ian.storm.ec.R2;
import org.ian.storm.ec.pay.FastPay;
import org.ian.storm.ec.pay.IAlPayResultListener;
import org.ian.storm.net.RestClient;
import org.ian.storm.net.callback.ISuccess;
import org.ian.storm.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ian on 2017/9/1.
 */

//首页
public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, ICartItemListener, IAlPayResultListener {

    private ShopCartAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    private View stubView = null;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_selct_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;

    @OnClick(R2.id.icon_shop_cart_selct_all)
    void onClickSelectAll() {
        final int tag = (int) mIconSelectAll.getTag();
        if (mAdapter != null) {
            if (tag == 0) {
                mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
                mIconSelectAll.setTag(1);
                mAdapter.setIsSelectedAll(true);
                //更新RecyclerView的显示状态
                mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
                //mAdapter.notifyDataSetChanged(); 同时更新所有的，影响性能
            } else {
                mIconSelectAll.setTextColor(Color.GRAY);
                mIconSelectAll.setTag(0);
                mAdapter.setIsSelectedAll(false);
                mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            }
        }
    }


    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        if (mAdapter != null) {
            final List<MultipleItemEntity> data = mAdapter.getData();
            //要删除的数据
            final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
            int i = 0;
            for (MultipleItemEntity entity : data) {
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                entity.setField(ShopCartItemFields.POSITION, i); //每次重新赋值位置
                if (isSelected) {
                    deleteEntities.add(entity);
                }
                i++;
            }
            //从List中最后一个开始删除，不影响整个下标
            for (int j = deleteEntities.size() - 1; j >= 0; j--) {
                int removePosition = deleteEntities.get(j).getField(ShopCartItemFields.POSITION);
                mAdapter.remove(removePosition); //remove方法内部调用notifyItemRangeChanged
            }
            checkItemCount();
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void OnClickClear() {
        if (mAdapter != null) {
            mAdapter.getData().clear();
            mAdapter.notifyDataSetChanged();
            checkItemCount();
        }
    }

    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        // FastPay.create(this).beginPayDialog();
        createOrder();
    }

    //创建订单，注意 和支付没有关系
    private void createOrder() {
        final String orderUrl = "JsonServlet?action=shop_cart_pay";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        orderParams.put("userid", "3");
        orderParams.put("amount", 0.01);
        orderParams.put("comment", "测试支付");
        orderParams.put("type", 1);
        orderParams.put("ordertype", 0);
        RestClient.builder()
                .url(orderUrl)
                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                        Log.d("ORDER", response);
                        final int orderId = JSON.parseObject(response).getInteger("result");
                        FastPay.create(ShopCartDelegate.this)
                                .setPayResultListener(ShopCartDelegate.this)
                                .setOrderId(orderId)
                                .beginPayDialog();

                    }
                })
                .build()
                .post();
    }

    //重新计算价格，并且显示
    public void refreshShowPrice() {
        mAdapter.calcSumPrice(mAdapter.getData());
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            if (stubView == null) {
                stubView = mStubNoItem.inflate();
            }
            final AppCompatTextView tvToBuy =
                    (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "你该购物了", Toast.LENGTH_LONG).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
        refreshShowPrice();      //重新计算价格，并且显示
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("JsonServlet?action=shop_cart_data")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter()
                        .setJsonData(response)
                        .convert();
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setCartItemListener(this);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //全选，全不选，会由闪烁的问题，是因为RecyclerView的动画造成的，下面一句，可以消除闪烁
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mRecyclerView.setAdapter(mAdapter);
//        mTotalPrice = mAdapter.getTotalPrice();
//        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));

    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}




