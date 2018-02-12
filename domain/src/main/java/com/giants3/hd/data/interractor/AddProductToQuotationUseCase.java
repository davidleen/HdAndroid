package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/9.
 */

class AddProductToQuotationUseCase extends DefaultUseCase {
    private final long quotationId;
    private final long productId;
    private final RestApi restApi;

    public AddProductToQuotationUseCase(long quotationId, long productId, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.productId = productId;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.addProductToQuotation(quotationId,productId);
    }
}
