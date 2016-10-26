package com.giants3.hd.android.mvp.workFlow;

import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.RemoteData;

import java.util.List;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public interface WorkFlowMvp {

    interface Model extends NewModel {

        void loadUnCompleteOrderItemWorkFlowReport(Subscriber<RemoteData<ErpOrderItem>> subscriber);

        void loadOrderWorkFlowReport(String key, int pageIndex, int pageSize, Subscriber<RemoteData<ErpOrderItem>> subscriber);

    }

    interface Presenter extends NewPresenter<Viewer> {
        void searchOrderItemWorkFlow(String value);
    }

    interface Viewer extends NewViewer {
        void bindUnCompleteOrderItem(List<ErpOrderItem> datas);

        void showUnCompletePanel();

        void bindSearchOrderItemResult(List<ErpOrderItem> datas);
    }

}
