package com.giants3.hd.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giants3.hd.android.BuildConfig;
import com.giants3.hd.android.R;
import com.giants3.hd.android.events.LoginSuccessEvent;
import com.giants3.hd.android.fragment.ItemPickDialogFragment;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.appdata.QRProduct;
import com.giants3.hd.crypt.DigestUtils;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.User;
import com.giants3.hd.utils.noEntity.BufferData;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import de.greenrobot.event.EventBus;
import rx.Subscriber;

/**
 * A login screen that offers login via email/mPasswordView.
 */
public class LoginActivity extends BaseActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;


    private List<User> users;
    private User loginUser;

    public static final String KEY_LAST_LOGIN_USER = "KEY_LAST_LOGIN_USER";
    private String lastLoginName;


    @Bind(R.id.user)
    TextView user;
    @Bind(R.id.password)
    EditText mPasswordView;
    @Bind(R.id.email_sign_in_button)
    Button emailSignInButton;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.act)
    Button act;
    @Bind(R.id.email_login_form)
    LinearLayout emailLoginForm;

    @Bind(R.id.setUrl)
    Button setUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lastLoginName = getPreferences(Context.MODE_PRIVATE).getString(KEY_LAST_LOGIN_USER, "admin");

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        emailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = new IntentIntegrator(LoginActivity.this);
//                 integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
//                 integrator.setPrompt("Scan a barcode");
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("扫描产品二维码");


                integrator.setCaptureActivity(QRCaptureActivity.class);
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(false);
                integrator.initiateScan();


            }
        });

        user.setOnClickListener(this);

        act.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
                startActivity(intent);
            }
        });


        setUrl.setOnClickListener(this);


        loadUsers();

    }


    private void attemptLogin() {

        if (loginUser == null) {
            ToastHelper.show("请选择人员登录");
            return
                    ;
        }
        // Store values at the time of the login attempt.
        final String userName = loginUser.name;
        String password = mPasswordView.getText().toString();


        mPasswordView.setError(null);


        boolean cancel = false;
        View focusView = null;

        // Check for a valid mPasswordView, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            Map<String, String> map = new HashMap<>();
            map.put("userName", userName);


            map.put("password", DigestUtils.md5(password));
            map.put("client", "ANDROID");
            map.put("version", "1.1.0");
            UseCaseFactory.getInstance().createLogin(map).execute(new Subscriber<RemoteData<AUser>>() {
                @Override
                public void onCompleted() {
                    //showProgress(false);

                }

                @Override
                public void onError(Throwable e) {
                    showProgress(false);
                    ToastHelper.show(e.getMessage());
                }

                @Override
                public void onNext(RemoteData<AUser> aUser) {
                    if (aUser.isSuccess()) {
                        SharedPreferencesHelper.saveLoginUser(aUser.datas.get(0));
                        getPreferences(Context.MODE_PRIVATE).edit().putString(KEY_LAST_LOGIN_USER, userName).commit();
                        HttpUrl.setToken(aUser.datas.get(0).token);

                        ToastHelper.show("登录成功");

                        loadInitData(aUser.datas.get(0).id);
//                        EventBus.getDefault().post(new LoginSuccessEvent());
//                        setResult(RESULT_OK);
//                        finish();
                    } else {
                        ToastHelper.show(aUser.message);
                        showProgress(false);
                    }
                }
            });

        }
    }


    /**
     * 初始数据加载
     */
    private void loadInitData(long userId) {

        UseCaseFactory.getInstance().createGetInitDataCase(userId).execute(new Subscriber<RemoteData<BufferData>>() {
            @Override
            public void onCompleted() {
                showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                showProgress(false);
                ToastHelper.show("初始数据加载失败：" + e.getMessage());
            }

            @Override
            public void onNext(RemoteData<BufferData> remoteData) {
                if (remoteData.isSuccess()) {


                    SharedPreferencesHelper.saveInitData(remoteData.datas.get(0));
                    EventBus.getDefault().post(new LoginSuccessEvent());
                    setResult(RESULT_OK);
                    finish();
                } else {
                    ToastHelper.show(remoteData.message);
                }
            }
        });
    }

    private boolean isPasswordValid(String password) {

        return true;
    }


    private void showProgress(final boolean show) {

        if (show) {
            showWaiting();

        } else {
            hideWaiting();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);


        if (result != null) {


            try {
                QRProduct product = GsonUtils.fromJson(result.getContents(), QRProduct.class);
                user.setText(product.name + "----" + product.className);
                Log.d("TEST", "result:" + product);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


    protected void onViewClick(int id, View v) {

        switch (id) {
            case R.id.setUrl:
                SettingActivity.startActivity(this, 100);

            case R.id.user:

                pickItem();

                break;
//            case  R.id.setUrl:break;
//            case  R.id.setUrl:break;
//            case  R.id.setUrl:break;


        }


    }


    /**
     * 读取用户列表
     */
    private void loadUsers() {

        UseCaseFactory.getInstance().createLoadUsersUseCase().execute(new Subscriber<RemoteData<User>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RemoteData<User> remoteData) {

                if (remoteData.isSuccess()) {
                    users = remoteData.datas;

                    //admin 默认显示admin

                    for (User user : users) {
                        if (user.name.equals(lastLoginName)) {
                            bindUser(user);
                            return;
                        }
                    }
                    bindUser(users.get(0));

                }


            }
        });
    }

    private void bindUser(User aUser) {
        loginUser = aUser;
        user.setText(loginUser.toString());


        if(BuildConfig.DEBUG)
        {

            user.setText(  loginUser.name);
            mPasswordView.setText("xin2975.");
            attemptLogin();
        }

    }

    private void pickItem() {


        if (users == null || users.size() == 0) {
            ToastHelper.show("目前无可登录用户");
            return;
        }
        ItemPickDialogFragment<User> dialogFragment = new ItemPickDialogFragment<User>();
        dialogFragment.set("登录用户选择", users, loginUser, new ItemPickDialogFragment.ValueChangeListener<User>() {
            @Override
            public void onValueChange(String title, User oldValue, User newValue) {

                bindUser(newValue);

            }
        });
        dialogFragment.show(getSupportFragmentManager(), null);


    }

}

