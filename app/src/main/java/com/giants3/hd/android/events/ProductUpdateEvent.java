package com.giants3.hd.android.events;

import com.giants3.hd.noEntity.ProductDetail;

/**  产品信息变动的提示
 * Created by davidleen29 on 2016/6/9.
 */
public class ProductUpdateEvent {

    public ProductDetail productDetail;

    public ProductUpdateEvent(ProductDetail productDetail) {
        this.productDetail=productDetail;
    }
}
