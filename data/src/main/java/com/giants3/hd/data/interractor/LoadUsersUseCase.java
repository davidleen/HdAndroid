package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/6/2.
 */

class LoadUsersUseCase extends DefaultUseCase {
    private RestApi restApi;

    public LoadUsersUseCase(RestApi restApi) {

        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.loadUsers();
    }
}
