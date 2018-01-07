package com.giants3.hd.android.mvp.completeorderitem;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.CompleteOrderItemMVP;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.noEntity.RemoteData;


/**
 * Created by davidleen29 on 2017/6/3.
 */

public class PresenterImpl extends BasePresenter<CompleteOrderItemMVP.Viewer, CompleteOrderItemMVP.Model> implements CompleteOrderItemMVP.Presenter {


    @Override
    public void start() {

    }

    @Override
    public CompleteOrderItemMVP.Model createModel() {
        return new ModelImpl();
    }


    @Override
    public void loadMoreWorkFlowOrderItems() {

        RemoteData<ErpOrderItem> remoteData = getModel().getRemoteData();
        String key = getModel().getKey();

        if (remoteData != null&&remoteData.pageIndex+1<remoteData.pageCount) {


            UseCaseFactory.getInstance().createGetCompleteWorkFlowOrderItemsUseCase(key, remoteData.pageIndex + 1, remoteData.pageSize).execute(new RemoteDataSubscriber<ErpOrderItem>(this) {
                @Override
                protected void handleRemoteData(RemoteData<ErpOrderItem> data) {


                    if (data.isSuccess()) {
                        getModel().setRemoteData(data);
                        getView().bindOrderItems(getModel().getDatas());
                    } else {
                        getView().showMessage(data.message);
                    }


                }


            });


        } else {
            getView().hideWaiting();
        }


    }

    @Override
    public void setKey(String key) {


        getModel().setKey(key);
    }

    @Override
    public void searchWorkFlowOrderItems() {


        String key = getModel().getKey();
        int pageIndex = 0;
        int pageSize = 20;
        RemoteData<ErpOrderItem> remoteData = getModel().getRemoteData();
        if (remoteData != null) {
            pageSize = remoteData.pageSize;
        }

        UseCaseFactory.getInstance().createGetCompleteWorkFlowOrderItemsUseCase(key, pageIndex, pageSize).execute(new RemoteDataSubscriber<ErpOrderItem>(this) {
            @Override
            protected void handleRemoteData(RemoteData<ErpOrderItem> data) {

                if (data.isSuccess()) {
                    getModel().setRemoteData(data);
                    getView().bindOrderItems(getModel().getDatas());
                } else {
                    getView().showMessage(data.message);
                }


            }


        });

        getView().showWaiting();


    }


}
