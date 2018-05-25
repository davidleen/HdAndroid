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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.activity.MaterialSelectActivity;
import com.giants3.hd.android.entity.ProductDetailSingleton;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.Flow;
import com.giants3.hd.entity.Material;
import com.giants3.hd.entity.PackMaterialClass;
import com.giants3.hd.entity.PackMaterialPosition;
import com.giants3.hd.entity.PackMaterialType;
import com.giants3.hd.logic.ProductAnalytics;
import com.giants3.hd.noEntity.ProductDetail;
import com.giants3.hd.entity.ProductMaterial;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.FloatHelper;

import java.util.List;

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
    public static String PRODUCT_MATERIAL_POSITION = "PRODUCT_PAINT_POSITION";


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

    @Bind(R.id.save)
    View save;


    @Bind(R.id.packMaterialClass)
    Spinner packMaterialClass;

    @Bind(R.id.packMaterialType)
    Spinner packMaterialType;
    @Bind(R.id.packMaterialPosition)
    Spinner packMaterialPositionSpinner;


    @Bind(R.id.ll_packMaterialClass)
    View ll_packMaterialClass;

    @Bind(R.id.ll_packMaterialType)
    View ll_packMaterialType;
    @Bind(R.id.ll_packMaterialPosition)
    View ll_packMaterialPosition;


    private OnFragmentInteractionListener mListener;


    private int position = -1;
    private int materialType = -1;

    public ProductMaterialFragment() {
        // Required empty public constructor
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


            materialType = getArguments().getInt(PRODUCT_MATERIAL_TYPE, ProductDetailSingleton.PRODUCT_MATERIAL_CONCEPTUS);
            position = getArguments().getInt(PRODUCT_MATERIAL_POSITION);


            productMaterial =ProductDetailSingleton.getInstance().getProductMaterial(materialType,position);



            if (productMaterial == null) {
                //构建新数据表示添加
                productMaterial = new ProductMaterial();
                productMaterial.flowId = Flow.FLOW_PACK;
            }


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
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {

            case REQUEST_MATERIAL_SELECT:

                try {
                    Material material = GsonUtils.fromJson(data.getExtras().getString(MaterialSelectActivity.EXTRA_MATERIAL), Material.class);

                    ProductAnalytics.setMaterialToProductPaint(productMaterial,material);
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


        bindData(productMaterial);

    }


    @OnClick(R.id.save)
    public void onSave(View v) {


        ProductDetail productDetail = ProductDetailSingleton.getInstance().getProductDetail();
        if (productMaterial.getId() <= 0)//新增数据
        {
            switch (materialType) {
                case ProductDetailSingleton.PRODUCT_MATERIAL_CONCEPTUS:

                    productDetail.conceptusMaterials.add(position, productMaterial);

                    break;

                case ProductDetailSingleton.PRODUCT_MATERIAL_ASSEMBLE:

                    productDetail.assembleMaterials.add(position, productMaterial);

                    break;

                case ProductDetailSingleton.PRODUCT_MATERIAL_PACK:

                    productDetail.packMaterials.add(position, productMaterial);

                    break;
            }

        } else {

            switch (materialType) {
                case ProductDetailSingleton.PRODUCT_MATERIAL_CONCEPTUS:

                    productDetail.conceptusMaterials.set(position, productMaterial);

                    break;

                case ProductDetailSingleton.PRODUCT_MATERIAL_ASSEMBLE:

                    productDetail.assembleMaterials.set(position, productMaterial);

                    break;

                case ProductDetailSingleton.PRODUCT_MATERIAL_PACK:

                    productDetail.packMaterials.set(position, productMaterial);

                    break;
            }


        }

        ProductAnalytics.updatePackDataOnPackMaterialClass(productDetail.packMaterials,productDetail.product,productMaterial);
        ProductAnalytics.updateProductPackRelateData(productDetail);
        ProductAnalytics.updateProductStatistics(productDetail,SharedPreferencesHelper.getInitData().globalData);


        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();

    }



    private void initView() {
        ll_packMaterialClass.setVisibility(materialType == ProductDetailSingleton.PRODUCT_MATERIAL_PACK ? View.VISIBLE : View.GONE);
        ll_packMaterialPosition.setVisibility(materialType == ProductDetailSingleton.PRODUCT_MATERIAL_PACK ? View.VISIBLE : View.GONE);
        ll_packMaterialType.setVisibility(materialType == ProductDetailSingleton.PRODUCT_MATERIAL_PACK ? View.VISIBLE : View.GONE);
        {
            // 建立Adapter并且绑定数据源
            List<PackMaterialClass> mItems = SharedPreferencesHelper.getInitData().packMaterialClasses;
            PackMaterialClass[] arrayItems = new PackMaterialClass[mItems.size()+1];
            for(int i=1;i<arrayItems.length;i++)
            {
                arrayItems[i ]= mItems.get(i-1);
            }
            arrayItems[0]=new PackMaterialClass();
            ArrayAdapter<PackMaterialClass> _Adapter = new ArrayAdapter<PackMaterialClass>(getActivity(), android.R.layout.simple_spinner_item, arrayItems);
            packMaterialClass.setAdapter(_Adapter);
        }

        {
            // 建立Adapter并且绑定数据源
            List<PackMaterialType> typeItems = SharedPreferencesHelper.getInitData().packMaterialTypes;
            PackMaterialType[] arrayTypeItems = new PackMaterialType[typeItems.size()+1];
            for(int i=1;i<arrayTypeItems.length;i++)
            {
                arrayTypeItems[i ]= typeItems.get(i-1);
            }
            arrayTypeItems[0]=new PackMaterialType();
            ArrayAdapter<PackMaterialType> _TypeAdapter = new ArrayAdapter<PackMaterialType>(getActivity(), android.R.layout.simple_spinner_item, arrayTypeItems);
            packMaterialType.setAdapter(_TypeAdapter);
        }
        {
            // 建立Adapter并且绑定数据源
            List<PackMaterialPosition> positionItems = SharedPreferencesHelper.getInitData().packMaterialPositions;
            PackMaterialPosition[] arrayPositionItems = new PackMaterialPosition[positionItems.size()+1];
            for(int i=1;i<arrayPositionItems.length;i++)
            {
                arrayPositionItems[i ]= positionItems.get(i-1);
            }
            arrayPositionItems[0]=new PackMaterialPosition();

            ArrayAdapter<PackMaterialPosition> _PositionAdapter = new ArrayAdapter<PackMaterialPosition>(getActivity(), android.R.layout.simple_spinner_item, arrayPositionItems);
            packMaterialPositionSpinner.setAdapter(_PositionAdapter);
        }
    }


    @OnClick({R.id.selectMaterial, R.id.selectMaterial2})
    public void onMaterialSelect(View v) {

        Intent intent = new Intent(getActivity(), MaterialSelectActivity.class);

        if (v.getId() == R.id.selectMaterial) {
            intent.putExtra(MaterialSelectFragment.ARG_PARAM_CODE, productMaterial.materialCode);
        } else {

            intent.putExtra(MaterialSelectFragment.ARG_PARAM_NAME, productMaterial.materialName);
        }


        startActivityForResult(intent, REQUEST_MATERIAL_SELECT);
    }


    private void bindData(final ProductMaterial productMaterial) {


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
        unit.setText(productMaterial.unitName);
        available.setText(String.valueOf(productMaterial.available));
        type.setText(String.valueOf(productMaterial.type));
        price.setText(String.valueOf(productMaterial.price));


        if (materialType == ProductDetailSingleton.PRODUCT_MATERIAL_PACK) {
            int packQuantity = Math.max(1, ProductDetailSingleton.getInstance().getProductDetail().product.packQuantity);
            amount.setText(String.valueOf(FloatHelper.scale(productMaterial.amount / packQuantity)));
        } else {

            amount.setText(String.valueOf(productMaterial.amount));
        }

        memo.setText(productMaterial.memo);


        {
            packMaterialClass.setOnItemSelectedListener(null);
            int position = -1;
            if (productMaterial.packMaterialClass != null) {
                int size = SharedPreferencesHelper.getInitData().packMaterialClasses.size();
                for (int i = 0; i < size; i++) {
                    PackMaterialClass packMaterialClass = SharedPreferencesHelper.getInitData().packMaterialClasses.get(i);
                    if (packMaterialClass.equals(productMaterial.packMaterialClass)) {
                        position = i;
                        break;
                    }
                }
            }

            packMaterialClass.setSelection(position+1,false);

            packMaterialClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    ProductAnalytics.setPackMaterialClass(productMaterial,(PackMaterialClass) parent.getItemAtPosition(position));

                    ProductAnalytics.update(productMaterial);
                    ProductDetail productDetail = ProductDetailSingleton.getInstance().getProductDetail();
                    ProductAnalytics.updateProductPackRelateData(productDetail);
                    ProductAnalytics.updatePackDataOnPackMaterialClass(productDetail.packMaterials,productDetail.product,productMaterial);


                    bindData(productMaterial);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


        {
            packMaterialPositionSpinner.setOnItemSelectedListener(null);
            int position = -1;
            if (productMaterial.packMaterialPosition != null) {
                int size = SharedPreferencesHelper.getInitData().packMaterialPositions.size();
                for (int i = 0; i < size; i++) {
                    PackMaterialPosition packMaterialPosition = SharedPreferencesHelper.getInitData().packMaterialPositions.get(i);
                    if (packMaterialPosition.equals(productMaterial.packMaterialPosition)) {
                        position = i;
                        break;
                    }
                }
            }

            packMaterialPositionSpinner.setSelection(position+1,false);
            packMaterialPositionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    productMaterial.setPackMaterialPosition((PackMaterialPosition) parent.getItemAtPosition(position));

                    ProductDetail productDetail = ProductDetailSingleton.getInstance().getProductDetail();
                    ProductAnalytics.updateProductPackRelateData(productDetail);

                    ProductAnalytics.updatePackDataOnPackMaterialClass(productDetail.packMaterials,productDetail.product,productMaterial);



                    bindData(productMaterial);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        {
            packMaterialType.setOnItemSelectedListener(null);
            int position = -1;
            if (productMaterial.packMaterialType != null) {
                int size = SharedPreferencesHelper.getInitData().packMaterialTypes.size();
                for (int i = 0; i < size; i++) {
                    PackMaterialType packMaterialType = SharedPreferencesHelper.getInitData().packMaterialTypes.get(i);
                    if (packMaterialType.equals(productMaterial.packMaterialType)) {
                        position = i;
                        break;
                    }
                }
            }

            packMaterialType.setSelection(position+1,false);
            packMaterialType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    productMaterial.setPackMaterialType((PackMaterialType) parent.getItemAtPosition(position));
                    ProductDetail productDetail = ProductDetailSingleton.getInstance().getProductDetail();

                    ProductAnalytics.updatePackDataOnPackMaterialClass(productDetail.packMaterials,productDetail.product,productMaterial);


                    bindData(productMaterial);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }


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

    @OnClick(R.id.available)
    public void onAvailableClick(View view) {


        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("利用率", String.valueOf(productMaterial.available), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    float newAvailable = Float.valueOf(newValue);
                    productMaterial.setAvailable(newAvailable);

                    ProductAnalytics.update(productMaterial);
                    bindData(productMaterial);
                } catch (Throwable t) {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);

    }

    @OnClick(R.id.quantity)
    public void onQuantityClick(View view) {


        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("数量", String.valueOf(productMaterial.quantity), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    float newValueFloat = Float.valueOf(newValue);
                    productMaterial.setQuantity(newValueFloat);
                    ProductAnalytics.update(productMaterial);
                    bindData(productMaterial);
                } catch (Throwable t) {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);


    }

    @OnClick(R.id.mLong)
    public void onLongClick(View view) {


        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("长", String.valueOf(productMaterial.pLong), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    float newValueFloat = Float.valueOf(newValue);
                    productMaterial.setpLong(newValueFloat);
                    ProductAnalytics.update(productMaterial);
                    bindData(productMaterial);
                } catch (Throwable t) {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);


    }

    @OnClick(R.id.mWidth)
    public void onWidthClick(View view) {


        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("宽", String.valueOf(productMaterial.pWidth), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    float newValueFloat = Float.valueOf(newValue);
                    productMaterial.setpWidth(newValueFloat);
                    ProductAnalytics.update(productMaterial);
                    bindData(productMaterial);
                } catch (Throwable t) {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);


    }

    @OnClick(R.id.mHeight)
    public void onHeightClick(View view) {


        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("高", String.valueOf(productMaterial.pHeight), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    float newValueFloat = Float.valueOf(newValue);
                    productMaterial.setpHeight(newValueFloat);
                    ProductAnalytics.update(productMaterial);
                    bindData(productMaterial);
                } catch (Throwable t) {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);


    }

    @OnClick(R.id.memo)
    public void onMemoClick(View view) {


        ValueEditDialogFragment dialogFragment = new ValueEditDialogFragment();
        dialogFragment.set("分件备注", String.valueOf(productMaterial.memo), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    productMaterial.setMemo(newValue);
                    bindData(productMaterial);
                } catch (Throwable t) {

                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);


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
