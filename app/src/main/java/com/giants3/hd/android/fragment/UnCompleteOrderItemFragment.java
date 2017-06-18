package com.giants3.hd.android.fragment;

import android.app.Activity;
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
import com.giants3.hd.android.activity.WorkFlowListActivity;
import com.giants3.hd.android.activity.WorkFlowOrderItemMemoActivity;
import com.giants3.hd.android.adapter.WorkFlowOrderItemListAdapter;
import com.giants3.hd.android.mvp.UnCompleteOrderItemMVP;
import com.giants3.hd.android.mvp.uncompleteorderitem.PresenterImpl;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.utils.entity_erp.ErpWorkFlowOrderItem;

import java.util.List;

import butterknife.Bind;

/**
 * 一排厂未完工的货款
 * Created by davidleen29 on 2017/6/3.
 */

public class UnCompleteOrderItemFragment extends BaseMvpFragment<UnCompleteOrderItemMVP.Presenter> implements UnCompleteOrderItemMVP.Viewer {

    private static final int REQUEST_MESSAGE_OPERATE = 9999;
    WorkFlowOrderItemListAdapter adapter;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    @Bind(R.id.list)
    ListView listView;
    @Bind(R.id.search_text)
    EditText search_text;

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

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchWorkFlowOrderItems();

            }
        });

        adapter = new WorkFlowOrderItemListAdapter(getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                ErpWorkFlowOrderItem erpOrderItem = (ErpWorkFlowOrderItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), WorkFlowOrderItemMemoActivity.class);
                intent.putExtra(WorkFlowOrderItemMemoActivity.KEY_ORDER_ITEM, GsonUtils.toJson(erpOrderItem));
                startActivityForResult(intent, REQUEST_MESSAGE_OPERATE);


            }
        });
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

    @Override
    public void showWaiting() {
        swipeLayout.setRefreshing(true);
    }

    @Override
    public void hideWaiting() {
        swipeLayout.setRefreshing(false);
    }


    @Override
    public void bindOrderItems(List<ErpWorkFlowOrderItem> datas) {
        adapter.setDataArray(datas);
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
}
