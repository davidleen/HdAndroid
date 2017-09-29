package com.giants3.hd.android.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.helper.AndroidUtils;
import com.giants3.hd.android.helper.ImageLoaderFactory;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.android.mvp.workFlow.WorkFlowSendMvp;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.ErpOrderItemProcess;
import com.giants3.hd.entity.WorkFlowArea;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.StringUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;

/**
 * 发送流程fragment
 */
public class SendWorkFlowFragment extends BaseDialogFragment<WorkFlowSendMvp.Presenter> implements WorkFlowSendMvp.Viewer {

    private static final String ARG_AVAILABLE_ITEMS = "ARG_AVAILABLE_ITEMS";

    @Bind(R.id.sendPanel)
    View sendPanel;

    @Bind(R.id.panel_select_item)
    View panel_select_item;
    @Bind(R.id.order_item_name)
    TextView order_item_name;
    @Bind(R.id.orderItemQty)
    TextView orderItemQty;
    @Bind(R.id.orderItemTranQty)
    TextView orderItemTranQty;
    @Bind(R.id.currentWorkFlow)
    TextView currentWorkFlow;
    @Bind(R.id.destWorkFlow)
    TextView destWorkFlow;
    @Bind(R.id.sendQty)
    EditText sendQty;
    @Bind(R.id.panel_factory)
    View panel_factory;
    @Bind(R.id.factory)
    TextView factory;
    @Bind(R.id.produceType)
    TextView produceType;
    @Bind(R.id.panel_picture)
    View panel_picture;

    @Bind(R.id.panel_subtype)
    View panel_subtype;


    @Bind(R.id.picture)
    ImageView picture;

    @Bind(R.id.submitFlow)
    View submitFlow;

    @Bind(R.id.panel_area)
    View panel_area;
    @Bind(R.id.area)
    TextView area;

    @Bind(R.id.memo)
    EditText memo;


    List<ErpOrderItemProcess> orderItemProcesses;

    private OnFragmentInteractionListener mListener;

    public SendWorkFlowFragment() {

    }


    public static SendWorkFlowFragment newInstance(List<ErpOrderItemProcess> workFlowStates) {
        SendWorkFlowFragment fragment = new SendWorkFlowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_AVAILABLE_ITEMS, GsonUtils.toJson(workFlowStates));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected WorkFlowSendMvp.Presenter onLoadPresenter() {
        return new com.giants3.hd.android.mvp.workFlow.WorkFlowSendPresenter();
    }

    @Override
    protected void initEventAndData() {


    }



    @Override
    protected void initViews(Bundle savedInstanceState) {
        panel_select_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getPresenter().pickOrderItem();

            }
        });


        submitFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getPresenter().sendWorkFlow();
            }
        });



        panel_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().pickWorkFlowArea();
            }
        });
        memo.addTextChangedListener(memoTExtWatcher);

        sendQty.post(new Runnable() {
            @Override
            public void run() {

                AndroidUtils.hideKeyboard(sendQty);

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


            Type typeToken = new TypeToken<List<ErpOrderItemProcess>>() {
            }.getType();
            try {
                orderItemProcesses = GsonUtils.fromJson(getArguments().getString(ARG_AVAILABLE_ITEMS, ""), typeToken);
            } catch (HdException e) {
                e.printStackTrace();
            }

            getPresenter().setInitDta(orderItemProcesses);

        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_send_work_flow, container, false);
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


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);

        void onWorkFlowSend();
    }


    @Override
    public void doPickItem(ErpOrderItemProcess lastPickItem, List<ErpOrderItemProcess> availableItems) {


        ItemPickDialogFragment<ErpOrderItemProcess> dialogFragment = new ItemPickDialogFragment<ErpOrderItemProcess>();
        dialogFragment.set("订单货款选择", availableItems, lastPickItem, new ItemPickDialogFragment.ValueChangeListener<ErpOrderItemProcess>() {
            @Override
            public void onValueChange(String title, ErpOrderItemProcess oldValue, ErpOrderItemProcess newValue) {


                getPresenter().setPickItem(newValue);


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);

    }


    @Override
    public void doPickItem(WorkFlowArea workFlowArea,List<WorkFlowArea> areas)
    {
        ItemPickDialogFragment<WorkFlowArea> dialogFragment = new ItemPickDialogFragment<WorkFlowArea>();
        dialogFragment.set("交接区域选择",areas,workFlowArea, new ItemPickDialogFragment.ValueChangeListener<WorkFlowArea>() {
            @Override
            public void onValueChange(String title, WorkFlowArea oldValue, WorkFlowArea newValue) {


                getPresenter().setPickItem(newValue);


            }
        });
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }


    @Override
    public void bindPickItem(ErpOrderItemProcess erpOrderItemWorkFlowState) {

        order_item_name.setText(erpOrderItemWorkFlowState.mrpNo );
        orderItemQty.setText(String.valueOf(erpOrderItemWorkFlowState.qty));
        orderItemTranQty.setText(String.valueOf(erpOrderItemWorkFlowState.unSendQty));
        currentWorkFlow.setText( erpOrderItemWorkFlowState.currentWorkFlowCode+erpOrderItemWorkFlowState.currentWorkFlowName );


        destWorkFlow.setText(erpOrderItemWorkFlowState.nextWorkFlowCode+erpOrderItemWorkFlowState.nextWorkFlowName);


        factory.setText(erpOrderItemWorkFlowState.jgh);



        String
                uri=   StringUtils.isEmpty(erpOrderItemWorkFlowState.photoThumb)? HttpUrl.completeUrl(erpOrderItemWorkFlowState.photoUrl): HttpUrl.completeUrl(erpOrderItemWorkFlowState.photoThumb);
        ImageLoaderFactory.getInstance().displayImage(uri, picture);
        getPresenter().updateQty(erpOrderItemWorkFlowState.unSendQty);


    }

    /**
     * 数量输入改变监听
     */
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            int qty = 0;
            try {
                qty = Integer.valueOf(s.toString().trim());
            } catch (Throwable t) {
                warnQtyInput(s + "数量输入不合法,必须 整数 ");
                return;
            }
            if (qty < 0) {
                warnQtyInput(s + "数量输入不合法,必须 > 0");
                return;
            }

            getPresenter().updateQty(qty);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     *备注输入改变监听
     */
    private TextWatcher memoTExtWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {




            getPresenter().updateMemo(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
///**
//     *备注输入改变监听
//     */
//    private TextWatcher areaTextWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//
//
//
//            getPresenter().updateArea(s.toString());
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };


    public void warnQtyInput(String message
    ) {
        ToastHelper.show(message);
        sendQty.requestFocus();


    }




    @Override
    public void updateSendQty(int qty) {
        sendQty.removeTextChangedListener(watcher);
        String newText = qty == 0 ? "" : String.valueOf(qty);
        sendQty.setText(newText);
        sendQty.setSelection(newText.length());
        sendQty.addTextChangedListener(watcher);

    }


    @Override
    public void doOnSuccessSend() {

        dismiss();
        if (mListener != null)
            mListener.onWorkFlowSend();

    }

    @Override
    public void bindPickItem(WorkFlowArea newValue) {


        area.setText(newValue.code+newValue.name);
    }
}
