package com.giants3.hd.android.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.fragment.SearchProductFragment;
import com.giants3.hd.android.fragment.SendWorkFlowFragment;
import com.giants3.hd.android.fragment.ValueEditDialogFragment;
import com.giants3.hd.android.mvp.appquotationdetail.AppQuotationDetailMVP;
import com.giants3.hd.android.mvp.appquotationdetail.PresenterImpl;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.QRProduct;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.app.Quotation;
import com.giants3.hd.entity.app.QuotationItem;
import com.giants3.hd.noEntity.app.QuotationDetail;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import butterknife.Bind;

public class AppQuotationActivity extends BaseHeadViewerActivity<AppQuotationDetailMVP.Presenter> implements AppQuotationDetailMVP.Viewer,SearchProductFragment.OnFragmentInteractionListener {

    public static final String KEY_QUOTATION_ID="KEY_QUOTATION_ID";


    @Bind(R.id.pickItem)
    ImageView pickItem;

    @Bind(R.id.scanItem)
    ImageView scanItem;

    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.createTime)
    TextView createTime;
    @Bind(R.id.customer)
    TextView customer;

    @Bind(R.id.salesman)
    TextView salesman;
    @Bind(R.id.memo)
    TextView memo;
    @Bind(R.id.quotation_item_list)
    ListView quotation_item_list;
    ItemListAdapter<QuotationItem>   adapter;

    boolean isEditable=true;
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


                if(isEditable)
                {

                    switch (field)
                    {
                        case "price":return true;
                        case "qty":return true;
                    }



                }



                return  false;







            }

            @Override
            public void onCellClick(String field, final QuotationItem data, int position) {



                switch (field)
                {
                    case "price": {

                        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
                        dialogFragment.set("修改单价", String.valueOf(data.price), new ValueEditDialogFragment.ValueChangeListener() {
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
                                    t.printStackTrace();
                                }


                            }
                        });
                        dialogFragment.show(getSupportFragmentManager(), null);


                    }

                        ;break;


                    case "qty": {

                        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
                        dialogFragment.set("修改数量", String.valueOf(data.qty), new ValueEditDialogFragment.ValueChangeListener() {
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
                        dialogFragment.show(getSupportFragmentManager(), null);

                    }


                        ;break;
                }






                Log.e(TAG,data.toString());

            }
        });
        quotation_item_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

               final QuotationItem item= (QuotationItem) parent.getItemAtPosition(position);
                if(item!=null) {
                    new AlertDialog.Builder(AppQuotationActivity.this).setItems(new String[]{"删除", "上移"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            switch (which) {

                                case 0:
                                    getPresenter().deleteQuotationItem(item.itm);
                                    dialog.dismiss();
                                    break;
                                case 1:
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

        long quotationId=getIntent().getLongExtra(KEY_QUOTATION_ID,-1);
        getPresenter().setQuotationId(quotationId);

    }



    @Override
    public void bindData(QuotationDetail data) {



        createTime.setText(data.quotation.qDate);
        name.setText(data.quotation.qNumber);
        customer.setText(data.quotation.customerCode);
        salesman.setText(data.quotation.salesman);
        memo.setText(data.quotation.memo);


        adapter.setDataArray(data.items);



    }

    public static void start(Context context, long id) {
        Intent intent = new Intent(context, AppQuotationActivity.class);
        intent.putExtra(AppQuotationActivity.KEY_QUOTATION_ID,id);
        context.startActivity(intent);
    }

    public static void start(Context context) {

        start( context,-1);

    }

    @Override
    protected void onViewClick(int id, View v) {

        switch (id)
        {
            case  R.id.pickItem:
                SearchProductFragment fragment = SearchProductFragment.newInstance( );
                fragment.show(getSupportFragmentManager(), "dialog9999");

                getPresenter().pickNewProduct();


                break;
            case  R.id.scanItem:

              //  getPresenter().scanNewProduct();

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

                if(product!=null)
                {
                    getPresenter().addNewProduct(product.id);
                }
                Log.d("TEST", "result:" + product);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
