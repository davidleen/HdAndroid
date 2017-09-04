package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/6/25.
 */

class GetWorkFlowMessageByOrderItemUseCase extends DefaultUseCase{
    private final String osNO;
    private final int itm;
    private final RestApi restApi;

    public GetWorkFlowMessageByOrderItemUseCase(String osNO, int itm, RestApi restApi) {
        this.osNO = osNO;
        this.itm = itm;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getWorkFLowMessageByOrderItem(osNO,itm);
    }
}
