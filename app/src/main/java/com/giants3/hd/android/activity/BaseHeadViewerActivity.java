package com.giants3.hd.android.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;

/**  支持新mvp模式的基类act
 * Created by davidleen29 on 2016/10/10.
 */

public  abstract  class BaseHeadViewerActivity<P extends NewPresenter> extends BaseActionBarActivity  implements NewViewer{

    protected P mPresenter;

    protected static   String TAG="BaseViewerActivity";
    @Override
    protected    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG=getClass().getName();
        mPresenter = onLoadPresenter();
        getPresenter().attachView(this);

        initEventAndData();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(getPresenter() != null) {
                    getPresenter().start();
                }
            }
        });

    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        super.onDestroy();
    }

    protected abstract P onLoadPresenter();

    protected abstract void initEventAndData();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }



}
