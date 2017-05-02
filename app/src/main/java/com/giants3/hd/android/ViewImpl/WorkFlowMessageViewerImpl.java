package com.giants3.hd.android.ViewImpl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.WorkFlowMessageAdapter;
import com.giants3.hd.android.fragment.WorkFlowMessageFragment;
import com.giants3.hd.android.helper.ImageLoaderFactory;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.presenter.WorkFlowMessagePresenter;
import com.giants3.hd.android.viewer.WorkFlowMessageViewer;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.OrderItemWorkFlowState;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlow;
import com.giants3.hd.utils.entity.WorkFlowMessage;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单流程处理界面
 * Created by davidleen29 on 2016/9/23.
 */

public class WorkFlowMessageViewerImpl extends BaseViewerImpl implements WorkFlowMessageViewer {


    private final WorkFlowMessagePresenter presenter;
    @Bind(R.id.list)
    ListView listView;


    @Bind(R.id.progressBar)
    View progressBar;

    @Bind(R.id.sendMessage)
    View sendMessage;
    @Bind(R.id.sendPanel)
    View sendPanel;

    @Bind(R.id.panel_select_item)
    View panel_select_item;
    @Bind(R.id.order_item_name)
    TextView order_item_name;
    @Bind(R.id.orderItemQty)
    TextView orderItemQty;
    @Bind(R.id.orderItemTranQty)
    TextView orderItemTranQty;
    @Bind(R.id.currentWorkFlow)
    TextView currentWorkFlow;
    @Bind(R.id.destWorkFlow)
    TextView destWorkFlow;
    @Bind(R.id.sendQty)
    EditText sendQty;




    @Bind(R.id.panel_dest_work)
    View panel_dest_work;


    @Bind(R.id.panel_factory)
    View panel_factory;

    @Bind(R.id.factory)
    TextView factory;


    @Bind(R.id.panel_subtype)
    View panel_subtype;

    @Bind(R.id.subtype)
    TextView subType;
    @Bind(R.id.panel_picture)
    View panel_picture;

    @Bind(R.id.picture)
    ImageView picture;

    @Bind(R.id.submitFlow)
    View submitFlow;

    @Bind(R.id.mySend)
    View mySend;

    @Bind(R.id.myReceive)
    View myReceive;
    @Bind(R.id.panel_memo)
    View panel_memo;
    @Bind(R.id.memo)
    TextView memo;

    WorkFlowMessageAdapter adapter;


    AlertDialog checkConfirmDialog;


    public WorkFlowMessageViewerImpl(Context context, WorkFlowMessagePresenter presenter) {
        super(context);
        this.presenter = presenter;
        setContentView(R.layout.fragment_work_flow_message);
    }


    /**
     * 数量输入改变监听
     */
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            int qty = 0;
            try {
                qty = Integer.valueOf(s.toString().trim());
            } catch (Throwable t) {
                warnQtyInput(s + "数量输入不合法,必须 整数 ");
                return;
            }
            if (qty < 0) {
                warnQtyInput(s + "数量输入不合法,必须 > 0");
                return;
            }

            presenter.updateQty(qty);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    /**
     * 数量输入改变监听
     */
    private TextWatcher memoWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            presenter.updateMemo(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    public void warnQtyInput(String message
    ) {
        ToastHelper.show(message);
        sendQty.requestFocus();


    }

    @Override
    public void updateSendQty(int qty) {
        sendQty.removeTextChangedListener(watcher);
        String newText = qty == 0 ? "" : String.valueOf(qty);
        sendQty.setText(newText);
        sendQty.setSelection(newText.length());
        sendQty.addTextChangedListener(watcher);

    }


    @Override
    public void updateNotTotalSend(boolean noEnough) {
        //订单发送不足显示备注输入
        panel_memo.setVisibility(noEnough ? View.VISIBLE : View.GONE);
        sendQty.setTextColor(noEnough ? Color.RED : Color.BLACK);


    }

    @Override
    public void showWaiting() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWaiting() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        adapter = new WorkFlowMessageAdapter(context, new WorkFlowMessageFragment.MessageHandler() {
            @Override
            public void receiveWorkFlow(WorkFlowMessage message) {
                presenter.receiveWorkFlow(message);
            }

            @Override
            public void checkWorkFlow(WorkFlowMessage message) {


                showCheckDialog(message);


            }
        });

        listView.setAdapter(adapter);

        myReceive.setSelected(true);

        boolean canSend = false;
        final List<WorkFlow> workFlows = SharedPreferencesHelper.getInitData().workFlows;
        for (WorkFlow workFlow : workFlows) {
            if (workFlow.userId == SharedPreferencesHelper.getLoginUser().id) {
                canSend = true;
                break;
            }
        }

        sendMessage.setVisibility(canSend ? View.VISIBLE : View.GONE);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearData();

                presenter.loadAvailableOrderItemForTransform();


            }
        });
        sendPanel.setVisibility(View.GONE);
        sendPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPanel.setVisibility(View.GONE);
            }
        });

        panel_select_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                presenter.pickOrderItem();

            }
        });

//        panel_dest_work.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                presenter.pickNextWorkFlow();
//
//
//            }
//        });

        submitFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                presenter.sendFlow();


            }
        });

        myReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!myReceive.isSelected()) {
                    myReceive.setSelected(true);
                    mySend.setSelected(false);
                    presenter.loadData();
                }

            }
        });

        mySend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mySend.isSelected()) {
                    myReceive.setSelected(false);
                    mySend.setSelected(true);
                    presenter.loadMySendMessage();
                }

            }
        });

        memo.addTextChangedListener(memoWatcher);


    }

    /**
     * 显示审核对话款  通过或者返工
     *
     * @param message
     */
    private void showCheckDialog(final WorkFlowMessage message) {


        //弹出选择 通过or 返工
        //
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_work_flow_check, null);

        final CheckDialogViewHolder holder = new CheckDialogViewHolder(contentView);
        holder.panel_reject.setVisibility(View.GONE);
        holder.panel_reason.setVisibility(  View.GONE);
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                holder.panel_reject.setVisibility(checkedId == R.id.reject ? View.VISIBLE : View.GONE);
                holder.panel_reason.setVisibility(checkedId == R.id.reject ? View.VISIBLE : View.GONE);
            }
        });

        //定义适配器

        final List<WorkFlow> workFlows = SharedPreferencesHelper.getInitData().workFlows;

        int size = workFlows.size();
        final List<WorkFlow> canRejectToWorkFlows = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            WorkFlow flow = workFlows.get(i);
            if (flow.flowStep < message.toFlowStep  ) {
                canRejectToWorkFlows.add(flow);
            }
        }
        size = canRejectToWorkFlows.size();
        WorkFlow[] workFlowArray = new WorkFlow[size];
        for (int i = 0; i < size; i++) {
            workFlowArray[i] = canRejectToWorkFlows.get(i);
        }
        ArrayAdapter<WorkFlow> adapter = new ArrayAdapter<WorkFlow>(context, android.R.layout.simple_list_item_1, workFlowArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        holder.workflows.setAdapter(adapter);

        if (checkConfirmDialog != null) checkConfirmDialog.dismiss();
        checkConfirmDialog = new AlertDialog.Builder(context).setView(contentView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int buttonId = holder.radioGroup.getCheckedRadioButtonId();
                if (buttonId != R.id.pass && buttonId != R.id.reject) {
                    showMessage("请选择审核结果：");
                    return;
                }

                if (buttonId == R.id.reject) {
                    presenter.rejectWorkFlow(message, (WorkFlow) holder.workflows.getSelectedItem(), holder.reason.getText().toString());
                } else {
                    presenter.passWorkFlow(message);
                }


            }
        }).setNegativeButton("取消", null).create();
        checkConfirmDialog.show();


    }


    public class CheckDialogViewHolder {


        @Bind(R.id.radioGroup)
        RadioGroup radioGroup;
        @Bind(R.id.pass)
        RadioButton pass;
        @Bind(R.id.reject)
        RadioButton reject;
        @Bind(R.id.panel_reject)
        View panel_reject;
        @Bind(R.id.panel_reason)
        View panel_reason;
        @Bind(R.id.reason)
        EditText reason;

        @Bind(R.id.workflows)
        Spinner workflows;

        public CheckDialogViewHolder(View contentView) {

            ButterKnife.bind(this, contentView);

        }


    }


    @Override
    public void hideCheckConfirmDialog() {

        if (checkConfirmDialog != null)
            checkConfirmDialog.dismiss();


    }

    @Override
    public void setMyReceiveMessage(RemoteData<WorkFlowMessage> remoteData) {

        if (remoteData.isSuccess()) {
            adapter.setDataArray(remoteData.datas);

        } else {
            ToastHelper.show(remoteData.message);

        }
    }

    @Override
    public void clearData() {
        orderItemQty.setText("");

        orderItemTranQty.setText("");
        updateSendQty(0);
        memo.setText("");
        panel_memo.setVisibility(View.GONE);
        order_item_name.setText("");
        currentWorkFlow.setText("");
        destWorkFlow.setText("");

        sendPanel.setVisibility(View.GONE);

    }

    @Override
    public void setOrderItemRelate(OrderItemWorkFlowState erpOrderItem) {
        order_item_name.setText(erpOrderItem.orderName + "    " + erpOrderItem.productFullName);
        orderItemQty.setText(String.valueOf(erpOrderItem.orderQty));
        orderItemTranQty.setText(String.valueOf(erpOrderItem.unSendQty));
        currentWorkFlow.setText(String.valueOf(erpOrderItem.workFlowName));


            destWorkFlow.setText(erpOrderItem.nextWorkFlowName);

        panel_factory.setVisibility(StringUtils.isEmpty(erpOrderItem.factoryName)?View.GONE:View.VISIBLE);
        panel_subtype.setVisibility(StringUtils.isEmpty(erpOrderItem.productTypeName)?View.GONE:View.VISIBLE);

        factory.setText(erpOrderItem.factoryName);
        subType.setText(erpOrderItem.productTypeName);

        String
                uri=   StringUtils.isEmpty(erpOrderItem.photoThumb)?HttpUrl.completeUrl(erpOrderItem.pictureUrl):HttpUrl.completeUrl(erpOrderItem.photoThumb);
        ImageLoaderFactory.getInstance().displayImage(uri, picture);
        updateSendQty(erpOrderItem.unSendQty);
    }

//    @Override
//    public void setNextWorkFlow(WorkFlow workFlow) {
//        destWorkFlow.setText(workFlow.name);
//    }

    @Override
    public void showSenPanel() {


        panel_factory.setVisibility(View.GONE);
        panel_subtype.setVisibility(View.GONE);


        sendPanel.setVisibility(View.VISIBLE);
    }

    @Override
    public void setMySendMessage(RemoteData<WorkFlowMessage> remoteData) {

        adapter.setDataArray(remoteData.datas);
        if (remoteData.isSuccess()) {
        } else {
            showMessage(remoteData.message);
        }
    }
}
