package com.giants3.hd.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.AbstractAdapter;
import com.giants3.hd.android.adapter.OrderListAdapter;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.utils.entity.ErpOrder;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.data.interractor.UseCaseFactory;

import butterknife.Bind;
import rx.Subscriber;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderListFragment extends ListFragment<ErpOrder> {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";




    public OrderListFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductListFragment.
     */

    public static OrderListFragment newInstance(String param1, String param2) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter =new OrderListAdapter(getActivity());
    }


    @Override
    protected UseCase getUserCase(String key, int pageIndex, int pageSize) {
        return  UseCaseFactory.getInstance().createOrderListCase(key,pageIndex,pageSize);
    }

    @Override
    protected AbstractAdapter<ErpOrder> getAdapter() {
        return adapter;
    }







}
