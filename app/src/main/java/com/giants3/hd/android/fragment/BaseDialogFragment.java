package com.giants3.hd.android.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giants3.hd.android.activity.BaseActivity;
import com.giants3.hd.android.activity.LoginActivity;
import com.giants3.hd.android.events.BaseEvent;
import com.giants3.hd.android.events.LoginSuccessEvent;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import android.support.v4.app.DialogFragment;
/**
 * Created by david on 2015/12/24.
 */
public abstract class BaseDialogFragment<P extends NewPresenter> extends DialogFragment implements NewViewer {


    protected P mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mPresenter = onLoadPresenter();
        getPresenter().attachView(this);

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initViews(savedInstanceState);
        initEventAndData();
        if (getPresenter() != null) {
            getPresenter().start();
        }
    }

    protected abstract P onLoadPresenter();

    protected abstract void initEventAndData();

    public P getPresenter() {
        return mPresenter;
    }

    protected abstract void initViews(Bundle savedInstanceState);


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }






    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    protected void startLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent, BaseActivity.REQUEST_LOGIN);

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

    @Override
    public void hideWaiting() {

    }

    @Override
    public void showMessage(String message) {

        ToastHelper.show(message);
    }

    @Override
    public void showWaiting() {

    }
}
