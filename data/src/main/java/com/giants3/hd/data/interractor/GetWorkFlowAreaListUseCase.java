package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/6/17.
 */

class GetWorkFlowAreaListUseCase extends DefaultUseCase {


    private RestApi restApi;

    public GetWorkFlowAreaListUseCase(RestApi restApi) {
        super();
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getWorkFlowAreaList();
    }
}
