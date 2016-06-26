package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giants3.hd.android.activity.BaseActivity;
import com.giants3.hd.android.activity.LoginActivity;
import com.giants3.hd.android.events.BaseEvent;
import com.giants3.hd.android.events.LoginSuccessEvent;
import com.giants3.hd.android.viewer.BaseViewer;


import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by david on 2015/12/24.
 */
public class BaseFragment extends Fragment {


    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p/>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if(getViewer()!=null)
        {
            getViewer().onCreateView(view);
        }
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if(getViewer()!=null)
        {
            getViewer().onDestroyView();
        }
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

    protected void startLoginActivity() {
        Intent intent=new Intent(getActivity(),LoginActivity.class);
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
     * 显示遮罩
     * @param show
     */
    public void showProgress(boolean show)
    {
            if(show)
            {
                getViewer().showWaiting();
            }else
                getViewer().hideWaiting();
    }


    protected BaseViewer getViewer()
    {


        return null;
    }


    /**
     * 登录成功后的回调。
     * <p/>
     * 子类 根据需要处理。（加载数据等）
     */

    protected void onLoginRefresh() {


    }
}
