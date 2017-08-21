package org.ian.storm.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import org.ian.storm.app.Storm;
import org.ian.storm.net.callback.IError;
import org.ian.storm.net.callback.IFailure;
import org.ian.storm.net.callback.IRequest;
import org.ian.storm.net.callback.ISuccess;
import org.ian.storm.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by ian on 2017/8/20.
 */

//异步下载保存文件的函数
public class SaveFileTask extends AsyncTask<Object,Void,File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS, IFailure FAILURE, IError ERROR) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final String name = (String) params[2];
        final ResponseBody body = (ResponseBody) params[3];
        final InputStream is = body.byteStream();  //输入流
        if (downloadDir==null ||downloadDir.equals("")){
            downloadDir="down_loads";
        }
        if (extension==null ||extension.equals("")){
            extension="";
        }
        if (name==null){
            return FileUtil.writeToDisk(is,downloadDir,extension.toLowerCase(),extension);
        }else{
            return FileUtil.writeToDisk(is,downloadDir,name);
        }

    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if(SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());  //输出path
        }
        if(FAILURE!=null){
            FAILURE.onFailure();
        }
        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file){
        if(FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            Storm.getApplicationContext().startActivity(install);
        }
    }
}

















