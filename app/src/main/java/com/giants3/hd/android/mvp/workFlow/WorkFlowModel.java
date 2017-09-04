package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.OrderItemWorkFlowState;
import com.giants3.hd.noEntity.RemoteData;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class WorkFlowModel implements WorkFlowReportMvp.Model {
    @Override
    public void loadUnCompleteOrderItemWorkFlowReport(Subscriber<RemoteData<OrderItemWorkFlowState>> subscriber) {

        UseCaseFactory.getInstance().createUnCompleteOrderWorkFlowReportUseCase( ).execute(subscriber);



    }

    @Override
    public void loadOrderWorkFlowReport(String key, int pageIndex, int pageSize, Subscriber<RemoteData<OrderItemWorkFlowState>> subscriber) {



        UseCaseFactory.getInstance().loadOrderWorkFlowReportUseCase(  key,   pageIndex,   pageSize ).execute(subscriber);



    }
}
