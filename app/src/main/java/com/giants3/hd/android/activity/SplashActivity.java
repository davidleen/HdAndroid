package com.giants3.hd.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import com.giants3.hd.android.R;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.appdata.AUser;

/**
 * Created by davidleen29 on 2018/2/2.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        doLoading();
    }

    private void doLoading() {
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMain();
            }
        },500);
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
