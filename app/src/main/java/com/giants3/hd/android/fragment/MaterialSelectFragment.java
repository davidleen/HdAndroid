package com.giants3.hd.android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.AbstractAdapter;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.adapter.MaterialListAdapter;
import com.giants3.hd.android.adapter.OrderItemListAdapter;
import com.giants3.hd.android.adapter.TableHeadAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.events.MaterialUpdateEvent;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.Material;
import com.giants3.hd.utils.entity.QuotationItem;
import com.giants3.hd.utils.entity.RemoteData;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.OnItemClick;
import rx.Subscriber;


/**
 *
 *
 * 材料选择
 */
public class MaterialSelectFragment extends BaseFragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_CODE= "ARG_PARAM_CODE";
    private static final String ARG_PARAM_NAME= "ARG_PARAM_NAME";


    ItemListAdapter<Material> materialItemListAdapter;

    // TODO: Rename and parse types of parameters
    private String materialCode;
    private String materialName;


    @Bind(R.id.search_text)
    EditText search_text;
    @Bind(R.id.progressBar)
    View progressBar;
    @Bind(R.id.item_list)
    ListView itemList;
    private OnFragmentInteractionListener mListener;


    public MaterialSelectFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProductListFragment.
     */
    // TODO: Rename and parse types and number of parameters
    public static MaterialSelectFragment newInstance(String code, String name) {
        MaterialSelectFragment fragment = new MaterialSelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_CODE, code);
        args.putString(ARG_PARAM_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            materialCode = getArguments().getString(ARG_PARAM_CODE,"");
            materialName = getArguments().getString(ARG_PARAM_NAME,"");
        }
       // materialItemListAdapter=new
        TableData tableData = TableData.resolveData(getContext(), R.array.table_head_material_item);
        materialItemListAdapter = new ItemListAdapter<>(getActivity());
        materialItemListAdapter.setTableData(tableData);

    }


    public interface
    OnFragmentInteractionListener {

        public void onFragmentInteraction(Material material) ;
    }



    @OnItemClick(R.id.item_list)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Material material= materialItemListAdapter.getItem(position);

        if(material==null) return;
        if(material.outOfService)
        {

            ToastHelper.show("该材料已经停用");
            return ;
        }


        if(mListener!=null)
            mListener.onFragmentInteraction(material);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_material_select, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        itemList.setAdapter(materialItemListAdapter);

        loadData("");

    }


    private void loadData(String key)
    {

        UseCaseFactory.getInstance().createMaterialListInServiceCase(key,0,100).execute(new Subscriber<RemoteData<Material>>() {
            @Override
            public void onCompleted() {
//                showProgress(false);
                if(progressBar!=null)
                    progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
//                showProgress(false);
                if(progressBar!=null)
                    progressBar.setVisibility(View.GONE);
                ToastHelper.show(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<Material> aUser) {
                if (aUser.isSuccess()) {
                    materialItemListAdapter.setDataArray(aUser.datas);
                } else {
                    ToastHelper.show(aUser.message);

                }
            }
        });
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
}
