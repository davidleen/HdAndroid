package com.giants3.hd.noEntity;



import com.giants3.hd.entity.ErpOrder;
import com.giants3.hd.entity.ErpOrderItem;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 订单详情
 * Created by davidleen29 on 2016/7/18.
 */
public class ErpOrderDetail implements Serializable {

    public ErpOrder erpOrder;
    public List<ErpOrderItem> items;
}
