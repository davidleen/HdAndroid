package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.activity.MaterialSelectActivity;
import com.giants3.hd.android.entity.ProductDetailSingleton;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.entity.Material;
import com.giants3.hd.utils.entity.ProductDetail;
import com.giants3.hd.utils.entity.ProductMaterial;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductMaterialFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductMaterialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductMaterialFragment extends BaseFragment implements View.OnClickListener {


    private static final int REQUEST_MATERIAL_SELECT = 11;
    public static String PRODUCT_MATERIAL_TYPE = "PRODUCT_MATERIAL_TYPE";
    public static String PRODUCT_MATERIAL_POSITION = "PRODUCT_MATERIAL_POSITION";


    ProductMaterial productMaterial;

    @Bind(R.id.materialCode)
    TextView materialCode;
    @Bind(R.id.materialName)
    TextView materialName;
    @Bind(R.id.quantity)
    TextView quantity;
    @Bind(R.id.mLong)
    TextView mLong;
    @Bind(R.id.mWidth)
    TextView mWidth;
    @Bind(R.id.mHeight)
    TextView mHeight;
    @Bind(R.id.wLong)
    TextView wLong;
    @Bind(R.id.wWidth)
    TextView wWidth;
    @Bind(R.id.wHeight)
    TextView wHeight;
    @Bind(R.id.quota)
    TextView quota;
    @Bind(R.id.unit)
    TextView unit;
    @Bind(R.id.available)
    TextView available;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.amount)
    TextView amount;
    @Bind(R.id.memo)
    TextView memo;
    @Bind(R.id.selectMaterial)
    View selectMaterial;
    @Bind(R.id.selectMaterial2)
    View selectMaterial2;

    private OnFragmentInteractionListener mListener;


    private int position = -1;
    private int materialType = -1;

    public ProductMaterialFragment() {
        // Required empty public constructor
    }


    public static final int PRODUCT_MATERIAL_CONCEPTUS = 1;
    //public  static final int PRODUCT_WAGE_CONCEPTUS=2;


    public static ProductMaterialFragment newInstance(int productMaterialType, int position) {
        ProductMaterialFragment fragment = new ProductMaterialFragment();
        Bundle args = new Bundle();
        args.putInt(PRODUCT_MATERIAL_TYPE, productMaterialType);
        args.putInt(PRODUCT_MATERIAL_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductMaterialFragment newInstance(Bundle args) {
        ProductMaterialFragment fragment = new ProductMaterialFragment();


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            materialType = getArguments().getInt(PRODUCT_MATERIAL_TYPE, PRODUCT_MATERIAL_CONCEPTUS);
            position = getArguments().getInt(PRODUCT_MATERIAL_POSITION, -1);


        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_material, container, false);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if(resultCode!= Activity.RESULT_OK) return ;
        switch (requestCode)
        {

            case REQUEST_MATERIAL_SELECT:

                try {
                    Material material= GsonUtils.fromJson(data.getExtras().getString(MaterialSelectActivity.EXTRA_MATERIAL),Material.class);
                    productMaterial.updateMaterial(material);
                    bindData(productMaterial);



                } catch (HdException e) {
                    e.printStackTrace();
                }
                break;
        }




    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

        if (position == -1) {

            return;
        }
        ProductDetail productDetail = ProductDetailSingleton.getInstance().getProductDetail();


        ProductMaterial material = null;

        switch (materialType) {
            case PRODUCT_MATERIAL_CONCEPTUS:
                material = productDetail.conceptusMaterials.get(position);
                break;
        }


        if (material == null) {
            ToastHelper.show("数据异常");
            getActivity().finish();
            return;

        }

        productMaterial=material;
        bindData(material);

    }


    private void initView() {


    }


    @OnClick({R.id.selectMaterial, R.id.selectMaterial2})
    public void onMaterialSelect(View v) {

        Intent intent = new Intent(getActivity(), MaterialSelectActivity.class);
        startActivityForResult(intent,REQUEST_MATERIAL_SELECT);
    }

    private void bindData(ProductMaterial productMaterial) {


        materialCode.setText(productMaterial.materialCode);
        materialName.setText(productMaterial.materialName);
        quantity.setText(String.valueOf(productMaterial.quantity));
        mLong.setText(String.valueOf(productMaterial.pLong));
        mWidth.setText(String.valueOf(productMaterial.pWidth));
        mHeight.setText(String.valueOf(productMaterial.pHeight));
        wLong.setText(String.valueOf(productMaterial.wLong));
        wWidth.setText(String.valueOf(productMaterial.wWidth));
        wHeight.setText(String.valueOf(productMaterial.wHeight));
        quota.setText(String.valueOf(productMaterial.quota));
        available.setText(String.valueOf(productMaterial.available));
        type.setText(String.valueOf(productMaterial.type));
        price.setText(String.valueOf(productMaterial.price));
        amount.setText(String.valueOf(productMaterial.amount));
        memo.setText(productMaterial.memo);


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onDestroyView() {


        super.onDestroyView();


    }



}
