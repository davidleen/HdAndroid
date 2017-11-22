package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/11/16.
 */

class SearchSampleDataUseCase extends DefaultUseCase {
    private String prd_name;
    private final String pVersion;
    private final RestApi restApi;

    public SearchSampleDataUseCase(String prd_name, String pVersion, RestApi restApi) {
        super();
        this.prd_name = prd_name;
        this.pVersion = pVersion;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.searchSampleData(prd_name,pVersion);
    }
}
