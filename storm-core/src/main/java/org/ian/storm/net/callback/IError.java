package org.ian.storm.net.callback;

/**
 * Created by ian on 2017/8/19.
 */

public interface IError {
    void onError(int code,String message);
}
