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


        Customer getCustomer();

        void setCustomer(Customer customer);
    }

    interface Presenter extends NewPresenter<Viewer> {


        void save();

        void updateValue(String codeText, String nameText, String telText, String faxText, String emailText, String addressText, String nationText);
    }

    interface Viewer extends NewViewer {


        void finish();
    }
}
