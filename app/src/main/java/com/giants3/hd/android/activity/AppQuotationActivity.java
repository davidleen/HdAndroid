package com.giants3.hd.android.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.giants3.android.api.qrCode.QRCodeResult;
import com.giants3.android.frame.util.Log;
import com.giants3.android.zxing.QRCodeFactory;
import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.events.CustomerUpdateEvent;
import com.giants3.hd.android.fragment.ItemPickDialogFragment;
import com.giants3.hd.android.fragment.ProductDetailFragment;
import com.giants3.hd.android.fragment.SearchProductFragment;
import com.giants3.hd.android.fragment.ValueEditDialogFragment;
import com.giants3.hd.android.helper.AuthorityUtil;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.mvp.AndroidRouter;
import com.giants3.hd.android.mvp.appquotationdetail.AppQuotationDetailMVP;
import com.giants3.hd.android.mvp.appquotationdetail.PresenterImpl;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.appdata.QRProduct;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.entity.User;
import com.giants3.hd.entity.app.QuotationItem;
import com.giants3.hd.noEntity.app.QuotationDetail;
import com.giants3.hd.utils.StringUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

import static com.giants3.hd.utils.DateFormats.FORMAT_YYYY_MM_DD;


public class AppQuotationActivity extends BaseHeadViewerActivity<AppQuotationDetailMVP.Presenter> implements AppQuotationDetailMVP.Viewer, SearchProductFragment.OnFragmentInteractionListener {

    public static final String KEY_QUOTATION_ID = "KEY_QUOTATION_ID";
    private static final int REQUEST_CODE_ADD_CUSTOMER = 998;


    @Bind(R.id.pickItem)
    ImageView pickItem;

    @Bind(R.id.scanItem)
    ImageView scanItem;
    @Bind(R.id.discountAll)
    View discountAll;

    @Bind(R.id.qNumber)
    TextView qNumber;
    @Bind(R.id.createTime)
    TextView createTime;
    @Bind(R.id.validateTime)
    TextView validateTime;
    @Bind(R.id.customer)
    TextView customer;

    @Bind(R.id.salesman)
    TextView salesman;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.delete)
    View delete;
    @Bind(R.id.print)
    TextView print;
    @Bind(R.id.memo)
    TextView memo;
    @Bind(R.id.addCustomer)
    TextView addCustomer;
    @Bind(R.id.quotation_item_list)
    ListView quotation_item_list;
    ItemListAdapter<QuotationItem> adapter;

    boolean isCellEditable = true;

    boolean canViewProduct = false;

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


                switch (field) {
                    case "price":
                        return isCellEditable;
                    case "qty":
                        return isCellEditable;
                    case "memo":
                        return isCellEditable;
                    case "productName":
                        return canViewProduct;

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
                    case "memo": {


                        updateValue("修改备注", data.memo == null ? "" : data.memo, true
                                , new ValueEditDialogFragment.ValueChangeListener() {
                                    @Override
                                    public void onValueChange(String title, String oldValue, String newValue) {
                                        try {
                                            if (!StringUtils.compare(newValue, data.memo)) {


                                                getPresenter().updateMemo(data.itm, newValue);
                                            }
                                        } catch (Throwable t) {
                                            t.printStackTrace();
                                        }


                                    }
                                });


                    }


                    ;
                    break;

                    case "productName": {

                        //跳转产品详情
                        AProduct aProduct = new AProduct();
                        aProduct.id = data.productId;
                        //调整act
                        Intent intent = new Intent(AppQuotationActivity.this, ProductDetailActivity.class);
                        intent.putExtra(ProductDetailFragment.ARG_ITEM, GsonUtils.toJson(aProduct));
                        startActivity(intent);


                    }


                    ;
                    break;
                }


                Log.e(data.toString());

            }
        });
        quotation_item_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final QuotationItem item = (QuotationItem) parent.getItemAtPosition(position);
                if (item != null) {
                    new AlertDialog.Builder(AppQuotationActivity.this).setItems(new String[]{"删除", "折扣"}, new DialogInterface.OnClickListener() {
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
        delete.setOnClickListener(this);
        validateTime.setOnClickListener(this);
        createTime.setOnClickListener(this);


        long quotationId = getIntent().getLongExtra(KEY_QUOTATION_ID, -1);
        getPresenter().setQuotationId(quotationId);

    }

    private void updateValue(String title, String value, ValueEditDialogFragment.ValueChangeListener listener) {
        updateValue(title, value, false, listener);
    }

    private void updateValue(String title, String value, boolean multiableText, ValueEditDialogFragment.ValueChangeListener listener) {
        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set(title, value, listener);
        dialogFragment.setMultiableText(multiableText);
        dialogFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    public void bindData(QuotationDetail data) {


        createTime.setText(data.quotation.qDate);

        qNumber.setText(data.quotation.qNumber);
        customer.setText(data.quotation.customerName);
        salesman.setText(data.quotation.salesman);
        memo.setText(data.quotation.memo);
        validateTime.setText(data.quotation.vDate);


        adapter.setDataArray(data.items);


        boolean canEdit = false;


        AUser loginUser = SharedPreferencesHelper.getLoginUser();
        if (loginUser != null && data.quotation != null && ((loginUser.isSalesman && data.quotation.saleId == loginUser.id) || loginUser.name.equals(User.ADMIN))) {
            canEdit = true;
        }
        isCellEditable = canEdit;
        canViewProduct = AuthorityUtil.getInstance().viewProductList();
        setEditable(createTime, canEdit);
        setEditable(validateTime, canEdit);
        setEditable(customer, canEdit);
        setEditable(memo, canEdit);
        setEditable(qNumber, canEdit);


    }

    private void setEditable(TextView v, boolean isEditable) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            v.setTextAppearance(isEditable ? R.style.value_edit_text_style : R.style.value_text_style);
        } else {
            v.setCompoundDrawablesWithIntrinsicBounds(0, 0, isEditable ? R.mipmap.ic_menu_edit : 0, 0);
        }
        v.setOnClickListener(isEditable ? this : null);

    }


    public static void start(AndroidRouter router, long id) {


        start(router, id, -1);
    }

    public static void start(AndroidRouter router, long id, int requestCode) {
        Intent intent = new Intent(router.getContext(), AppQuotationActivity.class);
        intent.putExtra(AppQuotationActivity.KEY_QUOTATION_ID, id);
        router.startActivityForResult(intent, requestCode);
    }

    public static void start(AndroidRouter router) {

        start(router, -1);

    }

    @Override
    protected void onViewClick(final int id, View v) {

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


            case R.id.validateTime:
            case R.id.createTime:


                Date date = null;

                try {
                    String text = (id == R.id.validateTime ? validateTime : createTime).getText().toString().trim();
                    date = FORMAT_YYYY_MM_DD.parse(text);
                } catch (ParseException e) {
                    e.printStackTrace();

                }
                final Calendar calendar = Calendar.getInstance();
                if (date != null)
                    calendar.setTime(date);
                DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        calendar.set(year, monthOfYear, dayOfMonth);
                        String dateString = FORMAT_YYYY_MM_DD.format(calendar.getTime());
                        if (id == R.id.validateTime)
                            getPresenter().updateValidateTime(dateString);
                        else
                            getPresenter().updateCreateTime(dateString);


                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();


                break;


            case R.id.print:


                getPresenter().printQuotation();

                break;


            case R.id.memo: {

                final String text = memo.getText().toString();
                updateValue("修改备注", text, true
                        , new ValueEditDialogFragment.ValueChangeListener() {
                            @Override
                            public void onValueChange(String title, String oldValue, String newValue) {
                                try {


                                    if (!StringUtils.compare(newValue, text)) {


                                        getPresenter().updateQuotationMemo(newValue);
                                    }
                                } catch (Throwable t) {
                                    t.printStackTrace();
                                }


                            }
                        });


            }

            break;

            case R.id.qNumber: {

                final String oldQnumber = qNumber.getText().toString();
                updateValue("修改报价单号", oldQnumber,
                        new ValueEditDialogFragment.ValueChangeListener() {
                            @Override
                            public void onValueChange(String title, String oldValue, String newValue) {
                                try {


                                    if (!StringUtils.compare(newValue, oldQnumber)) {


                                        getPresenter().updateQuotationNumber(newValue);
                                    }
                                } catch (Throwable t) {
                                    t.printStackTrace();
                                }


                            }
                        });


            }

            break;
            case R.id.addCustomer:


                addNewCustomer();

                break;
            case R.id.delete:
                new AlertDialog.Builder(AppQuotationActivity.this).setTitle("确定删除报价单?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().deleteQuotation();
                    }
                }).setNegativeButton("取消", null).create().show();


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


                startScanProduct();


                break;
        }


    }


    protected void startScanProduct() {
        QRCodeFactory.start(this, "扫描产品二维码");
    }


    private void addNewCustomer() {


        CustomerEditActivity.start(this, REQUEST_CODE_ADD_CUSTOMER);


    }

    @Override
    public void onProductSelect(AProduct aProduct) {

        getPresenter().addNewProduct(aProduct.id);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        QRCodeResult qrCodeResult = QRCodeFactory.onActivityResult(requestCode, resultCode, data);


        if (qrCodeResult != null) {
            try {
                QRProduct product = GsonUtils.fromJson(qrCodeResult.contents, QRProduct.class);

                if (product != null) {
                    getPresenter().addNewProduct(product.id);

                }
                Log.i("result:" + product);
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


    @Override
    public void setResultOK() {
        setResult(RESULT_OK);
    }

    /**
     * @param event
     * @author davidleen29
     */
    public void onEvent(CustomerUpdateEvent event) {

        getPresenter().loadCustomer();
    }
}
