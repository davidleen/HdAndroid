package com.giants3.hd.android.activity;

/*
 * Copyright 2009 Michael Burton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.internal.util.Stopwatch;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;


public class BaseActivity extends AppCompatActivity implements  View.OnClickListener {
    public static final  int REQUEST_LOGIN=1009;

    /**
     * 当前act 是否在最前面
     */
    private boolean isTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        isTop=true;
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        isTop=false;
        MobclickAgent.onPause(this);

    }


    public boolean isTop()
    {
        return isTop;
    }
    @Override
    protected void onNewIntent( Intent intent ) {
        super.onNewIntent(intent);

    }

    @Override
    protected void onStop() {

            super.onStop();

    }

    @Override
    protected void onDestroy() {

                super.onDestroy();

    }

    protected void startLoginActivity() {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode!=RESULT_OK) return ;

        switch (requestCode)
        {

            case REQUEST_LOGIN:
                onLoginRefresh();
                break;
        }

    }

    protected void onLoginRefresh()
    {}

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        return super.onCreateView(name, context, attrs);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {


        return super.onCreateView(parent, name, context, attrs);
    }



    @Override
    public void onClick(View v) {


        onViewClick(v.getId(),v);
    }


    protected void onViewClick(int id,View v){}


    public static final  void start(Context context,Bundle data,int  requestCode)
    {



    }

}
