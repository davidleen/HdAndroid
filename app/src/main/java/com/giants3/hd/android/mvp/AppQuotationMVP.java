package com.giants3.hd.android.mvp;


import com.giants3.hd.entity.app.Quotation;

import java.util.List;

/**
 * 代办流程消息列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface AppQuotationMVP {


    interface Model extends PageModel<Quotation> {



    }

    interface Presenter extends NewPresenter<AppQuotationMVP.Viewer> {


        void searchData();

        void loadMoreData();

        void setKey(String text);


    }

    interface Viewer extends NewViewer {


        void bindData(List<Quotation> datas);
    }
}
