package com.giants3.hd.android.mvp.customer;

import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.android.mvp.PageModel;
import com.giants3.hd.entity.Customer;

import java.util.List;

/**
 * Created by davidleen29 on 2018/3/11.
 */

public interface CustomerListMVP {


    interface Model extends PageModel<Customer> {



    }

    interface Presenter extends NewPresenter<Viewer> {


        void setKey(String text);

        void searchData();

        void loadMoreData();

    }

    interface Viewer extends NewViewer {



        void bindData(List<Customer> datas);
    }
}
