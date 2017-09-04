package com.giants3.hd.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.noEntity.RemoteData;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by david on 2016/5/12.
 */
public abstract class TableListFragment<T> extends BaseFragment  {

   ItemListAdapter<T> adapter;


    @Bind(R.id.item_list)
    ListView listView;
    @Bind(R.id.search_text)
    EditText search_text;

    @Bind(R.id.progressBar)
    View progressBar;
    private OnFragmentInteractionListener<T> mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_table_select, container, false);
    }






    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getAdapter() != null)
            adapter = getAdapter();
        if (adapter != null)
            listView.setAdapter(adapter);

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

        runnable.run();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (mListener != null) {
                    mListener.onFragmentInteraction(adapter.getItem(position));
                }
            }
        });

    }

    /**
     *
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            String key = search_text.getText().toString().trim();
            attemptLoadList(key, 0, 100);
        }
    };

    protected ItemListAdapter<T> getAdapter() {
        return null;
    }


    private void attemptLoadList(String key, int pageIndex, int pageSize) {
        if(progressBar!=null)
        progressBar.setVisibility(View.VISIBLE);
        UseCase useCase = getUserCase(key, pageIndex, pageSize);
        if (useCase == null) return;

        useCase.execute(new Subscriber<RemoteData<T>>() {
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
            public void onNext(RemoteData<T> aUser) {
                if (aUser.isSuccess()) {
                    adapter.setDataArray(aUser.datas);
                } else {
                    ToastHelper.show(aUser.message);
//                    if (aUser.code == RemoteData.CODE_UNLOGIN) {
//                        startLoginActivity();
//                    }
                }
            }
        });

    }




    /**
     * 获取数据访问实例
     *
     * @param key
     * @param pageIndex
     * @param pageSize
     * @return
     */
    protected UseCase getUserCase(String key, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    protected void onLoginRefresh() {


        if (runnable != null)
            runnable.run();

    }


    /**
     * 列表交互接口
     *
     * @param <T>
     */
    public interface OnFragmentInteractionListener<T> {

        void onFragmentInteraction(T data);
    }


    @Override
    public void onDestroyView() {

        search_text.removeCallbacks(runnable);
        super.onDestroyView();

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

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
