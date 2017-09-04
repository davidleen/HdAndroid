package com.giants3.hd.android.mvp.myavailableorderitem;

import com.giants3.hd.android.mvp.MyAvailableOrderItemMVP;
import com.giants3.hd.entity.ErpOrderItem;

import java.util.List;

/**
 * Created by davidleen29 on 2016/10/10.
 */

public class ModelImpl implements MyAvailableOrderItemMVP.Model {
    private String text;
    private int pageIndex;
    private int pageSize;
    private List<ErpOrderItem> datas;
    private int pageCount;


    @Override
    public void setErpOrderItems(List<ErpOrderItem> datas, String text, int pageIndex, int pageSize,int pageCount) {
        this.pageCount = pageCount;

        if (pageIndex == 0) {

            this.datas = datas;
        } else {
            this.datas.addAll(datas);
        }
        this.text = text;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    @Override
    public List<ErpOrderItem> getErpOrderItems() {
        return datas;
    }

    @Override
    public String getKey() {

        return text
                ;
    }

    @Override
    public int getPageIndex() {
        return pageIndex;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public boolean canSearchMore() {
        return datas!=null&&datas.size()>0&&pageIndex<pageCount-1;
    }
}
