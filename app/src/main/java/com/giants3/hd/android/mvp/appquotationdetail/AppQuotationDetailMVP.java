package com.giants3.hd.android.mvp.appquotationdetail;


import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.android.mvp.PageModel;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.entity.Product;
import com.giants3.hd.noEntity.app.QuotationDetail;

import java.util.List;

/**
 * 代办流程消息列表
 * Created by davidleen29 on 2017/5/23.
 */

public interface AppQuotationDetailMVP {


    interface Model extends PageModel<QuotationDetail> {


        void setQuotationDetail(QuotationDetail quotationDetail);

        QuotationDetail getQuotationDetail();

        void setCustomers(List<Customer> customer);
        List<Customer> getCustomers();

        void updateItemQty(int itmIndex, int newQty);

        void updateItemPrice(int itm, float newFloatValue);

        void updateItemMemo(int itm, String memo);

        void updateItemDiscount(int itm, float newDisCount);

        void updateCustomer(Customer customer);

        void deleteQuotationItem(int item);

        void updateValidateTime(String dateString);

        void updateCreateTime(String dateString);

        void updateQuotationNumber(String newValue);

        void updateQuotationMemo(String newValue);

        void addNewProduct(Product product);

        boolean hasModify();

    }

    interface Presenter extends NewPresenter<AppQuotationDetailMVP.Viewer> {


        void setQuotationId(long quotationId);


        void addNewProduct(long  productId);

        void deleteQuotationItem(int item);

        void updatePrice(int itm, float newFloatValue);

        void updateQty(int itm, int newQty);

        void updateItemDiscount(int itm, float newDisCount);

        void updateQuotationDiscount(float newDisCount);

        void saveQuotation();

        void goBack();

        void printQuotation();

        void pickCustomer();

        void updateCustomer(Customer newValue);

        void loadCustomer();


        void updateMemo(int itm, String newValue);

        void deleteQuotation();

        void updateValidateTime(String dateString);

        void updateCreateTime(String dateString);

        void updateQuotationMemo(String newValue);

        void updateQuotationNumber(String newValue);
    }

    interface Viewer extends NewViewer {


        void bindData(QuotationDetail data);

        void showUnSaveAlert();

        void exit();

        void chooseCustomer(Customer current, List<Customer> customers);

        void setResultOK();
    }
}
