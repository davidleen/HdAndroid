package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.activity.WorkFlowMessageReceiveActivity;
import com.giants3.hd.android.adapter.WorkFlowMessageAdapter;
import com.giants3.hd.android.mvp.MyUndoWorkFlowMessageMVP;
import com.giants3.hd.android.mvp.myundoworkflowmessage.PresenterImpl;
import com.giants3.hd.android.widget.RefreshLayoutConfig;
import com.giants3.hd.entity.WorkFlowMessage;
import com.giants3.hd.noEntity.RemoteData;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import butterknife.Bind;

/**
 * 我的代办任务  ----未处理的流程消息任务
 * Created by davidleen29 on 2017/6/3.
 */

public class MyUndoWorkFlowMessageFragment extends BaseMvpFragment<MyUndoWorkFlowMessageMVP.Presenter> implements MyUndoWorkFlowMessageMVP.Viewer {

    private static final int REQUEST_MESSAGE_OPERATE = 9999;
    WorkFlowMessageAdapter adapter;
    @Bind(R.id.swipeLayout)
    TwinklingRefreshLayout swipeLayout;

    @Bind(R.id.list)
    ListView listView;
    @Bind(R.id.search)
    EditText search;

    @Override
    protected MyUndoWorkFlowMessageMVP.Presenter createPresenter() {
        return new PresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.myundoworkflowmessage, null);
    }

    @Override
    protected void initView() {


        RefreshLayoutConfig.config(swipeLayout);
        swipeLayout.setOnRefreshListener(new RefreshListenerAdapter() {


            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {


                getPresenter().loadData();


            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {


                swipeLayout.finishLoadmore();


            }
        });

        adapter = new WorkFlowMessageAdapter(getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                WorkFlowMessage workFlowMessage = (WorkFlowMessage) parent.getItemAtPosition(position);
                WorkFlowMessageReceiveActivity.start(MyUndoWorkFlowMessageFragment.this, workFlowMessage, REQUEST_MESSAGE_OPERATE);

                swipeLayout.finishLoadmore();


            }
        });
        search.post(new Runnable() {
            @Override
            public void run() {

                getPresenter().loadData();
            }
        });
        search.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                getPresenter().setKey(s.toString());

                postSearch();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private  void postSearch()
    {
        search.removeCallbacks(searchRunnable);
        search.postDelayed(searchRunnable,1000);


    }
    public Runnable searchRunnable=new Runnable() {
        @Override
        public void run() {

            getPresenter().loadData();
        }
    };
    @Override
    public void showWaiting() {
        swipeLayout.startRefresh();
    }

    @Override
    public void hideWaiting() {
        swipeLayout.finishRefreshing();
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
