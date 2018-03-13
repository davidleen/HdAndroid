package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/14.
 */

class UpdateQuotationItemDiscountUseCase extends DefaultUseCase {
    private final long quotationId;
    private final int itm;
    private final float newDisCount;
    private final RestApi restApi;

    public UpdateQuotationItemDiscountUseCase(long quotationId, int itm, float newDisCount, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.itm = itm;
        this.newDisCount = newDisCount;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.updateQuotationItemDiscount(quotationId,itm,newDisCount);
    }
}
