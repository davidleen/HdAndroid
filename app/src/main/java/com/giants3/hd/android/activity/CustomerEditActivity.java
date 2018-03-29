package com.giants3.hd.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

//import com.giants3.android.namecardscan.activity.*;
import com.giants3.hd.android.R;
import com.giants3.hd.android.events.CustomerUpdateEvent;
import com.giants3.hd.android.mvp.AndroidRouter;
import com.giants3.hd.android.mvp.customer.CustomerEditMVP;
import com.giants3.hd.android.mvp.customer.PresenterImpl;
import com.giants3.hd.utils.StringUtils;

import butterknife.Bind;
//import cn.sharp.android.ncr.ocr.OCRItems;

public class CustomerEditActivity extends BaseHeadViewerActivity<CustomerEditMVP.Presenter> implements CustomerEditMVP.Viewer {



    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.namecard)
    TextView namecard;
    @Bind(R.id.nation)
    TextView nation;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.email)
    TextView email;
    @Bind(R.id.fax)
    TextView fax;
    @Bind(R.id.tel)
    TextView tel;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.code)
    TextView code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String codeText=code.getText().toString().trim();
                String nameText=name.getText().toString().trim();
                String telText=tel.getText().toString().trim();
                String faxText=fax.getText().toString().trim();
                String addressText=address.getText().toString().trim();
                String nationText=nation.getText().toString().trim();
                String emailText=email.getText().toString().trim();

                getPresenter().updateValue(codeText,nameText,telText,faxText,emailText,addressText,nationText);
                getPresenter().save();
            }
        });

        namecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//               NameCardScannerActivity.startActivity(CustomerEditActivity.this, NameCardScannerActivity.SCAN_MODE_PREVIEW);
//                 getPresenter().scanNameCard();

            }
        });



    }

    @Override
    protected View getContentView() {
        return getLayoutInflater().inflate(R.layout.activity_customer_edit,null);
    }

    @Override
    protected CustomerEditMVP.Presenter onLoadPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected void initEventAndData() {

    }


    public static void start(AndroidRouter communicator, int requestCode)
    {
        Intent intent=new Intent(communicator.getContext(),CustomerEditActivity.class);
        communicator.startActivityForResult(intent,requestCode);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if(resultCode==RESULT_OK&&requestCode==NameCardScannerActivity.SCAN_MODE_PREVIEW)
//        {
//
//            OCRItems items= (OCRItems) data.getSerializableExtra(NameCardScannerActivity.KEY_SCAN_RESULT);
//
////            if(StringUtils.isEmpty(items.email))
////            {
////
////            }
//        }
    }
}
