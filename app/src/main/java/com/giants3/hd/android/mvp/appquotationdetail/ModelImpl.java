package com.giants3.hd.android.mvp.appquotationdetail;

import com.giants3.hd.android.mvp.PageModelImpl;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.Customer;
import com.giants3.hd.entity.Product;
import com.giants3.hd.logic.AppQuotationAnalytics;
import com.giants3.hd.noEntity.app.QuotationDetail;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl extends PageModelImpl<QuotationDetail> implements AppQuotationDetailMVP.Model {



    String olddData;
    private QuotationDetail quotationDetail;
    private List<Customer> customerList;

    @Override
    public void setQuotationDetail(QuotationDetail quotationDetail) {

        this.quotationDetail = quotationDetail;

        olddData= GsonUtils.toJson(quotationDetail);
    }

    @Override
    public QuotationDetail  getQuotationDetail() {
        return quotationDetail;
    }

    @Override
    public void setCustomers(List<Customer> customers) {

        this.customerList = customers;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerList;
    }

    @Override
    public void updateItemQty(int itmIndex, int newQty) {


        AppQuotationAnalytics.updateQuotationItemQty(quotationDetail,itmIndex,newQty);
    }

    @Override
    public void updateItemPrice(int itm, float newFloatValue) {
        AppQuotationAnalytics.updateQuotationItemPrice(quotationDetail,itm,newFloatValue);
    }

    @Override
    public void updateItemMemo(int itm, String memo) {

        if(itm<0||itm>=quotationDetail.items.size())
            return;

        quotationDetail.items.get(itm).memo=memo;
    }

    @Override
    public void updateItemDiscount(int itm, float newDisCount) {
        AppQuotationAnalytics.discountItem(quotationDetail,new int[]{itm},newDisCount);
    }

    @Override
    public void updateCustomer(Customer customer) {


        AppQuotationAnalytics.updateQuotationCustomer(quotationDetail,customer);
    }

    @Override
    public void deleteQuotationItem(int item) {
        AppQuotationAnalytics.removeItem(quotationDetail,new int[]{item});
    }

    @Override
    public void updateValidateTime(String dateString) {
        quotationDetail.quotation.vDate=dateString;

    }

    @Override
    public void updateCreateTime(String dateString) {
        quotationDetail.quotation.qDate=dateString;
    }

    @Override
    public void updateQuotationNumber(String newValue) {
        quotationDetail.quotation.qNumber=newValue;
    }

    @Override
    public void updateQuotationMemo(String newValue) {
        quotationDetail.quotation.memo=newValue;
    }

    @Override
    public void addNewProduct(Product product) {


        AppQuotationAnalytics.addItem(quotationDetail,-1,product);
    }

    @Override
    public boolean hasModify() {

        return !GsonUtils.toJson(quotationDetail).equals(olddData);

    }
}
