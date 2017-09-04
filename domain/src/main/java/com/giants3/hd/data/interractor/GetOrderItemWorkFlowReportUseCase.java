package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * 订单的生产进度报表
 *
 * @return Created by davidleen29 on 2017/3/4.
 */
public class GetOrderItemWorkFlowReportUseCase extends DefaultUseCase {

    private int itm;
    private final RestApi restApi;
    private final String os_no;


    public GetOrderItemWorkFlowReportUseCase(String os_no, int   itm ,  RestApi restApi) {
        super();
        this.os_no = os_no;
        this.itm = itm;


        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getOrderItemWorkFlowReport(os_no,itm);
    }
}
