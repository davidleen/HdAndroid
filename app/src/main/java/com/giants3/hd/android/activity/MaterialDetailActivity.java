package com.giants3.hd.android.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.giants3.hd.android.R;
import com.giants3.hd.android.fragment.MaterialDetailFragment;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.Material;
import com.giants3.hd.exception.HdException;

import butterknife.Bind;

/**
 * An activity representing a single ProductListActivity detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item_work_flow_report details are presented side-by-side with a list of items
 * in a {@link ProductListActivity}.
 */
public class MaterialDetailActivity extends BaseActivity implements MaterialDetailFragment.OnFragmentInteractionListener, Toolbar.OnMenuItemClickListener {



    @Bind(R.id.detail_toolbar )
    Toolbar toolbar  ;


    @Bind(R.id.app_bar )
    AppBarLayout app_bar  ;



    Material material;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_detail);

        setSupportActionBar(toolbar);


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setDisplayShowTitleEnabled(true);


            actionBar.setTitle("材料详情");


            //actionBar.setOnMenuItemClickListener(this);
        }

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
            try {
                material = GsonUtils.fromJson(getIntent().getStringExtra(MaterialDetailFragment.ARG_ITEM),Material.class);
            } catch (HdException e) {
                e.printStackTrace();
                ToastHelper.show("参数异常");
                finish();
                return;
            }





            MaterialDetailFragment  fragment = MaterialDetailFragment.newInstance(material);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.material_detail_container, fragment)
                    .commit();
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
