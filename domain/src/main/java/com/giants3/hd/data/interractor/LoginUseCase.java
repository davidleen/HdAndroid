package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import java.util.Map;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by david on 2015/9/14.
 */
public class LoginUseCase extends DefaultUseCase {




    private final long userId;
    private final String passwordMd5;
    private String deviceToken;
    RestApi restApi;
    long quotationId;


    public LoginUseCase(  long userId,String passwordMd5,String deviceToken , RestApi restApi) {
        super( );
        this.userId = userId;
        this.passwordMd5 = passwordMd5;
        this.deviceToken = deviceToken;


        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.loginByUserId(userId,passwordMd5,deviceToken);



    }
}
