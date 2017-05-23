package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.fragment.WorkFlowMessageFragment;
import com.giants3.hd.android.helper.ImageLoaderFactory;
import com.giants3.hd.android.helper.ImageViewerHelper;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.entity.WorkFlowMessage;

import butterknife.Bind;

/**
 * 流程处理
 * Created by david on 20160919
 */
public class WorkFlowMessageAdapter
        extends AbstractAdapter<WorkFlowMessage> implements View.OnClickListener {


    private final WorkFlowMessageFragment.MessageHandler messageHandler;

    public WorkFlowMessageAdapter(Context context, WorkFlowMessageFragment.MessageHandler MessageHandler) {
        super(context);


        messageHandler = MessageHandler;
    }

    @Override
    protected Bindable<WorkFlowMessage> createViewHolder(int itemViewType) {
        return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.list_item_work_flow_message, null), this);
    }

    @Override
    public void onClick(View v) {

        WorkFlowMessage message = (WorkFlowMessage) v.getTag();
        switch (v.getId()) {
            case R.id.picture:

                ImageViewerHelper.view(v.getContext(), message.url);

                break;
            case R.id.check:

                messageHandler.checkWorkFlow(message);


                break;
            case R.id.receive:
                messageHandler.receiveWorkFlow(message);

                break;
        }

    }


    public static class ViewHolder extends AbstractViewHolder<WorkFlowMessage> {
        public WorkFlowMessage mItem;
        @Bind(R.id.picture)
        public ImageView picture;

        @Bind(R.id.fromFlow)
        public TextView fromFlow;

        @Bind(R.id.toFlow)
        public TextView toFlow;
        @Bind(R.id.tranQty)
        public TextView tranQty;

        @Bind(R.id.name)
        public TextView name;
        @Bind(R.id.orderName)
        public TextView orderName;
        @Bind(R.id.productName)
        public TextView productName;
        @Bind(R.id.mrpNo)
        public TextView mrpNo;
        @Bind(R.id.qty)
        public TextView qty;

        @Bind(R.id.unitName)
        public TextView unitName;

        @Bind(R.id.receive)
        public TextView receive;

        @Bind(R.id.check)
        public TextView check;
        @Bind(R.id.createTime)
        public TextView createTime;
        @Bind(R.id.state)
        public TextView state;
        @Bind(R.id.panel_reason)
        public View panel_reason;
        @Bind(R.id.reason)
        public TextView reason;



        @Bind(R.id.panel_factory)
        public View panel_factory;
        @Bind(R.id.factory)
        public TextView factory;

        public ViewHolder(View view, View.OnClickListener listener) {
            super(view);
            picture.setOnClickListener(listener);
            receive.setOnClickListener(listener);
            check.setOnClickListener(listener);

        }


        @Override
        public void bindData(AbstractAdapter<WorkFlowMessage> adapter, WorkFlowMessage data, int position) {

            mItem = data;
            name.setText(data.name);
            orderName.setText(data.orderName);
            qty.setText(String.valueOf(data.orderItemQty));
            productName.setText(data.productName);
            toFlow.setText(data.toFlowName);
            fromFlow.setText(data.fromFlowName);
            tranQty.setText(String.valueOf(data.transportQty));
            factory.setText(String.valueOf(data.factoryName));
            mrpNo.setText( data.mrpNo ==null?"":data.mrpNo);

            panel_factory.setVisibility(StringUtils.isEmpty(data.factoryName)?View.GONE:View.VISIBLE);


            ImageLoaderFactory.getInstance().displayImage(HttpUrl.completeUrl(data.url), picture);
            picture.setTag(data);
            unitName.setText("");
            check.setVisibility(data.state == WorkFlowMessage.STATE_RECEIVE ? View.VISIBLE : View.GONE);
            receive.setVisibility(data.state == WorkFlowMessage.STATE_REWORK || data.state == WorkFlowMessage.STATE_SEND ? View.VISIBLE : View.GONE);

            String stateText = "";
            switch (data.state) {
                case WorkFlowMessage.STATE_SEND:
                    stateText = "待接收";
                    break;
                case WorkFlowMessage.STATE_RECEIVE:
                    stateText = "待审核";
                    break;
                case WorkFlowMessage.STATE_REWORK:
                    stateText = "返工";
                    break;
                case WorkFlowMessage.STATE_REJECT:
                    stateText = "审核未通过";
                    break;
                case WorkFlowMessage.STATE_PASS:
                    stateText = "已通过";
                    break;

            }
            boolean  showReason= !StringUtils.isEmpty(data.memo);
            panel_reason.setVisibility(showReason?View.VISIBLE:View.GONE);
            if(showReason)
            {
                reason.setText(data.memo);
            }
            state.setText(stateText);
            createTime.setText(data.createTimeString.substring(0, 10));
            check.setTag(data);
            receive.setTag(data);

        }

        @Override
        public View getContentView() {
            return v;
        }


    }


}
