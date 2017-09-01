package org.ian.myec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import org.ian.storm.activites.ProxyActivity;
import org.ian.storm.app.Storm;
import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.ec.launcher.LauncherDeleaget;
import org.ian.storm.ec.sign.ISignListener;
import org.ian.storm.ec.sign.SignInDelegate;
import org.ian.storm.ui.launcher.ILauncherListener;
import org.ian.storm.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Storm.getConfigurator().withActivity(this); //获取全局activity
    }

    @Override
    public StormDelegate setRootDelegate() {
        return new LauncherDeleaget();  //启动轮播页面
        //return new SignUpDelegate();  //启动注册页面
    }

    @Override
    public void onSignInSuccess() {
        //登录成功
        Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        //注册成功
        Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        //判断启动的时候，轮播图怎么调用
        switch (tag){
            case SIGNED:
                Toast.makeText(this,"启动结束，用户登陆了",Toast.LENGTH_LONG).show();
                startWithPop(new ExampleDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this,"启动结束，用户没登陆",Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
