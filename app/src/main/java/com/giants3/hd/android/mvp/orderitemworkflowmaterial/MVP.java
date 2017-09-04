package com.giants3.hd.android.mvp.orderitemworkflowmaterial;


import com.giants3.hd.android.mvp.NewModel;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.entity_erp.WorkFlowMaterial;

import java.util.List;

/**流程物料清单
 * Created by davidleen29 on 2017/5/23.
 */

public interface MVP {


    interface Model extends NewModel {


        void setWorkFlowInfo(String osNo,int itm ,String  code);


        String getOsNo();

        int getItm();

        String getCode();
    }

    interface Presenter extends NewPresenter<MVP.Viewer> {


        void setErpWorkFlowInfo(String osNo,int itm ,String  code);


        void loadData();
    }

    interface Viewer extends NewViewer {


        void bindData(List<WorkFlowMaterial> datas);
    }
}
