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
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.GlobalData;
import com.giants3.hd.entity.Material;
import com.giants3.hd.logic.ProductAnalytics;
import com.giants3.hd.noEntity.ProductDetail;
import com.giants3.hd.entity.ProductPaint;
import com.giants3.hd.entity.ProductProcess;
import com.giants3.hd.exception.HdException;

import butterknife.Bind;
import butterknife.OnClick;

//import com.giants3.hd.utils.entity.ProductMaterial;


/** 产品油漆材料编辑
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductPaintFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductPaintFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductPaintFragment extends BaseFragment implements View.OnClickListener {


    private static final int REQUEST_MATERIAL_SELECT = 11;
    public static final String EXTRA_PRODUCT_PAINT = "EXTRA_PRODUCT_PAINT";
   public static String PRODUCT_PAINT_POSITION = "PRODUCT_PAINT_POSITION";


    ProductPaint productPaint;
    ProductPaint oldData;

    @Bind(R.id.materialCode)
    TextView materialCode;
    @Bind(R.id.materialName)
    TextView materialName;



    @Bind(R.id.processCode)
    TextView processCode;
    @Bind(R.id.processName)
    TextView processName;
    @Bind(R.id.processPrice)
    TextView processPrice;
    @Bind(R.id.unitName)
    TextView unitName;


    @Bind(R.id.ingredientRatio)
    TextView ingredientRatio;
    @Bind(R.id.quantity)
    TextView quantity;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.cost)
    TextView cost;
    @Bind(R.id.materialQuantity)
    TextView materialQuantity;

    @Bind(R.id.ingredientQuantity)
    TextView ingredientQuantity;

    @Bind(R.id.memo)
    TextView memo;


      @Bind(R.id.save)
      View save;



    private OnFragmentInteractionListener mListener;


    private int position = -1;
    private int materialType = -1;

    public ProductPaintFragment() {
        // Required empty public constructor
    }






    public static ProductPaintFragment newInstance(Bundle args) {
        ProductPaintFragment fragment = new ProductPaintFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


            materialType=getArguments().getInt(ProductMaterialFragment.PRODUCT_MATERIAL_TYPE, ProductDetailSingleton.PRODUCT_MATERIAL_PAINT);
            position=getArguments().getInt(PRODUCT_PAINT_POSITION);

            productPaint=   ProductDetailSingleton.getInstance().getProductPaint(position);

            try {
                oldData =GsonUtils.fromJson(getArguments().getString(EXTRA_PRODUCT_PAINT),ProductPaint.class);

        } catch (HdException e) {
            e.printStackTrace();
        }




        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_paint, container, false);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {

            case REQUEST_MATERIAL_SELECT:

                try {
                    Material material = GsonUtils.fromJson(data.getExtras().getString(MaterialSelectActivity.EXTRA_MATERIAL), Material.class);

                    GlobalData globalData = SharedPreferencesHelper.getInitData().globalData;
                    ProductAnalytics.setMaterialToProductPaint(productPaint,material, globalData);
                    ProductDetail productDetail = ProductDetailSingleton.getInstance().getProductDetail();
                    ProductAnalytics.updateProductInfoOnly(globalData, productDetail.product);
                    bindData(productPaint);


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





        bindData(productPaint);

    }



    @OnClick(R.id.save)
    public void onSave(View v)
    {


        ProductDetail productDetail1 = ProductDetailSingleton.getInstance().getProductDetail();
        ProductDetail productDetail= productDetail1;
        if(productPaint.getId()<=0)//新增数据
        {
                switch (materialType)
                {
                    case ProductDetailSingleton.PRODUCT_MATERIAL_PAINT:

                        productDetail.paints.add(position,productPaint);

                        break;


                }

        }else
        {

            switch (materialType)
            {
                case ProductDetailSingleton.PRODUCT_MATERIAL_PAINT:


                    productDetail.paints.set(position,productPaint);

                    break;
            }


        }


        GlobalData globalData = SharedPreferencesHelper.getInitData().globalData;
        ProductAnalytics.updateProductStatistics(productDetail1, globalData);
        ProductAnalytics.updateProductInfoOnly(globalData,productDetail.product);
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();

    }
    private void initView() {


    }


    @OnClick({R.id.selectMaterial, R.id.selectMaterial2})
    public void onMaterialSelect(View v) {

        Intent intent = new Intent(getActivity(), MaterialSelectActivity.class);

        if(v.getId()==R.id.selectMaterial)
        {
            intent.putExtra(MaterialSelectFragment.ARG_PARAM_CODE, productPaint.materialCode);
        }else {

            intent.putExtra(MaterialSelectFragment.ARG_PARAM_NAME, productPaint.materialName);
        }



        startActivityForResult(intent, REQUEST_MATERIAL_SELECT);
    }


    private void bindData(ProductPaint productPaint) {


        materialCode.setText(productPaint.materialCode);
        materialName.setText(productPaint.materialName);
        quantity.setText(String.valueOf(productPaint.quantity));


        processCode.setText(productPaint.processCode);
        processName.setText(productPaint.processName);
        processPrice.setText(String.valueOf(productPaint.processPrice));



          unitName.setText(productPaint.unitName);

        ingredientRatio.setText(String.valueOf(productPaint.ingredientRatio));


        quantity.setText(String.valueOf(productPaint.quantity));

        price.setText(String.valueOf(productPaint.price));

        cost.setText(String.valueOf(productPaint.cost));

        materialQuantity.setText(String.valueOf(productPaint.cost));

        ingredientQuantity.setText(String.valueOf(productPaint.ingredientQuantity));





        memo.setText(productPaint.memo);


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

    @OnClick(R.id.processCode)
    public void onProcessCodeClick(View view) {


        ValueEditDialogFragment dialogFragment=new ValueEditDialogFragment();
        dialogFragment.set("工序代码", String.valueOf(productPaint.processCode), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {

                    productPaint.setProcessCode(newValue);
                    bindData(productPaint);
                }catch (Throwable t )
                {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(),null);


    }

    @OnClick(R.id.processName)
    public void onProcessNameClick(View view) {


        ValueEditDialogFragment dialogFragment=new ValueEditDialogFragment();
        dialogFragment.set("工序名称",  productPaint.processName, new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {

                    productPaint.setProcessName(newValue);


                    //指定的工序特殊处理;
                    if(productPaint.processName!=null&&productPaint.processName.contains(ProductProcess.XISHUA) )
                    {
                        updateQuantityOfIngredient();

                    }
                    bindData(productPaint);
                }catch (Throwable t )
                {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(),null);


    }
    @OnClick(R.id.processPrice)
    public void onProcessPriceClick(View view) {


        ValueEditDialogFragment dialogFragment=new ValueEditDialogFragment();
        dialogFragment.set("工价", String.valueOf(productPaint.processPrice), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    float newValueFloat = Float.valueOf(newValue);
                    productPaint.setProcessPrice(newValueFloat);
                    bindData(productPaint);
                }catch (Throwable t )
                {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(),null);


    }

    @OnClick(R.id.quantity)
    public void onuantityClick(View view) {


        ValueEditDialogFragment dialogFragment=new ValueEditDialogFragment();
        dialogFragment.set("用量", String.valueOf(productPaint.quantity), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    float newValueFloat = Float.valueOf(newValue);
                    productPaint.quantity=newValueFloat;                    ;

                    ProductAnalytics.updateProductPaintPriceAndCostAndQuantity(productPaint,SharedPreferencesHelper.getInitData().globalData);
                    updateQuantityOfIngredient();
                    bindData(productPaint);
                }catch (Throwable t )
                {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(),null);


    }

    @OnClick(R.id.ingredientRatio)
    public void onIngredientRatioClick(View view) {


        ValueEditDialogFragment dialogFragment=new ValueEditDialogFragment();
        dialogFragment.set("配料比例", String.valueOf(productPaint.ingredientRatio), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    float newValueFloat = Float.valueOf(newValue);
                    productPaint.ingredientRatio=newValueFloat;                    ;
                      ProductAnalytics.updateProductPaintPriceAndCostAndQuantity(productPaint,SharedPreferencesHelper.getInitData().globalData);

                    updateQuantityOfIngredient();
                    bindData(productPaint);
                }catch (Throwable t )
                {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(),null);


    }
    @OnClick(R.id.memo)
    public void onMemoClick(View view) {


        ValueEditDialogFragment dialogFragment=new ValueEditDialogFragment();
        dialogFragment.set("备注", String.valueOf(productPaint.memo), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    productPaint.setMemo(newValue);
                    bindData(productPaint);
                }catch (Throwable t )
                {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(),null);


    }




    /**
     * 更新
     */
    public void updateQuantityOfIngredient()
    {

        ProductDetail productDetail=  ProductDetailSingleton.getInstance().getProductDetail();
        GlobalData globalData = SharedPreferencesHelper.getInitData().globalData;
        ProductAnalytics.updateQuantityOfIngredient(productDetail.paints, globalData);
        ProductAnalytics.updateProductStatistics(productDetail, globalData);
        ProductAnalytics.updateProductInfoOnly(globalData,productDetail.product);

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
