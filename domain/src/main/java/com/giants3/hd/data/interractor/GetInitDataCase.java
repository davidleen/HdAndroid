package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**
 * 获取系统初始化数据   在用户登录后
 *
 * Created by david on 2015/9/14.
 */
public class GetInitDataCase extends UseCase {


    private   long userId  ;

    RestApi restApi;



    public GetInitDataCase(Scheduler threadExecutor, Scheduler postExecutionThread   ,
            long userId, RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.userId = userId;


        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.getInitData(userId);



    }
}
