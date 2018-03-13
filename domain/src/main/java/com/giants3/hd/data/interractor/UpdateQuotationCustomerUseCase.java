package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/26.
 */

class UpdateQuotationCustomerUseCase extends DefaultUseCase {
    private final long quotationId;
    private final long customerId;
    private final RestApi restApi;

    public UpdateQuotationCustomerUseCase(long quotationId, long customerId, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.customerId = customerId;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.updateQuotationCustomer(quotationId,customerId);
    }
}
