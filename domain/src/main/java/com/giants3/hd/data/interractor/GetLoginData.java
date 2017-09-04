package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import java.util.Map;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by david on 2015/9/14.
 */
public class GetLoginData extends UseCase {


    private final Map<String,String > map;

    RestApi restApi;
    long quotationId;


    public GetLoginData(Scheduler threadExecutor, Scheduler postExecutionThread, Map<String,String > map,RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.map = map;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.login(map);



    }
}
