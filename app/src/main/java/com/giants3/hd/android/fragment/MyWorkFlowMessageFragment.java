package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.activity.WorkFlowMessageReceiveActivity;
import com.giants3.hd.android.adapter.WorkFlowMessageAdapter;
import com.giants3.hd.android.mvp.myworkflowmessage.MVP;
import com.giants3.hd.android.mvp.myworkflowmessage.PresenterImpl;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlowMessage;

import butterknife.Bind;

/**
 * 我的消息任务 我发起的  我收到的。
 * Created by davidleen29 on 2017/6/3.
 */

public class MyWorkFlowMessageFragment extends BaseMvpFragment<MVP.Presenter> implements MVP.Viewer {

    private static final int REQUEST_MESSAGE_OPERATE = 9999;
    WorkFlowMessageAdapter adapter;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    @Bind(R.id.list)
    ListView listView;

    @Override
    protected MVP.Presenter createPresenter() {
        return new PresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.myundoworkflowmessage, null);
    }

    @Override
    protected void initView() {

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                getPresenter().loadData();
            }
        });

        adapter = new WorkFlowMessageAdapter(getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                WorkFlowMessage workFlowMessage = (WorkFlowMessage) parent.getItemAtPosition(position);
                WorkFlowMessageReceiveActivity.start(MyWorkFlowMessageFragment.this, workFlowMessage, REQUEST_MESSAGE_OPERATE);


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

    @Override
    public void setData(RemoteData<WorkFlowMessage> remoteData) {
        adapter.setDataArray(remoteData.datas);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {
            case REQUEST_MESSAGE_OPERATE:
                getPresenter().loadData();
                break;
        }

    }
}
