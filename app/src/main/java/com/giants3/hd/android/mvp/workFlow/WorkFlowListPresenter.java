package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.OrderItem;
import com.giants3.hd.utils.entity.OrderItemWorkFlowState;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlow;
import com.giants3.hd.utils.entity.WorkFlowMessage;
import com.giants3.hd.utils.entity.WorkFlowReport;

import java.util.List;

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
    public void prepareData(long orderItemWorkFlowId, int workFlowStep) {



        if(getModel().canReceiveWorkFlow(workFlowStep))
        {

            //加载数据 当前节点未接受的数据。

            return ;

        }else
        {


        }

        if(getModel().canSendWorkFlow(workFlowStep)) {



            showSendReceiveDialog(null);

        }






        }






    private  void showSendReceiveDialog(List<WorkFlowMessage> messageList)
    {

        boolean hasUnDoMessage=false;
        for(WorkFlowMessage workFlowMessage:messageList)
        {
            //nt[] state = new int[]{WorkFlowMessage.STATE_SEND, WorkFlowMessage.STATE_REWORK}
            if(workFlowMessage.state==WorkFlowMessage.STATE_SEND|| workFlowMessage.state==WorkFlowMessage.STATE_REWORK)
            {
                hasUnDoMessage=true;
                break;
            }
        }


        if(!hasUnDoMessage)
            getView().showMessage("当前无流程可以接收");
        else
        getView().showSendReceiveDialog(messageList);


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
    public boolean canReceiveWorkFlow(int workFlowStep) {

        WorkFlowListMvp.Model  model=getModel();
        return   model.canReceiveWorkFlow(workFlowStep);


    }

    /**
     * 接受流程 查询当前流程下是否有未处理消息
     * @param orderItemWorkFlowId
     * @param workFlowStep
     */
    @Override
    public void receiveWorkFlow(long orderItemWorkFlowId, int workFlowStep) {



        getView().showWaiting();

        UseCaseFactory.getInstance().createGetWorkFlowMessageCase(orderItemWorkFlowId,workFlowStep).execute(new Subscriber<RemoteData<WorkFlowMessage>>() {
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
            public void onNext(RemoteData<WorkFlowMessage> remoteData) {
                if (remoteData.isSuccess()) {
                    showSendReceiveDialog(remoteData.datas);

                } else {
                    getView().showMessage("获取订单流程相关信息失败");
                }

            }
        });


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
