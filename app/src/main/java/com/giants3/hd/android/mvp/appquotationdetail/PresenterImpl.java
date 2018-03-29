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
    public  void loadCustomer()
    {

        RemoteDataSubscriber<Customer> subscriber = new RemoteDataSubscriber<Customer>(this,true) {

            @Override
            protected void handleRemoteData(RemoteData<Customer> data) {
                if (data.isSuccess()) {
                    getModel().setCustomers(data.datas );
                }
            }
        };


       executeUseCase( UseCaseFactory.getInstance().createGetCustomerListUseCase(),subscriber);
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


        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        long quotationId = quotationDetail.quotation.id;


        UseCase addProductToQuotationUseCase = UseCaseFactory.getInstance().createAddProductToQuotationUseCase(quotationId, productId);

        executeQuotationUseCase(addProductToQuotationUseCase);


    }

    @Override
    public void deleteQuotationItem(int item) {


        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        long quotationId = quotationDetail.quotation.id;


        UseCase useCase = UseCaseFactory.getInstance().createRemoveItemFromQuotationUseCase(quotationId, item);

        executeQuotationUseCase(useCase);
    }

    @Override
    public void updatePrice(int itm, float newFloatValue) {


        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        long quotationId = quotationDetail.quotation.id;


        UseCase useCase = UseCaseFactory.getInstance().createUpdateQuotationItemPriceUseCase(quotationId, itm, newFloatValue);


        executeQuotationUseCase(useCase);


    }


    @Override
    public void updateQty(int itm, int newQty) {
        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        long quotationId = quotationDetail.quotation.id;


        UseCase useCase = UseCaseFactory.getInstance().createUpdateQuotationItemQtyUseCase(quotationId, itm, newQty);


        executeQuotationUseCase(useCase);
    }
    @Override
    public void updateMemo(int itm, String memo) {
        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        long quotationId = quotationDetail.quotation.id;


        UseCase useCase = UseCaseFactory.getInstance().createUpdateQuotationItemMemoUseCase(quotationId, itm, memo);


        executeQuotationUseCase(useCase);
    }


    @Override
    public void updateValidateTime(String dateString) {


        QuotationDetail quotationDetail=getModel().getQuotationDetail();
        quotationDetail.quotation.vDate=dateString;
        bindData();


        executeQuotationUseCase( UseCaseFactory.getInstance().createUpdateQuotationFieldUseCase( quotationDetail.quotation.id,"vDate",dateString));


    }

    @Override
    public void updateCreateTime(String dateString) {
        QuotationDetail quotationDetail=getModel().getQuotationDetail();
        quotationDetail.quotation.qDate=dateString;
        bindData();
        executeQuotationUseCase( UseCaseFactory.getInstance().createUpdateQuotationFieldUseCase( quotationDetail.quotation.id,"qDate",dateString));

    }

    @Override
    public void updateQuotationMemo(String newValue) {
        QuotationDetail quotationDetail=getModel().getQuotationDetail();
        quotationDetail.quotation.memo=newValue;
        bindData();
        executeQuotationUseCase( UseCaseFactory.getInstance().createUpdateQuotationFieldUseCase( quotationDetail.quotation.id,"memo",newValue));
    }

    @Override
    public void deleteQuotation() {



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
        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        long quotationId = quotationDetail.quotation.id;


        UseCase useCase = UseCaseFactory.getInstance().createUpdateQuotationItemDiscountUseCase(quotationId, itm, newDisCount);


        executeQuotationUseCase(useCase);
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
        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        long quotationId = quotationDetail.quotation.id;


        UseCase useCase = UseCaseFactory.getInstance().createSaveQuotationUseCase(quotationId);
        executeQuotationUseCase(useCase);
    }

    private void executeQuotationUseCase(UseCase useCase) {
        RemoteDataSubscriber<QuotationDetail> useCaseSubscriber = new RemoteDataSubscriber<QuotationDetail>(this) {

            @Override
            protected void handleRemoteData(RemoteData<QuotationDetail> data) {
                if (data.isSuccess()) {
                    getModel().setQuotationDetail(data.datas.get(0));
                    bindData();

                }
            }
        };
        useCase.execute(useCaseSubscriber);

    }

    @Override
    public void goBack() {


        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        if (quotationDetail != null && quotationDetail.quotation != null && !quotationDetail.quotation.formal) {
            getView().showUnSaveAlert();

        } else {

            getView().exit();
        }


    }

    private void onPrintPdf(String  filePath) {
        PrintManager printManager = (PrintManager) getView().getContext().getSystemService(Context.PRINT_SERVICE);
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(PrintAttributes.COLOR_MODE_COLOR);

        printManager.print("test pdf print", new PDFPrintDocumentAdapter(getView().getContext(), filePath), builder.build());
    }
    @Override
    public void printQuotation() {



        QuotationDetail quotationDetail = getModel().getQuotationDetail();
        if(quotationDetail==null||quotationDetail.quotation==null)return ;
        if(!quotationDetail.quotation.formal)
        {
            getView().showMessage("先保存报价单。");
            return;
        }


        final String filePath=new File(getView().getContext().getExternalCacheDir(),System.currentTimeMillis()+".pdf").getPath();
        long quotationId = quotationDetail.quotation.id;



        UseCase useCase = UseCaseFactory.getInstance().createPrintQuotationUseCase(quotationId,filePath);

        getView().showWaiting();
        useCase.execute(new RemoteDataSubscriber<Void>(this) {
            @Override
            protected void handleRemoteData(RemoteData<Void> data) {

                if(data.isSuccess())
                {

                    onPrintPdf(filePath);



                }

            }


        });


    }


    @Override
    public void pickCustomer() {


        List<Customer> customers=getModel().getCustomers();



        QuotationDetail quotationDetail=getModel().getQuotationDetail();

        long customerId = quotationDetail.quotation.customerId;

        Customer current=null;

        for(Customer temp:customers)
        {
            if(temp.id==customerId) {
                current = temp;
                break;
            }

        }

        getView().chooseCustomer(current,customers);


    }


    @Override
    public void updateCustomer(Customer newValue) {

        QuotationDetail quotationDetail=getModel().getQuotationDetail();
        quotationDetail.quotation.customerId=newValue.id;
        quotationDetail.quotation.customerCode=newValue.code;
        quotationDetail.quotation.customerName =newValue.name;
        bindData();


       ;


        executeQuotationUseCase( UseCaseFactory.getInstance().createUpdateQuotationCustomerUseCase( quotationDetail.quotation.id,newValue.id));

    }



}
