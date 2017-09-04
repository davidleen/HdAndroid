package com.giants3.hd.android.mvp.orderitemworkflowmaterial;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity_erp.WorkFlowMaterial;

/**
 * Created by davidleen29 on 2017/6/11.
 */

public class PresenterImpl extends BasePresenter<MVP.Viewer,MVP.Model> implements   MVP.Presenter {


    @Override
    public void start() {

    }


    @Override
    public void setErpWorkFlowInfo(String osNo,int itm ,String  code) {
        getModel().setWorkFlowInfo(  osNo,  itm ,   code);
        loadData();

    }

    @Override
    public MVP.Model createModel() {
        return new ModelImpl();
    }




    @Override
    public void loadData()
    {





        String  osNo=getModel().getOsNo();
        int itm=getModel().getItm();
        String code=getModel().getCode();

        //加载当前流程下的材料列表
        UseCaseFactory.getInstance().createGetWorkFlowMaterialsUseCase(osNo,itm, code).execute(new RemoteDataSubscriber<WorkFlowMaterial>(this) {

            @Override
            protected void handleRemoteData(RemoteData<WorkFlowMaterial> data) {
                getView().bindData(data.datas);
            }

        });
        getView().showWaiting();
    }






}
