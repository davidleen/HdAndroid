package com.giants3.hd.android.mvp.workflowmemo;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.OrderItemWorkMemo;
import com.giants3.hd.entity.ProductWorkMemo;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.noEntity.WorkFlowMemoAuth;

/**
 * Created by davidleen29 on 2017/6/11.
 */

public class PresenterImpl extends BasePresenter<WorkFlowOrderItemMemoMVP.Viewer,WorkFlowOrderItemMemoMVP.Model> implements   WorkFlowOrderItemMemoMVP.Presenter {


    @Override
    public void start() {


        //读取权限列表
        loadMemoAuth();


    }

    private void loadMemoAuth() {


        UseCaseFactory.getInstance().createGetWorkFlowMemoAuthUseCase(  ).execute(new RemoteDataSubscriber<WorkFlowMemoAuth>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<WorkFlowMemoAuth> data) {
                getModel().setWorkFlowMemoAuth(data.datas);
                bindData();
            }

        }); //读取产品的生产备注数据


    }


    @Override
    public WorkFlowOrderItemMemoMVP.Model createModel() {
        return new ModelImpl();
    }

    @Override
    public void setOrderItem(ErpOrderItem orderItem) {




        getModel().setOrderItem(orderItem);
        loadData();


    }


    private void loadData()
    {


        ErpOrderItem orderItem=getModel().getOrderItem();
        //读取订单生产备注数据

        UseCaseFactory.getInstance().createGetOrderItemWorkMemoUseCase( orderItem.os_no,orderItem.itm).execute(new RemoteDataSubscriber<OrderItemWorkMemo>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<OrderItemWorkMemo> data) {


                getModel().setOrderItemWorkMemos(data.datas);
                bindData();
            }


        }); //读取产品的生产备注数据

        UseCaseFactory.getInstance().createGetProductWorkMemoUseCase( orderItem.prd_name,orderItem.pVersion).execute(new RemoteDataSubscriber<ProductWorkMemo>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<ProductWorkMemo> data) {


                getModel().setProductWorkMemo(data.datas);
                bindData();
            }


        });

        getView().showWaiting();
    }



    private  void  bindData()
    {

        ProductWorkMemo productWorkMemo=getModel().getSelectProductWorkMemo();
        getView().bindProductWorkMemo(productWorkMemo);

        OrderItemWorkMemo orderItemWorkMemo=getModel().getSelectOrderItemWorkMemo();
        getView().bindOrderItemWorkMemo(orderItemWorkMemo);

        WorkFlowMemoAuth workFlowMemoAuth=getModel().getSelectWorkFlowMemoAuth();
        getView().bindWorkFlowAuth(workFlowMemoAuth,getModel().getSelectOrderItemWorkMemo());


        int workflowStep=getModel().getSelectStep();
        getView().bindSeleteWorkFlowStep(workflowStep);




    }


    @Override
    public void save() {


        if(!getModel().hasNewWorkFlowMemo()) return;


        String[] memos=getModel().getNewMemoString()
        ;ErpOrderItem  orderItem=getModel().getOrderItem();

        int workFlowStep=getModel().getSelectStep();

        UseCaseFactory.getInstance().createSaveWorkMemoUseCase(workFlowStep,orderItem.os_no,orderItem.itm,memos[1],orderItem.prd_name,orderItem.pVersion,memos[0]).execute(new RemoteDataSubscriber<Void>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<Void> data) {


                loadData();
            }


        });

        getView().showWaiting();



    }


    @Override
    public void check() {

        final OrderItemWorkMemo workMemo=getModel().getSelectOrderItemWorkMemo();
        if(workMemo==null) return ;

        UseCaseFactory.getInstance().createCheckWorkFlowMemoUseCase(workMemo.id ,  true).execute(new RemoteDataSubscriber<Void>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<Void> data) {

               loadData();

            }


        });

        getView().showWaiting();


    }

    @Override
    public void unCheck() {

        final OrderItemWorkMemo workMemo=getModel().getSelectOrderItemWorkMemo();
        if(workMemo==null) return ;

        UseCaseFactory.getInstance().createCheckWorkFlowMemoUseCase(workMemo.id ,  false).execute(new RemoteDataSubscriber<Void>(this)   {


            @Override
            protected void handleRemoteData(RemoteData<Void> data) {

                workMemo.checked=false;
                bindData();
            }


        });

        getView().showWaiting();
    }

    @Override
    public boolean hasNewWorkFlowMemo() {
        return getModel().hasNewWorkFlowMemo();
    }

    @Override
    public void setWorkFlowMemo(String productWorkFlwoMemo, String orderItemWorkFlwoMemo) {


        getModel().setNewWorkFlowMemo(productWorkFlwoMemo,orderItemWorkFlwoMemo);
    }

    @Override
    public void setSelectStep(int workFlowStep,boolean checkSave) {

        //先检查当前输入是否改变
        if(checkSave&& getModel().hasNewWorkFlowMemo())
        {


            getView().showUnSaveEditDialog(workFlowStep);
            return ;

        }





        getModel().setSelectWorkFlowStep(workFlowStep);
        bindData();
    }
}
