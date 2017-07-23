package com.giants3.hd.android.mvp;


import com.giants3.hd.utils.entity.ErpOrderItem;


import java.util.List;

/** 我的生产事项， 我可以执行发起的流程列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface UnCompleteOrderItemMVP {


    interface Model extends NewModel {


        void setData(List<ErpOrderItem> datas);

        List<ErpOrderItem> getFilterData();

        int getSelectedStep();

        void setSelectedStep(int flowStep);
    }

    interface Presenter extends NewPresenter<UnCompleteOrderItemMVP.Viewer> {

        void searchWorkFlowOrderItems(String text);

        void filterData(int flowstep);

    }

    interface Viewer extends NewViewer {



        void bindOrderItems(List<ErpOrderItem> datas,int flowStep);
    }
}
