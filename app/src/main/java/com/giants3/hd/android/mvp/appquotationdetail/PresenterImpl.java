package com.giants3.hd.android.mvp.appquotationdetail;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintManager;

import com.giants3.hd.android.mvp.BasePresenter;
import com.giants3.hd.android.mvp.RemoteDataSubscriber;
import com.giants3.hd.android.print.PDFPrintDocumentAdapter;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.entity.Product;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.noEntity.app.QuotationDetail;

import java.io.File;
import java.util.List;

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
        loadCustomer();
    }


    @Override
    public void loadCustomer() {

        RemoteDataSubscriber<Customer> subscriber = new RemoteDataSubscriber<Customer>(this, true) {

            @Override
            protected void handleRemoteData(RemoteData<Customer> data) {
                if (data.isSuccess()) {
                    getModel().setCustomers(data.datas);
                }
            }
        };


        executeUseCase(UseCaseFactory.getInstance().createGetCustomerListUseCase(), subscriber);
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


    private void bindData() {

        getView().bindData(getModel().getQuotationDetail());
    }

    private <T> void executeUseCase(UseCase useCase, RemoteDataSubscriber<T> subscriber) {

        //createTempQuotation;
        useCase.execute(subscriber);

    }


    @Override
    public void addNewProduct(long productId) {


        getView().showWaiting();


        RemoteDataSubscriber<Product> useCaseSubscriber = new RemoteDataSubscriber<Product>(this) {

            @Override
            protected void handleRemoteData(RemoteData<Product> data) {
                if (data.isSuccess()) {

                    Product product = data.datas.get(0);
                    getModel().addNewProduct(product);
                    bindData();


                } else {
                    getView().showMessage(data.message);
                }
            }
        };

        UseCaseFactory.getInstance().createGetProductByIdCase(productId).execute(useCaseSubscriber);


    }

    @Override
    public void deleteQuotationItem(int item) {


        getModel().deleteQuotationItem(item);
        bindData();

    }

    @Override
    public void updatePrice(int itm, float newFloatValue) {

        getModel().updateItemPrice(itm, newFloatValue);
        getView().bindData(getModel().getQuotationDetail());

//        QuotationDetail quotationDetail = getModel().getQuotationDetail();
//        long quotationId = quotationDetail.quotation.id;
//
//
//        UseCase useCase = UseCaseFactory.getInstance().createUpdateQuotationItemPriceUseCase(quotationId, itm, newFloatValue);
//
//
//        executeQuotationUseCase(useCase);


    }


    @Override
    public void updateQty(int itm, int newQty) {


        getModel().updateItemQty(itm, newQty);
        getView().bindData(getModel().getQuotationDetail());


//        QuotationDetail quotationDetail = getModel().getQuotationDetail();
//        long quotationId = quotationDetail.quotation.id;
//
//
//        UseCase useCase = UseCaseFactory.getInstance().createUpdateQuotationItemQtyUseCase(quotationId, itm, newQty);
//
//
//        executeQuotationUseCase(useCase);
    }

    @Override
    public void updateMemo(int itm, String memo) {


        getModel().updateItemMemo(itm, memo);
        getView().bindData(getModel().getQuotationDetail());
//        QuotationDetail quotationDetail = getModel().getQuotationDetail();
//        long quotationId = quotationDetail.quotation.id;
//
//
//        UseCase useCase = UseCaseFactory.getInstance().createUpdateQuotationItemMemoUseCase(quotationId, itm, memo);
//
//
//        executeQuotationUseCase(useCase);
    }


    @Override
    public void updateValidateTime(String dateString) {


        getModel().updateValidateTime(dateString);
        bindData();


    }

    @Override
    public void updateCreateTime(String dateString) {
        getModel().updateCreateTime(dateString);
        bindData();


    }

    @Override
    public void updateQuotationMemo(String newValue) {
        getModel().updateQuotationMemo(newValue);

        bindData();
    }


    @Override
    public void updateQuotationNumber(String newValue) {


        getModel().updateQuotationNumber(newValue);

        bindData();


    }

    @Override
    public void deleteQuotation() {


        getView().showWaiting();
        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        long quotationId = quotationDetail.quotation.id;


        UseCase useCase = UseCaseFactory.getInstance().createDeleteQuotationUseCase(quotationId);

        RemoteDataSubscriber<Void> useCaseSubscriber = new RemoteDataSubscriber<Void>(this) {

            @Override
            protected void handleRemoteData(RemoteData<Void> data) {
                if (data.isSuccess()) {

                    getView().showMessage("删除成功");
                    getView().exit();


                } else {
                    getView().showMessage(data.message);
                }
            }
        };
        useCase.execute(useCaseSubscriber);


    }

    @Override
    public void updateItemDiscount(int itm, float newDisCount) {


        getModel().updateItemDiscount(itm, newDisCount);
        bindData();

    }

    @Override
    public void updateQuotationDiscount(float newDisCount) {
        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        long quotationId = quotationDetail.quotation.id;


        UseCase useCase = UseCaseFactory.getInstance().createUpdateQuotationDiscountUseCase(quotationId, newDisCount);

        executeQuotationUseCase(useCase);
    }


    @Override
    public void saveQuotation() {


        boolean hasModify = getModel().hasModify();
        if (!hasModify) {

            getView().showMessage("数据无改变");
            return;

        }
        getView().showWaiting();


        QuotationDetail quotationDetail = getModel().getQuotationDetail();


        UseCase useCase = UseCaseFactory.getInstance().createSaveQuotationUseCase(quotationDetail);

        RemoteDataSubscriber<QuotationDetail> useCaseSubscriber = new RemoteDataSubscriber<QuotationDetail>(this) {

            @Override
            protected void handleRemoteData(RemoteData<QuotationDetail> data) {
                if (data.isSuccess()) {
                    getModel().setQuotationDetail(data.datas.get(0));
                    bindData();
                    getView().showMessage("保存成功");
                    getView().setResultOK();

                } else {
                    getView().showMessage(data.message);
                }
            }
        };
        useCase.execute(useCaseSubscriber);
    }

    private void executeQuotationUseCase(UseCase useCase) {
        RemoteDataSubscriber<QuotationDetail> useCaseSubscriber = new RemoteDataSubscriber<QuotationDetail>(this) {

            @Override
            protected void handleRemoteData(RemoteData<QuotationDetail> data) {
                if (data.isSuccess()) {
                    getModel().setQuotationDetail(data.datas.get(0));


                } else {
                    getView().showMessage(data.message);
                }
                bindData();
            }
        };
        useCase.execute(useCaseSubscriber);

    }

    @Override
    public void goBack() {


        if (getModel().hasModify()) {
            getView().showUnSaveAlert();

        } else {

            getView().exit();
        }


    }

    private void onPrintPdf(String filePath) {
        PrintManager printManager = (PrintManager) getView().getContext().getSystemService(Context.PRINT_SERVICE);
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(PrintAttributes.COLOR_MODE_COLOR);

        printManager.print("test pdf print", new PDFPrintDocumentAdapter(getView().getContext(), filePath), builder.build());
    }

    @Override
    public void printQuotation() {


        if (getModel().hasModify()) {
            getView().showMessage("先保存报价单。");
            return;
        }


        QuotationDetail quotationDetail = getModel().getQuotationDetail();

        final String filePath = new File(getView().getContext().getExternalCacheDir(), System.currentTimeMillis() + ".pdf").getPath();
        long quotationId = quotationDetail.quotation.id;


        UseCase useCase = UseCaseFactory.getInstance().createPrintQuotationUseCase(quotationId, filePath);

        getView().showWaiting();
        useCase.execute(new RemoteDataSubscriber<Void>(this) {
            @Override
            protected void handleRemoteData(RemoteData<Void> data) {

                if (data.isSuccess()) {

                    onPrintPdf(filePath);


                }

            }


        });


    }


    @Override
    public void pickCustomer() {


        List<Customer> customers = getModel().getCustomers();


        QuotationDetail quotationDetail = getModel().getQuotationDetail();

        long customerId = quotationDetail.quotation.customerId;

        Customer current = null;

        for (Customer temp : customers) {
            if (temp.id == customerId) {
                current = temp;
                break;
            }

        }

        getView().chooseCustomer(current, customers);


    }


    @Override
    public void updateCustomer(Customer newValue) {


        getModel().updateCustomer(newValue);
        bindData();

        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        quotationDetail.quotation.customerId = newValue.id;
        quotationDetail.quotation.customerCode = newValue.code;
        quotationDetail.quotation.customerName = newValue.name;
        bindData();


        ;


        executeQuotationUseCase(UseCaseFactory.getInstance().createUpdateQuotationCustomerUseCase(quotationDetail.quotation.id, newValue.id));

    }


}
