package com.giants3.hd.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.giants3.hd.android.activity.MainActivity;
import com.giants3.hd.android.activity.ProductDetailActivity;
import com.giants3.hd.android.adapter.AbstractAdapter;
import com.giants3.hd.android.adapter.ProductListAdapter;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.utils.GsonUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductListFragment extends ListFragment<AProduct> {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and parse types of parameters
    private String mParam1;
    private String mParam2;




    public ProductListFragment() {
        // Required empty public constructor
        setFragmentListener(new OnProductFragmentInteractionListener() {
            @Override
            public void onFragmentInteraction(AProduct data) {
                //调整act
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra(ProductDetailFragment.ARG_ITEM, GsonUtils.toJson(data));
                startActivity(intent);
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductListFragment.
     */
    // TODO: Rename and parse types and number of parameters
    public static ProductListFragment newInstance(String param1, String param2) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        adapter =new ProductListAdapter(getActivity());
    }

    @Override
    protected AbstractAdapter<AProduct> getAdapter() {
        return adapter;
    }

    @Override
    protected UseCase getUserCase(String key, int pageIndex, int pageSize) {
        return  UseCaseFactory.getInstance().createProductListCase(key,pageIndex,pageSize);
    }








    public  interface OnProductFragmentInteractionListener  extends OnFragmentInteractionListener<AProduct>
    {

    }




}
