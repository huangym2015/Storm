package org.ian.storm.net;

import android.content.Context;

import org.ian.storm.net.callback.IError;
import org.ian.storm.net.callback.IFailure;
import org.ian.storm.net.callback.IRequest;
import org.ian.storm.net.callback.ISuccess;
import org.ian.storm.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ian on 2017/8/19.
 */

public class RestClientBuilder {

    private String mUrl = null;
    // 不需要每次new  private   Map<String,Object> mParams;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private String mDownload_dir = null;
    private String mExtension = null;
    private String mName = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private File mFile = null;
    private LoaderStyle mLoaderStyle = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String filepath) {
        this.mFile = new File(filepath);
        return this;
    }

    public final RestClientBuilder download_dir(String download_dir) {
        this.mDownload_dir = download_dir;
        return this;
    }
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }
    public final RestClientBuilder download_filename(String filename) {
        this.mName = filename;
        return this;
    }

    //默认的style
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }
//    private Map<String,Object> checkParams(){
//        if(mParams ==null){
//            return new WeakHashMap<>();
//        }
//        return mParams;
//    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mDownload_dir, mExtension, mName, mISuccess, mIFailure, mIError, mBody, mFile, mContext, mLoaderStyle);

    }

}












