package com.giants3.hd.data.module;

import com.giants3.hd.data.net.RestApi;
import com.giants3.hd.data.net.RestApiImpl;
import com.google.inject.AbstractModule;

/**
 * Created by david on 2015/9/15.
 */
public class AppModule extends AbstractModule {





    @Override
    protected void configure() {


        bind(RestApi.class).to(RestApiImpl.class);
       



    }


}
