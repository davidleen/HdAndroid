package com.giants3.hd.android.entity;

import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.entity.ProductDetail;

/**c产品信息 单例模式   整个应用只有一个产品处于 查看编辑状态
 * Created by david on 2016/4/13.
 */
public class ProductDetailSingleton{



    private ProductDetail originProductDetail;
    private ProductDetail productDetail;

    private boolean isEdit;



    public static ProductDetailSingleton singleton=new ProductDetailSingleton();

    public static ProductDetailSingleton getInstance()
    {
        return singleton;

    }


    public void setProductDetail(ProductDetail productDetail)
    {
        if(productDetail==null)
        {
            originProductDetail=null;
        }else {

            try {
                originProductDetail = GsonUtils.fromJson(GsonUtils.toJson(productDetail), ProductDetail.class);
            } catch (HdException e) {
                e.printStackTrace();
            }
        }
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

    public boolean hasModifyDetail() {

        if(originProductDetail==null) return false;

        return !originProductDetail.equals(productDetail);
    }
}
