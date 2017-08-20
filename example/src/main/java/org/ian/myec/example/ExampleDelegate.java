package org.ian.myec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.net.RestClient;
import org.ian.storm.net.callback.IError;
import org.ian.storm.net.callback.IFailure;
import org.ian.storm.net.callback.ISuccess;

/**
 * Created by ian on 2017/8/16.
 */

public class ExampleDelegate extends StormDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();

    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com")
                //.params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "failure", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        Toast.makeText(getContext(), "Error:"+code+" Msg:"+message, Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }
}
