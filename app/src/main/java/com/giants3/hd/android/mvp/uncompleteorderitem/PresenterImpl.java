package com.giants3.hd.android.mvp.uncompleteorderitem;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.android.mvp.UnCompleteOrderItemMVP;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity_erp.ErpWorkFlowOrderItem;

/**
 * Created by davidleen29 on 2017/6/3.
 */

public class PresenterImpl extends BasePresenter<UnCompleteOrderItemMVP.Viewer, UnCompleteOrderItemMVP.Model> implements UnCompleteOrderItemMVP.Presenter {

    @Override
    public void start() {
        searchWorkFlowOrderItems("");
    }

    @Override
    public UnCompleteOrderItemMVP.Model createModel() {
        return new ModelImpl();
    }


    @Override
    public void searchWorkFlowOrderItems(String text) {


        UseCaseFactory.getInstance().createGetUnCompleteWorkFlowOrderItemsUseCase(text).execute(new RemoteDataSubscriber<ErpWorkFlowOrderItem>(this) {
            @Override
            protected void handleRemoteData(RemoteData<ErpWorkFlowOrderItem> data) {

                getView().bindOrderItems(data.datas
                );

            }


        });

        getView().showWaiting();








    }
}
