package com.giants3.hd.android.mvp.workflowmessage;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity.WorkFlowMessage;


/**
 * Created by davidleen29 on 2017/6/11.
 */

public class PresenterImpl extends BasePresenter<OrderItemWorkFlowMessageMVP.Viewer, OrderItemWorkFlowMessageMVP.Model> implements OrderItemWorkFlowMessageMVP.Presenter {


    @Override
    public void start() {


        loadWorkFlowMessageByOrderItem();


    }

    @Override
    public void setOrderItem(ErpOrderItem orderItem) {
        getModel().setOrderItem(orderItem.os_no,orderItem.itm);
    }

    @Override
    public  void loadWorkFlowMessageByOrderItem()
    {

        String osNO=getModel().getOs_no(); int itm=getModel().getItm();


        UseCaseFactory.getInstance().createGetWorkFlowMessageByOrderItemUseCase(osNO,itm).execute(new RemoteDataSubscriber< WorkFlowMessage>(this){
            @Override
            protected void handleRemoteData(RemoteData<WorkFlowMessage> data) {


                getView().bindData(data.datas);
            }
        });

    }

    @Override
    public OrderItemWorkFlowMessageMVP.Model createModel() {
        return new ModelImpl();
    }


}
