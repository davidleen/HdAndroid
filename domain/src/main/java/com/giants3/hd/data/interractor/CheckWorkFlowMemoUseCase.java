package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/7/2.
 */

class CheckWorkFlowMemoUseCase extends DefaultUseCase {
    private final long orderItemWorkMemoId;
    private final boolean check;
    private final RestApi restApi;

    public CheckWorkFlowMemoUseCase(long orderItemWorkMemoId, boolean check, RestApi restApi) {
        super();
        this.orderItemWorkMemoId = orderItemWorkMemoId;
        this.check = check;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.checkWorkFlowMemoCase(orderItemWorkMemoId,check);
    }
}
