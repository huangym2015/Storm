package org.ian.myec.example.event;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.ian.storm.delegates.web.event.Event;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by ian on 2017/10/19.
 */

public class ShareEvent extends Event {


    @Override
    public String execute(String params) {

        Log.d("Ian",params);
        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");
        Log.e("Ian",title+url+imageUrl+text);

        //API文档粘过来的
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle("woshititile");
        oks.setText("woshitext");
        oks.setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg");
        oks.setUrl("http://www.baidu.com");
        // 启动分享GUI
        oks.show(getContext());

        return null;
    }
}
