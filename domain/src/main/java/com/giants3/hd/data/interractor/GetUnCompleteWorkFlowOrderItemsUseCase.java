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
    public GetUnCompleteWorkFlowOrderItemsUseCase(  String key, RestApi restApi) {

        this.key=key;
        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return restApi.getUnCompleteWorkFlowOrderItems( key);


    }
}
