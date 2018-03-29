package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/3/28.
 */

class DeleteQuotationUseCase extends DefaultUseCase {
    private final long quotationId;
    private final RestApi restApi;

    public DeleteQuotationUseCase(long quotationId, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.deleteQuotation(quotationId);
    }
}
