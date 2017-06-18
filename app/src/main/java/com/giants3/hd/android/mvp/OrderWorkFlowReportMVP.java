package com.giants3.hd.android.mvp;




/**
 * 代办流程消息列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface OrderWorkFlowReportMVP {


    interface Model extends NewModel {


    }

    interface Presenter extends NewPresenter<OrderWorkFlowReportMVP.Viewer> {


        void loadData();


    }

    interface Viewer extends NewViewer {


    }
}
