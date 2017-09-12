package org.ian.storm.ui.recycler;

import java.util.ArrayList;

/**
 * Created by ian on 2017/9/10.
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;
    public abstract ArrayList<MultipleItemEntity> convert();
    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }

    public String getmJsonData(){
        if (mJsonData == null) {
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }
}
