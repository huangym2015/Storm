package org.ian.myec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.net.RestClient;
import org.ian.storm.net.RestCreator;
import org.ian.storm.net.callback.IError;
import org.ian.storm.net.callback.IFailure;
import org.ian.storm.net.callback.ISuccess;
import org.ian.storm.net.rx.RxRestClient;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        //testRestClient();
        //onCallRxGet();
        //onCallRxRestClient();

    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
//                .url("http://news.baidu.com")
                //.params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("Ian:",response);
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

    //TODO:测试方法1    //TODO:可以在下方看到
    void onCallRxGet(){
        final  String url = "index";
        final WeakHashMap<String,Object>  params = new WeakHashMap<>();
        final Observable<String> observable = RestCreator.getRxRestService().get(url,params);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //处理结果在android主线程进行处理，下载必须为io线程中，不能在主线程操作
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        //此方法中为ui线程中
                        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO:测试方法2
    private void onCallRxRestClient(){
        final  String url = "index";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //处理结果在android主线程进行处理，下载必须为io线程中，不能在主线程操作
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        //此方法中为ui线程中
                        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}












