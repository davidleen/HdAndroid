package com.giants3.hd.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.mvp.workFlow.WorkFlowMvp;
import com.giants3.hd.android.widget.ExpandableHeightListView;
import com.giants3.hd.utils.entity.ErpOrderItem;

import java.util.List;

import butterknife.Bind;

/**
 * An activity representing a single ProductListActivity detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link WorkFlowActivity}.
 */
public class WorkFlowActivity extends BaseViewerActivity<WorkFlowMvp.Presenter> implements WorkFlowMvp.Viewer {


    @Bind(R.id.detail_toolbar)
    Toolbar toolbar;

    @Bind(R.id.progressSearch)
    View progressSearch;
    @Bind(R.id.unDeliveryList)
    View unDeliveryList;

    @Bind(R.id.list_unComplete)
    ExpandableHeightListView list_unComplete;
    @Bind(R.id.list_progress)
    ExpandableHeightListView list_progress;
    @Bind(R.id.panel_progress)
    View panel_progress;

    @Bind(R.id.search_text)
    View search_text;


    @Bind(R.id.key)
    EditText key;


    //未出库订单adapter
    ItemListAdapter<ErpOrderItem> adapter;

    //查询结果adapter
    ItemListAdapter<ErpOrderItem> searchAdapter;


    @Override
    protected WorkFlowMvp.Presenter onLoadPresenter() {
        return new com.giants3.hd.android.mvp.workFlow.WorkFlowPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_work_flow);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("生产流程报表");

        }
    }

    @Override
    protected void initEventAndData() {
        unDeliveryList.setOnClickListener(this);
        progressSearch.setOnClickListener(this);

        TableData tableData = TableData.resolveData(this, R.array.table_order_item_work_flow);

        adapter = new ItemListAdapter<>(this);
        adapter.setTableData(tableData);
        list_unComplete.setExpanded(true);
        list_unComplete.setAdapter(adapter);


        searchAdapter = new ItemListAdapter<>(this);
        searchAdapter.setTableData(tableData);
        list_progress.setExpanded(true);
        list_progress.setAdapter(searchAdapter);

        search_text.setOnClickListener(this);
    }


    @Override
    public void bindUnCompleteOrderItem(List<ErpOrderItem> datas) {
        adapter.setDataArray(datas);
    }


    @Override
    public void showUnCompletePanel() {

        unDeliveryList.setSelected(true)
        ;
        list_unComplete.setVisibility(View.VISIBLE);
        progressSearch.setSelected(false);
        panel_progress.setVisibility(View.GONE);
    }

    /**
     * 查询结果数据绑定
     *
     * @param datas
     */
    @Override
    public void bindSearchOrderItemResult(List<ErpOrderItem> datas) {

        searchAdapter.setDataArray(datas);
    }

    public void showProgressPanel() {
        unDeliveryList.setSelected(false);
        list_unComplete.setVisibility(View.GONE);
        progressSearch.setSelected(true);
        panel_progress.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onViewClick(int id, View v) {
        super.onViewClick(id, v);
        switch (id) {
            case R.id.unDeliveryList:
                showUnCompletePanel();
                break

                        ;
            case R.id.progressSearch:

                showProgressPanel();
                break;
            case R.id.search_text:

                searchOrderItems();
                break;
        }
    }

    private void searchOrderItems() {


        String value = key.getText().toString().trim();

        getPresenter().searchOrderItemWorkFlow(value);

    }
}