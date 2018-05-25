package com.giants3.hd.android.mvp.searchproduct;

import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.android.mvp.PageModel;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.entity.WorkFlowArea;

import java.util.List;

/**
 * 生产流程管理界面mvp 构造
 * Created by davidleen29 on 2016/10/10.
 */

public interface SearchProductMvp {

    interface Model extends PageModel<AProduct> {



    }

    interface Presenter extends NewPresenter<Viewer> {


        void search( );

        void searchMore();

        boolean canSearchMore();

        void setKeyWord(String s);

        void setWithCopy(boolean isChecked);
    }

    interface Viewer extends NewViewer {


        void bindDatas(List<AProduct> datas);
    }

}
