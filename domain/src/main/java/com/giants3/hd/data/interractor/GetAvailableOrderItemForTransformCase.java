package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/** 获取可以传递流程的订单item
 * Created by david on 2015/9/14.
 */
public class GetAvailableOrderItemForTransformCase extends UseCase {




    RestApi restApi;



    public GetAvailableOrderItemForTransformCase(Scheduler threadExecutor, Scheduler postExecutionThread,    RestApi restApi) {
        super(threadExecutor, postExecutionThread);


        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.getAvailableOrderItemForTransformCase(
       );



    }
}
