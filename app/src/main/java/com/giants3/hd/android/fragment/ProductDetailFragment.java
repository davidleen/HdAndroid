package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.giants3.hd.android.ViewImpl.ProductDetailViewerImpl;
import com.giants3.hd.android.activity.ProductDetailActivity;
import com.giants3.hd.android.activity.ProductListActivity;
import com.giants3.hd.android.activity.ProductMaterialActivity;
import com.giants3.hd.android.activity.ProductPaintActivity;
import com.giants3.hd.android.activity.ProductWageActivity;
import com.giants3.hd.android.entity.ProductDetailSingleton;
import com.giants3.hd.android.events.LoginSuccessEvent;
import com.giants3.hd.android.events.ProductUpdateEvent;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.presenter.ProductDetailPresenter;
import com.giants3.hd.android.viewer.BaseViewer;
import com.giants3.hd.android.viewer.ProductDetailViewer;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.Pack;
import com.giants3.hd.logic.ProductAnalytics;
import com.giants3.hd.noEntity.ProductDetail;
import com.giants3.hd.entity.ProductWage;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.StringUtils;

import de.greenrobot.event.EventBus;
import rx.Subscriber;


/**
 * A fragment representing a single ProductListActivity detail screen.
 * This fragment is either contained in a {@link ProductListActivity}
 * in two-pane mode (on tablets) or a {@link ProductDetailActivity}
 * on handsets.
 */
public class ProductDetailFragment extends BaseFragment implements ProductDetailPresenter {
    /**
     * The fragment argument representing the item_work_flow_report ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM = "item_work_flow_report";
    public static final String EXTRA_EDITABLE = "EXTRA_EDITABLE";
    private static final int REQUEST_PRODUCT_MATERIAL = 113;
    private static final int REQUEST_PRODUCT_WAGE = 114;
    private static final int REQUEST_PRODUCT_PAINT = 115;


    private boolean  editable=false;

    AProduct aProduct;

    private ProductDetailSingleton productDetailSingleton;

    private ProductDetail productDetail;


    ProductDetailViewer viewer;

    public static ProductDetailFragment newInstance(AProduct aProduct) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEM, GsonUtils.toJson(aProduct));
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
        if (getArguments().containsKey(ARG_ITEM)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.


            try {
                aProduct = GsonUtils.fromJson(getArguments().getString(ARG_ITEM), AProduct.class);
            } catch (HdException e) {
                e.printStackTrace();
                ToastHelper.show("参数异常");
                getActivity().finish();
            }
            productDetailSingleton = ProductDetailSingleton.getInstance();

        }

        editable=getArguments().getBoolean(ProductDetailFragment.EXTRA_EDITABLE,false);

        viewer = new ProductDetailViewerImpl(activity,editable);
        viewer.setPresenter(this);

    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loadProductDetail(aProduct==null?0:aProduct.id);
    }


    @Override
    protected BaseViewer getViewer() {
        return viewer;
    }

    private void loadProductDetail(long productId) {


        if(editable||productId==0)
        {

            productDetail=productDetailSingleton.getProductDetail();
            viewer.bindData(productDetail);
            viewer.showConceptusMaterial(productDetail);

        }else {

            viewer.showWaiting();
            UseCaseFactory.getInstance().createGetProductDetailCase(productId).execute(new Subscriber<RemoteData<ProductDetail>>() {
                @Override
                public void onCompleted() {
                    viewer.hideWaiting();
                }

                @Override
                public void onError(Throwable e) {
                    viewer.hideWaiting();
                    ToastHelper.show(e.getMessage());
                }

                @Override
                public void onNext(RemoteData<ProductDetail> remoteData) {
                    if (remoteData.isSuccess()) {

                        productDetail = remoteData.datas.get(0);

                        viewer.bindData(productDetail);
                        viewer.showConceptusMaterial(productDetail);


                    } else {
                        ToastHelper.show(remoteData.message);
                        if (remoteData.code == RemoteData.CODE_UNLOGIN) {
                            startLoginActivity();
                        }
                    }
                }
            });

        }
    }

    //当前材料清单显示标记
    /**
     * panelIndex 白配 组装 油漆 包装
     * subIndex   0 材料  1  工资
     */
    public int panelIndex, subIndex;

    @Override
    public void onPanelForClick(int index) {

        //无改变 不响应
        if (panelIndex == index)
            return;
        panelIndex = index;
        showBaseOnIndex();

    }

    @Override
    public void onMaterialWageClick(int index) {
//无改变 不响应
        if (subIndex == index)
            return;
        subIndex = index;
        showBaseOnIndex();
    }




    @Override
    public void toEditProductDetail() {
        //调整act
        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra(ProductDetailFragment.EXTRA_EDITABLE,true);
        ProductDetailSingleton.getInstance().setProductDetail(productDetail);
        startActivity(intent);
    }

    @Override
    public void onItemAdd(int position) {



            switch (panelIndex)
            {


                case 0://白胚
                {

                    if(subIndex==0)
                    {

                        int newPosition= ProductDetailSingleton.getInstance().addNewProductMaterial(ProductDetailSingleton.PRODUCT_MATERIAL_CONCEPTUS);
                        startProductMaterialEdit(ProductDetailSingleton.PRODUCT_MATERIAL_CONCEPTUS,newPosition);

                    }else
                    {
                        startProductWageEdit(ProductDetailSingleton.PRODUCT_MATERIAL_CONCEPTUS,position,null);
                    }

                }
                    break;
                case 1: //组装

                    if(subIndex==0)
                    {
                        int newPosition= ProductDetailSingleton.getInstance().addNewProductMaterial(ProductDetailSingleton.PRODUCT_MATERIAL_ASSEMBLE);
                        startProductMaterialEdit(ProductDetailSingleton.PRODUCT_MATERIAL_ASSEMBLE,newPosition);

                    }else
                    {
                        startProductWageEdit(ProductDetailSingleton.PRODUCT_MATERIAL_ASSEMBLE,position,null);
                    }


                    break;
                case 2://油漆

                {

                    int newPosition = ProductDetailSingleton.getInstance().addNewProductMaterial(ProductDetailSingleton.PRODUCT_MATERIAL_PAINT);
                    startProductPaintEdit(newPosition);
                }

                    break;
                case 3 ://包装

                    if(subIndex==0)
                    {
                        int newPosition= ProductDetailSingleton.getInstance().addNewProductMaterial(ProductDetailSingleton.PRODUCT_MATERIAL_PACK);
                        startProductMaterialEdit(ProductDetailSingleton.PRODUCT_MATERIAL_PACK,newPosition);

                    }else
                    {
                        startProductWageEdit(ProductDetailSingleton.PRODUCT_MATERIAL_PACK,position,null);
                    }
                    break;


            }





    }




    @Override
    public void onItemModify(Object object,int position) {



        switch (panelIndex)
        {
            case 0://白胚


                if(subIndex==0)
                {
                    startProductMaterialEdit(ProductDetailSingleton.PRODUCT_MATERIAL_CONCEPTUS,position);

                }else
                {
                    startProductWageEdit(ProductDetailSingleton.PRODUCT_MATERIAL_CONCEPTUS,position,productDetail.conceptusWages.get(position));
                }




                break;
            case 1: //组装



                if(subIndex==0)
                {
                    startProductMaterialEdit(ProductDetailSingleton.PRODUCT_MATERIAL_ASSEMBLE,position);

                }else
                {
                    startProductWageEdit(ProductDetailSingleton.PRODUCT_MATERIAL_ASSEMBLE,position,productDetail.assembleWages.get(position));
                }
                break;
            case 2://油漆

                startProductPaintEdit(position );
                break;
            case 3 ://包装
                if(subIndex==0) {

                    startProductMaterialEdit(ProductDetailSingleton.PRODUCT_MATERIAL_PACK,position);
                }else{
                    startProductWageEdit(ProductDetailSingleton.PRODUCT_MATERIAL_PACK,position,productDetail.packWages.get(position));
                }
                break;


        }


    }

    /**
     * 启动产品材料编辑
     * @param type
     * @param position

     */
    private void startProductMaterialEdit(int type, int position )
    {   Intent intent=new Intent(getActivity(),ProductMaterialActivity.class);


        intent.putExtra(ProductMaterialFragment.PRODUCT_MATERIAL_POSITION, position);
        intent.putExtra(ProductMaterialFragment.PRODUCT_MATERIAL_TYPE, type);
        startActivityForResult(intent,REQUEST_PRODUCT_MATERIAL);
    }  /**
     * 启动产品油漆材料编辑
     * @param position

     */
    private void startProductPaintEdit(  int position )
    {   Intent intent=new Intent(getActivity(),ProductPaintActivity.class);

        intent.putExtra(ProductPaintFragment.PRODUCT_PAINT_POSITION, position);
        intent.putExtra(ProductMaterialFragment.PRODUCT_MATERIAL_TYPE, ProductDetailSingleton.PRODUCT_MATERIAL_PAINT);
        startActivityForResult(intent,REQUEST_PRODUCT_PAINT);
    }
    /* 启动产品工资编辑
     * @param type
     * @param position
     * @param productPaint
     */
    private void startProductWageEdit(int type, int position, ProductWage productWage)
    {   Intent intent=new Intent(getActivity(),ProductWageActivity.class);
        if(productWage!=null)
        intent.putExtra(ProductWageFragment.EXTRA_PRODUCT_WAGE, GsonUtils.toJson(productWage));
        intent.putExtra(ProductWageFragment.PRODUCT_WAGE_POSITION, position);
        intent.putExtra(ProductWageFragment.PRODUCT_MATERIAL_TYPE, type);
        startActivityForResult(intent,REQUEST_PRODUCT_WAGE);
    }
    @Override
    public void onItemDelete(Object object,int position) {


        switch (panelIndex)
        {
            case 0://白胚
                if(subIndex==0)
                {
                    productDetail.conceptusMaterials.remove(position);

                }else
                {
                    //工资
                    productDetail.conceptusWages.remove(position);

                }



                break;
            case 1: //组装
                if(subIndex==0)
                {
                    productDetail.assembleMaterials.remove(position);




                }else
                {
                    //工资
                    productDetail.assembleWages.remove(position);


                }

                break;
            case 2://油漆

                //材料

                productDetail.paints.remove(position);

                break;
            case 3 ://包装
                if(subIndex==0)
                {
                    productDetail.packMaterials.remove(position);

                }else
                {
                    //工资
                    productDetail.packWages.remove(position);


                }


                break;


        }

        ProductAnalytics.updateProductStatistics(productDetail,SharedPreferencesHelper.getInitData().globalData);

        bindData();


    }

    @Override
    public void saveProductDetail() {


        if(!productDetailSingleton.hasModifyDetail())
        {

            ToastHelper.show("数据无改变");
            getActivity().finish();
            return;
        }

        showProgress(true);
        //保存产品详情信息
        UseCaseFactory.getInstance().saveProductDetailCase(productDetail).execute(new Subscriber<RemoteData<ProductDetail>>() {
            @Override
            public void onCompleted() {
                showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                ToastHelper.show(e.getMessage());
                showProgress(false);
            }

            @Override
            public void onNext(RemoteData<ProductDetail> remoteData) {
                if (remoteData.isSuccess()) {

                    ToastHelper.show("保存成功");
                     EventBus.getDefault().post(new ProductUpdateEvent(remoteData.datas.get(0)));
                    getActivity().finish();

                } else {
                    ToastHelper.show(remoteData.message);

                }
            }
        });

    }

    @Override
    public void onUnitClick() {

        if(!editable) return;
        if(productDetail==null)return;
        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("单位", String.valueOf(productDetail.product.pUnitName), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {

                    productDetail.product.setpUnitName(newValue) ;
                      viewer.bindData(productDetail);
                } catch (Throwable t) {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }


    @Override
    public void onPVersionEdit() {

        if(!editable) return;
        if(productDetail==null)return;
        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("配方号", String.valueOf(productDetail.product.pVersion), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {

                    productDetail.product.setpVersion(newValue); ;
                    viewer.bindData(productDetail);
                } catch (Throwable t) {
                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void onProductNameEdit() {
        if(!editable) return;
        if(productDetail==null)return;
        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("产品名称", String.valueOf(productDetail.product.name), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {

                    productDetail.product.setName(newValue);
                    viewer.bindData(productDetail);
                } catch (Throwable t) {
                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void onWeightEdit() {
        if(!editable) return;
        if(productDetail==null)return;
        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("净重KG", String.valueOf(productDetail.product.weight), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {

                    float  newWeight=Float.valueOf(newValue.trim());
                    productDetail.product.setWeight(newWeight);
                    viewer.bindData(productDetail);
                } catch (Throwable t) {
                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void onSpecCmEdit() {



        if(!editable) return;
        if(productDetail==null)return;
        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("产品规格cm", String.valueOf(productDetail.product.specCm), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {


                    String spec= StringUtils.convertCmStringToInchString(newValue);
                    productDetail.product.setSpecCm(newValue);
                    productDetail.product.setSpec(spec);
                    viewer.bindData(productDetail);
                } catch (Throwable t) {
                    t.printStackTrace();
                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void onPackQuantityEdit() {

        if(!editable) return;
        if(productDetail==null)return;
        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("包装装箱数", String.valueOf(productDetail.product.packQuantity ), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {

                    int  newV=Integer.valueOf(newValue.trim());
                    productDetail.product.setPackQuantity(newV);
                    viewer.bindData(productDetail);
                } catch (Throwable t) {
                    t.printStackTrace();
                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }

    @Override
    public void onPackEdit() {
        if(!editable) return;
        if(productDetail==null)return;
        ItemPickDialogFragment<Pack> dialogFragment = new ItemPickDialogFragment<Pack>();
        dialogFragment.set("包装类型选择",SharedPreferencesHelper.getInitData().packs,  productDetail.product.pack  , new ItemPickDialogFragment.ValueChangeListener<Pack>() {
            @Override
            public void onValueChange(String title, Pack oldValue, Pack newValue) {


                productDetail.product.pack=newValue;
                viewer.bindData(productDetail);
            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }


    public void onEvent(LoginSuccessEvent event) {

        onLoginRefresh();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode!=Activity.RESULT_OK) return;
        switch (requestCode)
        {
            case REQUEST_PRODUCT_MATERIAL:



                bindData();
                break;

            case REQUEST_PRODUCT_WAGE:

                bindData();
                break;
            case REQUEST_PRODUCT_PAINT
                    :

                bindData();
                break;
        }
    }


    private  void bindData()
    {

        switch (panelIndex)
        {
            case 0://白胚
                if(subIndex==0)
                {

                    viewer.showConceptusMaterial(productDetail);
                }else
                {

                    viewer.showConceptusWage(productDetail);
                }
                viewer.bindData(productDetail);


                break;
            case 1: //组装
                if(subIndex==0)
                {
                    viewer.showAssembleMaterial(productDetail);
                }else
                {

                    viewer.showAssembleWage(productDetail);

                }
                viewer.bindData(productDetail);
                break;
            case 2://油漆

                //材料


                viewer.showPaintMaterialWage(productDetail);
                // productDetail.updateProductStatistics();
                viewer.bindData(productDetail);
                break;
            case 3 ://包装
                if(subIndex==0)
                {

                    viewer.showPackMaterial(productDetail);
                }else
                {

                    viewer.showPackWage(productDetail);

                }

                viewer.bindData(productDetail);
                break;


        }

    }

    private void showBaseOnIndex() {


        switch (panelIndex) {

            case 0:
                if (subIndex == 0) {
                    viewer.showConceptusMaterial(productDetail);
                } else {
                    viewer.showConceptusWage(productDetail);
                }

                break;

            case 1:
                ;
                if (subIndex == 0) {
                    viewer.showAssembleMaterial(productDetail);
                } else {
                    viewer.showAssembleWage(productDetail);
                }
                break;
            case 2:
                ;
                viewer.showPaintMaterialWage(productDetail);
                break;
            case 3:
                if (subIndex == 0) {
                    viewer.showPackMaterial(productDetail);
                } else {
                    viewer.showPackWage(productDetail);
                }
                break;

        }

    }


    public void onEvent(ProductUpdateEvent event) {



        productDetail=event.productDetail;
        if(viewer!=null)
        {
            viewer.bindData(productDetail);
            bindData();
        }
        ProductDetailSingleton.getInstance().setProductDetail(null);
    }
}
