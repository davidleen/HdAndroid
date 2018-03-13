package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/14.
 */

class UpdateQuotationDiscountUseCase extends DefaultUseCase {
    private final long quotationId;
    private final float newDisCount;
    private final RestApi restApi;

    public UpdateQuotationDiscountUseCase(long quotationId, float newDisCount, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.newDisCount = newDisCount;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.updateQuotationDiscount( quotationId,  newDisCount);
    }
}
