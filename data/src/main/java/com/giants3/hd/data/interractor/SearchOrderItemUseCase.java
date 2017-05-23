package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/3/4.
 */
public class SearchOrderItemUseCase extends DefaultUseCase {
    private final String key;
    private final RestApi restApi;

    public SearchOrderItemUseCase(  String key, RestApi restApi) {
        super( );
        this.key = key;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {

       return  restApi.searchErpOrderItems(key);

    }
}
