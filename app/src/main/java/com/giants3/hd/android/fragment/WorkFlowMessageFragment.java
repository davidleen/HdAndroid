package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.giants3.hd.android.ViewImpl.WorkFlowMessageViewerImpl;
import com.giants3.hd.android.activity.WorkFlowMessageReceiveActivity;
import com.giants3.hd.android.presenter.WorkFlowMessagePresenter;
import com.giants3.hd.android.viewer.BaseViewer;
import com.giants3.hd.android.viewer.WorkFlowMessageViewer;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity.WorkFlowMessage;

import rx.Subscriber;


/**
 * 代办任务列表
 */
public class WorkFlowMessageFragment extends BaseFragment implements WorkFlowMessagePresenter {


    private static final int MYSEND = 1;
    private static final int MYRECEIVE = 2;
    private static final int REQUEST_MESSAGE_OPERATE = 999;
    private WorkFlowMessageViewer viewer;

    private int currentShowData = MYRECEIVE;


    public WorkFlowMessageFragment() {


    }

    @Override
    protected BaseViewer getViewer() {

        return viewer;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loadData();
    }


    private void setResult() {
        getActivity().setResult(Activity.RESULT_OK);
    }


    public static WorkFlowMessageFragment newInstance() {
        WorkFlowMessageFragment fragment = new WorkFlowMessageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        viewer = new WorkFlowMessageViewerImpl(getContext(), this);


    }


    @Override
    public void loadData() {

        if (currentShowData == MYRECEIVE) {

            UseCaseFactory.getInstance().createGetUnHandleWorkFlowMessageCase("").execute(new Subscriber<RemoteData<WorkFlowMessage>>() {
                @Override
                public void onCompleted() {
                    viewer.hideWaiting();
                }

                @Override
                public void onError(Throwable e) {
                    viewer.hideWaiting();

                    viewer.showMessage(e.getMessage());
                }

                @Override
                public void onNext(RemoteData<WorkFlowMessage> remoteData) {


                    viewer.setMyReceiveMessage(remoteData);

                }
            });
        } else {
            UseCaseFactory.getInstance().createGetMySendWorkFlowMessageCase().execute(new Subscriber<RemoteData<WorkFlowMessage>>() {
                @Override
                public void onCompleted() {
                    viewer.hideWaiting();
                }

                @Override
                public void onError(Throwable e) {
                    viewer.hideWaiting();

                    viewer.showMessage(e.getMessage());
                }

                @Override
                public void onNext(RemoteData<WorkFlowMessage> remoteData) {


                    viewer.setMySendMessage(remoteData);

                }
            });
        }
        viewer.showWaiting();

    }


    @Override
    public void setMySendShow() {

        currentShowData = MYSEND;
    }

    @Override
    public void setMyReceiveShow() {
        currentShowData = MYRECEIVE;

    }

    @Override
    public void showMessage(WorkFlowMessage message) {

        Intent intent = new Intent(getActivity(), WorkFlowMessageReceiveActivity.class);
        intent.putExtra(WorkFlowMessageReceiveActivity.KEY_MESSAGE, GsonUtils.toJson(message));
        startActivityForResult(intent, REQUEST_MESSAGE_OPERATE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_MESSAGE_OPERATE:
                    setResult();
                    loadData();

                    break;
            }
        }
    }
}
