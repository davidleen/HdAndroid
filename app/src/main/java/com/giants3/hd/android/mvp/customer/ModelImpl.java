package com.giants3.hd.android.mvp.customer;

import com.giants3.hd.android.mvp.AppQuotationMVP;
import com.giants3.hd.android.mvp.PageModelImpl;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.entity.app.Quotation;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl extends PageModelImpl<Customer> implements CustomerEditMVP.Model {


    private Customer customer;

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(Customer customer) {

        this.customer = customer;
    }
}
