package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by davidleen29 on 2017/3/4.
 */
public class SearchOrderItemUseCase extends UseCase {
    private final String key;
    private final RestApi restApi;

    public SearchOrderItemUseCase(Scheduler threadExecutor,
                                  Scheduler postExecutionThread, String key, RestApi restApi) {
        super(threadExecutor,postExecutionThread);
        this.key = key;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {

       return  restApi.searchOrderItem(key);

    }
}
