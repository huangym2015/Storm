package org.ian.storm.net.download;

import android.os.AsyncTask;

import org.ian.storm.net.RestCreator;
import org.ian.storm.net.callback.IError;
import org.ian.storm.net.callback.IFailure;
import org.ian.storm.net.callback.IRequest;
import org.ian.storm.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ian on 2017/8/20.
 */

public class DownloadHandler {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(
            String url,
            IRequest request,
            String download_dir,
            String extension,
            String name,
            ISuccess success,
            IFailure failure,
            IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    public final void handlerDownload(){
        //判断是否开始请求
        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST,SUCCESS,FAILURE,ERROR);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR,EXTENSION,NAME,responseBody);

                            //这里一定要注意判断，否则文件下载不全
                            if(task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else{
                            if (ERROR!=null){
                                ERROR.onError(response.code(),response.message());
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                        if (FAILURE!=null){
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}




















