package com.giants3.hd.android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.adapter.ItemListAdapter;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.widget.ExpandableHeightListView;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.Quotation;
import com.giants3.hd.noEntity.QuotationDetail;
import com.giants3.hd.entity.QuotationItem;
import com.giants3.hd.entity.QuotationXKItem;
import com.giants3.hd.entity.QuoteAuth;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.noEntity.RemoteData;

import butterknife.Bind;
import rx.Subscriber;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuotationDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuotationDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuotationDetailFragment extends BaseFragment implements View.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static String ARG_ITEM = "PRODUCT_MATERIAL_TYPE";

    private static final int MAX_MEMO_ROW_LINE = 3;


    ItemListAdapter<QuotationItem> quotationItemItemListAdapter;
    ItemListAdapter<QuotationXKItem> xkquotationItemItemListAdapter;
    private OnFragmentInteractionListener mListener;

    @Bind(R.id.listView)
    ExpandableHeightListView listView;



    @Bind(R.id.cus_no)
    TextView cus_no;

    @Bind(R.id.qDate)
    TextView qDate;
    @Bind(R.id.validDate)
    TextView validDate;
    @Bind(R.id.moneyType)
    TextView moneyType;
    @Bind(R.id.sal)
    TextView sal;
    @Bind(R.id.memo)
    TextView memo;
    @Bind(R.id.showMore)
    View showMore;
    @Bind(R.id.more_text)
    View more_text;
    @Bind(R.id.qNumber)
    TextView qNumber;

    @Bind(R.id.check)
    public View check;
    @Bind(R.id.overdue)
    public View overdue;


    @Bind(R.id.scrollIndicatorDown)
    HorizontalScrollView horizontalScrollView1;


    private TableData xkQuotationTable;

    private TableData quotationTable;
    private Quotation quotation;


    public QuotationDetailFragment() {
        // Required empty public constructor
    }


    public static QuotationDetailFragment newInstance(Quotation quotation) {
        QuotationDetailFragment fragment = new QuotationDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEM, GsonUtils.toJson(quotation));
        fragment.setArguments(args);
        return fragment;
    }

    public static QuotationDetailFragment newInstance(Bundle args) {
        QuotationDetailFragment fragment = new QuotationDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            try {
                quotation = GsonUtils.fromJson(getArguments().getString(ARG_ITEM), Quotation.class);
            } catch (HdException e) {
                e.printStackTrace();
                ToastHelper.show("参数异常");
                 return;
            }


            xkQuotationTable = TableData.resolveData(getActivity(), R.array.table_head_xkquotation_item);
            quotationTable = TableData.resolveData(getActivity(), R.array.table_head_quotation_item);


            QuoteAuth quoteAuth= SharedPreferencesHelper.getInitData().quoteAuth;

            if(quoteAuth!=null) {

                //根据权限 移除部分字段显示
                if (!quoteAuth.costVisible) {
                    quotationTable.removeField("cost");
                    xkQuotationTable.removeField("cost");
                    xkQuotationTable.removeField("cost2");
                }
                if (!quoteAuth.fobVisible) {
                    quotationTable.removeField("price");
                    xkQuotationTable.removeField("price");
                    xkQuotationTable.removeField("price2");
                }
            }




            quotationItemItemListAdapter = new ItemListAdapter<>(getActivity());
            xkquotationItemItemListAdapter = new ItemListAdapter<>(getActivity());
            quotationItemItemListAdapter.setTableData(quotationTable);
            xkquotationItemItemListAdapter.setTableData(xkQuotationTable);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quotation_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        horizontalScrollView1.setFocusable(true);
        listView.setExpanded(true);

        qNumber.setText(quotation.qNumber);
        qDate.setText(quotation.qDate);
      cus_no.setText(quotation.customerCode+quotation.customerName);
        sal.setText(quotation.salesman);
       validDate.setText(quotation.vDate);
       moneyType.setText(quotation.currency);

        memo.setText(quotation.memo);
        memo.setMaxLines(MAX_MEMO_ROW_LINE);
        memo.post(new Runnable() {
            @Override
            public void run() {


                int count = MAX_MEMO_ROW_LINE;
                boolean more = false;


                if (memo.getLineCount()== MAX_MEMO_ROW_LINE) {




                }


                showMore.setVisibility(more ? View.VISIBLE : View.GONE);
                more_text.setVisibility(more ? View.VISIBLE : View.GONE);


            }
        });


        showMore.setOnClickListener(this);


        if (quotation.quotationTypeName.contains("咸康")) {
            listView.setAdapter(xkquotationItemItemListAdapter);
        } else {
            listView.setAdapter(quotationItemItemListAdapter);
        }


        check.setSelected(quotation.isVerified);
        boolean isOverdueAndNotCheck=!quotation.isVerified &&quotation.isOverdue();
        check.setVisibility(  isOverdueAndNotCheck?View.GONE:View.VISIBLE);
        overdue.setVisibility(  isOverdueAndNotCheck?View.VISIBLE:View.GONE);


        attemptLoad(quotation.id);


    }

    private void attemptLoad() {
        attemptLoad(quotation.id);
    }


    private void attemptLoad(long quotationId) {

        UseCaseFactory.getInstance().createGetQuotationDetail(quotationId).execute(new Subscriber<RemoteData<QuotationDetail>>() {
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
            public void onNext(RemoteData<QuotationDetail> remoteData) {
                if (remoteData.isSuccess()) {

                    quotationItemItemListAdapter.setDataArray(remoteData.datas.get(0).items);
                    xkquotationItemItemListAdapter.setDataArray(remoteData.datas.get(0).XKItems);


                } else {
                    ToastHelper.show(remoteData.message);
                    if (remoteData.code == RemoteData.CODE_UNLOGIN) {
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
