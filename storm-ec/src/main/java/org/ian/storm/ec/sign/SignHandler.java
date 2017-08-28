package org.ian.storm.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.ian.storm.app.AccountManager;
import org.ian.storm.ec.database.DatabaseManager;
import org.ian.storm.ec.database.UserProfile;

/**
 * Created by ian on 2017/8/28.
 */

public class SignHandler {

    public static void onSignIn(String response,ISignListener mISignListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userid");
        final String username = profileJson.getString("username");
        final String email = profileJson.getString("email");
        final String avatar = profileJson.getString("avatar");
        final String password = profileJson.getString("password");
        final String gender = profileJson.getString("gender");


        final UserProfile profile = new UserProfile(userId,username,email,avatar,password,gender);
        DatabaseManager.getInstance().getmDao().insert(profile); //插入数据库


        //保存用户状态 已经注册，并登录成功
        AccountManager.setSignState(true);
        mISignListener.onSignInSuccess();

    }

    public static void onSignUp(String response,ISignListener mISignListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userid");
        final String username = profileJson.getString("username");
        final String email = profileJson.getString("email");
        final String avatar = profileJson.getString("avatar");
        final String password = profileJson.getString("password");
        final String gender = profileJson.getString("gender");


        final UserProfile profile = new UserProfile(userId,username,email,avatar,password,gender);
        DatabaseManager.getInstance().getmDao().insert(profile); //插入数据库


        //保存用户状态 已经注册，并登录成功
        AccountManager.setSignState(true);
        mISignListener.onSignUpSuccess();

    }
}











