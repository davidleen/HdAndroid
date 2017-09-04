package com.giants3.hd.noEntity;



import com.giants3.hd.entity.Quotation;
import com.giants3.hd.entity.QuotationItem;
import com.giants3.hd.entity.QuotationXKItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 报价详细数据
 */
public class QuotationDetail implements Serializable {

        public Quotation quotation;




       public List<QuotationItem> items;
    public  List<QuotationXKItem> XKItems ;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuotationDetail)) return false;

        QuotationDetail that = (QuotationDetail) o;

        if (quotation != null ? !quotation.equals(that.quotation) : that.quotation != null) return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        return !(XKItems != null ? !XKItems.equals(that.XKItems) : that.XKItems != null);

    }

    @Override
    public int hashCode() {
        int result = quotation != null ? quotation.hashCode() : 0;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        result = 31 * result + (XKItems != null ? XKItems.hashCode() : 0);
        return result;
    }

    public void init() {
        quotation=new Quotation();
        items=new ArrayList<>();
        XKItems=new ArrayList<>();
    }
}
