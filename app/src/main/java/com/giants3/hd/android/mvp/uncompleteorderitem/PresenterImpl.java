package com.giants3.hd.android.mvp.uncompleteorderitem;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.android.mvp.UnCompleteOrderItemMVP;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.RemoteData;

import java.util.List;


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


        UseCaseFactory.getInstance().createGetUnCompleteWorkFlowOrderItemsUseCase(text).execute(new RemoteDataSubscriber<ErpOrderItem>(this) {
            @Override
            protected void handleRemoteData(RemoteData<ErpOrderItem> data) {


                getModel().setData(data.datas);
              bindData();

            }


        });

        getView().showWaiting();








    }


    private   void  bindData()

    {
        List<ErpOrderItem> datas=getModel().getFilterData();
        int flowStep=getModel().getSelectedStep();
        getView().bindOrderItems( datas,flowStep );

    }





    @Override
    public void filterData(int flowStep) {
        getModel().setSelectedStep(flowStep);
        bindData();

    }
}
