package com.giants3.hd.android.viewer;

import com.giants3.hd.android.presenter.ProductDetailPresenter;
import com.giants3.hd.noEntity.ProductDetail;

/**
 * Created by david on 2016/4/12.
 */
public interface ProductDetailViewer extends BaseViewer {


      void bindData(ProductDetail productDetail);

      void setPresenter(ProductDetailPresenter productDetailPresenter);

      void showConceptusMaterial(ProductDetail productDetail);

      void showConceptusWage(ProductDetail productDetail);

      void showAssembleMaterial(ProductDetail productDetail);

      void showAssembleWage(ProductDetail productDetail);

      void showPaintMaterialWage(ProductDetail productDetail);

      void showPackMaterial(ProductDetail productDetail);

      void showPackWage(ProductDetail productDetail);
}
