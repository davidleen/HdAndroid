package com.giants3.hd.android.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.giants3.hd.android.R;
import com.giants3.hd.android.mvp.customer.CustomerEditMVP;

public class CustomerEditActivity extends BaseHeadViewerActivity<CustomerEditMVP.Presenter> implements CustomerEditMVP.Viewer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);

    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected CustomerEditMVP.Presenter onLoadPresenter() {
        return null;
    }

    @Override
    protected void initEventAndData() {

    }

}
