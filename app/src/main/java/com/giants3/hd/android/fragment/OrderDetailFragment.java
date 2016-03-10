package com.giants3.hd.android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.Utils;
import com.giants3.hd.android.adapter.OrderItemListAdapter;
import com.giants3.hd.android.adapter.TableHeadAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.data.entity.ErpOrder;
import com.giants3.hd.data.entity.ErpOrderItem;
import com.giants3.hd.data.entity.RemoteData;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;

import java.util.Arrays;

import butterknife.Bind;
import rx.Subscriber;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDetailFragment extends BaseFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static String ARG_ITEM = "ARG_ITEM";


    ErpOrder erpOrder;

    OrderItemListAdapter orderItemListAdapter;
    private OnFragmentInteractionListener mListener;

    @Bind(R.id.order_item_list)
    ListView order_item_list;




    @Bind(R.id.order_no)
    TextView order_no;
    @Bind(R.id.cus_no)
    TextView cus_no;
    @Bind(R.id.cus_os_no)
    TextView cus_os_no;
    @Bind(R.id.os_dd)
    TextView os_dd;
    @Bind(R.id.est_dd)
    TextView est_dd;
    @Bind(R.id.so_data)
    TextView so_data;
    @Bind(R.id.sal)
    TextView sal;
    @Bind(R.id.memo)
    TextView memo;

    public OrderDetailFragment() {
        // Required empty public constructor
    }


    public static OrderDetailFragment newInstance(ErpOrder erpOrder) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEM, GsonUtils.toJson(erpOrder));

        fragment.setArguments(args);
        return fragment;
    }

    public static OrderDetailFragment newInstance(Bundle args) {
        OrderDetailFragment fragment = new OrderDetailFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            try {
                erpOrder = GsonUtils.fromJson(getArguments().getString(ARG_ITEM), ErpOrder.class);
            } catch (HdException e) {
                e.printStackTrace();
                ToastHelper.show("参数异常");
                getActivity().finish();
            }


//        String[] tableHead=    getResources().getStringArray(R.array.table_head_order_item);


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        order_no.setText(erpOrder.os_no);
        cus_no.setText(erpOrder.cus_no);
        cus_os_no.setText(erpOrder.cus_os_no);
        os_dd.setText(erpOrder.os_dd);
        est_dd.setText(erpOrder.est_dd);
        so_data.setText(erpOrder.so_data);
        sal.setText(erpOrder.sal_name);
        memo.setText(erpOrder.rem);


        TableData tableData = TableData.resolveData(getContext(), R.array.table_head_order_item);
        TableHeadAdapter adapter = new TableHeadAdapter(getActivity());
        adapter.setTableData(tableData);
        adapter.setDataArray(Arrays.asList(tableData.headNames));


        orderItemListAdapter = new OrderItemListAdapter(getActivity());

        orderItemListAdapter.setTableData(tableData);


        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(Gravity.CENTER);
        int totalWidth = 0;
        if (tableData != null) {

            for (int i = 0; i < tableData.size; i++) {
                TextView textView = new TextView(getContext());
                textView.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(tableData.width[i], Utils.dp2px(40));
                textView.setText(tableData.headNames[i]);
                linearLayout.addView(textView, layoutParams);
                totalWidth+=tableData.width[i];
            }
        }
        linearLayout.setDividerDrawable(getResources().getDrawable(R.drawable.icon_divider));
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        View tempView=new View(getContext());
        tempView.setLayoutParams(new AbsListView.LayoutParams(totalWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
        order_item_list.addHeaderView(tempView);
        order_item_list.addHeaderView(linearLayout);

        order_item_list.setAdapter(orderItemListAdapter);


        attemptLoad(erpOrder.os_no);
    }

    private void attemptLoad() {
        attemptLoad(erpOrder.os_no);
    }


    private void attemptLoad(String orderNo) {

        UseCaseFactory.getInstance().createOrderItemListCase(orderNo).execute(new Subscriber<RemoteData<ErpOrderItem>>() {
            @Override
            public void onCompleted() {
                showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                showProgress(false);
                ToastHelper.show(e.getMessage());
            }

            @Override
            public void onNext(RemoteData<ErpOrderItem> orderItemRemoteData) {
                if (orderItemRemoteData.isSuccess()) {
                    orderItemListAdapter.setDataArray(orderItemRemoteData.datas);


                } else {
                    ToastHelper.show(orderItemRemoteData.message);
                    if (orderItemRemoteData.code == RemoteData.CODE_UNLOGIN) {
                        startLoginActivity();
                    }
                }
            }
        });

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

        void onFragmentInteraction(Uri uri);
    }


    @Override
    protected void onLoginRefresh() {

        attemptLoad();


    }

}
