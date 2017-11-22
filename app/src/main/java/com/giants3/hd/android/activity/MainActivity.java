package com.giants3.hd.android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.fragment.ListFragment;
import com.giants3.hd.android.fragment.MaterialDetailFragment;
import com.giants3.hd.android.fragment.MaterialListFragment;
import com.giants3.hd.android.fragment.OrderDetailFragment;
import com.giants3.hd.android.fragment.OrderListFragment;
import com.giants3.hd.android.fragment.ProductDetailFragment;
import com.giants3.hd.android.fragment.ProductListFragment;
import com.giants3.hd.android.fragment.QuotationDetailFragment;
import com.giants3.hd.android.fragment.QuotationListFragment;
import com.giants3.hd.android.helper.AuthorityUtil;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.helper.UpgradeUtil;
import com.giants3.hd.android.mvp.MainAct.MainActMvp;
import com.giants3.hd.android.mvp.MainAct.MainActPresenter;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.ErpOrder;
import com.giants3.hd.entity.Material;
import com.giants3.hd.entity.Quotation;
import com.giants3.hd.noEntity.BufferData;
import com.giants3.hd.noEntity.FileInfo;
import com.giants3.hd.noEntity.RemoteData;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

import static com.giants3.hd.android.fragment.ProductListFragment.OnFragmentInteractionListener;

/**
 * 主界面
 */
public class MainActivity extends BaseViewerActivity<MainActMvp.Presenter>
        implements MainActMvp.Viewer, NavigationView.OnNavigationItemSelectedListener,

        OrderDetailFragment.OnFragmentInteractionListener,
        MaterialDetailFragment.OnFragmentInteractionListener,
        QuotationDetailFragment.OnFragmentInteractionListener {


    public static final Fragment EMPTYP_FRAGMENT = new Fragment();
    public static final Fragment EMPTY_LIST_FRAGMENT = new Fragment();
    NavigationViewHelper helper;


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.list_container)
    FrameLayout frameLayout;




    View view;
    private int currentListFragmentIndex;

    private ListFragment<Material> materialListFragment;
    private ListFragment<AProduct> productListFragment;
    private ListFragment<ErpOrder> orderListFragment;
    private ListFragment<Quotation> quotationListFragment;
    //材料列表监听
    ListFragment.OnFragmentInteractionListener<Material> materialListFragmentListener;
    //订单列表 监听
    ListFragment.OnFragmentInteractionListener<ErpOrder> orderOnFragmentInteractionListener;
    //报价列表监听
    ListFragment.OnFragmentInteractionListener<Quotation> quotationListFragmentListener;
    //产品列表监听
    ListFragment.OnFragmentInteractionListener<AProduct> productListFragmentListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "退出", Snackbar.LENGTH_LONG)
                        .setAction("退出", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                finish();
                            }
                        }).show();
            }
        });

        //无用 不显示
        fab.setVisibility(View.GONE);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);


        helper = new NavigationViewHelper(navigationView.getHeaderView(0));
        bindData();


        //登录验证

        if (SharedPreferencesHelper.getLoginUser() == null) {

            startLoginActivity();
        }
        createListeners();
        productListFragment = ProductListFragment.newInstance("", "");
        productListFragment.setFragmentListener(productListFragmentListener);
        quotationListFragment = QuotationListFragment.newInstance("", "");
        quotationListFragment.setFragmentListener(quotationListFragmentListener);
        orderListFragment = OrderListFragment.newInstance("", "");
        orderListFragment.setFragmentListener(orderOnFragmentInteractionListener);
        materialListFragment = MaterialListFragment.newInstance("", "");
        materialListFragment.setFragmentListener(materialListFragmentListener);

        showNewListFragment(EMPTY_LIST_FRAGMENT);


    }

    @Override
    protected MainActMvp.Presenter onLoadPresenter() {
        return new MainActPresenter();
    }



    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }
    private void bindData() {

        navigationView.getMenu().getItem(0).setVisible(AuthorityUtil.getInstance().viewProductModule());
        navigationView.getMenu().getItem(1).setVisible(AuthorityUtil.getInstance().viewQuotationList());
        navigationView.getMenu().getItem(2).setVisible(AuthorityUtil.getInstance().viewOrderMenu());
        navigationView.getMenu().getItem(3).setVisible(AuthorityUtil.getInstance().viewMaterialList());
        helper.bind();

    }

    private void createListeners() {

        orderOnFragmentInteractionListener = new OnFragmentInteractionListener<ErpOrder>() {
            @Override
            public void onFragmentInteraction(ErpOrder data) {

                if (findViewById(R.id.detail_container) == null) {

                    //调整act
                    Intent intent = new Intent(MainActivity.this, OrderDetailActivity.class);
                    intent.putExtra(OrderDetailFragment.ARG_ITEM, GsonUtils.toJson(data));
                    startActivity(intent);

                } else {

                    OrderDetailFragment fragment = OrderDetailFragment.newInstance(data);
                    getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment).commit();

                }
            }
        };


        quotationListFragmentListener = new OnFragmentInteractionListener<Quotation>() {
            @Override
            public void onFragmentInteraction(Quotation data) {
                //打开报价详情单
                if (findViewById(R.id.detail_container) == null) {

                    //调整act
                    Intent intent = new Intent(MainActivity.this, QuotationDetailActivity.class);
                    intent.putExtra(QuotationDetailFragment.ARG_ITEM, GsonUtils.toJson(data));
                    startActivity(intent);

                } else {

                    QuotationDetailFragment fragment = QuotationDetailFragment.newInstance(data);
                    getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment).commit();
                }
            }
        };

        productListFragmentListener = new OnFragmentInteractionListener<AProduct>() {
            @Override
            public void onFragmentInteraction(AProduct data) {
                if (findViewById(R.id.detail_container) == null) {

                    //调整act
                    Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                    intent.putExtra(ProductDetailFragment.ARG_ITEM, GsonUtils.toJson(data));
                    startActivity(intent);

                } else {

                    ProductDetailFragment fragment = ProductDetailFragment.newInstance(data);
                    getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment).commit();
                }

            }
        };
        materialListFragmentListener = new OnFragmentInteractionListener<Material>() {
            @Override
            public void onFragmentInteraction(Material data) {
//打开报价详情单
                if (findViewById(R.id.detail_container) == null) {

                    //调整act
                    Intent intent = new Intent(MainActivity.this, MaterialDetailActivity.class);
                    intent.putExtra(MaterialDetailFragment.ARG_ITEM, GsonUtils.toJson(data));
                    startActivity(intent);

                } else {

                    MaterialDetailFragment fragment = MaterialDetailFragment.newInstance(data);
                    getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment).commit();
                }
            }
        };


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void startDownLoadApk(final FileInfo newApkFileInfo) {


        UpgradeUtil.startUpgrade2(MainActivity.this, 1, "云飞家居", HttpUrl.completeUrl(newApkFileInfo.url), newApkFileInfo.length);


    }


    public static class NavigationViewHelper {

        @Bind(R.id.head)
        ImageView userHead;
        @Bind(R.id.code)
        TextView code;
        @Bind(R.id.name)
        TextView name;


        public NavigationViewHelper(View view) {
            ButterKnife.bind(this, view);
        }

        public void bind() {
            AUser user = SharedPreferencesHelper.getLoginUser();
            if (user == null) return;
            code.setText(user.code);
            name.setText(user.name + "(" + user.chineseName + ")");
            HttpUrl.setToken(user.token);
        }

    }


    @Override
    protected void onLoginRefresh() {

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {

                bindData();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (getPresenter().checkBack()) {
                super.onBackPressed();

            } else {
                ToastHelper.show("再次点击返回键退出应用");
            }


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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item_work_flow_report clicks here.
        int id = item.getItemId();


        switch (id) {
            case R.id.nav_product:
                showNewListFragment(productListFragment);
                getSupportActionBar().setTitle("产品列表");
                // Handle the camera action
                break;
            case R.id.nav_quotate:
                showNewListFragment(quotationListFragment);
                getSupportActionBar().setTitle("报价列表");
                break;
            case R.id.nav_order:
                showNewListFragment(orderListFragment);
                getSupportActionBar().setTitle("订单列表");
                break;
            case R.id.nav_material:
                showNewListFragment(materialListFragment);
                getSupportActionBar().setTitle("材料列表");
                break;
            case R.id.nav_schedule: {
                Intent intent = new Intent(this, WorkFlowMessageActivity.class);

                startActivity(intent);

            }
            break;

            case R.id.nav_workFlowReport:

            {
                Intent intent = new Intent(this, WorkFlowReportActivity.class);

                startActivity(intent);

                break;
            }
            case R.id.nav_work_flow_list:

            {
                Intent intent = new Intent(this, WorkFlowListActivity.class);

                startActivity(intent);
            }

            break;
            case R.id.nav_send:
                break;


            case R.id.checkUpdate:

                getPresenter().checkAppUpdateInfo();


                break;

            case R.id.reLogin:
                startLoginActivity();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showNewListFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list_container, fragment)
                .commit();


        //清空详情fragment
        if (findViewById(R.id.detail_container) != null) {
            LinearLayout.LayoutParams layoutParams = ((LinearLayout.LayoutParams) frameLayout.getLayoutParams());
            layoutParams.weight = fragment instanceof MaterialListFragment ? 2 : 1;
            frameLayout.setLayoutParams(layoutParams);
            //替换空白
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, EMPTYP_FRAGMENT).commit();
        }
    }


}
