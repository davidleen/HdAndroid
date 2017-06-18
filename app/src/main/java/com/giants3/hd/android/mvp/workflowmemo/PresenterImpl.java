package com.giants3.hd.android.mvp.workflowmemo;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.OrderItemWorkMemo;
import com.giants3.hd.utils.entity.ProductWorkMemo;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity_erp.ErpWorkFlowOrderItem;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2017/6/11.
 */

public class PresenterImpl extends BasePresenter<WorkFlowOrderItemMemoMVP.Viewer,WorkFlowOrderItemMemoMVP.Model> implements   WorkFlowOrderItemMemoMVP.Presenter {


    @Override
    public void start() {

    }



    @Override
    public WorkFlowOrderItemMemoMVP.Model createModel() {
        return new ModelImpl();
    }

    @Override
    public void setOrderItem(ErpWorkFlowOrderItem orderItem) {




        getModel().setOrderItem(orderItem);
        loadData();


    }


    private void loadData()
    {


        ErpWorkFlowOrderItem orderItem=getModel().getOrderItem();
        //读取订单生产备注数据

        UseCaseFactory.getInstance().createGetOrderItemWorkMemoUseCase( orderItem.os_no,orderItem.itm).execute(new RemoteDataSubscriber<OrderItemWorkMemo>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<OrderItemWorkMemo> data) {


                getModel().setOrderItemWorkMemos(data.datas);
                bindData();
            }


        }); //读取产品的生产备注数据

        UseCaseFactory.getInstance().createGetProductWorkMemoUseCase( orderItem.prd_name,orderItem.pversion).execute(new RemoteDataSubscriber<ProductWorkMemo>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<ProductWorkMemo> data) {


                getModel().setProductWorkMemo(data.datas);
                bindData();
            }


        });

        getView().showWaiting();
    }



    private  void  bindData()
    {

        ProductWorkMemo productWorkMemo=getModel().getSelectProductWorkMemo();
        getView().bindProductWorkMemo(productWorkMemo);

        OrderItemWorkMemo orderItemWorkMemo=getModel().getSelectOrderItemWorkMemo();
        getView().bindOrderItemWorkMemo(orderItemWorkMemo);

        int workflowStep=getModel().getSelectStep();
        getView().bindSeleteWorkFlowStep(workflowStep);
    }


    @Override
    public void save(String productWorkMemo, String orderItemWorkMemo) {




        ErpWorkFlowOrderItem  orderItem=getModel().getOrderItem();

        int workFlowStep=getModel().getSelectStep();

        UseCaseFactory.getInstance().createSaveWorkMemoUseCase(workFlowStep,orderItem.os_no,orderItem.itm,orderItemWorkMemo,orderItem.prd_name,orderItem.pversion,productWorkMemo).execute(new RemoteDataSubscriber<Void>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<Void> data) {


                loadData();
            }


        });

        getView().showWaiting();



    }

    @Override
    public void setSelectStep(int workFlowStep) {

        getModel().setSelectWorkFlowStep(workFlowStep);
        bindData();
    }
}
