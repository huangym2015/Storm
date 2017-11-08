package org.ian.storm.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.ian.storm.delegates.StormDelegate;
import org.ian.storm.util.callback.CallbackManager;
import org.ian.storm.util.callback.CallbackType;
import org.ian.storm.util.callback.IGlobalCallback;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by ian on 2017/10/19.
 */

public class ScannerDelegate extends StormDelegate implements ZBarScannerView.ResultHandler{

    private ScanView mScanView =null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mScanView==null){
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }

    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @Override
    public void onResume() {
        super.onResume();
        if (mScanView!=null){
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScanView!=null){
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result result) {
        @SuppressWarnings("unchecked")
        final IGlobalCallback<String> callback = CallbackManager.getInstance()
                .getCallback(CallbackType.ON_SCAN);
        if (callback != null) {
            callback.executeCallback(result.getContents());
        }

        getSupportDelegate().pop();

    }
}
