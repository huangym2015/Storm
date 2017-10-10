package org.ian.storm.ec.pay;

/**
 * Created by ian on 2017/10/4.
 */

public interface IAlPayResultListener {
    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();

}
