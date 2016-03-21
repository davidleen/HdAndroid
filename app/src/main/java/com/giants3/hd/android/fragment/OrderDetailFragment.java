package com.giants3.hd.android.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.Utils;
import com.giants3.hd.android.activity.LongTextActivity;
import com.giants3.hd.android.adapter.OrderItemListAdapter;
import com.giants3.hd.android.adapter.TableHeadAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.widget.CustomScrollView;
import com.giants3.hd.android.widget.ExpandableHeightListView;
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
public class OrderDetailFragment extends BaseFragment implements View.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static String ARG_ITEM = "ARG_ITEM";

    private static  final int MAX_MEMO_ROW_LINE=3;

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
    @Bind(R.id.showMore)
    View showMore;
    @Bind(R.id.more_text)
    View more_text;
    @Bind(R.id.scrollIndicatorDown)
    HorizontalScrollView horizontalScrollView1;

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


       horizontalScrollView1.setFocusable(true);


        order_no.setText(erpOrder.os_no);
        cus_no.setText(erpOrder.cus_no);
        cus_os_no.setText(erpOrder.cus_os_no);
        os_dd.setText(erpOrder.os_dd);
        est_dd.setText(erpOrder.est_dd);
        so_data.setText(erpOrder.so_data);
        sal.setText(erpOrder.sal_name);
        memo.setText(erpOrder.rem);
        memo.setMaxLines(MAX_MEMO_ROW_LINE);
        memo.post(new Runnable() {
            @Override
            public void run() {


                int count = MAX_MEMO_ROW_LINE;
                boolean more = false;


                if (memo.getLineCount() > MAX_MEMO_ROW_LINE) {

                    int width = memo.getLayout().getLineEnd(count - 1);

                    more = erpOrder.rem.length() > width;


                }


                showMore.setVisibility(more ? View.VISIBLE : View.GONE);
                more_text.setVisibility(more ? View.VISIBLE : View.GONE);


            }
        });


         showMore.setOnClickListener(this);

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


        View footDivder=new View(getContext());
        footDivder.setLayoutParams(new AbsListView.LayoutParams(totalWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
        footDivder.setBackgroundResource(R.drawable.icon_divider);
        order_item_list.addFooterView(footDivder);

        //order_item_list.setExpanded(true);
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
//                    int size=orderItemListAdapter.getCount();
//                   // order_item_list.removeAllViews();
//                    for(int i=0;i<size;i++)
//                    {
//                        order_item_list.addView(orderItemListAdapter.getView(i,null,null));
//                    }
//                    horizontalScrollView1.invalidate();

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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.showMore:
                Intent intent=new Intent(getActivity(), LongTextActivity.class);
                intent.putExtra(LongTextActivity.PARAM_TITLE,"订单备注");
                intent.putExtra(LongTextActivity.PARAM_CONTENT,erpOrder.rem);
                getActivity().startActivity(intent);

                break;
        }
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
