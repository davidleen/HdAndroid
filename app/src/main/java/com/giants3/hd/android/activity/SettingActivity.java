package com.giants3.hd.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.giants3.hd.android.BuildConfig;
import com.giants3.hd.android.R;
import com.giants3.hd.data.net.HttpUrl;

import butterknife.Bind;

//import com.giants3.hd.data.net.HttpUrl;

/**
 * 设置界面
 */
public class SettingActivity extends BaseActionBarActivity {


    @Bind(R.id.et_ip)
    EditText etIp;
    @Bind(R.id.et_port)
    EditText etPort;
    @Bind(R.id.et_service)
    EditText etService;
    @Bind(R.id.btn_save)
    Button btnSave;

    @Bind(R.id.setOutUrl)
    Button setOutUrl;

    @Bind(R.id.setInUrl)
    Button setInUrl;


    @Bind(R.id.setProduceUrl)
    Button setProduceUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setTitle("网络设置");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSetting();


            }
        });

        setOutUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etIp.setText("fzyunfei.f3322.net");
                etPort.setText("8079");
                etService.setText("Server");
            }
        });
        setInUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etIp.setText("192.168.10.198");
                etPort.setText("8080");
                etService.setText("Server");
            }
        });

        setProduceUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etIp.setText("192.168.0.198");
                etPort.setText("8080");
                etService.setText("Server");

            }
        });
       setOutUrl.setVisibility(BuildConfig.DEBUG?View.VISIBLE:View.GONE);
        etIp.setText(HttpUrl.IPAddress);
        etPort.setText(HttpUrl.IPPort);
        etService.setText(HttpUrl.ServiceName);

    }

    @Override
    protected View getContentView() {
         return getLayoutInflater().inflate(R.layout.activity_setting,null);
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
