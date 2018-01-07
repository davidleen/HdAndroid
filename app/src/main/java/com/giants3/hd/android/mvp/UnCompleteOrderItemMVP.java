package com.giants3.hd.android.mvp;


import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.noEntity.RemoteData;

import java.util.List;

/** 我的生产事项， 我可以执行发起的流程列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface UnCompleteOrderItemMVP {


    interface Model extends PageModel<ErpOrderItem> {


        void setData(List<ErpOrderItem> datas);

        List<ErpOrderItem> getFilterData();

        int getSelectedStep();

        void setSelectedStep(int flowStep);


        void setRemoteData(RemoteData<ErpOrderItem> data, int workFlowStep);
    }

    interface Presenter extends NewPresenter<UnCompleteOrderItemMVP.Viewer> {

        void searchWorkFlowOrderItems( );

        void filterData(int flowstep);

        void setKey(String key);

        void loadMoreWorkFlowOrderItems();
    }

    interface Viewer extends NewViewer {



        void bindOrderItems(List<ErpOrderItem> datas,int flowStep);
    }
}
