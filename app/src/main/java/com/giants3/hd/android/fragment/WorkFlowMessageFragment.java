package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.giants3.hd.android.R;
import com.giants3.hd.android.ViewImpl.WorkFlowMessageViewerImpl;
import com.giants3.hd.android.activity.WorkFlowMessageReceiveActivity;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.presenter.WorkFlowMessagePresenter;
import com.giants3.hd.android.viewer.BaseViewer;
import com.giants3.hd.android.viewer.WorkFlowMessageViewer;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.ErpOrderItemProcess;

import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlow;
import com.giants3.hd.utils.entity.WorkFlowMessage;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;


/**
 * 代办任务列表
 */
public class WorkFlowMessageFragment extends BaseFragment implements WorkFlowMessagePresenter {


    private ErpOrderItemProcess erpOrderItem;
    private List<ErpOrderItemProcess> orderItems;
    private WorkFlowMessageViewer viewer;

    private int sendQty;
    private String memo;

    public WorkFlowMessageFragment() {


    }

    @Override
    protected BaseViewer getViewer() {

        return viewer;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loadData();
    }


    /**
     * 发送提交下一流程的请求
     *
     * @param erpOrderItemProcess
     */

    public void sendFlow(ErpOrderItemProcess erpOrderItemProcess, int tranQty, String memo) {

        UseCaseFactory.getInstance().createSendWorkFlowMessageCase(erpOrderItemProcess,   tranQty, memo).execute(new Subscriber<RemoteData<Void>>() {
            @Override
            public void onCompleted() {

                viewer.showWaiting();


            }

            @Override
            public void onError(Throwable e) {
                viewer.hideWaiting();

                ToastHelper.show(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<Void> remoteData) {
                if (remoteData.isSuccess()) {


                    ToastHelper.show("提交成功");
                    setResult();
                    clearData();
                    viewer.hideWaiting();
                    loadData();
                } else {
                    ToastHelper.show(remoteData.message);
                }

            }
        });


        viewer.showWaiting();

    }

    private void setResult() {


        getActivity().setResult(Activity.RESULT_OK);
    }

    /**
     * 清除数据set
     */
    public void clearData() {

        erpOrderItem = null;

        sendQty = 0;
        memo = "";
        viewer.clearData();


    }


    @Override
    public void pickOrderItem() {
        ItemPickDialogFragment<ErpOrderItemProcess> dialogFragment = new ItemPickDialogFragment<ErpOrderItemProcess>();
        dialogFragment.set("订单货款选择", orderItems, erpOrderItem, new ItemPickDialogFragment.ValueChangeListener<ErpOrderItemProcess>() {
            @Override
            public void onValueChange(String title, ErpOrderItemProcess oldValue, ErpOrderItemProcess newValue) {


                erpOrderItem = newValue;
                sendQty = erpOrderItem.unSendQty;
                viewer.setOrderItemRelate(erpOrderItem);


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }

    public static WorkFlowMessageFragment newInstance() {
        WorkFlowMessageFragment fragment = new WorkFlowMessageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        viewer = new WorkFlowMessageViewerImpl(getContext(), this);


    }


    @Override
    public void sendFlow() {
        if (erpOrderItem == null) {

            viewer.showMessage("请先选择货款");
            return;
        }




        if (sendQty <= 0) {

            viewer.warnQtyInput("请输入传递数量");

            return;
        }


        if (sendQty > erpOrderItem.unSendQty) {
            viewer.warnQtyInput(sendQty + "超过当前可发送的数量" + erpOrderItem.unSendQty);

            return;
        }




        sendFlow(erpOrderItem, sendQty, memo);
    }

    /**
     * 读取适合发起请求的订单数据
     */
    @Override
    public void loadAvailableOrderItemForTransform() {
        UseCaseFactory.getInstance().createGetAvailableOrderItemForTransformCase().execute(new Subscriber<RemoteData<ErpOrderItemProcess>>() {
            @Override
            public void onCompleted() {
                viewer.hideWaiting();
            }

            @Override
            public void onError(Throwable e) {
                viewer.hideWaiting();

                viewer.showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<ErpOrderItemProcess> remoteData) {
                if (remoteData.isSuccess()) {

                    orderItems = remoteData.datas;
                    if (remoteData.datas.size() == 0) {
                        viewer.showMessage("未查询到可以提交到下流程的货款");
                        return;
                    }

                    viewer.showSenPanel();
                    viewer.showWaiting();

                } else {

                    viewer.showMessage(remoteData.message);

                }
            }
        });

        viewer.showWaiting();

    }

    @Override
    public void receiveWorkFlow(WorkFlowMessage message) {


//        Intent intent=new Intent(this.getActivity(),WorkFlowMessageReceiveActivity.class);
//        startActivity(intent);

        UseCaseFactory.getInstance().createReceiveWorkFlow(message.id).execute(new Subscriber<RemoteData<Void>>() {
            @Override
            public void onCompleted() {

                getViewer().hideWaiting();
            }

            @Override
            public void onError(Throwable e) {
                getViewer().hideWaiting();
                viewer.showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<Void> remoteData) {
                if (remoteData.isSuccess()) {

                    setResult();
                    viewer.showMessage("接收成功");
                    loadData();
                }else
                {
                    viewer.showMessage(remoteData.message);
                }

            }
        });

        getViewer().showWaiting();
    }

    @Override
    public void loadData() {

        UseCaseFactory.getInstance().createGetUnHandleWorkFlowMessageCase().execute(new Subscriber<RemoteData<WorkFlowMessage>>() {
            @Override
            public void onCompleted() {
                viewer.hideWaiting();
            }

            @Override
            public void onError(Throwable e) {
                viewer.hideWaiting();

                viewer.showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<WorkFlowMessage> remoteData) {


                viewer.setMyReceiveMessage(remoteData);

            }
        });
        viewer.showWaiting();

    }


    public interface MessageHandler {
        public void receiveWorkFlow(WorkFlowMessage message);

        public void checkWorkFlow(WorkFlowMessage message);
    }

    @Override
    public void passWorkFlow(WorkFlowMessage message) {

        UseCaseFactory.getInstance().createCheckWorkFlow(message.id).execute(new Subscriber<RemoteData<Void>>() {
            @Override
            public void onCompleted() {
                viewer.hideWaiting();
                viewer.hideCheckConfirmDialog();
            }

            @Override
            public void onError(Throwable e) {
                viewer.hideWaiting();
                viewer.showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<Void> remoteData) {
                if (remoteData.isSuccess()) {
                    viewer.showMessage("审核成功");
                    loadData();
                }
            }
        });
        viewer.showWaiting();

    }


//    @Override
//    public void pickNextWorkFlow() {
//
//        if (erpOrderItem == null) {
//            viewer.showMessage("请先选择货款");
//            return;
//        }
//        List<WorkFlow> workFlows = SharedPreferencesHelper.getInitData().workFlows;
//        List<WorkFlow> nextWorkFlows = new ArrayList<WorkFlow>();
//        for (WorkFlow workFlow : workFlows) {
//            if (workFlow.flowStep > erpOrderItem.currentWorkStep) {
//                nextWorkFlows.add(workFlow);
//            }
//        }
//
//        ItemPickDialogFragment<WorkFlow> dialogFragment = new ItemPickDialogFragment<WorkFlow>();
//        dialogFragment.set("目标流程选择", nextWorkFlows, workFlow, new ItemPickDialogFragment.ValueChangeListener<WorkFlow>() {
//            @Override
//            public void onValueChange(String title, WorkFlow oldValue, WorkFlow newValue) {
//
//
//                workFlow = newValue;
//
//                viewer.setNextWorkFlow(workFlow);
//
//
//            }
//        });
//        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
//
//    }

    @Override
    public void loadMySendMessage() {
        UseCaseFactory.getInstance().createGetMySendWorkFlowMessageCase().execute(new Subscriber<RemoteData<WorkFlowMessage>>() {
            @Override
            public void onCompleted() {
                viewer.hideWaiting();
            }

            @Override
            public void onError(Throwable e) {
                viewer.hideWaiting();

                viewer.showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<WorkFlowMessage> remoteData) {


                viewer.setMySendMessage(remoteData);

            }
        });
        viewer.showWaiting();
    }


    @Override
    public void updateQty(int qty) {

        if (erpOrderItem == null) {
            viewer.showMessage("请先选择货款");
            return;
        }


        if (qty > erpOrderItem.unSendQty) {
            viewer.warnQtyInput(qty + "超过当前可发送的数量" + erpOrderItem.unSendQty);
            return;
        }


        sendQty = qty;
        viewer.updateNotTotalSend(sendQty < erpOrderItem.unSendQty);


        viewer.updateSendQty(qty);


    }


    @Override
    public void updateMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public void rejectWorkFlow(WorkFlowMessage message, WorkFlow rejectToWorkFlow, String reason) {



        UseCaseFactory.getInstance().createRejectWorkFlowUseCase(message.id,rejectToWorkFlow.flowStep,reason).execute(new Subscriber<RemoteData<Void>>() {
            @Override
            public void onCompleted() {
                viewer.hideWaiting();
                viewer.hideCheckConfirmDialog();
            }

            @Override
            public void onError(Throwable e) {
                viewer.hideWaiting();
                viewer.showMessage(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<Void> remoteData) {
                if (remoteData.isSuccess()) {
                    viewer.showMessage("审核成功");
                    loadData();
                }
            }
        });
        viewer.showWaiting();



    }
}
