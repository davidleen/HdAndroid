package com.giants3.hd.android.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.giants3.hd.android.R;
import com.giants3.hd.android.entity.ProductDetailSingleton;
import com.giants3.hd.android.fragment.ProductDetailFragment;

import butterknife.Bind;

/**
 * An activity representing a single ProductListActivity detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item_work_flow_report details are presented side-by-side with a list of items
 * in a {@link ProductListActivity}.
 */
public class ProductDetailActivity extends BaseActivity {



      boolean editable;

    @Bind(R.id.detail_toolbar )
    Toolbar toolbar  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setSupportActionBar(toolbar);



        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ProductDetailFragment.ARG_ITEM,
                    getIntent().getStringExtra(ProductDetailFragment.ARG_ITEM));

            editable=getIntent().getBooleanExtra(ProductDetailFragment.EXTRA_EDITABLE,false);
            arguments.putBoolean(ProductDetailFragment.EXTRA_EDITABLE,
                    editable);
            ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.product_detail_container, fragment)
                    .commit();
        }



        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(editable?"产品编辑":"产品详情");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {


        if(ProductDetailSingleton.getInstance().hasModifyDetail())
        {
            AlertDialog alertDialog =new AlertDialog.Builder(this).setMessage("未保存数据，确认退出？").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    finish();

                }
            }).create();
            alertDialog.show();


        }else
        {
            super.onBackPressed();
        }

    }
}
