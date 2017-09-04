package com.giants3.hd.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.giants3.hd.android.R;
import com.giants3.hd.android.fragment.MaterialSelectFragment;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.Material;

import butterknife.Bind;

/**材料挑选界面
 * An activity representing a single ProductListActivity detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item_work_flow_report details are presented side-by-side with a list of items
 * in a {@link ProductListActivity}.
 */
public class MaterialSelectActivity extends BaseActivity implements MaterialSelectFragment.OnFragmentInteractionListener {


    public static final String EXTRA_MATERIAL = "EXTRA_MATERIAL";
    @Bind(R.id.detail_toolbar )
    Toolbar toolbar  ;


    @Bind(R.id.app_bar )
    AppBarLayout app_bar  ;



    Material material;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_select);

        setSupportActionBar(toolbar);


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setDisplayShowTitleEnabled(true);


            actionBar.setTitle("材料选择");

        }


        if (savedInstanceState == null) {

            MaterialSelectFragment  fragment = MaterialSelectFragment.newInstance(getIntent().getStringExtra(MaterialSelectFragment.ARG_PARAM_CODE),getIntent().getStringExtra(MaterialSelectFragment.ARG_PARAM_NAME));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.material_select_container, fragment)
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
    public void onFragmentInteraction(Material material) {



        Intent intent=new Intent();
        intent.putExtra(EXTRA_MATERIAL,GsonUtils.toJson(material));
        setResult(RESULT_OK,intent);
        finish();

    }


}
