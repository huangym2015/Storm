package org.ian.storm.net.rx;

import android.content.Context;

import org.ian.storm.net.HttpMethod;
import org.ian.storm.net.RestCreator;
import org.ian.storm.ui.loader.LoaderStyle;
import org.ian.storm.ui.loader.StormLoader;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by ian on 2017/8/19.
 */

public class RxRestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
//    private final IRequest REQUEST;   //以下都不需要
//    private final String DOWNLOAD_DIR;
//    private final String EXTENSION;
//    private final String NAME;
//    private final ISuccess SUCCESS;
//    private final IFailure FAILURE;
//    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;

    public RxRestClient(String url,
                        Map<String, Object> params,
//                        IRequest request,
//                        String download_dir,
//                        String extension,
//                        String name,
//                        ISuccess success,
//                        IFailure failure,
//                        IError error,
                        RequestBody body,
                        File file,
                        Context context,
                        LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
//        this.REQUEST = request;
//        this.DOWNLOAD_DIR = download_dir;
//        this.EXTENSION = extension;
//        this.NAME = name;
//        this.SUCCESS = success;
//        this.FAILURE = failure;
//        this.ERROR = error;
        this.BODY = body;
        this.CONTEXT = context;
        this.FILE = file;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();

    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> Observable = null;
//        if (REQUEST != null) {
//            REQUEST.onRequestStart();
//
//        }

        if (LOADER_STYLE != null) {
            StormLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                Observable = service.get(URL, PARAMS);
                break;
            case POST:
                Observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                Observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                Observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                Observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                Observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD: //上传文件
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                Observable = service.upload(URL, body);
                break;
            default:
                break;
        }

        return Observable;
    }

//    private Callback<String> getRequestCallback() {
//        return new RequestCallbacks(
//                REQUEST,
//                SUCCESS,
//                FAILURE,
//                ERROR,
//                LOADER_STYLE
//        );
//    }


    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> put() {

        if (BODY == null) {
            return  request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) { //post元数据时参数必须为空，否则出错
                throw new RuntimeException("params must be null!");
            } else {
                return  request(HttpMethod.PUT_RAW);
            }
        }


    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) { //post元数据时参数必须为空，否则出错
                throw new RuntimeException("params must be null!");
            } else {
                return  request(HttpMethod.POST_RAW);
            }
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<ResponseBody> download(){
//        new DownloadHandler(URL,REQUEST,DOWNLOAD_DIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR)
//                .handlerDownload();
        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(URL,PARAMS);
        return responseBodyObservable;
    }
}



























