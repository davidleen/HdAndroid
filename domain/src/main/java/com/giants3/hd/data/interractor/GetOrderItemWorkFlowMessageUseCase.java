package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/5/1.
 */

class GetOrderItemWorkFlowMessageUseCase extends DefaultUseCase {

    private final String os_no;
    private int itm;
    private final int workFlowStep;
    private final RestApi restApi;

    public GetOrderItemWorkFlowMessageUseCase(String os_no,int itm , int workFlowStep, RestApi restApi) {
        super();
        this.os_no = os_no;
        this.itm = itm;


        this.workFlowStep = workFlowStep;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getOrderItemWorkFlowMessage(  os_no,  itm,  workFlowStep);
    }
}
