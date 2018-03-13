package com.giants3.hd.android.mvp.customer;

import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.android.mvp.PageModel;
import com.giants3.hd.entity.Customer;

/**
 * Created by davidleen29 on 2018/3/11.
 */

public interface CustomerEditMVP {


    interface Model extends PageModel<Customer> {



    }

    interface Presenter extends NewPresenter<Viewer> {





    }

    interface Viewer extends NewViewer {



    }
}
