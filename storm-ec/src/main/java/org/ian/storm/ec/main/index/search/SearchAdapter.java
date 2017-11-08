package org.ian.storm.ec.main.index.search;

import android.support.v7.widget.AppCompatTextView;

import org.ian.storm.ec.R;
import org.ian.storm.ui.recycler.MultipleFields;
import org.ian.storm.ui.recycler.MultipleItemEntity;
import org.ian.storm.ui.recycler.MultipleRecyclerAdapter;
import org.ian.storm.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by ian on 2017/10/22.
 */

public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH,R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()){
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = holder.getView(R.id.tv_search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;

        }
    }
}
