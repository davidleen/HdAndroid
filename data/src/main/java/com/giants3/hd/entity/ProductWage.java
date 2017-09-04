package com.giants3.hd.entity;

import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.interf.Valuable;

import java.io.Serializable;

/**
 * 产品工资信息
 */

public class ProductWage  implements Serializable,Valuable {

    public long id;


    public long productId;

    public int itemIndex;


    public long flowId;


    public String flowName;





    public long processId;


    public String processCode;

    public String processName;


    public float  price;



   public  float amount;



    public String memo ;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getFlowId() {
        return flowId;
    }

    public void setFlowId(long flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public long getProcessId() {
        return processId;
    }

    public void setProcessId(long processId) {
        this.processId = processId;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    /**
     * 替换工序
     * @param productProcess
     */
    public void setProductProcess(ProductProcess productProcess)
    {
        processId=productProcess.id;
        processCode=productProcess.code;
        processName=productProcess.name;
        memo=productProcess.memo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductWage)) return false;

        ProductWage that = (ProductWage) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (flowId != that.flowId) return false;
        if (processId != that.processId) return false;
        if (Float.compare(that.price, price) != 0) return false;
        if (Float.compare(that.amount, amount) != 0) return false;
        if (Float.compare(that.itemIndex, itemIndex) != 0) return false;
        if (flowName != null ? !flowName.equals(that.flowName) : that.flowName != null) return false;
        if (processCode != null ? !processCode.equals(that.processCode) : that.processCode != null) return false;
        if (processName != null ? !processName.equals(that.processName) : that.processName != null) return false;
        return !(memo != null ? !memo.equals(that.memo) : that.memo != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (productId ^ (productId >>> 32));
        result = 31 * result + (int) (flowId ^ (flowId >>> 32));
        result = 31 * result + (flowName != null ? flowName.hashCode() : 0);
        result = 31 * result + (int) (processId ^ (processId >>> 32));
        result = 31 * result + (processCode != null ? processCode.hashCode() : 0);
        result = 31 * result + (processName != null ? processName.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result +itemIndex;
        result = 31 * result + (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @Override
    public boolean isEmpty() {

        //工序相关信息都未填 即为空。
       return processId<=0&& StringUtils.isEmpty(processCode)&&StringUtils.isEmpty(processName) ;

    }
}
