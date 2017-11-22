package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.entity.ErpWorkFlowReport;
import com.giants3.hd.entity.OrderItemWorkMemo;
import com.giants3.hd.entity.ProductWorkMemo;
import com.giants3.hd.entity.WorkFlowMessage;
import com.giants3.hd.entity_erp.SampleState;

import java.util.List;

/**
 * 生产流程管理界面mvp 构造
 * Created by davidleen29 on 2016/10/10.
 */

public interface WorkFlowListMvp {

    interface Model extends NewModel {


        boolean canSendWorkFlow(ErpWorkFlowReport workFlowStep);

        void setSelectOrderItem(ErpOrderItem orderItem);

        ErpOrderItem getSelectOrderItem();

        boolean canReceiveWorkFlow(ErpWorkFlowReport workFlowStep);

        void setProductWorkMemo(List<ProductWorkMemo> datas);

        void setOrderItemWorkMemos(List<OrderItemWorkMemo> datas);

        ProductWorkMemo getSelectProductMemo(int workFlowStep);

        OrderItemWorkMemo getSelectOrderItemMemo(int workFlowStep);
    }

    interface Presenter extends NewPresenter<Viewer> {


        /**
         * 加载数据
         */
        void searchData();

        boolean canSendWorkFlow(ErpWorkFlowReport workFLowStep);

        void sendWorkFlow(String os_no, int itm, int workFlowStep);

        void setSelectOrderItem(ErpOrderItem erpOrderItem);



        boolean canReceiveWorkFlow(ErpWorkFlowReport workFlowStep);

        void receiveWorkFlow(String os_no, int itm, int workFlowStep);

        void chooseWorkFlowReport(ErpWorkFlowReport workFlowReport);


        void clearWorkFlow();
    }

    interface Viewer extends NewViewer {

        void bindOrderIteWorkFlowReport(List<ErpWorkFlowReport> datas);


        void sendWorkFlowMessage(List<ErpOrderItemProcess> datas);

        void showSelectOrderItem(ErpOrderItem orderItem);

        void showSendReceiveDialog(List<WorkFlowMessage> messageList);

        void showSendWorkFlowDialog(ErpWorkFlowReport workFlowReport, ProductWorkMemo productWorkMemo, OrderItemWorkMemo orderItemWorkMemo);

        void showSampleState(SampleState sampleState);
    }

}
