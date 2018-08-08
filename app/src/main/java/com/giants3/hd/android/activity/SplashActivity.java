package com.giants3.hd.android.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.content.Context;
import android.support.annotation.NonNull;

import com.giants3.hd.android.R;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.appdata.AUser;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by davidleen29 on 2018/2/2.
 */

@RuntimePermissions
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashActivityPermissionsDispatcher.doLoadingWithPermissionCheck(this);

    }
    @NeedsPermission( {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE})
    protected void doLoading() {
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMain();
            }
        },500);
    }


    @OnShowRationale( {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE})
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.permission_write_read_sd)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                        finish();
                    }
                })
                .show();
    }

    @OnPermissionDenied( {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE})
    void showDeniedForSD() {
        SplashActivityPermissionsDispatcher.doLoadingWithPermissionCheck(this);
    }

    @OnNeverAskAgain(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE})
    void showNeverAskForSD() {
       ToastHelper.show(  R.string.permission_write_read_neverask );


        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private void goToMain()
    {

        //登录验证

        AUser loginUser = SharedPreferencesHelper.getLoginUser();
        if (loginUser == null) {

            startLoginActivity();
            return;
        } else {


           goWorkFlow();

            finish();

        }
    }


    private void goWorkFlow()
    {

        WorkFlowMainActivity.start(this);


    }
    protected void enterAnimation() {
        overridePendingTransition(R.anim.in_from_right, R.anim.hold);
    }
    protected void exitAnimation() {
        overridePendingTransition(R.anim.hold, R.anim.out_to_right);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==REQUEST_LOGIN)
        {

            goToMain();

        }else
        {
            doLoading();
        }


    }
}
