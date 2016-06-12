package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giants3.hd.android.R;
import com.giants3.hd.android.ViewImpl.ProductDetailViewerImpl;
import com.giants3.hd.android.activity.ProductDetailActivity;
import com.giants3.hd.android.activity.ProductListActivity;
import com.giants3.hd.android.activity.ProductMaterialActivity;
import com.giants3.hd.android.entity.ProductDetailSingleton;
import com.giants3.hd.android.events.LoginSuccessEvent;
import com.giants3.hd.android.events.MaterialUpdateEvent;
import com.giants3.hd.android.events.ProductUpdateEvent;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.presenter.ProductDetailPresenter;
import com.giants3.hd.android.viewer.BaseViewer;
import com.giants3.hd.android.viewer.ProductDetailViewer;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.entity.ProductDetail;
import com.giants3.hd.utils.entity.RemoteData;

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
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM = "item";
    public static final String EXTRA_EDITABLE = "EXTRA_EDITABLE";


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        return rootView;
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
    public void onItemAdd() {


            switch (panelIndex)
            {
                case 0://白胚
                {

                    if(subIndex==0)
                    {
                            startProductMaterial();
                    }else
                    {

                    }

                }
                    break;
                case 1: //组装
                    break;
                case 2://油漆
                    break;
                case 3 ://包装
                    break;


            }




    }


    /**
     * 启动产品材料界面
     */
    private void startProductMaterial() {


        Intent intent=new Intent(getActivity(), ProductMaterialActivity.class);
        startActivity(intent);

    }

    @Override
    public void onItemModify(Object object,int position) {

        Intent intent=new Intent(getActivity(),ProductMaterialActivity.class);

        switch (panelIndex)
        {
            case 0://白胚



                intent.putExtra(ProductMaterialFragment.PRODUCT_MATERIAL_POSITION,position);
                intent.putExtra(ProductMaterialFragment.PRODUCT_MATERIAL_TYPE,ProductMaterialFragment.PRODUCT_MATERIAL_CONCEPTUS);


                break;
            case 1: //组装
                break;
            case 2://油漆
                break;
            case 3 ://包装
                break;


        }
        startActivity(intent);

    }

    @Override
    public void onItemDelete(Object object,int position) {


        switch (panelIndex)
        {
            case 0://白胚
                break;
            case 1: //组装
                break;
            case 2://油漆
                break;
            case 3 ://包装
                break;


        }


    }

    @Override
    public void saveProductDetail() {



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
        }
    }
}
