package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;


/**
 * 审核流程传递
 *
 *
 */
public class GetCompleteWorkFlowOrderItemsUseCase extends DefaultUseCase {


    private final String key;
    RestApi restApi;
    private final int pageIndex;
    private final int pageSize;

    public GetCompleteWorkFlowOrderItemsUseCase(  String key, RestApi restApi,int pageIndex,int pageSize) {

        this.key=key;
        this.restApi=restApi;


        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    @Override
    protected Observable buildUseCaseObservable() {



       return restApi.getCompleteWorkFlowOrderItems( key, pageIndex, pageSize);


    }
}
