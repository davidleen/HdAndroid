package com.giants3.hd.android.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.WorkFLowMainMenuAdapter;
import com.giants3.hd.android.events.LoginSuccessEvent;
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
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.noEntity.BufferData;
import com.giants3.hd.noEntity.FileInfo;

import butterknife.Bind;
import rx.Subscriber;

/**
 * 生产流程管理主界面
 */
public class WorkFlowMainActivity extends BaseViewerActivity<WorkFlowMainActMvp.Presenter>
        implements WorkFlowMainActMvp.Viewer,

        OrderDetailFragment.OnFragmentInteractionListener,
        MaterialDetailFragment.OnFragmentInteractionListener,
        QuotationDetailFragment.OnFragmentInteractionListener {


    public static final Fragment EMPTYP_FRAGMENT = new Fragment();
    public static final Fragment EMPTY_LIST_FRAGMENT = new Fragment();


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.menu)
    ListView menu;
    @Bind(R.id.content)
    FrameLayout content;

    @Bind(R.id.head)
    ImageView head;
    @Bind(R.id.code)
    TextView code;

    @Bind(R.id.name)
    TextView name;


    View view;

    String[] menuTitles=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_flow_main);
        setSupportActionBar(toolbar);
        menuTitles=  getResources().getStringArray(R.array.menu_title);

        createListeners();


        //登录验证

        if (SharedPreferencesHelper.getLoginUser() == null) {

            startLoginActivity();
            return;
        } else {
            getPresenter().setLoginUser(SharedPreferencesHelper.getLoginUser());
        }


    }

    @Override
    protected WorkFlowMainActMvp.Presenter onLoadPresenter() {
        return new WorkFlowMainActPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initEventAndData() {

    }


    private void createListeners() {


        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String title = (String) parent.getItemAtPosition(position);


                String className = getResources().getStringArray(R.array.menu_fragemnt_class)[position];
                showNewListFragment(className);
                setActTitle(title);

            }
        });
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.menu_title, android.R.layout.simple_list_item_1);
        WorkFLowMainMenuAdapter adapter=new WorkFLowMainMenuAdapter(this);
        adapter.setDataArray(menuTitles);

        menu.setAdapter(adapter);


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

                getPresenter().checkAppUpdateInfo();

                return true;
            case R.id.updatePassword:


                getPresenter().updatePassword();

                return true;
        }


        return super.onOptionsItemSelected(item);
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
        getSupportActionBar().setTitle(title);
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
                .replace(R.id.content, fragment)
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



        int childCount=menu.getChildCount();
        for (int i = 0; i < childCount; i++) {

            View child=menu.getChildAt(i);
            if(child.getTag() instanceof  WorkFLowMainMenuAdapter.ViewHolder)
            {
                WorkFLowMainMenuAdapter.ViewHolder tag = (WorkFLowMainMenuAdapter.ViewHolder) child.getTag();

                if(i==0)
                   tag.setMessageCount(count);
                else
                    tag.setMessageCount(0);

            }
        }




    }
}
