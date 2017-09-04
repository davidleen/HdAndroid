package com.giants3.hd.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.mvp.orderitemworkflowmaterial.MVP;
import com.giants3.hd.android.mvp.orderitemworkflowmaterial.PresenterImpl;
import com.giants3.hd.android.widget.ExpandableHeightListView;
import com.giants3.hd.entity_erp.WorkFlowMaterial;

import java.util.List;

import butterknife.Bind;

/**
 * Created by davidleen29 on 2017/6/25.
 */

public class OrderItemWorkFlowMaterialActivity extends BaseViewerActivity<MVP.Presenter> implements MVP.Viewer  {
    private static final int REQUEST_MESSAGE_OPERATE = 1000;

    private static final String KEY_WORK_FLOW_CODE = "KEY_WORK_FLOW_CODE";

    private static final String KEY_OS_NO = "KEY_OS_NO";
    private static final String KEY_ITM = "KEY_ITM";
    @Bind(R.id.detail_toolbar)
    Toolbar toolbar;


    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;


    @Bind(R.id.list_material)
    ExpandableHeightListView list_material;

    ItemListAdapter<WorkFlowMaterial> adapter;

    @Override
    protected MVP.Presenter onLoadPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_order_item_work_flow_material);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("订单流程物料列表记录");

        }




    }

    @Override
    public void bindData(List<WorkFlowMaterial> datas) {
        adapter.setDataArray(datas);
    }

    @Override
    protected void initEventAndData() {






        String osNo=getIntent().getStringExtra(KEY_OS_NO);
        int itm=getIntent().getIntExtra(KEY_ITM,1);
        String code=getIntent().getStringExtra(KEY_WORK_FLOW_CODE);

        getPresenter().setErpWorkFlowInfo(osNo,itm,code);

        TableData tableData = TableData.resolveData(this, R.array.table_work_flow_material);

        adapter   = new ItemListAdapter<>(this);
        adapter.setTableData(tableData);
        adapter.setRowHeight( 50 );
        list_material.setExpanded(true);
        list_material.setAdapter(adapter);


        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                getPresenter().loadData();

            }
        });




    }

    @Override
    public void showWaiting() {



        swipeLayout.setRefreshing(true);
    }

    @Override
    public void hideWaiting() {
        swipeLayout.setRefreshing(false);
    }

//    public static void start(Activity activity, ErpWorkFlowReport workFlowReport, int requestCode) {
//
////
////        Intent intent=new Intent(activity,OrderItemWorkFlowMaterialActivity.class);
////
////
////        activity.startActivityForResult(intent, requestCode);
//
//    }

    /**
     *
     * @param activity
     * @param osNO  订单
     * @param itm   订单项次
     * @param code  流程标号
     * @param requestCode
     */
    public static void start(Activity activity,String osNO,int  itm, String  code , int requestCode) {


        Intent intent=new Intent(activity,OrderItemWorkFlowMaterialActivity.class);

        intent.putExtra(OrderItemWorkFlowMaterialActivity.KEY_OS_NO, osNO);
        intent.putExtra(OrderItemWorkFlowMaterialActivity.KEY_ITM, itm);
        intent.putExtra(OrderItemWorkFlowMaterialActivity.KEY_WORK_FLOW_CODE,code);
        activity.startActivityForResult(intent, requestCode);

    }
}
