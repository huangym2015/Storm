package org.ian.storm.delegates.web;

import com.alibaba.fastjson.JSON;


/**
 * Created by ian on 2017/9/21.
 */

//原生与web进行交互
public class StormWebInterface {
    private final WebDelegate DELEGATE;

    public StormWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

   static StormWebInterface create(WebDelegate delegate){
        return new StormWebInterface(delegate);
    }

    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        return action;
    }
}
