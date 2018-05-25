package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
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
import com.giants3.hd.android.adapter.ErpOrderItemListAdapter;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.mvp.CompleteOrderItemMVP;
import com.giants3.hd.android.mvp.completeorderitem.PresenterImpl;
import com.giants3.hd.android.widget.RefreshLayoutConfig;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.ErpOrderItem;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

import butterknife.Bind;

/**
 * 排厂完工的货款
 * Created by davidleen29 on 2017/6/3.
 */

public class CompleteOrderItemFragment extends BaseMvpFragment<CompleteOrderItemMVP.Presenter> implements CompleteOrderItemMVP.Viewer, View.OnClickListener {

    private static final int REQUEST_MESSAGE_OPERATE = 9999;
    ItemListAdapter<ErpOrderItem> adapter;
    @Bind(R.id.swipeLayout)
    TwinklingRefreshLayout swipeLayout;

    @Bind(R.id.list)
    ListView listView;
    @Bind(R.id.search_text)
    EditText search_text;


    @Override
    protected CompleteOrderItemMVP.Presenter createPresenter() {
        return new PresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.completeorderitem, null);
    }

    @Override
    protected void initView() {

        RefreshLayoutConfig.config(swipeLayout);
        swipeLayout.setTargetView(listView);

        swipeLayout.setOnRefreshListener(new RefreshListenerAdapter() {


            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {


                getPresenter().searchWorkFlowOrderItems();


            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {

                getPresenter().loadMoreWorkFlowOrderItems();


            }
        });


        adapter = new ErpOrderItemListAdapter(getActivity());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                final ErpOrderItem erpOrderItem = (ErpOrderItem) parent.getItemAtPosition(position);

                if (erpOrderItem == null) return;
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
                                //查询订单生产交接流程


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
        search_text.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                getPresenter().setKey(s.toString().trim());
                search_text.removeCallbacks(searchRunnable);
                search_text.postDelayed(searchRunnable, 1500);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        search_text.post(new Runnable() {
            @Override
            public void run() {
                swipeLayout.startRefresh();
            }
        });

    }




    private Runnable searchRunnable = new Runnable() {
        @Override
        public void run() {


           swipeLayout.startRefresh();

        }
    };


    @Override
    public void onDestroyView() {


        search_text.removeCallbacks(searchRunnable);
        super.onDestroyView();


    }

    @Override
    public void showWaiting() {

    }

    @Override
    public void hideWaiting() {
//        super.hideWaiting();

        swipeLayout.finishRefreshing();
        swipeLayout.finishLoadmore();
    }

    @Override
    public void bindOrderItems(List<ErpOrderItem> datas) {
        adapter.setDataArray(datas);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {
            case REQUEST_MESSAGE_OPERATE:
                getPresenter().searchWorkFlowOrderItems();

                break;
        }

    }

    @Override
    public void onClick(View v) {


    }
}
