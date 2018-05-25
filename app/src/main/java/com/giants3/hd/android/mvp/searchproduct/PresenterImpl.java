package com.giants3.hd.android.mvp.searchproduct;

import com.giants3.hd.android.mvp.BasePresenter;

import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.noEntity.RemoteData;

/**
 * Created by davidleen29 on 2017/6/3.
 */

public class PresenterImpl extends BasePresenter<SearchProductMvp.Viewer, SearchProductMvp.Model> implements SearchProductMvp.Presenter {

    private boolean isSearching=false;
    private String s="";
    private boolean isChecked;

    @Override
    public void start() {

    }

    @Override
    public SearchProductMvp.Model createModel() {
        return new ModelImpl();
    }


    @Override
    public void search( ) {


        String key=s;
        final int pageIndex=0;
        final int pageSize=100;
        doSearch(key,pageIndex,pageSize);




    }


    private void doSearch(final String key, final int pageIndex, final int pageSize)
    {

        isSearching=true;
        UseCase searchOrderItemUseCase = UseCaseFactory.getInstance().createProductListCase(key,pageIndex,pageSize,isChecked);

        searchOrderItemUseCase.execute(new RemoteDataSubscriber<AProduct>(this) {


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
            protected void handleRemoteData(RemoteData<AProduct> data) {

                getModel().setRemoteData(data );
                bindOrderItems();
            }
        });

        getView().showWaiting();
    }

    private  void bindOrderItems()
    {
        getView().bindDatas(getModel().getDatas());

    }

    @Override
    public void searchMore() {

        if(isSearching) return ;


        if(!getModel().hasNextPage())return;

        final String key=getModel().getKey();
        final int pageIndex=getModel().getPageIndex()+1;
        final int pageSize=getModel().getPageSize();

        doSearch(key,pageIndex,pageSize);



    }


    @Override
    public boolean canSearchMore() {

        return getModel().hasNextPage();
    }

    @Override
    public void setKeyWord(String s) {

        this.s = s;
    }


    @Override
    public void setWithCopy(boolean isChecked) {

        this.isChecked = isChecked;
    }
}
