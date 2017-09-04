package com.giants3.hd.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.giants3.hd.android.R;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.crypt.DigestUtils;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.utils.StringUtils;

import butterknife.Bind;
import rx.Subscriber;

//import com.giants3.hd.data.net.HttpUrl;

/**
 * 设置界面
 */
public class UpdatePasswordActivity extends BaseActionBarActivity {




    @Bind(R.id.et_new_password)
    EditText et_new_password;
    @Bind(R.id.et_old_password)
    EditText et_old_password;
    @Bind(R.id.btn_save)
    Button btnSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle("修改密码");
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();


            }
        });


    }

    @Override
    protected View getContentView() {
        return getLayoutInflater().inflate(R.layout.activity_update_password,null);
    }

    public void save() {


        String oldPass = et_old_password.getText().toString();
        if(StringUtils.isEmpty(oldPass))
        {
            ToastHelper.show("旧密码不得为空");
            return;
        }

        String newPass = et_new_password.getText().toString();

        if(StringUtils.isEmpty(newPass))
        {
            ToastHelper.show("新密码不得为空");
            return;
        }
        String oldPasswordMd5 = DigestUtils.md5(oldPass);
        String newPasswordMd5 =DigestUtils.md5(newPass);
        UseCaseFactory.getInstance().createUpdatePasswordUseCase(oldPasswordMd5, newPasswordMd5).execute(new Subscriber<RemoteData<Void>>() {
            @Override
            public void onCompleted() {
                 hideWaiting();
            }

            @Override
            public void onError(Throwable e) {
                hideWaiting();
                ToastHelper.show("操作失败：" + e.getMessage());
            }

            @Override
            public void onNext(RemoteData<Void> remoteData) {
                if (remoteData.isSuccess()) {
                    ToastHelper.show("密码修改成功");
                    finish();

                } else {
                    ToastHelper.show(remoteData.message);
                }
            }
        });

        showWaiting();
    }


    public static void startActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, UpdatePasswordActivity.class);
        activity.startActivityForResult(intent, requestCode);

    }
}
