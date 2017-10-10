package org.ian.storm.ec.pay;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.alipay.sdk.app.PayTask;

import org.ian.storm.ui.loader.StormLoader;

/**
 * Created by ian on 2017/10/4.
 */

public class PayAsyncTask extends AsyncTask<String,Void,String> {

    private final Activity ACTIVITY;
    private final IAlPayResultListener LISTENER;

    //订单支付成功
    private static final String AL_PAY_STATUS_SUCCESS = "9000";
    //订单处理中
    private static final String AL_PAY_STATUS_PAYING = "8000";
    //订单支付失败
    private static final String AL_PAY_STATUS_FAIL = "4000";
    //订单用户取消
    private static final String AL_PAY_STATUS_CANCEL = "6001";
    //订单网络错误
    private static final String AL_PAY_STATUS_CONNECT_ERROR = "6002";


    public PayAsyncTask(Activity activity, IAlPayResultListener listener) {
        this.ACTIVITY = activity;
        this.LISTENER = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        final String alPaySign = params[0];
        final PayTask payTask = new PayTask(ACTIVITY);
        return payTask.pay(alPaySign,true);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        StormLoader.stopLoading();
        final PayResult payResult = new PayResult(result);
        //支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();
        Log.d("AL_PAY_RESULT",resultInfo);
        Log.d("AL_PAY_RESULT",resultStatus);

        switch (resultStatus){
            case AL_PAY_STATUS_SUCCESS:
                if(LISTENER!=null){
                    LISTENER.onPaySuccess();
                }
                break;
            case AL_PAY_STATUS_FAIL:
                if(LISTENER!=null){
                    LISTENER.onPayFail();
                }
                break;
            case AL_PAY_STATUS_PAYING:
                if(LISTENER!=null){
                    LISTENER.onPaying();
                }
                break;
            case AL_PAY_STATUS_CANCEL:
                if(LISTENER!=null){
                    LISTENER.onPayCancel();
                }
                break;
            case AL_PAY_STATUS_CONNECT_ERROR:
                if(LISTENER!=null){
                    LISTENER.onPayConnectError();
                }
                break;
            default:
                break;
        }
    }
}
