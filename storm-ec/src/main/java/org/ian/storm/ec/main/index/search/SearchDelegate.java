package org.ian.storm.ec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.ec.R;
import org.ian.storm.ec.R2;
import org.ian.storm.ui.recycler.MultipleItemEntity;
import org.ian.storm.util.storage.StormPreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ian on 2017/10/22.
 */

public class SearchDelegate extends StormDelegate {

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView=null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchEdit = null;

    @OnClick(R2.id.tv_top_search)
    void onClickSearch(){
        //首页搜索框点击事件
//        RestClient.builder()
//                .url("search.php?key=")
//                .loader(getContext())
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        final String searchItemText = mSearchEdit.getText().toString();
//                        saveItem(searchItemText);
//                        mSearchEdit.setText("");
//                        //展示一些东西
//                        //弹出一段话
//                    }
//                })
//                .build()
//                .get();

        final String searchItemText = mSearchEdit.getText().toString();
        saveItem(searchItemText);
        mSearchEdit.setText("");

    }


    @OnClick(R2.id.icon_top_search_back)
    void onClickBack(){
        getSupportDelegate().pop();
    }

    @SuppressWarnings("unchecked")
    private void saveItem(String item){
        if (!StringUtils.isEmpty(item)&&!StringUtils.isSpace(item)){
            List<String> history;
            final String historyStr =
                    StormPreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);
            if (StringUtils.isEmpty(historyStr)){
                history = new ArrayList<>();
            }else {
                history = JSON.parseObject(historyStr,ArrayList.class);
            }
            history.add(item);
            final String json = JSON.toJSONString(history);

            StormPreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY,json);

        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_search;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //mSearchEdit.setFocusable(true); //打开此页面时，文本框优先获得焦点,此方法无用，通过布局文件,控件后申明<requestFocus/>
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data = new SearchDataConverter().convert();
        final SearchAdapter adapter = new SearchAdapter(data);
        mRecyclerView.setAdapter(adapter);

        //分割线
        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2)
                        .margin(20,20)
                        .color(Color.GRAY)
                        .build();
            }
        });
        mRecyclerView.addItemDecoration(itemDecoration);
    }
}
