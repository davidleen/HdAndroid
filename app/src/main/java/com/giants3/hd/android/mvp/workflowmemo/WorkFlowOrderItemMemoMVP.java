package com.giants3.hd.android.mvp.workflowmemo;


import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.OrderItemWorkMemo;
import com.giants3.hd.utils.entity.ProductWorkMemo;
import com.giants3.hd.utils.entity_erp.ErpWorkFlowOrderItem;

import java.util.List;

/** 我的生产事项， 我可以执行发起的流程列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface WorkFlowOrderItemMemoMVP {


    interface Model extends NewModel {


        void setOrderItem(ErpWorkFlowOrderItem orderItem);

        void setOrderItemWorkMemos(List<OrderItemWorkMemo> datas);

        void setProductWorkMemo(List<ProductWorkMemo> datas);

        ProductWorkMemo getSelectProductWorkMemo();

        OrderItemWorkMemo getSelectOrderItemWorkMemo();

        ErpWorkFlowOrderItem getOrderItem();

        int getSelectStep();

        void setSelectWorkFlowStep(int workFlowStep);
    }

    interface Presenter extends NewPresenter<WorkFlowOrderItemMemoMVP.Viewer> {


        void setOrderItem(ErpWorkFlowOrderItem orderItem);

        void save(String productWorkMemo, String orderItemWorkMemo);

        void setSelectStep(int workFlowStep);
    }

    interface Viewer extends NewViewer {


        void bindProductWorkMemo(ProductWorkMemo productWorkMemo);

        void bindOrderItemWorkMemo(OrderItemWorkMemo orderItemWorkMemo);
        void bindSeleteWorkFlowStep(int workFlowStep);
    }
}
