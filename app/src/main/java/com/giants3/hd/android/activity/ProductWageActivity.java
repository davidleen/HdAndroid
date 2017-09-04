package com.giants3.hd.android.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.giants3.hd.android.R;
import com.giants3.hd.android.fragment.ProductWageFragment;

import butterknife.Bind;

/**
 *
 * 产品材料清单编辑界面
 * An activity representing a single ProductListActivity detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item_work_flow_report details are presented side-by-side with a list of items
 * in a {@link ProductListActivity}.
 */
public class ProductWageActivity extends BaseActivity implements  ProductWageFragment.OnFragmentInteractionListener {



    @Bind(R.id.detail_toolbar )
    Toolbar toolbar  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_wage);
        setSupportActionBar(toolbar);




        // Show the Up button in the action bar.


        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(ProductWageFragment.PRODUCT_MATERIAL_TYPE,
                    getIntent().getIntExtra(ProductWageFragment.PRODUCT_MATERIAL_TYPE,ProductWageFragment.PRODUCT_MATERIAL_CONCEPTUS));
            arguments.putInt(ProductWageFragment.PRODUCT_WAGE_POSITION,
                    getIntent().getIntExtra(ProductWageFragment.PRODUCT_WAGE_POSITION,0));
            arguments.putString(ProductWageFragment.EXTRA_PRODUCT_WAGE,
                    getIntent().getStringExtra(ProductWageFragment.EXTRA_PRODUCT_WAGE  ));
            ProductWageFragment fragment = new ProductWageFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.product_wage_container, fragment)
                    .commit();
        }



        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("产品工资编辑" );
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
    public void onFragmentInteraction(Uri uri) {

    }
}
