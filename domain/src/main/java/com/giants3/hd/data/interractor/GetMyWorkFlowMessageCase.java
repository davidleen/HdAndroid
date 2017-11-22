package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/6/25.
 */

class GetMyWorkFlowMessageCase extends DefaultUseCase {
    private String key;
    private RestApi restApi;

    public GetMyWorkFlowMessageCase(String key,RestApi restApi) {
        super();
        this.key = key;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getMyWorkFlowMessage(key);
    }
}
