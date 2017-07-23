package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.activity.OrderItemWorkFlowMessageActivity;
import com.giants3.hd.android.activity.WorkFlowListActivity;
import com.giants3.hd.android.activity.WorkFlowOrderItemMemoActivity;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.mvp.UnCompleteOrderItemMVP;
import com.giants3.hd.android.mvp.uncompleteorderitem.PresenterImpl;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.ErpWorkFlow;

import java.util.List;

import butterknife.Bind;

/**
 * 一排厂未完工的货款
 * Created by davidleen29 on 2017/6/3.
 */

public class UnCompleteOrderItemFragment extends BaseMvpFragment<UnCompleteOrderItemMVP.Presenter> implements UnCompleteOrderItemMVP.Viewer, View.OnClickListener {

    private static final int REQUEST_MESSAGE_OPERATE = 9999;
    ItemListAdapter<ErpOrderItem> adapter;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    @Bind(R.id.list)
    ListView listView;
    @Bind(R.id.search_text)
    EditText search_text;


    @Bind(R.id.all)
    View all;
    @Bind(R.id.step1)
    View step1;
    @Bind(R.id.step2)
    View step2;
    @Bind(R.id.step3)
    View step3;
    @Bind(R.id.step4)
    View step4;

    @Bind(R.id.step5)
    View step5;
    View[] steps;

    @Override
    protected UnCompleteOrderItemMVP.Presenter createPresenter() {
        return new PresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.uncompleteorderitem, null);
    }

    @Override
    protected void initView() {
        steps = new View[]{all, step1,step2,  step3, step4, step5};
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchWorkFlowOrderItems();

            }
        });
        swipeLayout.setEnabled(false);
        adapter = new ItemListAdapter<>(getActivity());
        adapter.setTableData(TableData.resolveData(getActivity(), R.array.table_erp_order_item));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                final ErpOrderItem erpOrderItem = (ErpOrderItem) parent.getItemAtPosition(position);

                if(erpOrderItem==null) return ;
//                if((SharedPreferencesHelper.getLoginUser().position& CompanyPosition.PRIVILAGE_WORKFLOW_MEMO)==CompanyPosition.PRIVILAGE_WORKFLOW_MEMO)
//                {
                //有备注权限
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setItems(new String[]{"生产备注", "生产交接记录", "生产进度查看"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent;

                        switch (which) {

                            case 0:
                                intent = new Intent(getActivity(), WorkFlowOrderItemMemoActivity.class);
                                intent.putExtra(WorkFlowOrderItemMemoActivity.KEY_ORDER_ITEM, GsonUtils.toJson(erpOrderItem));
                                startActivityForResult(intent, REQUEST_MESSAGE_OPERATE);
                                break;
                            case 1:
                                //查询订单生产交接记录

                                intent = new Intent(getActivity(), OrderItemWorkFlowMessageActivity.class);
                                intent.putExtra(OrderItemWorkFlowMessageActivity.KEY_ORDER_ITEM, GsonUtils.toJson(erpOrderItem));
                                startActivityForResult(intent, REQUEST_MESSAGE_OPERATE);


                                break;

                            case 2:
                                //查询订单生产交接记录


                                intent = new Intent(getActivity(), WorkFlowListActivity.class);
                                intent.putExtra(WorkFlowListActivity.KEY_ORDER_ITEM, GsonUtils.toJson(erpOrderItem));
                                startActivityForResult(intent, REQUEST_MESSAGE_OPERATE);

                                break;
                        }


                    }
                }).create();
                alertDialog.show();


            }
        });
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                search_text.removeCallbacks(searchRunnable);
                search_text.postDelayed(searchRunnable, 1500);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        for (int i = 0; i < steps.length; i++) {


            steps[i].setOnClickListener(this);
        }
    }

    private Runnable searchRunnable = new Runnable() {
        @Override
        public void run() {


            searchWorkFlowOrderItems();


        }
    };

    private void searchWorkFlowOrderItems() {

        String text = search_text.getText().toString().trim();
        getPresenter().searchWorkFlowOrderItems(text);

    }


    @Override
    public void onDestroyView() {


        search_text.removeCallbacks(searchRunnable);
        super.onDestroyView();


    }

//    @Override
//    public void showWaiting() {
//        swipeLayout.setRefreshing(true);
//    }
//
//    @Override
//    public void hideWaiting() {
//        swipeLayout.setRefreshing(false);
//    }


    @Override
    public void bindOrderItems(List<ErpOrderItem> datas, int flowStep) {
        adapter.setDataArray(datas);
        int index = 0;
        if (flowStep == -1) {
            index = 0;
        }

        for (int i = 0; i < ErpWorkFlow.STEPS.length; i++) {


            if (ErpWorkFlow.STEPS[i] == flowStep) {
                index = i + 1;
                break;
            }
        }

        for (int i = 0; i < steps.length; i++) {

            steps[i].setSelected(i == index);

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {
            case REQUEST_MESSAGE_OPERATE:
                searchWorkFlowOrderItems();

                break;
        }

    }

    @Override
    public void onClick(View v) {


        int flowstep = -1;
        switch (v.getId()) {

            case R.id.all:
                flowstep = -1;
                break;
            case R.id.step1:
                flowstep = ErpWorkFlow.STEPS[0];
                break;
            case R.id.step2:
                flowstep = ErpWorkFlow.STEPS[1];
                break;
            case R.id.step3:
                flowstep = ErpWorkFlow.STEPS[2];
                break;
            case R.id.step4:
                flowstep = ErpWorkFlow.STEPS[3];
                break;
            case R.id.step5:
                flowstep = ErpWorkFlow.STEPS[4];
                break;


        }


        getPresenter().filterData(flowstep);


    }
}
