package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.ErpOrderItemProcess;
import com.giants3.hd.utils.entity.OrderItem;
import com.giants3.hd.utils.entity.OrderItemWorkFlowState;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlowMessage;
import com.giants3.hd.utils.entity.ErpWorkFlowReport;

import java.util.List;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2017/3/4.
 */

public class WorkFlowListPresenter extends BasePresenter<WorkFlowListMvp.Viewer, WorkFlowListMvp.Model> implements WorkFlowListMvp.Presenter  {



    @Override
    public WorkFlowListMvp.Model createModel() {
        return new WorkFlowListModel();
    }

    @Override
    public void start() {




    }


    @Override
    public void searchErpOrderItems(String key) {


//        getView().showWaiting();
        UseCaseFactory.getInstance().createSearchOrderItemUseCase(key ).execute(new Subscriber<RemoteData<ErpOrderItem>>() {
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
            public void onNext(RemoteData<ErpOrderItem> remoteData) {
                if (remoteData.isSuccess()) {
                    getView().bindOrderItems(remoteData.datas);
                } else {


                    getView().showMessage(remoteData.message);

                    if (remoteData.code == RemoteData.CODE_UNLOGIN) {
                        getView().startLoginActivity();
                    }
                }

            }
        });



    }

    @Override
    public void getOrderItemWorkFlowReport( ) {



        String os_no=getModel().getSelectOrderItem().os_no;
        int itm=getModel().getSelectOrderItem().itm;

        UseCaseFactory.getInstance().createGetOrderItemWorkFlowReportUseCase(os_no,itm).execute(new Subscriber<RemoteData<ErpWorkFlowReport>>() {
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
            public void onNext(RemoteData<ErpWorkFlowReport> remoteData) {
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
    public void setSelectOrderItem(ErpOrderItem orderItem) {
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
     * @param os_no
     * @param itm
     * @param workFlowStep
     */
    @Override
    public void receiveWorkFlow(String os_no,int itm , int workFlowStep) {



        getView().showWaiting();

        UseCaseFactory.getInstance().createGetWorkFlowMessageCase(  os_no,  itm,workFlowStep).execute(new Subscriber<RemoteData<WorkFlowMessage>>() {
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

//    @Override
//    public void sendWorkFlow(String osNo,String prdNo, int workFlowStep) {
//
//
//        //获取关联的流程信息
//        UseCaseFactory.getInstance().createGetOrderItemProcessUseCase(osNo,  prdNo ,workFlowStep ).execute(new Subscriber<RemoteData<ErpOrderItemProcess>>() {
//            @Override
//            public void onCompleted() {
//                getView().hideWaiting();
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                getView().hideWaiting();
//
//                getView().showMessage(e.getMessage());
//            }
//
//            @Override
//            public void onNext(RemoteData<ErpOrderItemProcess> remoteData) {
//                if (remoteData.isSuccess()) {
//
//                    if(remoteData.datas.size()<=0)
//                    {
//
//                        getView().showMessage("当前流程已经无可发送的订单");
//                    }else
//
//
//                    getView().sendWorkFlowMessage(remoteData.datas);
//                } else {
//                    getView().showMessage("获取订单流程相关信息失败");
//                }
//
//            }
//        });
//
//    }


    @Override
    public void sendWorkFlow(String os_no, int itm  , int workFlowStep) {

//        //获取关联的流程信息
        UseCaseFactory.getInstance().createGetOrderItemProcessUseCase(os_no,  itm ,workFlowStep ).execute(new Subscriber<RemoteData<ErpOrderItemProcess>>() {
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
