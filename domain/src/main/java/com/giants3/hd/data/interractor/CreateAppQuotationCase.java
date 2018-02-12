package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/5.
 */

class CreateAppQuotationCase extends DefaultUseCase {

    private final RestApi restApi;

    public CreateAppQuotationCase(RestApi restApi) {
        super();

        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.createAppQuotation( );
    }
}
