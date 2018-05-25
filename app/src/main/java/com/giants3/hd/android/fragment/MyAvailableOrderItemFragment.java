package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.activity.WorkFlowListActivity;
import com.giants3.hd.android.adapter.ErpOrderItemListAdapter;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.mvp.MyAvailableOrderItemMVP;
import com.giants3.hd.android.mvp.myavailableorderitem.PresenterImpl;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.ErpOrderItem;

import java.util.List;

import butterknife.Bind;

/**
 * 我的生产事项  我可以发起的任务的货款列表
 * Created by davidleen29 on 2017/6/3.
 */

public class MyAvailableOrderItemFragment extends BaseMvpFragment<MyAvailableOrderItemMVP.Presenter> implements MyAvailableOrderItemMVP.Viewer {

    private static final int REQUEST_MESSAGE_OPERATE = 9999;
    ItemListAdapter<ErpOrderItem> adapter;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    @Bind(R.id.list)
    ListView listView;
    @Bind(R.id.search_text)
    EditText search_text;
    View loadingMoreView;
    TextWatcher watcher;

    @Override
    protected MyAvailableOrderItemMVP.Presenter createPresenter() {
        return new PresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.myavailableorderitemprocess, null);
    }

    @Override
    protected void initView() {

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                searchErpOrderItems();

            }
        });

       swipeLayout.setEnabled(false);

        adapter = new ErpOrderItemListAdapter(getActivity());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {


                View inflate =  getLayoutInflater().inflate(R.layout.menu_my_available_work, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setView(inflate).create();
                View event =  inflate.findViewById(R.id.event);
                View process =   inflate.findViewById(R.id.process);
                process.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        ErpOrderItem erpOrderItem = (ErpOrderItem) parent.getItemAtPosition(position);
                        Intent intent = new Intent(getActivity(), WorkFlowListActivity.class);
                        intent.putExtra(WorkFlowListActivity.KEY_ORDER_ITEM, GsonUtils.toJson(erpOrderItem));
                        startActivityForResult(intent, REQUEST_MESSAGE_OPERATE);
                        alertDialog.dismiss();
                    }
                });

                event.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {






                        alertDialog.dismiss();
                    }
                });


                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();


            }
        });
        watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                search_text.removeCallbacks(searchRunnable);
                search_text.postDelayed(searchRunnable, 1500);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        search_text.addTextChangedListener(watcher);
        search_text.setRawInputType(InputType.TYPE_CLASS_NUMBER);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount >= totalItemCount) {

                    if (getPresenter() != null && getPresenter().canSearchMore())
                        getPresenter().searchMore();
                }


            }
        });

        loadingMoreView = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_load_more, null);
        listView.addFooterView(loadingMoreView);
        loadingMoreView.setVisibility(View.GONE);

    }

    private LayoutInflater getLayoutInflater() {

        return LayoutInflater.from( getActivity());

    }

    private Runnable searchRunnable = new Runnable() {
        @Override
        public void run() {


            searchErpOrderItems();


        }
    };



    private void searchErpOrderItems() {
        if (search_text != null && getPresenter() != null) {
            String text = search_text.getText().toString().trim();
            getPresenter().searchErpOrderItems(text);
        }
    }

//    @Override
//    public void showWaiting() {
//
//        swipeLayout.setEnabled(true);
//        swipeLayout.setRefreshing(true);
//    }
//
//    @Override
//    public void hideWaiting() {
//        swipeLayout.setEnabled(false);
//        swipeLayout.setRefreshing(false);
//    }


    @Override
    public void bindOrderItems(List<ErpOrderItem> datas) {
        adapter.setDataArray(datas);


        loadingMoreView.setVisibility(getPresenter().canSearchMore() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroyView() {

        if (watcher != null && search_text != null)
            search_text.removeTextChangedListener(watcher);
        super.onDestroyView();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {
            case REQUEST_MESSAGE_OPERATE:
                searchErpOrderItems();

                break;
        }

    }
}
