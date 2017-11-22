package com.giants3.hd.android.mvp.completeorderitem;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.CompleteOrderItemMVP;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.noEntity.RemoteData;

import java.util.List;


/**
 * Created by davidleen29 on 2017/6/3.
 */

public class PresenterImpl extends BasePresenter<CompleteOrderItemMVP.Viewer, CompleteOrderItemMVP.Model> implements CompleteOrderItemMVP.Presenter {

    @Override
    public void start() {
        searchWorkFlowOrderItems("");
    }

    @Override
    public CompleteOrderItemMVP.Model createModel() {
        return new ModelImpl();
    }


    @Override
    public void searchWorkFlowOrderItems(String text) {


        UseCaseFactory.getInstance().createGetCompleteWorkFlowOrderItemsUseCase(text).execute(new RemoteDataSubscriber<ErpOrderItem>(this) {
            @Override
            protected void handleRemoteData(RemoteData<ErpOrderItem> data) {

                getView().bindOrderItems(data.datas );


            }


        });

        getView().showWaiting();


    }






}
