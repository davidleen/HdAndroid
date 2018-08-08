package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/7/22.
 */

class GetUnHandleWorkFlowMessageReportCase extends DefaultUseCase {
    private final int hourLimit;
    private final RestApi restApi;

    public GetUnHandleWorkFlowMessageReportCase(int hourLimit, RestApi restApi) {
        super();
        this.hourLimit = hourLimit;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getUnHandleWorkFlowMessageReport(hourLimit);
    }
}
