package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/6/12.
 */

class GetOrderItemWorkMemoUseCase extends DefaultUseCase {
    private final String os_no;
    private final int itm;
    private final RestApi restApi;

    public GetOrderItemWorkMemoUseCase(String os_no, int itm, RestApi restApi) {
        super();
        this.os_no = os_no;
        this.itm = itm;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.getOrderItemWorkMemoList(os_no,itm);
    }
}
