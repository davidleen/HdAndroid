package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/7/17.
 */

class UpdatePasswordUseCase extends DefaultUseCase {
    private final String oldPasswordMd5;
    private final String newPasswordMd5;
    private final RestApi restApi;

    public UpdatePasswordUseCase(String oldPasswordMd5, String newPasswordMd5, RestApi restApi) {
        super();
        this.oldPasswordMd5 = oldPasswordMd5;
        this.newPasswordMd5 = newPasswordMd5;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {

        return  restApi.updatePassword(oldPasswordMd5,newPasswordMd5);


    }
}
