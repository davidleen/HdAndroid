package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.OrderItemWorkFlowState;
import com.giants3.hd.utils.entity.RemoteData;

import java.util.List;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class WorkFlowSendPresenter extends BasePresenter<WorkFlowSendMvp.Viewer, WorkFlowSendMvp.Model> implements WorkFlowSendMvp.Presenter {




    @Override
    public void start() {




    }


    public void setInitDta(List<OrderItemWorkFlowState> stateList)
    {

        mModel.setAvailableItems(stateList);
    }


    public WorkFlowSendPresenter() {


        mModel=new WorkFlowSendModel();


    }


    @Override
    public void pickOrderItem() {

            OrderItemWorkFlowState lastPickItem=getModel().getLastPickItem();
            List<OrderItemWorkFlowState> availableItems=mModel.getStateList();


        getView().doPickItem(lastPickItem,availableItems);





    }

    @Override
    public void updateQty(int qty) {


        getModel().setSendQty(qty);
        getView().updateSendQty(qty);
    }

    @Override
    public void sendWorkFlow() {

        OrderItemWorkFlowState lastPickItem = mModel.getLastPickItem();
        if(lastPickItem ==null)
        {

            getView().showMessage("请先选择货款");
            return;
        }

        if (mModel.getSendQty() <= 0) {

            getView().warnQtyInput("请输入传递数量");

            return;
        }
        int sendQty=mModel.getSendQty();

        if (sendQty > lastPickItem.unSendQty) {
            getView().warnQtyInput(sendQty + "超过当前可发送的数量" + lastPickItem.unSendQty);

            return;
        }

        sendFlow(lastPickItem, sendQty, mModel.getMemo());

    }

    @Override
    public void setPickItem(OrderItemWorkFlowState newValue) {
        getModel().setLastPickItem(newValue);

        getView().bindPickItem(newValue);

    }


    /**
     * 发送提交下一流程的请求
     *
     * @param orderItemWorkFlowState
     */

    public void sendFlow(OrderItemWorkFlowState orderItemWorkFlowState,   int tranQty, String memo) {

        UseCaseFactory.getInstance().createSendWorkFlowMessageCase(orderItemWorkFlowState.id, tranQty, memo).execute(new Subscriber<RemoteData<Void>>() {
            @Override
            public void onCompleted() {

                getView().showWaiting();


            }

            @Override
            public void onError(Throwable e) {
                getView().hideWaiting();

                getView().showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<Void> remoteData) {
                if (remoteData.isSuccess()) {


                    getView().showMessage("提交成功");


                    getView().doOnSuccessSend();
                    //loadData();
                } else {
                    ToastHelper.show(remoteData.message);
                }

            }
        });


        getView().showWaiting();
    }
}