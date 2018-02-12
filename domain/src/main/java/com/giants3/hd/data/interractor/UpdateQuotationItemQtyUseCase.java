package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/12.
 */

class UpdateQuotationItemQtyUseCase extends DefaultUseCase {
    private final long quotationId;
    private final int itm;
    private final int newQty;
    private final RestApi restApi;

    public UpdateQuotationItemQtyUseCase(long quotationId, int itm, int newQty, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.itm = itm;
        this.newQty = newQty;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.updateQuotationItemQty(  quotationId,   itm,   newQty);
    }
}
