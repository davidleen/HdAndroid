package com.giants3.hd.android.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintManager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.giants3.android.frame.util.Log;
import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.fragment.ItemPickDialogFragment;
import com.giants3.hd.android.fragment.SearchProductFragment;
import com.giants3.hd.android.fragment.ValueEditDialogFragment;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.mvp.appquotationdetail.AppQuotationDetailMVP;
import com.giants3.hd.android.mvp.appquotationdetail.PresenterImpl;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.QRProduct;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.entity.User;
import com.giants3.hd.entity.app.QuotationItem;
import com.giants3.hd.noEntity.app.QuotationDetail;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import butterknife.Bind;

public class AppQuotationActivity extends BaseHeadViewerActivity<AppQuotationDetailMVP.Presenter> implements AppQuotationDetailMVP.Viewer, SearchProductFragment.OnFragmentInteractionListener {

    public static final String KEY_QUOTATION_ID = "KEY_QUOTATION_ID";


    @Bind(R.id.pickItem)
    ImageView pickItem;

    @Bind(R.id.scanItem)
    ImageView scanItem;
    @Bind(R.id.discountAll)
    View discountAll;

    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.createTime)
    TextView createTime;
    @Bind(R.id.customer)
    TextView customer;

    @Bind(R.id.salesman)
    TextView salesman;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.print)
    TextView print;
    @Bind(R.id.memo)
    TextView memo;
    @Bind(R.id.addCustomer)
    TextView addCustomer;
    @Bind(R.id.quotation_item_list)
    ListView quotation_item_list;
    ItemListAdapter<QuotationItem> adapter;

    boolean isEditable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected View getContentView() {
        return getLayoutInflater().inflate(R.layout.activity_app_quotation, null);
    }

    @Override
    protected AppQuotationDetailMVP.Presenter onLoadPresenter() {
        return new PresenterImpl();
    }


    @Override
    protected void initEventAndData() {


        adapter = new ItemListAdapter(this);
        TableData tableData = TableData.resolveData(this, R.array.table_app_quotation_item);
        adapter.setTableData(tableData);
        quotation_item_list.setAdapter(adapter);
        adapter.setCellClickListener(new ItemListAdapter.CellClickListener<QuotationItem>() {
            @Override
            public boolean isCellClickable(String field) {


                if (isEditable) {

                    switch (field) {
                        case "price":
                            return true;
                        case "qty":
                            return true;
                    }


                }


                return false;


            }

            @Override
            public void onCellClick(String field, final QuotationItem data, int position) {


                switch (field) {
                    case "price": {


                        updateValue("修改单价", String.valueOf(data.price), new ValueEditDialogFragment.ValueChangeListener() {
                            @Override
                            public void onValueChange(String title, String oldValue, String newValue) {
                                try {

                                    float newFloatValue = Float.valueOf(newValue.trim());

                                    if (Float.compare(newFloatValue, data.price) != 0) {
                                        data.price = newFloatValue;
                                        adapter.notifyDataSetChanged();


                                        getPresenter().updatePrice(data.itm, newFloatValue);
                                    }
                                } catch (Throwable t) {
                                   Log.e(t);
                                }


                            }
                        });

                    }

                    ;
                    break;


                    case "qty": {


                        updateValue("修改数量", String.valueOf(data.qty)
                                , new ValueEditDialogFragment.ValueChangeListener() {
                                    @Override
                                    public void onValueChange(String title, String oldValue, String newValue) {
                                        try {

                                            int newQty = Integer.valueOf(newValue.trim());

                                            if (Integer.compare(newQty, data.qty) != 0) {
                                                data.qty = newQty;
                                                adapter.notifyDataSetChanged();


                                                getPresenter().updateQty(data.itm, newQty);
                                            }
                                        } catch (Throwable t) {
                                            t.printStackTrace();
                                        }


                                    }
                                });


                    }


                    ;
                    break;
                }


                Log.e(  data.toString());

            }
        });
        quotation_item_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final QuotationItem item = (QuotationItem) parent.getItemAtPosition(position);
                if (item != null) {
                    new AlertDialog.Builder(AppQuotationActivity.this).setItems(new String[]{"删除", "折扣", "上移"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {

                                case 0:
                                    getPresenter().deleteQuotationItem(item.itm);
                                    dialog.dismiss();
                                    break;
                                case 1:


                                    updateValue("设置折扣", "0", new ValueEditDialogFragment.ValueChangeListener() {
                                        @Override
                                        public void onValueChange(String title, String oldValue, String newValue) {
                                            float newDisCount = 0;
                                            try {
                                                newDisCount = Float.valueOf(newValue.trim());


                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                            }
                                            if (newDisCount <= 0 || newDisCount > 1) {

                                                ToastHelper.show("不合法的输入值：" + newDisCount + ",取值范围(0,1]");

                                            } else {


                                                getPresenter().updateItemDiscount(item.itm, newDisCount);

                                            }


                                        }
                                    });
                                    break;

                            }


                        }
                    }).create().show();
                }


                return true;


            }
        });


        pickItem.setOnClickListener(this);
        scanItem.setOnClickListener(this);
        discountAll.setOnClickListener(this);
        save.setOnClickListener(this);
        print.setOnClickListener(this);
        customer.setOnClickListener(this);
        addCustomer.setOnClickListener(this);


        long quotationId = getIntent().getLongExtra(KEY_QUOTATION_ID, -1);
        getPresenter().setQuotationId(quotationId);

    }


    private void updateValue(String title, String value, ValueEditDialogFragment.ValueChangeListener listener) {
        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set(title, value, listener);
        dialogFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    public void bindData(QuotationDetail data) {


        createTime.setText(data.quotation.qDate);
        name.setText(data.quotation.qNumber);
        customer.setText(data.quotation.customerName);
        salesman.setText(data.quotation.salesman);
        memo.setText(data.quotation.memo);


        adapter.setDataArray(data.items);


    }

    public static void start(Context context, long id) {
        Intent intent = new Intent(context, AppQuotationActivity.class);
        intent.putExtra(AppQuotationActivity.KEY_QUOTATION_ID, id);
        context.startActivity(intent);
    }

    public static void start(Context context) {

        start(context, -1);

    }

    @Override
    protected void onViewClick(int id, View v) {

        switch (id) {
            case R.id.pickItem:
                SearchProductFragment fragment = SearchProductFragment.newInstance();
                fragment.show(getSupportFragmentManager(), "dialog9999");

                break;

            case R.id.save:


                getPresenter().saveQuotation();

                break;

                case R.id.customer:


                getPresenter().pickCustomer();

                break;
            case R.id.print:


                getPresenter().printQuotation();

                break;
                case R.id.addCustomer:


                 addNewCustomer();

                break;
            case R.id.discountAll:


                updateValue("设置全部折扣", "0", new ValueEditDialogFragment.ValueChangeListener() {
                    @Override
                    public void onValueChange(String title, String oldValue, String newValue) {
                        float newDisCount = 0;
                        try {
                            newDisCount = Float.valueOf(newValue.trim());
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                        if (newDisCount <= 0 || newDisCount > 1) {

                            ToastHelper.show("不合法的输入值：" + newDisCount + ",取值范围(0,1]");

                        } else {


                            getPresenter().updateQuotationDiscount(newDisCount);

                        }


                    }


                });

                break;
            case R.id.scanItem:


                IntentIntegrator integrator = new IntentIntegrator(AppQuotationActivity.this);
//                 integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
//                 integrator.setPrompt("Scan a barcode");
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("扫描产品二维码");


                integrator.setCaptureActivity(QRCaptureActivity.class);
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(false);
                integrator.initiateScan();
                break;
        }


    }

    private void addNewCustomer() {


        // CustomerEditActivity.start(this,REQUEST_CODE_ADD_CUSTOMER);



    }

    @Override
    public void onProductSelect(AProduct aProduct) {

        getPresenter().addNewProduct(aProduct.id);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);


        if (result != null) {


            try {
                QRProduct product = GsonUtils.fromJson(result.getContents(), QRProduct.class);

                if (product != null) {
                    getPresenter().addNewProduct(product.id);
                }
                Log.i(  "result:" + product);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }


    @Override
    public void onBackPressed() {


        getPresenter().goBack();


    }


    @Override
    public void showUnSaveAlert() {


        new AlertDialog.Builder(this).setTitle("未保存报价单，确定退出?").setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //do nothing

            }
        }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                finish();





            }
        }).create().show();


    }


    @Override
    public void exit() {
        finish();
    }


    @Override
    public void chooseCustomer(Customer current, List<Customer> customers) {



        ItemPickDialogFragment<Customer> dialogFragment = new ItemPickDialogFragment<Customer>();
        dialogFragment.set("选择客户", customers, current, new ItemPickDialogFragment.ValueChangeListener<Customer>() {
            @Override
            public void onValueChange(String title, Customer oldValue, Customer newValue) {

                getPresenter().updateCustomer(newValue);

            }
        });
        dialogFragment.show(getSupportFragmentManager(), null);

    }
}
