package com.giants3.hd.android.mvp.appquotationdetail;

import com.giants3.hd.android.mvp.AppQuotationMVP;
import com.giants3.hd.android.mvp.PageModelImpl;
import com.giants3.hd.entity.app.Quotation;
import com.giants3.hd.noEntity.app.QuotationDetail;

import java.util.List;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl extends PageModelImpl<QuotationDetail> implements AppQuotationDetailMVP.Model {


    private QuotationDetail quotationDetail;

    @Override
    public void setQuotationDetail(QuotationDetail quotationDetail) {

        this.quotationDetail = quotationDetail;
    }

    @Override
    public QuotationDetail  getQuotationDetail() {
        return quotationDetail;
    }
}
