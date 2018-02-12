package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/12.
 */

class UpdateQuotationItemPriceUseCase extends DefaultUseCase {
    private final long quotationId;
    private final int itm;
    private final float price;
    private final RestApi restApi;

    public UpdateQuotationItemPriceUseCase(long quotationId, int itm, float price, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.itm = itm;
        this.price = price;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.updateQuotationItemPrice(quotationId,itm,price);
    }
}
