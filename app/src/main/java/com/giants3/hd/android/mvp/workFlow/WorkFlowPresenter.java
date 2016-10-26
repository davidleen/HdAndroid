package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.RemoteData;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class WorkFlowPresenter extends BasePresenter<WorkFlowMvp.Viewer, WorkFlowMvp.Model> implements WorkFlowMvp.Presenter {


    @Override
    public void start() {


        loadUnDeliveryWorkItemReport();
        getView().showUnCompletePanel();

    }


    public WorkFlowPresenter() {


        mModel = new WorkFlowModel();


    }

    /**
     * 读取所有未出货的订单出货记录
     */
    public void loadUnDeliveryWorkItemReport() {

        getModel().loadUnCompleteOrderItemWorkFlowReport(new Subscriber<RemoteData<ErpOrderItem>>() {
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
                    getView().bindUnCompleteOrderItem(remoteData.datas);
                } else {
                    getView().showMessage(remoteData.message);
                }

            }
        });

        getView().showWaiting();


    }




    @Override
    public void searchOrderItemWorkFlow(String value) {
        getModel().loadOrderWorkFlowReport(value,0,100,new Subscriber<RemoteData<ErpOrderItem>>() {
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
                    getView().bindSearchOrderItemResult(remoteData.datas);
                } else {
                    getView().showMessage(remoteData.message);
                }

            }
        });

        getView().showWaiting();

    }
}
