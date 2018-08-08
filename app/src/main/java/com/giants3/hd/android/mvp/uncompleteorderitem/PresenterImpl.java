package com.giants3.hd.android.mvp.uncompleteorderitem;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.android.mvp.UnCompleteOrderItemMVP;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.noEntity.RemoteData;

import java.util.List;


/**
 * Created by davidleen29 on 2017/6/3.
 */

public class PresenterImpl extends BasePresenter<UnCompleteOrderItemMVP.Viewer, UnCompleteOrderItemMVP.Model> implements UnCompleteOrderItemMVP.Presenter {

    @Override
    public void start() {
         //searchWorkFlowOrderItems( );
        // getView().showWaiting();
    }

    @Override
    public UnCompleteOrderItemMVP.Model createModel() {
        return new ModelImpl();
    }


    @Override
    public void searchWorkFlowOrderItems( ) {


        String key=getModel().getKey();
        final int workFlowStep=getModel().getSelectedStep();
        int pageIndex = 0;
        int pageSize = 20;


//
        UseCaseFactory.getInstance().createGetUnCompleteWorkFlowOrderItemsUseCase(key,workFlowStep,pageIndex,pageSize).execute(new RemoteDataSubscriber<ErpOrderItem>(this) {
            @Override
            protected void handleRemoteData(RemoteData<ErpOrderItem> data) {


                getModel().setRemoteData(data,workFlowStep);
                bindData();

            }


        });




    }


    @Override
    public void loadMoreWorkFlowOrderItems() {



        if(!getModel().hasNextPage())
        {
            getView().hideWaiting();
            return   ;}
        final RemoteData remoteData = getModel().getRemoteData();

        final int workFlowStep=getModel().getSelectedStep();
        String key=getModel().getKey();

//
        UseCaseFactory.getInstance().createGetUnCompleteWorkFlowOrderItemsUseCase(key,workFlowStep,remoteData.pageIndex+1,remoteData.pageSize).execute(new RemoteDataSubscriber<ErpOrderItem>(this) {
            @Override
            protected void handleRemoteData(RemoteData<ErpOrderItem> data) {


                getModel().setRemoteData(data,workFlowStep);
                bindData();

            }


        });

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


        List<ErpOrderItem> erpOrderItems=getModel().getFilterData();

        if(erpOrderItems==null)
        {
            getView().bindOrderItems(null,flowStep);
            searchWorkFlowOrderItems();
            getView().showWaiting();

        }else
        {
            bindData();
        }




    }


    @Override
    public void setKey(String key) {

        getModel().setKey(key);



    }
}
