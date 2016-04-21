package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.ViewImpl.ProductDetailViewerImpl;
import com.giants3.hd.android.activity.ProductDetailActivity;
import com.giants3.hd.android.activity.ProductListActivity;
import com.giants3.hd.android.R;
import com.giants3.hd.android.entity.ProductDetailSingleton;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.viewer.BaseViewer;
import com.giants3.hd.android.viewer.ProductDetailViewer;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.utils.entity.ProductDetail;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import rx.Subscriber;


/**
 * A fragment representing a single ProductListActivity detail screen.
 * This fragment is either contained in a {@link ProductListActivity}
 * in two-pane mode (on tablets) or a {@link ProductDetailActivity}
 * on handsets.
 */
public class ProductDetailFragment extends BaseFragment {
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
                aProduct=GsonUtils.fromJson(getArguments().getString(ARG_ITEM),AProduct.class);
            } catch (HdException e) {
                e.printStackTrace();
                ToastHelper.show("参数异常");
                getActivity().finish();
            }
            productDetailSingleton=ProductDetailSingleton.getInstance();

        }

        viewer=new ProductDetailViewerImpl(activity);
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

    private void loadProductDetail(long productId)
    {
        viewer.showWaiting();
        UseCaseFactory.getInstance().createGetProductDetailCase(productId ).execute(new Subscriber<RemoteData<ProductDetail>>() {
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
                if(remoteData.isSuccess()) {



                    productDetailSingleton.setProductDetail(remoteData.datas.get(0));
                    viewer.bindData(productDetailSingleton.getProductDetail());



                }else
                {
                    ToastHelper.show(remoteData.message);
                    if(remoteData.code==RemoteData.CODE_UNLOGIN)
                    {
                        startLoginActivity();
                    }
                }
            }
        });

    }

}
