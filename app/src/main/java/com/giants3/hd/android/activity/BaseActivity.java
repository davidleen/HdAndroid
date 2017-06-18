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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

import com.giants3.hd.android.events.BaseEvent;
import com.giants3.hd.android.events.LoginSuccessEvent;
import com.giants3.hd.android.helper.AnalysisFactory;
import com.giants3.hd.android.helper.ToastHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_LOGIN = 1009;
    public static final int REQUEST_CODE_PERMISSION = 200;


    ProgressDialog progressDialog;

    /**
     * 当前act 是否在最前面
     */
    private boolean isTop;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);


        sharedPreferences = getSharedPreferences("PERMMISON", Context.MODE_PRIVATE);


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
        isTop = true;
        AnalysisFactory.getInstance().onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        isTop = false;
        AnalysisFactory.getInstance().onPause(this);

    }


    public boolean isTop() {
        return isTop;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    protected void onStop() {

        super.onStop();

    }

    @Override
    protected void onDestroy() {
        hideWaiting();
        sharedPreferences = null;
        EventBus.getDefault().unregister(this);
        super.onDestroy();


    }

    public void startLoginActivity() {

        if (getClass().getName().equals(LoginActivity.class.getName())) return;
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_LOGIN);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

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


    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode != RESULT_OK) return;

        switch (requestCode) {

            case REQUEST_LOGIN:
                onLoginRefresh();
                break;
        }

    }

    protected void onLoginRefresh() {
    }

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


        onViewClick(v.getId(), v);
    }


    protected void onViewClick(int id, View v) {
    }


    public void hideWaiting() {

        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }


    }

    public void showMessage(String message) {

        ToastHelper.show(message);

    }

    public void showWaiting() {
        hideWaiting();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading");
        progressDialog.show();

    }


    public void showWaiting(String message) {
        hideWaiting();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.show();

    }

    protected boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermission(String[] perms) {
        int permsRequestCode = REQUEST_CODE_PERMISSION;
        requestPermissions(perms, permsRequestCode);

    }


    /**
     * 授权成功回调
     *
     * @param permission
     */

    public void onPermissionGranted(String permission) {

    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {

        switch (permsRequestCode) {

            case REQUEST_CODE_PERMISSION:
                int size = grantResults.length;
                for (int i = 0; i < size; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        markAsAsked(permissions[i]);
                        onPermissionGranted(permissions[i]);
                    }
                }


                break;

        }

    }

    private List<String> findUnAskedPermissions(List<String> wanted) {

        List<String> result = new ArrayList<>();

        for (String perm : wanted) {

            if (!hasPermission(perm) && shouldWeAsk(perm)) {

                result.add(perm);

            }

        }

        return result;

    }


    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {

        if (shouldAskPermission()) {

            return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);

        }

        return true;

    }


    private boolean shouldWeAsk(String permission) {

        return (sharedPreferences.getBoolean(permission, true));

    }


    private void markAsAsked(String permission) {


        sharedPreferences.edit().putBoolean(permission, false).apply();

    }


    /**
     * 登录成功回调事件
     *
     * @param event
     */
    public void onEvent(LoginSuccessEvent event) {

    }

}
