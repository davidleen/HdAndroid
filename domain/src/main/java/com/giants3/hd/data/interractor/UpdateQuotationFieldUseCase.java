package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/3/29.
 */

class UpdateQuotationFieldUseCase extends DefaultUseCase {
    private final long quotationId;
    private final String field;
    private final String data;
    private final RestApi restApi;

    public UpdateQuotationFieldUseCase(long quotationId, String field, String data, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.field = field;
        this.data = data;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.updateQuotationFieldValue(quotationId,field,data);
    }
}
