package com.giants3.hd.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.mvp.workflowmemo.PresenterImpl;
import com.giants3.hd.android.mvp.workflowmemo.WorkFlowOrderItemMemoMVP;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.entity.ErpWorkFlow;
import com.giants3.hd.utils.entity.OrderItemWorkMemo;
import com.giants3.hd.utils.entity.ProductWorkMemo;
import com.giants3.hd.utils.entity_erp.ErpWorkFlowOrderItem;

import butterknife.Bind;

/**
 * 订单生产备注界面备注
 */
public class WorkFlowOrderItemMemoActivity extends BaseViewerActivity<WorkFlowOrderItemMemoMVP.Presenter> implements WorkFlowOrderItemMemoMVP.Viewer {


    public static final int REQUEST_CODE = 33;
    public static final String KEY_ORDER_ITEM = "KEY_ORDER_ITEM";
    @Bind(R.id.detail_toolbar)
    Toolbar toolbar;

    @Bind(R.id.conceptusManage)
    TextView conceptusManage;

    @Bind(R.id.colorManage)
    TextView colorManage;

    @Bind(R.id.packManage)
    TextView packManage;


    @Bind(R.id.productWorkMemo)
    EditText productWorkMemoView;


    @Bind(R.id.orderItemWorkMemo)
    EditText orderItemWorkMemoView;


    @Bind(R.id.save)
    View save;



    @Override
    protected WorkFlowOrderItemMemoMVP.Presenter onLoadPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_work_flow_memo);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("订单生产备注");

        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String productWorkMemo=productWorkMemoView.getText().toString();
                String orderItemWorkMemo=orderItemWorkMemoView .getText().toString();


                getPresenter().save(productWorkMemo,orderItemWorkMemo);
            }
        });
        conceptusManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPresenter().setSelectStep(ErpWorkFlow.STEPS[1]);
            }
        });
        colorManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().setSelectStep(ErpWorkFlow.STEPS[2]);
            }
        });
        packManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().setSelectStep(ErpWorkFlow.STEPS[3]);
            }
        });
    }

    @Override
    protected void initEventAndData() {


        ErpWorkFlowOrderItem orderItem = null;
        try {
            orderItem = GsonUtils.fromJson(getIntent().getStringExtra(KEY_ORDER_ITEM), ErpWorkFlowOrderItem.class);
        } catch (HdException e) {
            e.printStackTrace();

            showMessage("未发现传递的订单数据");
            finish();
            return;
        }

        getPresenter().setOrderItem(orderItem);


    }


    private void doSearchWorkFlowReport() {


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            doSearchWorkFlowReport();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void bindProductWorkMemo(ProductWorkMemo productWorkMemo) {

        productWorkMemoView.setText(productWorkMemo==null?"":productWorkMemo.memo);
    }

    @Override
    public void bindOrderItemWorkMemo(OrderItemWorkMemo orderItemWorkMemo) {
        orderItemWorkMemoView.setText(orderItemWorkMemo==null?"":orderItemWorkMemo.memo);
    }

    @Override
    public void bindSeleteWorkFlowStep(int workFlowStep) {
        conceptusManage.setSelected(workFlowStep==ErpWorkFlow.STEPS[1]);
        colorManage.setSelected(workFlowStep==ErpWorkFlow.STEPS[2]);
        packManage.setSelected(workFlowStep==ErpWorkFlow.STEPS[3]);
    }
}
