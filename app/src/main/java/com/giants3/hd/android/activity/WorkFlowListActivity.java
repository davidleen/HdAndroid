package com.giants3.hd.android.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.WorkFlowReportItemAdapter;
import com.giants3.hd.android.fragment.SendWorkFlowFragment;
import com.giants3.hd.android.mvp.workFlow.WorkFlowListMvp;
import com.giants3.hd.android.widget.ExpandableGridView;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.entity.ErpWorkFlow;
import com.giants3.hd.entity.ErpWorkFlowReport;
import com.giants3.hd.entity.OrderItemWorkMemo;
import com.giants3.hd.entity.ProductWorkMemo;
import com.giants3.hd.entity.WorkFlowMessage;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.StringUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 生产流程管理界面
 * 挑选订单， 显示流程序列
 */
public class WorkFlowListActivity extends BaseViewerActivity<WorkFlowListMvp.Presenter> implements WorkFlowListMvp.Viewer, SendWorkFlowFragment.OnFragmentInteractionListener {


    public static final int REQUEST_CODE = 33;
    public static final String KEY_ORDER_ITEM = "KEY_ORDER_ITEM";
    @Bind(R.id.detail_toolbar)
    Toolbar toolbar;


    @Bind(R.id.orderItemInfo)
    TextView orderItemInfo;




    @Bind(R.id.workFlowReport)
    ExpandableGridView workFlowReport;

    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;


    // 进度显示adapter
    WorkFlowReportItemAdapter adapter;


    //Order adapter;


    @Override
    protected WorkFlowListMvp.Presenter onLoadPresenter() {
        return new com.giants3.hd.android.mvp.workFlow.WorkFlowListPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_work_flow_list);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("生产流程管理");

        }
    }

    @Override
    protected void initEventAndData() {
        ErpOrderItem orderItem = null;
        try {
            orderItem = GsonUtils.fromJson(getIntent().getStringExtra(KEY_ORDER_ITEM), ErpOrderItem.class);
        } catch (HdException e) {
            e.printStackTrace();

            showMessage("未发现传递的订单数据");
            finish();
            return;
        }


        getPresenter().setSelectOrderItem(orderItem);


        adapter = new WorkFlowReportItemAdapter(this);
        workFlowReport.setAdapter(adapter);

        workFlowReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ErpWorkFlowReport workFlowReport = (ErpWorkFlowReport) parent.getItemAtPosition(position);
                getPresenter().chooseWorkFlowReport(workFlowReport);


            }
        });


        workFlowReport.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ErpWorkFlowReport workFlowReport = (ErpWorkFlowReport) parent.getItemAtPosition(position);


                getPresenter().chooseWorkFlowReport(workFlowReport);


                return true;
            }
        });

        workFlowReport.setExpanded(true);

        orderItemInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!StringUtils.isEmpty(orderItemInfo.getText().toString().trim())) {
                    getPresenter().searchData();
                }


            }
        });
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doSearchWorkFlowReport();
            }
        });




    }


    private void doSearchWorkFlowReport() {
        swipeLayout.setRefreshing(true);
        getPresenter().searchData();


    }


    @Override
    public void showSendWorkFlowDialog(final ErpWorkFlowReport workFlowReport, ProductWorkMemo productWorkMemo, OrderItemWorkMemo orderItemWorkMemo) {

        //是否生产设置备注的流程
        boolean unMemoStep = workFlowReport.workFlowStep == ErpWorkFlow.STEPS[0] || workFlowReport.workFlowStep == ErpWorkFlow.STEPS[ErpWorkFlow.STEPS.length - 1];

        boolean canSend = getPresenter().canSendWorkFlow(workFlowReport.workFlowStep);
        boolean canReceive = getPresenter().canReceiveWorkFlow(workFlowReport.workFlowStep) && workFlowReport.workFlowStep != ErpWorkFlow.FIRST_STEP;


        if(unMemoStep&&!canSend&&!canReceive) return;

        View inflate = getLayoutInflater().inflate(R.layout.layout_work_flow_info, null);
        TextView productMemoView = (TextView) inflate.findViewById(R.id.productMemo);
        TextView title_productMemoView = (TextView) inflate.findViewById(R.id.title_product_memo);
        productMemoView.setText(productWorkMemo == null ? "" : productWorkMemo.memo);
        TextView orderItemMemoView = (TextView) inflate.findViewById(R.id.orderItemMemo);
        TextView viewMaterial = (TextView) inflate.findViewById(R.id.viewMaterial);
        TextView title_orderItemMemoView = (TextView) inflate.findViewById(R.id.title_order_item_memo);
        orderItemMemoView.setText(orderItemWorkMemo == null ? "" : orderItemWorkMemo.memo);

        productMemoView.setVisibility(unMemoStep ? View.GONE : View.VISIBLE);
        title_productMemoView.setVisibility(unMemoStep ? View.GONE : View.VISIBLE);
        orderItemMemoView.setVisibility(unMemoStep ? View.GONE : View.VISIBLE);
        title_orderItemMemoView.setVisibility(unMemoStep ? View.GONE : View.VISIBLE);
        viewMaterial.setVisibility(unMemoStep ? View.GONE : View.VISIBLE);





        TextView send = (TextView) inflate.findViewById(R.id.send);
        send.setVisibility(canSend ? View.VISIBLE : View.GONE);
        send.setVisibility(canSend ? View.VISIBLE : View.GONE);

        send.setText(workFlowReport.workFlowStep == ErpWorkFlow.LAST_STEP ? "出 货" : "发起流程");


        TextView receive = (TextView) inflate.findViewById(R.id.receive);
        receive.setVisibility(canReceive ? View.VISIBLE : View.GONE);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().sendWorkFlow(workFlowReport.osNo, workFlowReport.itm, workFlowReport.workFlowStep);

            }
        });

        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().receiveWorkFlow(workFlowReport.osNo, workFlowReport.itm, workFlowReport.workFlowStep);

            }
        });

        viewMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // 包装流程，并且名称带有组装时候， 弹出选择查看组装的还是包装的
                if(ErpWorkFlow.CODE_BAOZHUANG.equals(workFlowReport.workFlowCode)&&workFlowReport.workFlowName.startsWith(ErpWorkFlow.NAME_ZUZHUANG))
                {


                    final AlertDialog zhbzDialog = new AlertDialog.Builder(WorkFlowListActivity.this)
                            .setItems(new String[]{ErpWorkFlow.NAME_ZUZHUANG+"材料", ErpWorkFlow.NAME_BAOZHUANG+"材料"}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    switch(which)
                                    {
                                        case 0:
                                            OrderItemWorkFlowMaterialActivity.start(WorkFlowListActivity.this,workFlowReport.osNo,workFlowReport.itm,ErpWorkFlow.CODE_ZUZHUANG,0);

                                            break;
                                        case 1:
                                            OrderItemWorkFlowMaterialActivity.start(WorkFlowListActivity.this,workFlowReport.osNo,workFlowReport.itm,ErpWorkFlow.CODE_BAOZHUANG,0);
                                            break;
                                    }

                                }
                            }).create();
                    zhbzDialog.setCanceledOnTouchOutside(true);
                    zhbzDialog.show();


                }else


                OrderItemWorkFlowMaterialActivity.start(WorkFlowListActivity.this,workFlowReport.osNo,workFlowReport.itm,workFlowReport.workFlowCode,0);


            }
        });
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(inflate).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }

    @Override
    public void showSelectOrderItem(ErpOrderItem orderItem) {


        orderItemInfo.setText("订单号:" + orderItem.os_no + ",货号:" + orderItem.prd_name);


    }


    @Override
    public void showSendReceiveDialog(List<WorkFlowMessage> messageList) {


        Intent intent = new Intent(this, WorkFlowMessageActivity.class);
        startActivityForResult(intent, REQUEST_CODE);


    }


    @Override
    public void bindOrderIteWorkFlowReport(List<ErpWorkFlowReport> datas) {


        adapter.setDataArray(datas);

        swipeLayout.setRefreshing(false);


    }


    @Override
    public void sendWorkFlowMessage(List<ErpOrderItemProcess> datas) {


        SendWorkFlowFragment fragment = SendWorkFlowFragment.newInstance(datas);
        fragment.show(getSupportFragmentManager(), "dialog9999");


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onWorkFlowSend() {


        getPresenter().searchData();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            doSearchWorkFlowReport();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
