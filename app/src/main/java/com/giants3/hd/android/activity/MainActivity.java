package com.giants3.hd.android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.fragment.OrderDetailFragment;
import com.giants3.hd.android.fragment.OrderListFragment;
import com.giants3.hd.android.fragment.ProductDetailFragment;
import com.giants3.hd.android.fragment.ProductListFragment;
import com.giants3.hd.android.fragment.QuotationListFragment;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.AUser;
import com.giants3.hd.utils.entity.ErpOrder;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.data.utils.GsonUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProductListFragment.OnFragmentInteractionListener, QuotationListFragment.OnFragmentInteractionListener, OrderListFragment.OnFragmentInteractionListener,OrderDetailFragment.OnFragmentInteractionListener {


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

        helper = new NavigationViewHelper(navigationView);


        //登录验证

        if (SharedPreferencesHelper.getLoginUser() == null) {

            startLoginActivity();
        } else {


            getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {


                    helper.bind();
                }
            });

        }


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(ErpOrder erpOrder) {


        if (findViewById(R.id.detail_container) == null) {

            //调整act
            Intent intent=new Intent(this,OrderDetailActivity.class);
            intent.putExtra(OrderDetailFragment.ARG_ITEM, GsonUtils.toJson(erpOrder));
            startActivity(intent);

        } else {

            OrderDetailFragment fragment = OrderDetailFragment.newInstance(erpOrder);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment).commit();

        }


    }

    @Override
    public void onFragmentInteraction(AProduct aProduct) {



        if (findViewById(R.id.detail_container) == null) {

            //调整act
            Intent intent=new Intent(this,ProductDetailActivity.class);
            intent.putExtra(ProductDetailFragment.ARG_ITEM, GsonUtils.toJson(aProduct));
            startActivity(intent);

        } else {

            ProductDetailFragment fragment = ProductDetailFragment.newInstance(aProduct);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, fragment).commit();
        }



    }


    public static class NavigationViewHelper {

        @Bind(R.id.head)
        ImageView userHead;
        @Bind(R.id.code)
        TextView code;
        @Bind(R.id.name)
        TextView name;
        NavigationView view;

        boolean viewInject = false;

        public NavigationViewHelper(NavigationView view) {

            this.view = view;


        }


        public void bind() {

            if (!viewInject) {
                ButterKnife.bind(this, view);
                viewInject = true;
            }


            AUser user = SharedPreferencesHelper.getLoginUser();

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
                helper.bind();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SettingActivity.startActivity(this, 100);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_product) {

            ProductListFragment fragment = ProductListFragment.newInstance("", "");

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_container, fragment)
                    .commit();

            getSupportActionBar().setTitle("产品列表");

            // Handle the camera action
        } else if (id == R.id.nav_quotate) {


            QuotationListFragment fragment = QuotationListFragment.newInstance("", "");

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_container, fragment)
                    .commit();
            getSupportActionBar().setTitle("报价列表");

        } else if (id == R.id.nav_order) {

            OrderListFragment fragment = OrderListFragment.newInstance("", "");

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_container, fragment)
                    .commit();
            getSupportActionBar().setTitle("订单列表");

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.reLogin) {
            startLoginActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
