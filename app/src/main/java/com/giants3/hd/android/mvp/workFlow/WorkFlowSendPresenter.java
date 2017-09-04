package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity.WorkFlowArea;

import java.util.List;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class WorkFlowSendPresenter extends BasePresenter<WorkFlowSendMvp.Viewer, WorkFlowSendMvp.Model> implements WorkFlowSendMvp.Presenter {


    @Override
    public void start() {


        loadWorkFlowArea();

    }

    private void loadWorkFlowArea() {

        UseCaseFactory.getInstance().createGetWorkFlowAreaListUseCase().execute(new RemoteDataSubscriber<WorkFlowArea>(this) {
            @Override
            protected void handleRemoteData(RemoteData<WorkFlowArea> data) {


                getModel().setAreas(data.datas);
            }
        });


    }

    @Override
    public WorkFlowSendMvp.Model createModel() {
        return new WorkFlowSendModel();
    }

    @Override
    public void setInitDta(List<ErpOrderItemProcess> stateList) {

        mModel.setAvailableItems(stateList);
    }


    @Override
    public void pickOrderItem() {

        ErpOrderItemProcess lastPickItem = getModel().getLastPickItem();
        List<ErpOrderItemProcess> availableItems = mModel.getStateList();


        getView().doPickItem(lastPickItem, availableItems);


    }

    @Override
    public void updateQty(int qty) {


        getModel().setSendQty(qty);
        getView().updateSendQty(qty);
    }

    @Override
    public void sendWorkFlow() {

        ErpOrderItemProcess lastPickItem = mModel.getLastPickItem();
        if (lastPickItem == null) {

            getView().showMessage("请先选择货款");
            return;
        }

        if (mModel.getSendQty() <= 0) {

            getView().warnQtyInput("请输入传递数量");

            return;
        }
        int sendQty = mModel.getSendQty();

        if (sendQty > lastPickItem.unSendQty) {
            getView().warnQtyInput(sendQty + "超过当前可发送的数量" + lastPickItem.unSendQty);

            return;
        }

        WorkFlowArea area = mModel.getArea();
        if (area==null ) {
            getView().warnQtyInput("交接区域未选择");

            return;
        }


        sendFlow(lastPickItem, sendQty, area, mModel.getMemo());

    }

    @Override
    public void setPickItem(ErpOrderItemProcess newValue) {
        getModel().setLastPickItem(newValue);

        getView().bindPickItem(newValue);

    }


    @Override
    public void updateMemo(String memo) {
        getModel().setMemo(memo);
    }


    @Override
    public void pickWorkFlowArea() {
        getView().doPickItem(getModel().getArea(), getModel().getAreas());

    }


    @Override
    public void setPickItem(WorkFlowArea newValue) {
        getModel().setArea(newValue);
        getView().bindPickItem(newValue);
    }

    /**
     * 发送提交下一流程的请求
     *
     * @param orderItemProcess
     */

    public void sendFlow(ErpOrderItemProcess orderItemProcess, int tranQty, WorkFlowArea area, String memo) {

        if (tranQty > orderItemProcess.unSendQty) {
            getView().showMessage("发送数量超过当前可发送数量");
            return;
        }

        UseCaseFactory.getInstance().createSendWorkFlowMessageCase(orderItemProcess, tranQty, area.id, memo).execute(new Subscriber<RemoteData<Void>>() {
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
