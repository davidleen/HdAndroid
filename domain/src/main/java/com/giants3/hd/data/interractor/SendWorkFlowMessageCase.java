package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;
import com.giants3.hd.entity.ErpOrderItemProcess;

import rx.Observable;


/**
 * 审核流程传递
 *
 *
 */
public class SendWorkFlowMessageCase extends DefaultUseCase {


    private final ErpOrderItemProcess orderItemProcess;

    private final int tranQty;
    private long area;
    private final String memo;
    RestApi restApi;
    public SendWorkFlowMessageCase(ErpOrderItemProcess orderItemProcess, int tranQty, long area, String memo, RestApi restApi) {

        this.orderItemProcess = orderItemProcess;

        this.tranQty = tranQty;
        this.area = area;
        this.memo = memo;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return restApi.sendWorkFlowMessageCase( orderItemProcess,      tranQty,area,memo);


    }
}
