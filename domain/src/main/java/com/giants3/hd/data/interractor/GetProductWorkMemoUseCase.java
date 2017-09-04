package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/6/12.
 */

class GetProductWorkMemoUseCase extends DefaultUseCase {
    private final String prd_name;
    private final String pversion;
    private final RestApi restApi;

    public GetProductWorkMemoUseCase(String prd_name, String pversion, RestApi restApi) {

        this.prd_name = prd_name;
        this.pversion = pversion;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getProductWorkMemoList(prd_name,pversion);
    }
}
