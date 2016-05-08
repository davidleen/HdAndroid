package com.giants3.hd.data.interractor;

import com.giants3.hd.data.module.AppModule;
import com.giants3.hd.data.net.RestApi;

import com.google.inject.Guice;
import com.google.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.Map;
import java.util.concurrent.Executor;


/**   用例工厂类
 * Created by david on 2015/9/16.
 */
public class UseCaseFactory  {




    @Inject
    RestApi restApi;



    private    UseCaseFactory()
    {


        Guice.createInjector(new AppModule() ).injectMembers(this);

    }


    public static   UseCaseFactory factory=null;


    public synchronized static UseCaseFactory getInstance() {



        if (factory == null) {

            factory = new UseCaseFactory();

        }
        return factory;
    }



    public   UseCase createLogin(Map<String,String> map)
    {


        return new GetLoginData( Schedulers.newThread()    , AndroidSchedulers.mainThread(),map, restApi);
    }



    public   UseCase createProductListCase(String name,int pageIndex,int pageSize)
    {


        return new GetProductListCase( Schedulers.newThread()    , AndroidSchedulers.mainThread(),name ,pageIndex,pageSize, restApi);
    }

    public   UseCase createOrderListCase(String name,int pageIndex,int pageSize)
    {


        return new GetOrderListCase( Schedulers.newThread()    , AndroidSchedulers.mainThread(),name ,pageIndex,pageSize, restApi);
    }

    /**
     * 读取订单详情货款列表
     * @param orderNo
     * @return
     */
    public UseCase createOrderItemListCase(String orderNo) {
        return new GetOrderItemListCase( Schedulers.newThread()    , AndroidSchedulers.mainThread(),orderNo  , restApi);
    }

    /***
     * 读取订单详情
     * @param productId
     * @return
     */
    public UseCase createGetProductDetailCase(long productId) {
        return new GetProductDetailCase( Schedulers.newThread()    , AndroidSchedulers.mainThread(),productId  , restApi);
    }

    public UseCase createGetQuotationList(String name, int pageIndex, int pageSize) {

        return new GetQuotationListCase( Schedulers.newThread()    , AndroidSchedulers.mainThread(),name,pageIndex,pageSize  , restApi);
    }

    public UseCase createGetQuotationDetail(long quotationId) {
        return new GetQuotationDetailCase( Schedulers.newThread()    , AndroidSchedulers.mainThread(),quotationId , restApi);

    }

    public UseCase createMaterialListCase(String name, int pageIndex, int pageSize) {

        return new GetMaterialListCase( Schedulers.newThread()    , AndroidSchedulers.mainThread(),name,pageIndex,pageSize , restApi);
    }
}
