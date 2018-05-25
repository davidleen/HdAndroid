package com.giants3.hd.android.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.giants3.hd.android.R;
import com.giants3.hd.android.Utils;
import com.giants3.hd.android.activity.AppQuotationActivity;
import com.giants3.hd.android.activity.ProductDetailActivity;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.mvp.searchproduct.SearchProductMvp;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.data.utils.GsonUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 发送流程fragment
 */
public class SearchProductFragment extends BaseDialogFragment<SearchProductMvp.Presenter> implements SearchProductMvp.Viewer {

    private static final String ARG_AVAILABLE_ITEMS = "ARG_AVAILABLE_ITEMS";



    @Bind(R.id.key)
    EditText editText;
    @Bind(R.id.withCopy)
    CheckBox withCopy;
    @Bind(R.id.list)
    ListView listView;
    ItemListAdapter<AProduct> productItemListAdapter;
    private OnFragmentInteractionListener mListener;

    public SearchProductFragment() {

    }


    public static SearchProductFragment newInstance() {
        SearchProductFragment fragment = new SearchProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected SearchProductMvp.Presenter onLoadPresenter() {
        return new com.giants3.hd.android.mvp.searchproduct.PresenterImpl();
    }

    @Override
    protected void initEventAndData() {



      productItemListAdapter=new ItemListAdapter<>(getActivity());
        productItemListAdapter.setTableData(TableData.resolveData(getActivity(),R.array.table_product_item));
     int wh[]=   Utils.getScreenWH();

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.width= (int) (wh[0]*0.8);
        layoutParams.height= (int) (wh[1]*0.8);
        listView.setLayoutParams(layoutParams);

        listView.setAdapter( productItemListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                AProduct aProduct= (AProduct) parent.getItemAtPosition(position);
                if(aProduct!=null&&mListener!=null
                        )
                {
                    mListener.onProductSelect(aProduct);
                    dismiss();
                }
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                //调整act
                Intent intent = new Intent(SearchProductFragment.this.getContext(), ProductDetailActivity.class);
                intent.putExtra(ProductDetailFragment.ARG_ITEM, GsonUtils.toJson(parent.getItemAtPosition(position)));
                startActivity(intent);
                return true;
            }
        }) ;



        withCopy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                getPresenter().setWithCopy(isChecked);
                productItemListAdapter.setTableData(TableData.resolveData(getActivity(),isChecked?R.array.table_product_item_with_copy:R.array.table_product_item));
                listView.setAdapter( productItemListAdapter);
                doSearch();

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                getPresenter().setKeyWord(s.toString());
                doSearch();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        editText.post(new Runnable() {
            @Override
            public void run() {
                String text = "18A";
                editText.setText(text);
                editText.setSelection(text.length());
            }
        });

    }


    Runnable runnable=new Runnable() {
        @Override
        public void run() {

        getPresenter().search();
        }
    };

    private void doSearch() {

        editText.removeCallbacks(runnable);
        editText.postDelayed(runnable,1000);

    }


    @Override
    protected void initViews(Bundle savedInstanceState) {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_search_product, container, false);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        else {
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
    public void bindDatas(List<AProduct> datas) {



         productItemListAdapter.setDataArray(datas);

    }


    public interface OnFragmentInteractionListener {



        void onProductSelect(AProduct aProduct);
    }


}
