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
import com.giants3.hd.android.activity.ProductProcessSelectActivity;
import com.giants3.hd.android.entity.ProductDetailSingleton;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.logic.ProductAnalytics;
import com.giants3.hd.noEntity.ProductDetail;
import com.giants3.hd.entity.ProductProcess;
import com.giants3.hd.entity.ProductWage;
import com.giants3.hd.exception.HdException;

import butterknife.Bind;
import butterknife.OnClick;



/** 分析表工资输入界面
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductWageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductWageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductWageFragment extends BaseFragment implements View.OnClickListener {


    private static final int REQUEST_PROCESS_SELECT = 12;
    public static final String EXTRA_PRODUCT_WAGE = "EXTRA_PRODUCT_WAGE";

    public static String PRODUCT_MATERIAL_TYPE = "PRODUCT_MATERIAL_TYPE";
   public static String PRODUCT_WAGE_POSITION = "PRODUCT_WAGE_POSITION";


    ProductWage productWage;

    @Bind(R.id.processCode)
    TextView processCode;
    @Bind(R.id.processName)
    TextView processName;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.amount)
    TextView amount;
    @Bind(R.id.memo)
    TextView memo;
    @Bind(R.id.selectWage)
    View selectWage;
    @Bind(R.id.selectWage2)
    View selectWage2;

      @Bind(R.id.save)
      View save;



    private OnFragmentInteractionListener mListener;


    private int position = -1;
    private int materialType = -1;

    public ProductWageFragment() {
        // Required empty public constructor
    }


    public static final int PRODUCT_MATERIAL_CONCEPTUS = 1;
    public static final int PRODUCT_MATERIAL_ASSEMBLE = 2;
    public static final int PRODUCT_MATERIAL_PACK = 3;
    //public  static final int PRODUCT_WAGE_CONCEPTUS=2;


    public static ProductWageFragment newInstance(ProductWage productWage) {
        ProductWageFragment fragment = new ProductWageFragment();
        Bundle args = new Bundle();
        if(productWage!=null)
        args.putString(EXTRA_PRODUCT_WAGE, GsonUtils.toJson(productWage));
        fragment.setArguments(args);
        return fragment;
    }

    public static ProductWageFragment newInstance(Bundle args) {
        ProductWageFragment fragment = new ProductWageFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


            materialType=getArguments().getInt(PRODUCT_MATERIAL_TYPE,PRODUCT_MATERIAL_CONCEPTUS);
            position=getArguments().getInt(PRODUCT_WAGE_POSITION);

            try {
                productWage=GsonUtils.fromJson(getArguments().getString(EXTRA_PRODUCT_WAGE ),ProductWage.class);

        } catch (HdException e) {
            e.printStackTrace();
        }

            if(productWage==null)
            {
                //构建新数据表示添加
                productWage=new ProductWage();
            }


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_wage, container, false);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {

            case REQUEST_PROCESS_SELECT:

                try {
                    ProductProcess productProcess = GsonUtils.fromJson(data.getExtras().getString(ProductProcessSelectActivity.EXTRA_PRODUCT_PROCESS), ProductProcess.class);
                    productWage.setProductProcess(productProcess);
                    bindData(productWage);

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
        bindData(productWage);

    }



    @OnClick(R.id.save)
    public void onSave(View v)
    {



        ProductDetail productDetail=ProductDetailSingleton.getInstance().getProductDetail();
        if(productWage.getId()<=0)//新增数据
        {
                switch (materialType)
                {
                    case PRODUCT_MATERIAL_CONCEPTUS:

                        productDetail.conceptusWages.add(position,productWage);

                        break;

                    case PRODUCT_MATERIAL_ASSEMBLE:
                        productDetail.assembleWages.add(position,productWage);

                        break;

                    case PRODUCT_MATERIAL_PACK:
                        productDetail.packWages.add(position,productWage);


                        break;
                }

        }else
        {

            switch (materialType)
            {
                case PRODUCT_MATERIAL_CONCEPTUS:

                    productDetail.conceptusWages.set(position,productWage);

                    break;

                case PRODUCT_MATERIAL_ASSEMBLE:


                    productDetail.assembleWages.set(position,productWage);
                    break;

                case PRODUCT_MATERIAL_PACK:

                    productDetail.packWages.set(position,productWage);


                    break;
            }


        }

         ProductAnalytics.updateProductStatistics(ProductDetailSingleton.getInstance().getProductDetail(),SharedPreferencesHelper.getInitData().globalData);
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();

    }
    private void initView() {


    }


    @OnClick({R.id.selectWage, R.id.selectWage2})
    public void onProcessSelect(View v) {

        Intent intent = new Intent(getActivity(), ProductProcessSelectActivity.class);

        if(v.getId()==R.id.selectWage)
        {
            intent.putExtra(ProcessSelectFragment.ARG_PARAM_CODE,productWage.processCode);
        }else {

            intent.putExtra(ProcessSelectFragment.ARG_PARAM_NAME,productWage.processName);
        }



        startActivityForResult(intent, REQUEST_PROCESS_SELECT);
    }


    private void bindData(ProductWage productWage) {


        processCode.setText(productWage.processCode);
        processName.setText(productWage.processName);
        price.setText(String.valueOf(productWage.price));
        amount.setText(String.valueOf(productWage.amount));
        memo.setText(String.valueOf(productWage.memo));


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

    @OnClick(R.id.price)
    public void onPriceClick(View view) {


        ValueEditDialogFragment dialogFragment=new ValueEditDialogFragment();
        dialogFragment.set("工价", String.valueOf(productWage.price), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    float newAvailable = Float.valueOf(newValue);
                    productWage.setPrice(newAvailable);
                    productWage.setAmount(newAvailable);               ;
                   // productWage.
                    bindData(productWage);
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
        dialogFragment.set("备注", String.valueOf(productWage.memo), new ValueEditDialogFragment.ValueChangeListener() {
            @Override
            public void onValueChange(String title, String oldValue, String newValue) {
                try {
                    productWage.setMemo(newValue);
                    bindData(productWage);
                }catch (Throwable t )
                {
                }


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(),null);


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
