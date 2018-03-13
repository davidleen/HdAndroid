package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/2/16.
 */

class PrintQuotationUseCase extends DefaultUseCase {
    private final long quotationId;
    private String filePath;
    private final RestApi restApi;

    public PrintQuotationUseCase(long quotationId,String filePath, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.filePath = filePath;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.printQuotation(quotationId,  filePath);
    }
}
