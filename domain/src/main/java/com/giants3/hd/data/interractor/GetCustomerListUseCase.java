package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/25.
 */

class GetCustomerListUseCase extends DefaultUseCase {
    private RestApi restApi;

    public GetCustomerListUseCase(RestApi restApi) {
        super();
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getCustomerList();
    }
}
