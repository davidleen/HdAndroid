package com.giants3.hd.android.mvp.myavailableorderitem;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.MyAvailableOrderItemMVP;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.noEntity.RemoteData;

/**
 * Created by davidleen29 on 2017/6/3.
 */

public class PresenterImpl extends BasePresenter<MyAvailableOrderItemMVP.Viewer, MyAvailableOrderItemMVP.Model> implements MyAvailableOrderItemMVP.Presenter {

    private boolean isSearching=false;

    @Override
    public void start() {
        searchErpOrderItems("");
    }

    @Override
    public MyAvailableOrderItemMVP.Model createModel() {
        return new ModelImpl();
    }


    @Override
    public void searchErpOrderItems(final String text) {


        String key=text;
        final int pageIndex=0;
        final int pageSize=20;
        doSearch(key,pageIndex,pageSize);




    }


    private void doSearch(final String key, final int pageIndex, final int pageSize)
    {

        isSearching=true;
        UseCase searchOrderItemUseCase = UseCaseFactory.getInstance().createSearchOrderItemUseCase(key,pageIndex,pageSize);

        searchOrderItemUseCase.execute(new RemoteDataSubscriber<ErpOrderItem>(this) {


            @Override
            public void onCompleted() {
                super.onCompleted();
                isSearching=false;
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                isSearching=false;
            }

            @Override
            protected void handleRemoteData(RemoteData<ErpOrderItem> data) {

                getModel().setErpOrderItems(data.datas,key,pageIndex,pageSize,data.pageCount);
                bindOrderItems();
            }
        });

        getView().showWaiting();
    }

    private  void bindOrderItems()
    {
        getView().bindOrderItems(getModel().getErpOrderItems());

    }

    @Override
    public void searchMore() {

        if(isSearching) return ;


        if(!getModel().canSearchMore())return;

        final String key=getModel().getKey();
        final int pageIndex=getModel().getPageIndex()+1;
        final int pageSize=getModel().getPageSize();

        doSearch(key,pageIndex,pageSize);



    }


    @Override
    public boolean canSearchMore() {

        return getModel().canSearchMore();
    }
}
