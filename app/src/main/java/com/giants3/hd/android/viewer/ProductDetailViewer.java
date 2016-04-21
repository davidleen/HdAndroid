package com.giants3.hd.android.viewer;

import com.giants3.hd.utils.entity.ProductDetail;

/**
 * Created by david on 2016/4/12.
 */
public interface ProductDetailViewer extends BaseViewer {


      void bindData(ProductDetail productDetail);
}
