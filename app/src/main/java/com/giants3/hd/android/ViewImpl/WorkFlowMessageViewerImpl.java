package com.giants3.hd.android.ViewImpl;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.WorkFlowMessageAdapter;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.presenter.WorkFlowMessagePresenter;
import com.giants3.hd.android.viewer.WorkFlowMessageViewer;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity.WorkFlowMessage;

import butterknife.Bind;

/**
 * 订单流程处理界面
 * Created by davidleen29 on 2016/9/23.
 */

public class WorkFlowMessageViewerImpl extends BaseViewerImpl implements WorkFlowMessageViewer {


    private final WorkFlowMessagePresenter presenter;
    @Bind(R.id.list)
    ListView listView;

    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;




    @Bind(R.id.mySend)
    View mySend;

    @Bind(R.id.myReceive)
    View myReceive;


    WorkFlowMessageAdapter adapter;


    public WorkFlowMessageViewerImpl(Context context, final WorkFlowMessagePresenter presenter) {
        super(context);
        this.presenter = presenter;
        setContentView(R.layout.fragment_work_flow_message);

    }





    @Override
    public void showWaiting() {
        swipeLayout.setRefreshing(true);
    }

    @Override
    public void hideWaiting() {
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        adapter = new WorkFlowMessageAdapter(context);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                presenter.showMessage((WorkFlowMessage) parent.getItemAtPosition(position));


            }
        });

        myReceive.setSelected(true);



        myReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!myReceive.isSelected()) {
                    myReceive.setSelected(true);
                    mySend.setSelected(false);
                    presenter.setMyReceiveShow();
                    presenter.loadData();
                }

            }
        });

        mySend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mySend.isSelected()) {
                    myReceive.setSelected(false);
                    mySend.setSelected(true);
                    presenter.setMySendShow();
                    presenter.loadData();
                }

            }
        });
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadData();
            }
        });

    }


    @Override
    public void setMyReceiveMessage(RemoteData<WorkFlowMessage> remoteData) {

        if (remoteData.isSuccess()) {
            adapter.setDataArray(remoteData.datas);

        } else {
            ToastHelper.show(remoteData.message);

        }
    }


    @Override
    public void setMySendMessage(RemoteData<WorkFlowMessage> remoteData) {

        adapter.setDataArray(remoteData.datas);
        if (remoteData.isSuccess()) {
        } else {
            showMessage(remoteData.message);
        }
    }
}
