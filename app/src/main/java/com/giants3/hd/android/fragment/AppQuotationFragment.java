package com.giants3.hd.android.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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
import com.giants3.hd.android.activity.WorkFlowListActivity;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.mvp.AppQuotationMVP;
import com.giants3.hd.android.mvp.appquotation.PresenterImpl;
import com.giants3.hd.android.widget.RefreshLayoutConfig;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.app.Quotation;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

import butterknife.Bind;

/**
 * 广交会报价
 * Created by davidleen29 on 2017/6/3.
 */

public class AppQuotationFragment extends BaseMvpFragment<AppQuotationMVP.Presenter> implements AppQuotationMVP.Viewer {

    private static final int REQUEST_MESSAGE_OPERATE = 9999;
    ItemListAdapter<Quotation> adapter;
    @Bind(R.id.swipeLayout)
    TwinklingRefreshLayout swipeLayout;
    @Bind(R.id.list)
    ListView listView;
    @Bind(R.id.search_text)
    EditText search_text;
    @Bind(R.id.add)
    View add;

    TextWatcher watcher;

    @Override
    protected AppQuotationMVP.Presenter createPresenter() {
        return new PresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_app_quotation, null);
    }

    @Override
    protected void initView() {

        RefreshLayoutConfig.config(swipeLayout);

        swipeLayout.setEnabled(false);

        adapter = new ItemListAdapter(getActivity());
        adapter.setTableData(TableData.resolveData(getActivity(), R.array.table_app_quotation));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {


                View inflate = getLayoutInflater().inflate(R.layout.menu_my_available_work, null);
                final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setView(inflate).create();
                View event = inflate.findViewById(R.id.event);
                View process = inflate.findViewById(R.id.process);
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

                if (search_text != null && getPresenter() != null) {
                    String text = search_text.getText().toString().trim();
                    getPresenter().setKey(text);

                }
                search_text.removeCallbacks(searchRunnable);
                search_text.postDelayed(searchRunnable, 1500);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        search_text.addTextChangedListener(watcher);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        swipeLayout.setTargetView(listView);
        swipeLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {


                getPresenter().searchData();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {


                getPresenter().loadMoreData();
            }
        });



    }

    @Override
    public void bindData(List<Quotation> datas) {

        adapter.setDataArray(datas);

        swipeLayout.finishRefreshing();
    }

    private LayoutInflater getLayoutInflater() {

        return LayoutInflater.from(getActivity());

    }

    private Runnable searchRunnable = new Runnable() {
        @Override
        public void run() {


            searchData();


        }
    };


    private void searchData() {

        swipeLayout.startRefresh();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {
            case REQUEST_MESSAGE_OPERATE:
                searchData();

                break;
        }

    }
}
