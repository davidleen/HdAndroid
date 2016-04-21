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
import com.giants3.hd.android.adapter.OrderListAdapter;
import com.giants3.hd.android.helper.ToastHelper;
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
public class OrderListFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    OrderListAdapter adapter;
    private OnFragmentInteractionListener mListener;

    @Bind(R.id.list)
    ListView orderList;

    @Bind(R.id.btn_search)
    View btn_search;

    @Bind(R.id.search_text)
    EditText search_text;

    @Bind(R.id.progressBar)
    View progressBar;




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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_list, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        orderList.setAdapter(adapter);
        btn_search.setOnClickListener(this);

        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                getActivity().getWindow().getDecorView().removeCallbacks(runnable);
                getActivity().getWindow().getDecorView().postDelayed(runnable, 1000);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        attemptLoadList();

    }
    private void attemptLoadList()
    {
        attemptLoadList(0,100);
    }

    private void attemptLoadList(int pageIndex,int pageSize)
    {
        attemptLoadList("",pageIndex,pageSize);

    }
    private void attemptLoadList(String name,int pageIndex,int pageSize)
    {

        progressBar.setVisibility(View.VISIBLE);
        UseCaseFactory.getInstance().createOrderListCase(name,pageIndex,pageSize).execute(new Subscriber<RemoteData<ErpOrder>>() {
            @Override
            public void onCompleted() {
//                showProgress(false);
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onError(Throwable e) {
//                showProgress(false);
                progressBar.setVisibility(View.GONE);
                ToastHelper.show(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<ErpOrder> aUser) {
                if(aUser.isSuccess()) {
                    adapter.setDataArray(aUser.datas);
                }else
                {
                    ToastHelper.show(aUser.message);
                    if(aUser.code==RemoteData.CODE_UNLOGIN)
                    {
                        startLoginActivity();
                    }
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_search:
                getActivity().getWindow().getDecorView().removeCallbacks(runnable);
                getActivity().getWindow().getDecorView().postDelayed(runnable,1000);
                break;

        }

    }


    /**
     *
     */
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {


           String key= search_text.getText().toString().trim();

            attemptLoadList(key,0,100);
        }
    };

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

        void onFragmentInteraction(ErpOrder erpOrder);
    }


    @Override
    protected void onLoginRefresh() {

          attemptLoadList();


    }

}
