package com.giants3.hd.android.mvp.appquotationdetail;


import com.giants3.hd.android.mvp.NewPresenter;
import com.giants3.hd.android.mvp.NewViewer;
import com.giants3.hd.android.mvp.PageModel;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.entity.app.Quotation;
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
    }

    interface Presenter extends NewPresenter<AppQuotationDetailMVP.Viewer> {


        void setQuotationId(long quotationId);

        void pickNewProduct();

        void addNewProduct(long productId  );

        void deleteQuotationItem(int item);

        void updatePrice(int itm, float newFloatValue);

        void updateQty(int itm, int newQty);
    }

    interface Viewer extends NewViewer {


        void bindData(QuotationDetail data);
    }
}
