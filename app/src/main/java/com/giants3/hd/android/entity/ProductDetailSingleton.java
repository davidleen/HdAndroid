package com.giants3.hd.android.entity;

import com.giants3.hd.utils.entity.ProductDetail;

/**c产品信息 单例模式   整个应用只有一个产品处于 查看编辑状态
 * Created by david on 2016/4/13.
 */
public class ProductDetailSingleton{


    private ProductDetail productDetail;

    private boolean isEdit;



    public static ProductDetailSingleton singleton=new ProductDetailSingleton();

    public static ProductDetailSingleton getInstance()
    {
        return singleton;

    }


    public void setProductDetail(ProductDetail productDetail)
    {
        this.productDetail=productDetail;

    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }


    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public boolean isEdit() {
        return isEdit;
    }
}
