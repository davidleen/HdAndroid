package com.giants3.hd.entity;

import com.giants3.hd.utils.interf.Valuable;

import java.io.Serializable;

/**
 * 报价权限
 */

public class QuoteAuth  implements Serializable,Valuable{

    public long id;





    public User user;
    /**
     * 是否可以修改FOB单价
     */
    public boolean fobVisible;
    /**
     * 是否可以查看FOB单价
     */
    public boolean fobEditable;
    /**
     * 是否可以查看成本单价
     */
    public boolean costVisible;


    /**
     * 是否只能查看自己
     */
    public  boolean limitSelf;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuoteAuth)) return false;

        QuoteAuth quoteAuth = (QuoteAuth) o;

        if (id != quoteAuth.id) return false;
        if (fobVisible != quoteAuth.fobVisible) return false;
        if (fobEditable != quoteAuth.fobEditable) return false;
        if (costVisible != quoteAuth.costVisible) return false;
        if (limitSelf != quoteAuth.limitSelf) return false;
        return !(user != null ? !user.equals(quoteAuth.user) : quoteAuth.user != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (fobVisible ? 1 : 0);
        result = 31 * result + (fobEditable ? 1 : 0);
        result = 31 * result + (costVisible ? 1 : 0);
        result = 31 * result + (limitSelf ? 1 : 0);
        return result;
    }

    @Override
    public boolean isEmpty() {
        return user==null;
    }
}
