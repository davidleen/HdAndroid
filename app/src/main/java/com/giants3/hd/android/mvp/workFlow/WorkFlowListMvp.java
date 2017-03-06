package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.OrderItem;
import com.giants3.hd.utils.entity.OrderItemWorkFlowState;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlowReport;

import java.util.List;

import rx.Subscriber;

/**
 * 生产流程管理界面mvp 构造
 * Created by davidleen29 on 2016/10/10.
 */

public interface WorkFlowListMvp {

    interface Model extends NewModel {


        boolean canSendWorkFlow(int workFlowStep);

        void setSelectOrderItem(OrderItem orderItem);

        OrderItem getSelectOrderItem();
    }

    interface Presenter extends NewPresenter<Viewer> {

           void searchOrder(String key);

        void   getOrderItemWorkFlowReport( );

        boolean canSendWorkFlow(int workFLowStep);

        void sendWorkFlow(long orderItemId, int workFlowStep);

        void setSelectOrderItem(OrderItem erpOrderItem);
    }

    interface Viewer extends NewViewer {

        void bindOrderIteWorkFlowReport(List<WorkFlowReport> datas);

        void bindOrderItems(List<OrderItem> datas);

        void sendWorkFlowMessage(List<OrderItemWorkFlowState> datas);

        void showSelectOrderItem(OrderItem orderItem);
    }

}
