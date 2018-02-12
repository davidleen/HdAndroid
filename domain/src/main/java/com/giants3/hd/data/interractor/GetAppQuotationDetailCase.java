package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/5.
 */

class GetAppQuotationDetailCase extends DefaultUseCase {
    private final long quotationId;
    private final RestApi restApi;

    public GetAppQuotationDetailCase(long quotationId, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getAppQuotationDetail(quotationId);
    }
}
