package com.giants3.hd.android.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.OrderItemListAdapter;
import com.giants3.hd.android.adapter.WorkFlowReportItemAdapter;
import com.giants3.hd.android.fragment.SendWorkFlowFragment;
import com.giants3.hd.android.helper.AndroidUtils;
import com.giants3.hd.android.mvp.workFlow.WorkFlowListMvp;
import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.ErpOrderItemProcess;
import com.giants3.hd.utils.entity.ErpWorkFlow;
import com.giants3.hd.utils.entity.ErpWorkFlowReport;
import com.giants3.hd.utils.entity.WorkFlowMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 生产流程管理界面
 * 挑选订单， 显示流程序列
 */
public class WorkFlowListActivity extends BaseViewerActivity<WorkFlowListMvp.Presenter> implements WorkFlowListMvp.Viewer, SendWorkFlowFragment.OnFragmentInteractionListener {


    public static final int REQUEST_CODE = 33;
    @Bind(R.id.detail_toolbar)
    Toolbar toolbar;


    @Bind(R.id.search_text)
    EditText search_text;

    @Bind(R.id.orderItemInfo)
    TextView orderItemInfo;


    @Bind(R.id.searchResult)
    ListView searchResult;


    @Bind(R.id.workFlowReport)
    GridView workFlowReport;

    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;


    // 进度显示adapter
    WorkFlowReportItemAdapter adapter;
    OrderItemListAdapter orderItemListAdapter;
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


        adapter = new WorkFlowReportItemAdapter(this);
        workFlowReport.setAdapter(adapter);
        orderItemListAdapter = new OrderItemListAdapter(this);
        searchResult.setAdapter(orderItemListAdapter);

        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                search_text.removeCallbacks(searchRunnable);
                search_text.postDelayed(searchRunnable, 500);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                ErpOrderItem orderItem = (ErpOrderItem) parent.getItemAtPosition(position);


                if (orderItem != null) {

                    getPresenter().setSelectOrderItem(orderItem);
                    doSearchWorkFlowReport();


                }

            }
        });


        workFlowReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ErpWorkFlowReport workFlowReport = (ErpWorkFlowReport) parent.getItemAtPosition(position);

                showSendWorkFlowDialog(workFlowReport);


            }
        });


        workFlowReport.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ErpWorkFlowReport workFlowReport = (ErpWorkFlowReport) parent.getItemAtPosition(position);


                showSendWorkFlowDialog(workFlowReport);


                return false;
            }
        });

        orderItemInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!StringUtils.isEmpty(orderItemInfo.getText().toString().trim())) {
                    getPresenter().getOrderItemWorkFlowReport();
                }


            }
        });
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doSearchWorkFlowReport();
            }
        });
        searchRunnable.run();

    }


    private void doSearchWorkFlowReport() {
        swipeLayout.setRefreshing(true);
        getPresenter().getOrderItemWorkFlowReport();
        AndroidUtils.hideKeyboard(search_text);

    }

    private void showSendWorkFlowDialog(final ErpWorkFlowReport workFlowReport) {


        boolean canSend = getPresenter().canSendWorkFlow(workFlowReport.workFlowStep);
        boolean canReceive = getPresenter().canReceiveWorkFlow(workFlowReport.workFlowStep);

        List<String> titles = new ArrayList<>();
        if (canSend) {
            titles.add(workFlowReport.workFlowStep == ErpWorkFlow.LAST_STEP ? "           出货  " : "           发起流程  ");
        }
        if (canReceive && workFlowReport.workFlowStep != ErpWorkFlow.FIRST_STEP) {
            titles.add("           接收流程  ");
        }

        int size = titles.size();
        if (size == 0) return;
        final String[] strings = new String[size];
        titles.toArray(strings);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String title = strings[which];

                switch (title.trim()) {

                    case "发起流程":
                        getPresenter().sendWorkFlow(workFlowReport.osNo, workFlowReport.itm, workFlowReport.workFlowStep);


                        break;
                    case "接收流程":

                        getPresenter().receiveWorkFlow(workFlowReport.osNo, workFlowReport.itm, workFlowReport.workFlowStep);


                        break;

                }

                dialog.dismiss();
            }
        }).create();
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

    private Runnable searchRunnable = new Runnable() {
        @Override
        public void run() {

            String text = search_text.getText().toString().trim();


            searchErpOrderItems(text);


        }
    };

    private void searchErpOrderItems(String text) {


        getPresenter().searchErpOrderItems(text);


    }


    @Override
    public void bindOrderIteWorkFlowReport(List<ErpWorkFlowReport> datas) {


        adapter.setDataArray(datas);
        swipeLayout.setVisibility(View.VISIBLE);
        swipeLayout.setRefreshing(false);
        searchResult.setVisibility(View.GONE);
    }


    @Override
    public void bindOrderItems(List<ErpOrderItem> datas) {

        orderItemListAdapter.setDataArray(datas);
        searchResult.setVisibility(datas.size() > 0 ? View.VISIBLE : View.GONE);
        swipeLayout.setVisibility(datas.size() > 0 ? View.GONE : View.VISIBLE);

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


        getPresenter().getOrderItemWorkFlowReport();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            doSearchWorkFlowReport();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
