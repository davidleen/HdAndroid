package com.giants3.hd.android.activity;
import android.app.AlertDialog;
import android.content.Context;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.giants3.android.frame.util.Log;
import com.giants3.android.frame.util.StorageUtils;
import com.giants3.hd.android.BuildConfig;
import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.WorkFLowMainMenuAdapter;
import com.giants3.hd.android.events.LoginSuccessEvent;
import com.giants3.hd.android.fragment.AppQuotationFragment;
import com.giants3.hd.android.fragment.ListFragment;
import com.giants3.hd.android.fragment.MaterialDetailFragment;
import com.giants3.hd.android.fragment.OrderDetailFragment;
import com.giants3.hd.android.fragment.QuotationDetailFragment;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.helper.UpgradeUtil;
import com.giants3.hd.android.mvp.MainAct.WorkFlowMainActMvp;
import com.giants3.hd.android.mvp.MainAct.WorkFlowMainActPresenter;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.entity.WorkFlowMessage;
import com.giants3.hd.noEntity.BufferData;
import com.giants3.hd.noEntity.FileInfo;
import com.giants3.hd.noEntity.RemoteData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscriber;

/**
 * 生产流程管理主界面
 */
public class WorkFlowMainActivity extends BaseHeadViewerActivity<WorkFlowMainActMvp.Presenter>
        implements WorkFlowMainActMvp.Viewer,

        OrderDetailFragment.OnFragmentInteractionListener,
        MaterialDetailFragment.OnFragmentInteractionListener,
        QuotationDetailFragment.OnFragmentInteractionListener {


    public static final Fragment EMPTYP_FRAGMENT = new Fragment();
    public static final Fragment EMPTY_LIST_FRAGMENT = new Fragment();

    /**
     * 传进参数， 当这个参数有值时候， 直接跳转消息详情界面
     */
    public static final String EXTRA_FLOW_MESSAGE = "EXTRA_FLOW_MESSAGE";


    @Bind(R.id.menu)
    ListView menu;
    @Bind(R.id.main_content)
    FrameLayout main_content;

    @Bind(R.id.head)
    ImageView head;
    @Bind(R.id.code)
    TextView code;

    @Bind(R.id.name)
    TextView name;


    @Bind(R.id.quotation)
    TextView quotation;


    View view;



    List<String> menuTitleList = null;
    List<String> menuFragmentClassList;
    WorkFLowMainMenuAdapter adapter  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setBackEnable(false);

//        menuTitles = getResources().getStringArray(R.array.menu_title);
//        menuFragmentClass= getResources().getStringArray(R.array.menu_fragemnt_class);

        createListeners();


        //登录验证

        if (SharedPreferencesHelper.getLoginUser() == null) {

            startLoginActivity();
            return;
        } else {
            getPresenter().setLoginUser(SharedPreferencesHelper.getLoginUser());
        }


        quotation.setVisibility(BuildConfig.DEBUG ?View.VISIBLE:View.GONE);
       // quotation.setVisibility(BuildConfig.DEBUG&&SharedPreferencesHelper.getLoginUser().isSalesman?View.VISIBLE:View.GONE);
        handleMessageEntry(getIntent());


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getPresenter().checkAppUpdateInfo(true);
            }
        });

    }

    @Override
    protected WorkFlowMainActMvp.Presenter onLoadPresenter() {
        return new WorkFlowMainActPresenter();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleMessageEntry(intent);
    }


    private  void handleMessageEntry(Intent intent)
    {
        Log.e("handleMessageEntry");

        final long messageId=intent.getLongExtra(EXTRA_FLOW_MESSAGE,0);
        if(messageId!=0)
        {


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    WorkFlowMessageReceiveActivity.start(WorkFlowMainActivity.this,messageId,0);
                }
            });


        }



    }

    @Override
    protected void initEventAndData() {

    }


    private void createListeners() {


        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String title = (String) parent.getItemAtPosition(position);


                int index = -1;
                int length = menuTitleList.size();
                for (int i = 0; i < length; i++) {
                    String temp = menuTitleList.get(i) ;
                    if (temp.equalsIgnoreCase(title)) {
                        index = i;
                        break;
                    }
                }


                String className =menuFragmentClassList.get(index);
                showNewListFragment(className);
                setActTitle(title);

            }
        });
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.menu_title, android.R.layout.simple_list_item_1);
        adapter = new WorkFLowMainMenuAdapter(this);
        menu.setAdapter(adapter);


//        adapter.setDataArray(menuTitles);




        quotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String className = AppQuotationFragment.class.getName();
                showNewListFragment(className);
                setActTitle("广交会报价");

            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void startDownLoadApk(final FileInfo newApkFileInfo) {


        UpgradeUtil.startUpgrade2(WorkFlowMainActivity.this, 1, "云飞家居", HttpUrl.completeUrl(newApkFileInfo.url), newApkFileInfo.length);


    }

    @Override
    public void bindUser(AUser loginUser) {


        code.setText(loginUser.code + "," + loginUser.name);
        name.setText(loginUser.chineseName);



        updateMenu(loginUser);


    }



    private void updateMenu(AUser loginUser)

    {
        menuTitleList=new ArrayList<>();
        menuFragmentClassList=new ArrayList<>();
        if(loginUser.isSalesman||BuildConfig.DEBUG)
        {
          String[]  menuTitles=getResources().getStringArray(R.array.quotation_menu_title);
            String[]   menuFragmentClass=getResources().getStringArray(R.array.quotation_menu_fragemnt_class);
            for (int i = 0; i < menuTitles.length; i++) {
                menuTitleList.add(menuTitles[i]);
                menuFragmentClassList.add(menuFragmentClass[i]);
            }

        }



        {
            String[]    menuTitles = getResources().getStringArray(R.array.menu_title);
            String[]     menuFragmentClass= getResources().getStringArray(R.array.menu_fragemnt_class);

            for (int i = 0; i < menuTitles.length; i++) {
                menuTitleList.add(menuTitles[i]);
                menuFragmentClassList.add(menuFragmentClass[i]);
            }
        }

        {
            String[]    menuTitles = getResources().getStringArray(R.array.product_menu_title);
            String[]     menuFragmentClass= getResources().getStringArray(R.array.product_menu_fragemnt_class);

            for (int i = 0; i < menuTitles.length; i++) {
                menuTitleList.add(menuTitles[i]);
                menuFragmentClassList.add(menuFragmentClass[i]);
            }
        }
        adapter.setDataArray(menuTitleList);

    }

    @Override
    public void onBackPressed() {


        if (getPresenter().checkBack()) {
            super.onBackPressed();

        } else {
            ToastHelper.show("再次点击返回键退出应用");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item_work_flow_report clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                SettingActivity.startActivity(this, 100);
                return true;

            case R.id.action_clean:


                reLoadBufferData();
                return true;
            case R.id.login:


                startLoginActivity();
                return true;
            case R.id.upgrade:

                getPresenter().checkAppUpdateInfo(false);

                return true;
            case R.id.updatePassword:


                getPresenter().updatePassword();

                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected View getContentView() {


        return getLayoutInflater().inflate(R.layout.activity_work_flow_main, null);

    }


    /**
     * 重新读取缓存数据
     */
    private void reLoadBufferData() {

        if (SharedPreferencesHelper.getLoginUser() != null) {
            UseCaseFactory.getInstance().createGetInitDataCase(SharedPreferencesHelper.getLoginUser().id).execute(new Subscriber<RemoteData<BufferData>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                    ToastHelper.show(e.getMessage());
                }

                @Override
                public void onNext(RemoteData<BufferData> remoteData) {
                    if (remoteData.isSuccess()) {

                        SharedPreferencesHelper.saveInitData(remoteData.datas.get(0));
                        ToastHelper.show("缓存清理成功");

                    } else {
                        ToastHelper.show(remoteData.message);

                    }
                }
            });

        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


        }
    }

    private void setActTitle(String title) {
        setTitle(title);
    }

    private void showNewListFragment(String fragmentClassName) {

        Fragment fragment = null;

        try {
            Class<Fragment> fragmentClass = (Class<Fragment>) Class.forName(fragmentClassName);
            fragment = fragmentClass.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (fragment == null) {
            showMessage("未配置对应的fragemnt");

            fragment = EMPTYP_FRAGMENT;
        }


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit();


    }


    @Override
    public void onEvent(LoginSuccessEvent event) {


        getPresenter().setLoginUser(SharedPreferencesHelper.getLoginUser());


    }


    @Override
    protected void onResume() {
        super.onResume();

        //每次界面恢复
        //读取一次新消息记录 后面改成推送
        getPresenter().attemptUpdateNewMessageCount();


    }

    @Override
    public void setNewWorkFlowMessageCount(int count) {


        int childCount = menu.getChildCount();
        int index=menuTitleList.indexOf(getResources().getString(R.string.work_need_finish));
        for (int i = 0; i < childCount; i++) {

            View child = menu.getChildAt(i);
            if (child.getTag() instanceof WorkFLowMainMenuAdapter.ViewHolder) {
                WorkFLowMainMenuAdapter.ViewHolder tag = (WorkFLowMainMenuAdapter.ViewHolder) child.getTag();

                if (i == index)
                    tag.setMessageCount(count);
                else
                    tag.setMessageCount(0);

            }
        }


    }

    @Override
    public void showApkUpdate(final FileInfo fileInfo) {


       new  AlertDialog.Builder(this).setMessage("有新版本apk，是否下载安装？").setNegativeButton("取消",null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               startDownLoadApk(fileInfo);

           }
       }).create().show();




    }

    public static void start(Context context) {


        Intent intent = new Intent(context,WorkFlowMainActivity.class);
        context.startActivity(intent);
    }
}
