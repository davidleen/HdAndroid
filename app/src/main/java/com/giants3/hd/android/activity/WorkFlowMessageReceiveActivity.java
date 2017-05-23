package com.giants3.hd.android.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.giants3.hd.android.R;
import com.giants3.hd.android.mvp.WorkFlowMessageReceive;
import com.giants3.hd.android.mvp.workflowmessagereceive.PresenterImpl;

import static com.giants3.hd.android.mvp.WorkFlowMessageReceive.*;

/**
 * 流程接受处理事件
 */
public class WorkFlowMessageReceiveActivity extends BaseViewerActivity< Presenter>  implements Viewer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_flow_message_receive);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected Presenter onLoadPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initEventAndData() {

    }

}
