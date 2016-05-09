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
import com.giants3.hd.android.adapter.MaterialListAdapter;
import com.giants3.hd.android.adapter.ProductListAdapter;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.utils.entity.Material;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.utils.entity.RemoteData;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscriber;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MaterialListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MaterialListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaterialListFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and parse types of parameters
    private String mParam1;
    private String mParam2;


    MaterialListAdapter adapter;
    private OnFragmentInteractionListener mListener;

    @Bind(R.id.list)
    ListView product_list;
    @Bind(R.id.search_text)
    EditText search_text;

    public MaterialListFragment() {
        // Required empty public constructor
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
    public static MaterialListFragment newInstance(String param1, String param2) {
        MaterialListFragment fragment = new MaterialListFragment();
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

        adapter =new MaterialListAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_material_list, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        product_list.setAdapter(adapter);
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

    private void attemptLoadList(int pageIndex,int pageSize)
    {
        attemptLoadList("",pageIndex,pageSize);

    }
    private void attemptLoadList(String name,int pageIndex,int pageSize)
    {

        UseCaseFactory.getInstance().createMaterialListCase(name,pageIndex,pageSize).execute(new Subscriber<RemoteData<Material>>() {
            @Override
            public void onCompleted() {
//                showProgress(false);
            }
            @Override
            public void onError(Throwable e) {
//                showProgress(false);
                ToastHelper.show(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<Material> aUser) {
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

        void onFragmentInteraction(Material Material);
    }


    @Override
    protected void onLoginRefresh() {

          attemptLoadList();


    }

    @Override
    public void onDestroyView() {

        search_text.removeCallbacks(runnable);
        super.onDestroyView();

    }
}
