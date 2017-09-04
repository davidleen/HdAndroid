package com.giants3.hd.entity;

import com.giants3.hd.utils.DateFormats;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 报价数据
 */

public class Quotation  implements Serializable {

    public static final int QUOTATION_TYPE_NORMAL=1;

    public static final int QUOTATION_TYPE_XK=2;


    public long id;


    /**
     * 顾客id
     */

    public long customerId  ;


    /**
     * 顾客名称
     */

    public String customerCode ="" ;


    /**
     * 顾客名称
     */

    public String customerName ="" ;


    /**
     * 公司名称，当且仅当客户是临时客户时 手动录入  即customerCode=‘000’
     */

    public String companyName="";
    /**
     * 报价日期
     */

    public String qDate="";

    /**
     * 报价单号
     */

    public String qNumber="";


    /**
     * 有效日期
     */

    public String vDate="";


    /**
     * 业务员id
     */

    public long  salesmanId;

    /**
     * 业务员
     */

    public String  salesman="";


    /**
     *  币别
     */

    public  String  currency="";


    /**
     * 备注
     */


    public  String  memo="";


    /**
     * 报价类别  普通 咸康
     */

    public long quotationTypeId;

    /**
     * 报价类别  普通 咸康
     */

    public String quotationTypeName;

    /**
     * 是否已经审核
     */

    public boolean isVerified;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quotation)) return false;

        Quotation quotation = (Quotation) o;

        if (id != quotation.id) return false;
        if (customerId != quotation.customerId) return false;
        if (salesmanId != quotation.salesmanId) return false;
        if (quotationTypeId != quotation.quotationTypeId) return false;
        if (isVerified != quotation.isVerified) return false;
        if (customerCode != null ? !customerCode.equals(quotation.customerCode) : quotation.customerCode != null)
            return false;
        if (customerName != null ? !customerName.equals(quotation.customerName) : quotation.customerName != null)
            return false;
        if (companyName != null ? !companyName.equals(quotation.companyName) : quotation.companyName != null)
            return false;
        if (qDate != null ? !qDate.equals(quotation.qDate) : quotation.qDate != null) return false;
        if (qNumber != null ? !qNumber.equals(quotation.qNumber) : quotation.qNumber != null) return false;
        if (vDate != null ? !vDate.equals(quotation.vDate) : quotation.vDate != null) return false;
        if (salesman != null ? !salesman.equals(quotation.salesman) : quotation.salesman != null) return false;
        if (currency != null ? !currency.equals(quotation.currency) : quotation.currency != null) return false;
        if (memo != null ? !memo.equals(quotation.memo) : quotation.memo != null) return false;
        return !(quotationTypeName != null ? !quotationTypeName.equals(quotation.quotationTypeName) : quotation.quotationTypeName != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (customerCode != null ? customerCode.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (qDate != null ? qDate.hashCode() : 0);
        result = 31 * result + (qNumber != null ? qNumber.hashCode() : 0);
        result = 31 * result + (vDate != null ? vDate.hashCode() : 0);
        result = 31 * result + (int) (salesmanId ^ (salesmanId >>> 32));
        result = 31 * result + (salesman != null ? salesman.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (int) (quotationTypeId ^ (quotationTypeId >>> 32));
        result = 31 * result + (quotationTypeName != null ? quotationTypeName.hashCode() : 0);
        result = 31 * result + (isVerified ? 1 : 0);
        return result;
    }


    /**
     * 判断是否过期
     * @return
     */
    public  boolean isOverdue()
    {

        if(id<=1) return false;


      String nowDate=  DateFormats.FORMAT_YYYY_MM_DD.format(Calendar.getInstance().getTime());

        return nowDate.compareTo(vDate)>=1;



    }
}
