package org.ian.storm.ec.main.index.search;

import com.alibaba.fastjson.JSONArray;

import org.ian.storm.ui.recycler.DataConverter;
import org.ian.storm.ui.recycler.MultipleFields;
import org.ian.storm.ui.recycler.MultipleItemEntity;
import org.ian.storm.util.storage.StormPreference;

import java.util.ArrayList;

/**
 * Created by ian on 2017/10/22.
 */

public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr = StormPreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")){
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText  = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT,historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }

        return ENTITIES;
    }
}
