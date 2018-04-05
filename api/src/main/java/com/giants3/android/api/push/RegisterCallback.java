package com.giants3.android.api.push;

/**
 * Created by davidleen29 on 2018/3/31.
 */

public interface RegisterCallback {

    void onSuccess(String... deviceToken);
    void onFail(String... message);
}
