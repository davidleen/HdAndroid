package com.giants3.hd.android.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.giants3.hd.android.activity.BaseActivity;
import com.giants3.hd.android.activity.LoginActivity;
import com.giants3.hd.android.events.BaseEvent;
import com.giants3.hd.android.events.LoginSuccessEvent;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * mvp基础结构 view层
 * Created by david on 2015/12/24.
 */
public abstract class BaseMvpFragment<P extends NewPresenter> extends Fragment implements NewViewer {


    P presenter;
    private ProgressDialog progressDialog;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        initView();

        presenter = createPresenter();
        presenter.attachView(this);




        if(getPresenter() != null) {
            getPresenter().start();
        }


    }

    protected abstract void initView();


    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }


    protected P getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void showWaiting() {
        hideWaiting();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("loading");
        progressDialog.show();

    }

    @Override
    public void hideWaiting() {

        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }


    }

    @Override
    public void showMessage(String message) {

        ToastHelper.show(message);

    }


    public void startLoginActivity() {


        LoginActivity.start(this, BaseActivity.REQUEST_LOGIN);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    /**
     * 这个方法 适配 evenbus 必须实现一个这种方法 否则会报错。
     * <p/>
     * <br>Created 2016年4月19日 下午3:58:56
     *
     * @param event
     * @author davidleen29
     */
    public void onEvent(BaseEvent event) {
    }


    public void onEvent(LoginSuccessEvent event) {

        onLoginRefresh();

    }


    /**
     * 登录成功后的回调。
     * <p/>
     * 子类 根据需要处理。（加载数据等）
     */

    protected void onLoginRefresh() {


    }
}
