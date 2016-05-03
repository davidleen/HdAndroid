package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giants3.hd.android.R;
import com.giants3.hd.android.ViewImpl.ProductDetailViewerImpl;
import com.giants3.hd.android.activity.ProductDetailActivity;
import com.giants3.hd.android.activity.ProductListActivity;
import com.giants3.hd.android.entity.ProductDetailSingleton;
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


    AProduct aProduct;

    private ProductDetailSingleton productDetailSingleton;


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

        viewer = new ProductDetailViewerImpl(activity);
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


        loadProductDetail(aProduct.id);
    }


    @Override
    protected BaseViewer getViewer() {
        return viewer;
    }

    private void loadProductDetail(long productId) {
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


                    productDetailSingleton.setProductDetail(remoteData.datas.get(0));
                    viewer.bindData(productDetailSingleton.getProductDetail());
                    viewer.showConceptusMaterial(productDetailSingleton.getProductDetail());


                } else {
                    ToastHelper.show(remoteData.message);
                    if (remoteData.code == RemoteData.CODE_UNLOGIN) {
                        startLoginActivity();
                    }
                }
            }
        });

    }

    //当前材料清单显示标记
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

    private void showBaseOnIndex() {


        switch (panelIndex) {

            case 0:
                if (subIndex == 0) {
                    viewer.showConceptusMaterial(productDetailSingleton.getProductDetail());
                } else {
                    viewer.showConceptusWage(productDetailSingleton.getProductDetail());
                }

                break;

            case 1:
                ;
                if (subIndex == 0) {
                    viewer.showAssembleMaterial(productDetailSingleton.getProductDetail());
                } else {
                    viewer.showAssembleWage(productDetailSingleton.getProductDetail());
                }
                break;
            case 2:
                ;
                viewer.showPaintMaterialWage(productDetailSingleton.getProductDetail());
                break;
            case 3:
                if (subIndex == 0) {
                    viewer.showPackMaterial(productDetailSingleton.getProductDetail());
                } else {
                    viewer.showPackWage(productDetailSingleton.getProductDetail());
                }
                break;

        }

    }
}
