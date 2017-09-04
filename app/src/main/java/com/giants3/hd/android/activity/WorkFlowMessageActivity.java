package com.giants3.hd.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.giants3.hd.android.R;
import com.giants3.hd.android.fragment.WorkFlowMessageFragment;

import butterknife.Bind;

/**
 * An activity representing a single ProductListActivity detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item_work_flow_report details are presented side-by-side with a list of items
 * in a {@link ProductListActivity}.
 */
public class WorkFlowMessageActivity extends BaseActivity  {



    @Bind(R.id.detail_toolbar )
    Toolbar toolbar  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_flow_message);


        setSupportActionBar(toolbar);


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("我的流程任务");


        }

        if (savedInstanceState == null) {


            WorkFlowMessageFragment fragment = WorkFlowMessageFragment.newInstance(  );
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            onBackPressed();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
