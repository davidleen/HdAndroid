package com.giants3.hd.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.giants3.hd.android.adapter.AbstractAdapter;
import com.giants3.hd.android.adapter.MaterialListAdapter;
import com.giants3.hd.android.events.MaterialUpdateEvent;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.entity.Material;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MaterialListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MaterialListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaterialListFragment extends ListFragment<Material> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and parse types of parameters
    private String mParam1;
    private String mParam2;


    MaterialListAdapter adapter;


    public MaterialListFragment() {

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
    protected AbstractAdapter<Material> getAdapter() {
        return adapter;
    }

    @Override
    protected UseCase getUserCase(String key, int pageIndex, int pageSize) {
        return UseCaseFactory.getInstance().createMaterialListCase(key,pageIndex,pageSize);
    }



    public void onEvent(MaterialUpdateEvent event) {

        onLoginRefresh();
    }




}
