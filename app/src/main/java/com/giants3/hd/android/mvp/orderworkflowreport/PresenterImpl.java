package com.giants3.hd.android.mvp.orderworkflowreport;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.OrderWorkFlowReportMVP;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpOrder;
import com.giants3.hd.noEntity.RemoteData;

/**
 * Created by davidleen29 on 2017/6/3.
 */

public class PresenterImpl extends BasePresenter<OrderWorkFlowReportMVP.Viewer, OrderWorkFlowReportMVP.Model> implements OrderWorkFlowReportMVP.Presenter {

    @Override
    public void start() {
        loadData();
    }

    @Override
    public OrderWorkFlowReportMVP.Model createModel() {
        return new ModelImpl();
    }

    @Override
    public void loadData() {


        UseCaseFactory.getInstance().createGetUnHandleWorkFlowMessageCase("").execute(new RemoteDataSubscriber<ErpOrder>(this) {
            @Override
            protected void handleRemoteData(RemoteData<ErpOrder> data) {

            }
        });


        getView().showWaiting();


    }

}
