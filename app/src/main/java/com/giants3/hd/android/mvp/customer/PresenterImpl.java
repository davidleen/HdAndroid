package com.giants3.hd.android.mvp.customer;

import com.giants3.hd.android.events.CustomerUpdateEvent;
import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.noEntity.RemoteData;

import de.greenrobot.event.EventBus;

/**
 * Created by davidleen29 on 2018/3/17.
 */

public class PresenterImpl extends BasePresenter<CustomerEditMVP.Viewer, CustomerEditMVP.Model> implements CustomerEditMVP.Presenter {

    @Override
    public CustomerEditMVP.Model createModel() {
        return new ModelImpl();
    }

    @Override
    public void save() {

        Customer customer = getModel().getCustomer();

        if (customer == null) return;

        UseCase useCase = UseCaseFactory.getInstance().createSaveCustomerUseCase(customer);

        getView().showWaiting();
        useCase.execute(new RemoteDataSubscriber<Void>(this) {
            @Override
            protected void handleRemoteData(RemoteData<Void> data) {

                if (data.isSuccess()) {


                    getView().showMessage("保存成功");
                    EventBus.getDefault().post(new CustomerUpdateEvent());
                    getView().finish();

                }

            }


        });


    }

    @Override
    public void updateValue(String codeText, String nameText, String telText, String faxText, String emailText, String addressText, String nationText) {


        Customer customer = getModel().getCustomer();

        if (customer == null) {
            customer = new Customer();
            getModel().setCustomer(customer);
            }


            customer.code=codeText;
        customer.name=nameText;
        customer.tel=telText;
        customer.fax=faxText;
        customer.email=emailText;
        customer.addr=addressText;

        customer.nation=nationText;




    }

    @Override
    public void start() {

    }
}
