package org.ian.storm.util.callback;

import android.support.annotation.Nullable;

/**
 * Created by ian on 2017/10/11.
 */

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
