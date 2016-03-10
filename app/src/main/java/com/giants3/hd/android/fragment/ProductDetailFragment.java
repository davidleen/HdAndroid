package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.support.annotation.BinderThread;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giants3.hd.android.activity.ProductDetailActivity;
import com.giants3.hd.android.activity.ProductListActivity;
import com.giants3.hd.android.R;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.data.entity.ErpOrder;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;

import butterknife.Bind;


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


    @Bind(R.id.textView)
    TextView textView;

    AProduct aProduct;

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
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(aProduct.name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.productlistactivity_detail, container, false);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        textView.setText(aProduct.name);
    }
}
