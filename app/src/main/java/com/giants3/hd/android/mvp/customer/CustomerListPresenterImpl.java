package com.giants3.hd.android.mvp.customer;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.noEntity.RemoteData;

/**
 * Created by davidleen29 on 2018/3/17.
 */

public class CustomerListPresenterImpl extends BasePresenter<CustomerListMVP.Viewer, CustomerListMVP.Model> implements CustomerListMVP.Presenter {

    @Override
    public CustomerListMVP.Model createModel() {
        return new CustomerListModelImpl();
    }



    @Override
    public void start() {

    }

    @Override
    public void setKey(String text) {

        getModel().setKey(text);

    }

    @Override
    public void searchData() {

        String key=getModel().getKey();



//
        UseCaseFactory.getInstance().createGetCustomerListUseCase(key ).execute(new RemoteDataSubscriber<Customer>(this) {
            @Override
            protected void handleRemoteData(RemoteData<Customer> data) {
                getModel().setRemoteData(data);
                    bindData();

            }


        });


    }

    private void bindData() {


        getView().bindData(getModel().getDatas());
    }

    @Override
    public void loadMoreData() {

    }
}
