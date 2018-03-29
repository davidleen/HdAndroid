package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;
import com.giants3.hd.entity.Customer;

import rx.Observable;

/**
 * Created by davidleen29 on 2018/3/18.
 */

class SaveCustomerUseCase extends DefaultUseCase {
    private final Customer customer;
    private final RestApi restApi;

    public SaveCustomerUseCase(Customer customer, RestApi restApi) {
        super();
        this.customer = customer;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return restApi.saveCustomer(customer);
    }
}
