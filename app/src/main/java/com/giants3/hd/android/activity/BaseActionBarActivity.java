package com.giants3.hd.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.giants3.hd.android.R;

import butterknife.Bind;

/**
 * 默认带头部导航条的act
 * <p>
 * Created by davidleen29 on 2017/7/21.
 */

public abstract class BaseActionBarActivity extends BaseActivity {
    @Bind(R.id.toolbar )
    Toolbar toolbar  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.activity_base_action_bar, null);

        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.content);
        frameLayout.addView(getContentView());
        setContentView(view);
        setSupportActionBar(toolbar);
        setBackEnable(true);

    }

    /**
     * 设置标题
     * @param title
     */
    protected final   void setTitle(String title)
    {



        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setTitle(title);


        }
    }


    protected  final void setBackEnable(boolean show)
    {
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show);



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
    protected abstract View getContentView();

}
