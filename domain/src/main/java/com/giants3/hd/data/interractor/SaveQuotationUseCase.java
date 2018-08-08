package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;
import com.giants3.hd.noEntity.app.QuotationDetail;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/15.
 */

class SaveQuotationUseCase extends DefaultUseCase {
    private final QuotationDetail quotationDetail;
    private final RestApi restApi;

    public SaveQuotationUseCase(QuotationDetail quotationDetail, RestApi restApi) {
        super();
        this.quotationDetail = quotationDetail;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.saveAppQuotation(quotationDetail);
    }
}
