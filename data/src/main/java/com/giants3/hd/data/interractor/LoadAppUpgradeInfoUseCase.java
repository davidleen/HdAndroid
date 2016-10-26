package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by davidleen29 on 2016/10/21.
 */
public class LoadAppUpgradeInfoUseCase extends UseCase {
    private final RestApi restApi;

    public LoadAppUpgradeInfoUseCase(Scheduler threadExecutor, Scheduler postExecutionThread, RestApi restApi) {
         super(threadExecutor,postExecutionThread);
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.loadAppUpgradeInfo();
    }
}
