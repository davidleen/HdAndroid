package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/6/23.
 */

class GetNewMessageInfoUseCase extends DefaultUseCase {
    private RestApi restApi;

    public GetNewMessageInfoUseCase(RestApi restApi) {
        super();
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {

        return  restApi.getNewMessageInfo();
    }
}
