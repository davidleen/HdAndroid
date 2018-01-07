package com.giants3.hd.android.widget;

import android.content.Context;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

/**
 * Created by davidleen29 on 2017/11/24.
 */

public class RefreshLayoutConfig {

    public static void config(TwinklingRefreshLayout swipeLayout)
    {

        Context context   =swipeLayout.getContext();
        swipeLayout.setHeaderView(new ProgressLayout(context));
        swipeLayout.setBottomView(new LoadingView(context));
        swipeLayout.setAutoLoadMore(true);
        swipeLayout.setFloatRefresh(true);
    }
}
