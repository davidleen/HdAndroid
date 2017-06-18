package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/6/12.
 */

class SaveWorkMemoUseCase extends DefaultUseCase {
    private int workFlowStep;
    private final String os_no;
    private final int itm;
    private final String orderItemWorkMemo;
    private final String prd_name;
    private final String pversion;
    private final String productWorkMemo;
    private final RestApi restApi;

    public SaveWorkMemoUseCase(int workFlowStep,String os_no, int itm, String orderItemWorkMemo, String prd_name, String pversion, String productWorkMemo, RestApi restApi) {
        super();
        this.workFlowStep = workFlowStep;
        this.os_no = os_no;
        this.itm = itm;
        this.orderItemWorkMemo = orderItemWorkMemo;
        this.prd_name = prd_name;
        this.pversion = pversion;
        this.productWorkMemo = productWorkMemo;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return  restApi.saveWorkMemo(   workFlowStep,os_no,   itm,   orderItemWorkMemo,   prd_name,   pversion,   productWorkMemo);
    }
}
