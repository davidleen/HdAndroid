package com.giants3.hd.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.giants3.hd.android.R;
import com.giants3.hd.data.net.HttpUrl;

import butterknife.Bind;

//import com.giants3.hd.data.net.HttpUrl;

/**
 * 设置界面
 */
public class SettingActivity extends BaseActivity {


    @Bind(R.id.et_ip)
    EditText etIp;
    @Bind(R.id.et_port)
    EditText etPort;
    @Bind(R.id.et_service)
    EditText etService;
    @Bind(R.id.btn_save)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSetting();
            }
        });

        etIp.setText(HttpUrl.IPAddress);
        etPort.setText(HttpUrl.IPPort);
        etService.setText(HttpUrl.ServiceName);
    }


    public void saveSetting() {
   HttpUrl.reset(etIp.getText().toString().trim(), etPort.getText().toString().trim(), etService.getText().toString().trim());

        setResult(RESULT_OK);
        finish();
    }


    public static void startActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }
}
