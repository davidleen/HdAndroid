package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;


/**
 * 审核流程传递
 *
 *
 */
public class GetUnCompleteWorkFlowOrderItemsUseCase extends DefaultUseCase {


    private final String key;
    RestApi restApi;
    private final int workFlowStep;
    private final int pageIndex;
    private final int pageSize;

    public GetUnCompleteWorkFlowOrderItemsUseCase(String key, RestApi restApi, int workFlowStep, int pageIndex, int pageSize) {

        this.key=key;
        this.restApi=restApi;


        this.workFlowStep = workFlowStep;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    @Override
    protected Observable buildUseCaseObservable() {



       return restApi.getUnCompleteWorkFlowOrderItems( key,   workFlowStep,   pageIndex,   pageSize);


    }
}
