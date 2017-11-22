package com.giants3.hd.android.mvp;


import com.giants3.hd.entity.ErpOrderItem;

import java.util.List;

/** 我的生产事项， 我可以执行发起的流程列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface CompleteOrderItemMVP {


    interface Model extends NewModel {





    }

    interface Presenter extends NewPresenter<CompleteOrderItemMVP.Viewer> {

        void searchWorkFlowOrderItems(String text);


    }

    interface Viewer extends NewViewer {



        void bindOrderItems(List<ErpOrderItem> datas );
    }
}
