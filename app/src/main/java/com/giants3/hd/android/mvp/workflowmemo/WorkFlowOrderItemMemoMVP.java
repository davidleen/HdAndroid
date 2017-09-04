package com.giants3.hd.android.mvp.workflowmemo;


import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.OrderItemWorkMemo;
import com.giants3.hd.entity.ProductWorkMemo;
import com.giants3.hd.noEntity.WorkFlowMemoAuth;

import java.util.List;

/** 我的生产事项， 我可以执行发起的流程列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface WorkFlowOrderItemMemoMVP {


    interface Model extends NewModel {


        void setOrderItem(ErpOrderItem orderItem);

        void setOrderItemWorkMemos(List<OrderItemWorkMemo> datas);

        void setProductWorkMemo(List<ProductWorkMemo> datas);

        ProductWorkMemo getSelectProductWorkMemo();

        OrderItemWorkMemo getSelectOrderItemWorkMemo();

        ErpOrderItem getOrderItem();

        int getSelectStep();

        void setSelectWorkFlowStep(int workFlowStep);

        String[] getNewMemoString();

        void setNewWorkFlowMemo(String productWorkFlwoMemo, String orderItemWorkFlwoMemo);

        boolean hasNewWorkFlowMemo();

        void setWorkFlowMemoAuth(List<WorkFlowMemoAuth> datas);

        WorkFlowMemoAuth getSelectWorkFlowMemoAuth();
    }

    interface Presenter extends NewPresenter<WorkFlowOrderItemMemoMVP.Viewer> {


        void setOrderItem(ErpOrderItem orderItem);

        void save( );

        void setSelectStep(int workFlowStep,boolean checkSave);

        void setWorkFlowMemo(String productWorkFlwoMemo, String orderItemWorkFlwoMemo);

        boolean hasNewWorkFlowMemo();

        void check();
        void  unCheck();
    }

    interface Viewer extends NewViewer {


        void bindProductWorkMemo(ProductWorkMemo productWorkMemo);

        void bindOrderItemWorkMemo(OrderItemWorkMemo orderItemWorkMemo);
        void bindSeleteWorkFlowStep(int workFlowStep);

        void showUnSaveEditDialog(int nextStep );

        void bindWorkFlowAuth(WorkFlowMemoAuth workFlowMemoAuth, OrderItemWorkMemo orderItem);
    }
}
