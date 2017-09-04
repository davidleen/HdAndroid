package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.entity.OrderItemWorkFlowState;
import com.giants3.hd.noEntity.RemoteData;

import java.util.List;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public interface WorkFlowReportMvp {

    interface Model extends NewModel {

        void loadUnCompleteOrderItemWorkFlowReport(Subscriber<RemoteData<OrderItemWorkFlowState>> subscriber);

        void loadOrderWorkFlowReport(String key, int pageIndex, int pageSize, Subscriber<RemoteData<OrderItemWorkFlowState>> subscriber);

    }

    interface Presenter extends NewPresenter<Viewer> {
        void searchOrderItemWorkFlow(String value);
    }

    interface Viewer extends NewViewer {
        void bindUnCompleteOrderItem(List<OrderItemWorkFlowState> datas);

        void showUnCompletePanel();

        void bindSearchOrderItemResult(List<OrderItemWorkFlowState> datas);
    }

}
