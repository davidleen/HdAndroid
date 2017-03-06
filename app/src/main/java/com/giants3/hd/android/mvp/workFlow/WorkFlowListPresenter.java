package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.OrderItem;
import com.giants3.hd.utils.entity.OrderItemWorkFlowState;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlowReport;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2017/3/4.
 */

public class WorkFlowListPresenter extends BasePresenter<WorkFlowListMvp.Viewer, WorkFlowListMvp.Model> implements WorkFlowListMvp.Presenter  {


    public WorkFlowListPresenter() {

        mModel=new WorkFlowListModel();
    }

    @Override
    public void start() {




    }


    @Override
    public void searchOrder(String key) {


//        getView().showWaiting();
        UseCaseFactory.getInstance().createSearchOrderItemUseCase(key ).execute(new Subscriber<RemoteData<OrderItem>>() {
            @Override
            public void onCompleted() {


                getView().hideWaiting();


            }

            @Override
            public void onError(Throwable e) {
                getView().hideWaiting();

                getView().showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<OrderItem> remoteData) {
                if (remoteData.isSuccess()) {
                    getView().bindOrderItems(remoteData.datas);
                } else {
                    getView().showMessage(remoteData.message);
                }

            }
        });



    }

    @Override
    public void getOrderItemWorkFlowReport( ) {

        long orderItemId=getModel().getSelectOrderItem().id;

        UseCaseFactory.getInstance().createGetOrderItemWorkFlowReportUseCase(orderItemId ).execute(new Subscriber<RemoteData<WorkFlowReport>>() {
            @Override
            public void onCompleted() {


                getView().hideWaiting();


            }

            @Override
            public void onError(Throwable e) {
                getView().hideWaiting();

                getView().showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<WorkFlowReport> remoteData) {
                if (remoteData.isSuccess()) {
                    getView().bindOrderIteWorkFlowReport(remoteData.datas);
                } else {
                    getView().showMessage(remoteData.message);
                }

            }
        });


    }


    @Override
    public void setSelectOrderItem(OrderItem orderItem) {
        getView().showSelectOrderItem(orderItem);
        getModel().setSelectOrderItem(orderItem);
    }

    @Override
    public boolean canSendWorkFlow(int workFlowStep) {


        WorkFlowListMvp.Model  model=getModel();
     return   model.canSendWorkFlow(workFlowStep);






    }


    @Override
    public void sendWorkFlow(long orderItemId, int workFlowStep) {


        //获取关联的流程信息
        UseCaseFactory.getInstance().createGetOrderItemWorkFlowStateUseCase(orderItemId,workFlowStep ).execute(new Subscriber<RemoteData<OrderItemWorkFlowState>>() {
            @Override
            public void onCompleted() {


                getView().hideWaiting();


            }

            @Override
            public void onError(Throwable e) {
                getView().hideWaiting();

                getView().showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<OrderItemWorkFlowState> remoteData) {
                if (remoteData.isSuccess()) {

                    if(remoteData.datas.size()<=0)
                    {


                        getView().showMessage("当前流程已经无可发送的订单");
                    }else


                    getView().sendWorkFlowMessage(remoteData.datas);
                } else {
                    getView().showMessage("获取订单流程相关信息失败");
                }

            }
        });

    }
}
