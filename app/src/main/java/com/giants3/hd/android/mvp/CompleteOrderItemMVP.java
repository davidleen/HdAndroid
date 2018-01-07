package com.giants3.hd.android.mvp;


import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.noEntity.RemoteData;

import java.util.List;

/** 我的生产事项， 我可以执行发起的流程列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface CompleteOrderItemMVP {


    interface Model<T> extends PageModel<T> {


    }

    interface Presenter extends NewPresenter<CompleteOrderItemMVP.Viewer> {

        void searchWorkFlowOrderItems( );


        void loadMoreWorkFlowOrderItems();

        void setKey(String trim);
    }

    interface Viewer extends NewViewer {



        void bindOrderItems(List<ErpOrderItem> datas );
    }
}
