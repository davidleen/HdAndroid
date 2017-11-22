package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.entity.ErpWorkFlowReport;
import com.giants3.hd.entity.OrderItemWorkMemo;
import com.giants3.hd.entity.ProductWorkMemo;
import com.giants3.hd.entity_erp.SampleState;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity.WorkFlowMessage;

import java.util.List;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2017/3/4.
 */

public class WorkFlowListPresenter extends BasePresenter<WorkFlowListMvp.Viewer, WorkFlowListMvp.Model> implements WorkFlowListMvp.Presenter {


    @Override
    public WorkFlowListMvp.Model createModel() {
        return new WorkFlowListModel();
    }

    @Override
    public void start() {


        loadWorkFlowWorker();

    }

    /**
     * 读取当前用户的流程工作权限数据
     */
    private void loadWorkFlowWorker() {


    }


    @Override
    public void searchData() {


        ErpOrderItem orderItem = getModel().getSelectOrderItem();
        String os_no = orderItem.os_no;
        int itm = orderItem.itm;

        UseCaseFactory.getInstance().createGetOrderItemWorkFlowReportUseCase(os_no, itm).execute(new RemoteDataSubscriber<ErpWorkFlowReport>(this) {


            @Override
            protected void handleRemoteData(RemoteData<ErpWorkFlowReport> remoteData) {
                getView().bindOrderIteWorkFlowReport(remoteData.datas);
            }
        });
        //读取订单生产备注数据

        UseCaseFactory.getInstance().createGetOrderItemWorkMemoUseCase( orderItem.os_no,orderItem.itm).execute(new RemoteDataSubscriber<OrderItemWorkMemo>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<OrderItemWorkMemo> data) {


                getModel().setOrderItemWorkMemos(data.datas);

            }


        }); //读取产品的生产备注数据

        UseCaseFactory.getInstance().createGetProductWorkMemoUseCase( orderItem.prd_name,orderItem.pVersion).execute(new RemoteDataSubscriber<ProductWorkMemo>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<ProductWorkMemo> data) {


                getModel().setProductWorkMemo(data.datas);

            }


        });


        getView().showWaiting();


        UseCaseFactory.getInstance().createSearchSampleData( orderItem.prd_name,orderItem.pVersion).execute(new Subscriber<RemoteData<SampleState>>()   {


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                e.printStackTrace();

            }

            @Override
            public void onNext(RemoteData<SampleState> data) {

                getView().showSampleState(data.datas.get(0) );

            }




        });
    }




    private void showSendReceiveDialog(List<WorkFlowMessage> messageList) {

        boolean hasUnDoMessage = false;
        for (WorkFlowMessage workFlowMessage : messageList) {
            //nt[] state = new int[]{WorkFlowMessage.STATE_SEND, WorkFlowMessage.STATE_REWORK}
            if (workFlowMessage.state == WorkFlowMessage.STATE_SEND || workFlowMessage.state == WorkFlowMessage.STATE_REWORK) {
                hasUnDoMessage = true;
                break;
            }
        }


        if (!hasUnDoMessage)
            getView().showMessage("当前无流程可以接收");
        else
            getView().showSendReceiveDialog(messageList);


    }

    @Override
    public void setSelectOrderItem(ErpOrderItem orderItem) {
        getView().showSelectOrderItem(orderItem);
        getModel().setSelectOrderItem(orderItem);
        searchData();


        searchSampleData();

    }

    private void searchSampleData() {


    }


    @Override
    public boolean canSendWorkFlow(ErpWorkFlowReport workFlowStep) {


        WorkFlowListMvp.Model model = getModel();
        return model.canSendWorkFlow(workFlowStep);


    }


    @Override
    public boolean canReceiveWorkFlow(ErpWorkFlowReport workFlowStep) {

        WorkFlowListMvp.Model model = getModel();
        return model.canReceiveWorkFlow(workFlowStep);


    }

    /**
     * 接受流程 查询当前流程下是否有未处理消息
     *
     * @param os_no
     * @param itm
     * @param workFlowStep
     */
    @Override
    public void receiveWorkFlow(String os_no, int itm, int workFlowStep) {


        getView().showWaiting();

        UseCaseFactory.getInstance().createGetWorkFlowMessageCase(os_no, itm, workFlowStep).execute(new Subscriber<RemoteData<WorkFlowMessage>>() {
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
                    getView().showMessage(remoteData.message);
                }

            }
        });


    }


    @Override
    public void chooseWorkFlowReport(ErpWorkFlowReport workFlowReport) {




        ProductWorkMemo productWorkMemo = getModel().getSelectProductMemo(workFlowReport.workFlowStep);
        OrderItemWorkMemo orderItemWorkMemo =  getModel().getSelectOrderItemMemo(workFlowReport.workFlowStep);
        getView().showSendWorkFlowDialog(workFlowReport,productWorkMemo,orderItemWorkMemo);






    }

    @Override
    public void clearWorkFlow() {
        //获取关联的流程信息
        ErpOrderItem selectOrderItem = getModel().getSelectOrderItem();
        if(selectOrderItem==null) return;
        UseCaseFactory.getInstance().createGetClearWorkFlowUseCase(selectOrderItem.os_no, selectOrderItem.itm).execute(new RemoteDataSubscriber<Void>(this) {

            @Override
            protected void handleRemoteData(RemoteData<Void> data) {
                if(data.isSuccess())
                {
                    getView().showMessage("清除成功");
                    searchData();


                }else
                {
                    getView().showMessage("清除失败："+data.message);
                }

            }
        });
    }

    @Override
    public void sendWorkFlow(String os_no, int itm, int workFlowStep) {

//        //获取关联的流程信息
        UseCaseFactory.getInstance().createGetAvailableOrderItemProcessUseCase(os_no, itm, workFlowStep).execute(new Subscriber<RemoteData<ErpOrderItemProcess>>() {
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
            public void onNext(RemoteData<ErpOrderItemProcess> remoteData) {
                if (remoteData.isSuccess()) {

                    if (remoteData.datas.size() <= 0) {

                        getView().showMessage("当前流程已经无可发送的订单");
                    } else


                        getView().sendWorkFlowMessage(remoteData.datas);
                } else {
                    getView().showMessage(remoteData.message);
                }

            }
        });
    }
}
