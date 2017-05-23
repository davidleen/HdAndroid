package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by davidleen29 on 2017/3/5.
 */
public class GetOrderItemWorkProcessUseCase extends DefaultUseCase {

    private final String osNo;
    private final int itm;
    private final int workFlowStep;
    private final RestApi restApi;

    public GetOrderItemWorkProcessUseCase(String osNo,  int itm  , int workFlowStep, RestApi restApi) {
        super( );
        this.osNo = osNo;
        this.itm = itm;

        this.workFlowStep = workFlowStep;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {

      return   restApi.getOrderItemProcesses(osNo,itm,workFlowStep);

    }
}
