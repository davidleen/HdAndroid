package com.giants3.hd.android.mvp;


import com.giants3.hd.entity.ErpOrderItem;

import java.util.List;

/** 我的生产事项， 我可以执行发起的流程列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface MyAvailableOrderItemMVP {


    interface Model extends NewModel {


        void setErpOrderItems(List<ErpOrderItem> datas,String text, int pageIndex, int pageSize,int pageCount);

        List<ErpOrderItem> getErpOrderItems();

        String getKey();

        int getPageIndex();

        int getPageSize();


        boolean canSearchMore();
    }

    interface Presenter extends NewPresenter<MyAvailableOrderItemMVP.Viewer> {



        void searchErpOrderItems(String text);


        void searchMore();

        boolean canSearchMore();

    }

    interface Viewer extends NewViewer {



        void bindOrderItems(List<ErpOrderItem> datas);
    }
}
