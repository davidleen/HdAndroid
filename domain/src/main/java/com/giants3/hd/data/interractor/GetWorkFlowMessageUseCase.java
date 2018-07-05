package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/6/26.
 */

class GetWorkFlowMessageUseCase extends DefaultUseCase {
    private final long workflowMessageId;
    private final RestApi restApi;

    public GetWorkFlowMessageUseCase(long workflowMessageId, RestApi restApi) {
        super();
        this.workflowMessageId = workflowMessageId;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getWorkFlowMessageById(workflowMessageId);
    }
}
