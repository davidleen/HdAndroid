package com.giants3.hd.android.mvp.appquotationdetail;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.noEntity.app.QuotationDetail;

/**
 * 广交会报价 P层接口
 * Created by davidleen29 on 2016/9/23.
 */

public class PresenterImpl extends BasePresenter<AppQuotationDetailMVP.Viewer, AppQuotationDetailMVP.Model> implements AppQuotationDetailMVP.Presenter {


    @Override
    public AppQuotationDetailMVP.Model createModel() {
        return new ModelImpl();
    }

    @Override
    public void start() {

    }



    @Override
    public void setQuotationId(long quotationId) {

        UseCase useCase;

        //loadQuotation;
        //createTempQuotation;
        RemoteDataSubscriber<QuotationDetail> useCaseSubscriber = new RemoteDataSubscriber<QuotationDetail>(this) {

            @Override
            protected void handleRemoteData(RemoteData<QuotationDetail> data) {
                if (data.isSuccess()) {
                    getModel().setQuotationDetail(data.datas.get(0));
                    bindData();



                }
            }
        };
        if (quotationId > -1) {


            useCase = UseCaseFactory.getInstance().getAppQuotationDetailCase(quotationId);


        } else {

            //createTempQuotation;
            useCase = UseCaseFactory.getInstance().createTempQuotation();

        }


        executeUseCase(useCase, useCaseSubscriber);


    }


    private  void  bindData()
    {

        getView().bindData(getModel().getQuotationDetail());
    }
    private void executeUseCase(UseCase useCase, RemoteDataSubscriber<QuotationDetail> subscriber) {

        //createTempQuotation;
        useCase.execute(subscriber);

    }

    @Override
    public void pickNewProduct() {




    }

    @Override
    public void addNewProduct(long productId ) {



        QuotationDetail quotationDetail=getModel().getQuotationDetail();
        long quotationId=quotationDetail.quotation.id;

        RemoteDataSubscriber<QuotationDetail> useCaseSubscriber = new RemoteDataSubscriber<QuotationDetail>(this) {

            @Override
            protected void handleRemoteData(RemoteData<QuotationDetail> data) {
                if (data.isSuccess()) {
                    getModel().setQuotationDetail(data.datas.get(0));
                    bindData();

//
//                    UseCase addProductToQuotationUseCase = UseCaseFactory.getInstance().createAddProductToQuotationUseCase(data.datas.get(0).quotation.id, 123);
//                    addProductToQuotationUseCase.execute(useCaseSubscriber);
                }
            }
        };

        UseCase addProductToQuotationUseCase = UseCaseFactory.getInstance().createAddProductToQuotationUseCase(quotationId, productId);
        addProductToQuotationUseCase.execute(useCaseSubscriber);




    }

    @Override
    public void deleteQuotationItem(int  item) {


        QuotationDetail quotationDetail=getModel().getQuotationDetail();
        long quotationId=quotationDetail.quotation.id;

        RemoteDataSubscriber<QuotationDetail> useCaseSubscriber = new RemoteDataSubscriber<QuotationDetail>(this) {

            @Override
            protected void handleRemoteData(RemoteData<QuotationDetail> data) {
                if (data.isSuccess()) {
                    getModel().setQuotationDetail(data.datas.get(0));
                    bindData();

//
//                    UseCase addProductToQuotationUseCase = UseCaseFactory.getInstance().createAddProductToQuotationUseCase(data.datas.get(0).quotation.id, 123);
//                    addProductToQuotationUseCase.execute(useCaseSubscriber);
                }
            }
        };

        UseCase useCase = UseCaseFactory.getInstance().createRemoveItemFromQuotationUseCase(quotationId, item);
        useCase.execute(useCaseSubscriber);
    }

    @Override
    public void updatePrice(int itm, float newFloatValue) {


        QuotationDetail quotationDetail=getModel().getQuotationDetail();
        long quotationId=quotationDetail.quotation.id;

        RemoteDataSubscriber<QuotationDetail> useCaseSubscriber = new RemoteDataSubscriber<QuotationDetail>(this) {

            @Override
            protected void handleRemoteData(RemoteData<QuotationDetail> data) {
                if (data.isSuccess()) {
                    getModel().setQuotationDetail(data.datas.get(0));
                    bindData();

//
//                    UseCase addProductToQuotationUseCase = UseCaseFactory.getInstance().createAddProductToQuotationUseCase(data.datas.get(0).quotation.id, 123);
//                    addProductToQuotationUseCase.execute(useCaseSubscriber);
                }
            }
        };

        UseCase useCase = UseCaseFactory.getInstance().createUpdateQuotationItemPriceUseCase(quotationId, itm,newFloatValue);
        useCase.execute(useCaseSubscriber);



    }


    @Override
    public void updateQty(int itm, int newQty) {
        QuotationDetail quotationDetail=getModel().getQuotationDetail();
        long quotationId=quotationDetail.quotation.id;

        RemoteDataSubscriber<QuotationDetail> useCaseSubscriber = new RemoteDataSubscriber<QuotationDetail>(this) {

            @Override
            protected void handleRemoteData(RemoteData<QuotationDetail> data) {
                if (data.isSuccess()) {
                    getModel().setQuotationDetail(data.datas.get(0));
                    bindData();

                }
            }
        };

        UseCase useCase = UseCaseFactory.getInstance().createUpdateQuotationItemQtyUseCase(quotationId, itm,newQty);
        useCase.execute(useCaseSubscriber);
    }
}
