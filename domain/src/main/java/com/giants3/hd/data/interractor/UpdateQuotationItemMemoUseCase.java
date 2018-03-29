package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/3/28.
 */

class UpdateQuotationItemMemoUseCase extends DefaultUseCase {
    private final long quotationId;
    private final int itm;
    private final String memo;
    private final RestApi restApi;

    public UpdateQuotationItemMemoUseCase(long quotationId, int itm, String memo, RestApi restApi) {
        super();
        this.quotationId = quotationId;
        this.itm = itm;
        this.memo = memo;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.updateQuotationItemMemo(  quotationId,   itm,   memo);
    }
}
