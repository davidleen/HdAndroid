package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;
import com.giants3.hd.utils.entity.ErpOrderItemProcess;

import rx.Observable;
import rx.Scheduler;


/**
 * 审核流程传递
 *
 *
 */
public class SendWorkFlowMessageCase extends DefaultUseCase {


    private final ErpOrderItemProcess orderItemProcess;

    private final int tranQty;
    private final String memo;
    RestApi restApi;
    public SendWorkFlowMessageCase(ErpOrderItemProcess orderItemProcess, int tranQty, String memo, RestApi restApi) {

        this.orderItemProcess = orderItemProcess;

        this.tranQty = tranQty;
        this.memo = memo;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return restApi.sendWorkFlowMessageCase( orderItemProcess,      tranQty,memo);


    }
}
