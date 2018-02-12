package com.giants3.hd.android.mvp.appquotation;

import com.giants3.hd.android.activity.AppQuotationActivity;
import com.giants3.hd.android.activity.QuotationDetailActivity;
import com.giants3.hd.android.mvp.AppQuotationMVP;
import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.app.Quotation;
import com.giants3.hd.noEntity.RemoteData;

import java.util.List;

/**
 * Created by davidleen29 on 2017/6/3.
 */

public class PresenterImpl extends BasePresenter<AppQuotationMVP.Viewer, AppQuotationMVP.Model> implements AppQuotationMVP.Presenter {


    @Override
    public void start() {

    }

    @Override
    public AppQuotationMVP.Model createModel() {
        return new ModelImpl();
    }

    @Override
    public void searchData() {

        String key = getModel().getKey();

        int pageIndex = 0;
        int pageSize = 20;


//
        UseCaseFactory.getInstance().createGetAppQuotationsUseCase(key, pageIndex, pageSize).execute(new RemoteDataSubscriber<Quotation>(this) {
            @Override
            protected void handleRemoteData(RemoteData<Quotation> data) {
                getModel().setRemoteData(data);
                bindData();

            }


        });


    }

    private void bindData() {

        List<Quotation> datas=getModel().getDatas();
        getView().bindData(datas);

    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void setKey(String text) {
        getModel().setKey(text);
    }


}
