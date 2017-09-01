package org.ian.storm.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.ec.R;
import org.ian.storm.ec.R2;
import org.ian.storm.net.RestClient;
import org.ian.storm.net.callback.IError;
import org.ian.storm.net.callback.IFailure;
import org.ian.storm.net.callback.ISuccess;
import org.ian.storm.wechat.StormWeChat;
import org.ian.storm.wechat.callbacks.IWeChatSigInCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ian on 2017/8/28.
 */

public class SignInDelegate extends StormDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;


    private ISignListener mISignListener = null;
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if (checkForm()){
            RestClient.builder()
                    .url("http://192.168.1.113:8080/json/json1.do")
                    .params("email",mEmail.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.e("USER_PROFILE",response);
                            SignHandler.onSignIn(response,mISignListener); //返回的数据存入数据库
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getContext(),"failed",Toast.LENGTH_LONG).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String message) {
                            Toast.makeText(getContext(),"error"+message,Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .post();

            //Toast.makeText(getContext(),"验证通过",Toast.LENGTH_LONG).show();
        }
    }


    private boolean checkForm(){
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;
        if (email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass=false;
        }else{
            mEmail.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    /**
     * 微信登陆点击事件，使用前需要在配置APP_ID,APP_SECRET
     */
    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){
        StormWeChat.getInstance().onSigInSucess(new IWeChatSigInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {

            }
        }).signIn();
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        start(new SignUpDelegate());
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
