package com.giants3.hd.android.fragment;

import android.os.Bundle;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.ProductProcess;


/**
 *
 *
 * 工序选择
 */
public class ProcessSelectFragment extends TableListFragment<ProductProcess>  {

    public static final String ARG_PARAM_CODE= "ARG_PARAM_CODE";
    public static final String ARG_PARAM_NAME= "ARG_PARAM_NAME";




    private String processCode;
    private String processName;


    ItemListAdapter<ProductProcess> adapter;


    public ProcessSelectFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProductListFragment.
     */
    // TODO: Rename and parse types and number of parameters
    public static ProcessSelectFragment newInstance(String code, String name) {
        ProcessSelectFragment fragment = new ProcessSelectFragment();
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
            processCode = getArguments().getString(ARG_PARAM_CODE,"");
            processName = getArguments().getString(ARG_PARAM_NAME,"");
        }
       // materialItemListAdapter=new
        TableData tableData = TableData.resolveData(getContext(), R.array.table_head_product_process);
        adapter = new ItemListAdapter<>(getActivity());
        adapter.setTableData(tableData);

    }

    @Override
    protected ItemListAdapter<ProductProcess> getAdapter() {
        return adapter;
    }












    @Override
    protected UseCase getUserCase(String key, int pageIndex, int pageSize) {
        return  UseCaseFactory.getInstance().createProductProcessListCase(key,pageIndex,pageSize);
    }



}
