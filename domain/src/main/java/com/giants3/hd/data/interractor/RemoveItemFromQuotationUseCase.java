package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/11.
 */

class RemoveItemFromQuotationUseCase extends DefaultUseCase {
    private final long quotationId;
    private final int item;
    private final RestApi restApi;

    public RemoveItemFromQuotationUseCase(long quotationId, int item, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.item = item;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.removeItemFromQuotation(quotationId,item);
    }
}
