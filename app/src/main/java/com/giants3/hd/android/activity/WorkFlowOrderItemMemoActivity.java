package com.giants3.hd.android.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.mvp.workflowmemo.PresenterImpl;
import com.giants3.hd.android.mvp.workflowmemo.WorkFlowOrderItemMemoMVP;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.ErpWorkFlow;
import com.giants3.hd.entity.OrderItemWorkMemo;
import com.giants3.hd.entity.ProductWorkMemo;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.noEntity.WorkFlowMemoAuth;
import com.giants3.hd.utils.StringUtils;

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


    @Bind(R.id.check)
    View check;


    @Bind(R.id.unCheck)
    View unCheck;

    @Bind(R.id.checkState)
    TextView checkState;

    @Bind(R.id.checker)
    TextView checker;

    @Bind(R.id.checkTime)
    TextView checkTime;

    @Bind(R.id.modifier)
    TextView modifier;

    @Bind(R.id.modifyTime)
    TextView modifyTime;


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




                getPresenter().save( );
            }
        });
        conceptusManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPresenter().setSelectStep(ErpWorkFlow.STEPS[1],true);
            }
        });
        colorManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().setSelectStep(ErpWorkFlow.STEPS[2],true);
            }
        });
        packManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().setSelectStep(ErpWorkFlow.STEPS[3],true);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().check();
            }
        });

        unCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().unCheck();
            }
        });
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

        getPresenter().setOrderItem(orderItem);


    }


    private void doSearchWorkFlowReport() {


    }

    TextWatcher productWorkMemoWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateWorkFlowMemo();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
      TextWatcher orderItemWorkMemoWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {




        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            updateWorkFlowMemo();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



    private  void updateWorkFlowMemo()
    {
        String productWorkFlwoMemo=productWorkMemoView.getText().toString().trim();
        String orderItemWorkFlwoMemo=orderItemWorkMemoView.getText().toString().trim();
        getPresenter().setWorkFlowMemo(productWorkFlwoMemo,orderItemWorkFlwoMemo);
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
        productWorkMemoView.removeTextChangedListener(productWorkMemoWatcher);
        String text = productWorkMemo == null ? "" : productWorkMemo.memo;
        productWorkMemoView.setText(text);
        productWorkMemoView.setSelection(text.length());
        productWorkMemoView.addTextChangedListener(productWorkMemoWatcher);



    }

    @Override
    public void bindOrderItemWorkMemo(OrderItemWorkMemo orderItemWorkMemo) {
        orderItemWorkMemoView.removeTextChangedListener( orderItemWorkMemoWatcher);
        String text = orderItemWorkMemo == null ? "" : orderItemWorkMemo.memo;
        orderItemWorkMemoView.setText(text);
        orderItemWorkMemoView.setSelection(text.length());
        orderItemWorkMemoView.addTextChangedListener( orderItemWorkMemoWatcher);






        modifier.setText(orderItemWorkMemo==null|| StringUtils.isEmpty(orderItemWorkMemo.lastModifierName)?"":orderItemWorkMemo.lastModifierName);
        modifyTime.setText(orderItemWorkMemo==null|| StringUtils.isEmpty(orderItemWorkMemo.lastModifyTimeString)?"":orderItemWorkMemo.lastModifyTimeString);



        checkState.setText(orderItemWorkMemo!=null&&orderItemWorkMemo.checked?"已经审核":"未审核");
        checker.setText(orderItemWorkMemo==null|| StringUtils.isEmpty(orderItemWorkMemo.lastCheckerName)?"":orderItemWorkMemo.lastCheckerName);
        checkTime.setText(orderItemWorkMemo==null|| StringUtils.isEmpty(orderItemWorkMemo.lastCheckTimeString)?"":orderItemWorkMemo.lastCheckTimeString);
    }

    @Override
    public void bindSeleteWorkFlowStep(int workFlowStep) {
        conceptusManage.setSelected(workFlowStep==ErpWorkFlow.STEPS[1]);
        colorManage.setSelected(workFlowStep==ErpWorkFlow.STEPS[2]);
        packManage.setSelected(workFlowStep==ErpWorkFlow.STEPS[3]);
    }


    @Override
    public void showUnSaveEditDialog(final int nextStep) {


        AlertDialog alertDialog =new AlertDialog.Builder(this).setMessage("当前录入数据未保存，是否保存？").setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getPresenter().save();

            }
        }).setNegativeButton("不保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getPresenter().setSelectStep(nextStep,false);

            }
        }).create();

        alertDialog.show();


    }


    @Override
    public void onBackPressed() {

        if(getPresenter().hasNewWorkFlowMemo())
        {
            AlertDialog alertDialog =new AlertDialog.Builder(this).setMessage("当前录入数据未保存，是否退出？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                   finish();

                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {



                }
            }).create();

            alertDialog.show();


        }else
        super.onBackPressed();
    }

    @Override
    public void bindWorkFlowAuth(WorkFlowMemoAuth workFlowMemoAuth, OrderItemWorkMemo orderItem) {


        if(workFlowMemoAuth==null)
        {

            save.setVisibility(View.GONE);
            check.setVisibility(View.GONE);
            unCheck.setVisibility(View.GONE);
        }else
        {

            boolean itemHasChecked=orderItem!=null&& orderItem.checked;
            save.setVisibility(workFlowMemoAuth.modifiable&&!itemHasChecked?View.VISIBLE:View.GONE);




            check.setVisibility(workFlowMemoAuth.checkable&&!itemHasChecked?View.VISIBLE:View.GONE);
            unCheck.setVisibility(workFlowMemoAuth.checkable&&itemHasChecked?View.VISIBLE:View.GONE);


        }

    }
}
