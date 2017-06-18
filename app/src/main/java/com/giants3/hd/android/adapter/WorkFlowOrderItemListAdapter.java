package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.utils.entity_erp.ErpWorkFlowOrderItem;

import butterknife.Bind;

/**
 * Created by david on 2016/2/14.
 */
public class WorkFlowOrderItemListAdapter
        extends AbstractAdapter<ErpWorkFlowOrderItem> {


    public WorkFlowOrderItemListAdapter(Context context) {
        super(context);


    }

    @Override
    protected Bindable<ErpWorkFlowOrderItem> createViewHolder(int itemViewType) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.list_item_work_flow_order_item, null, false);
        return new ViewHolder(view);
    }


    public class ViewHolder extends BaseBindable<ErpWorkFlowOrderItem> {


        @Bind(R.id.os_no)
        public   TextView os_no;
        @Bind(R.id.productName)
        public   TextView productName;
        @Bind(R.id.pversion)
        public   TextView pversion;
        @Bind(R.id.workflow)
        public   TextView workflow;



        public ErpWorkFlowOrderItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView.setBackgroundResource(R.drawable.list_item_bg_selector);
        }

        @Override
        public void bindData(AbstractAdapter<ErpWorkFlowOrderItem> adapter, ErpWorkFlowOrderItem data, int position) {


            mItem = data;

            os_no.setText(data.os_no);
            productName.setText(data.prd_name);
            pversion.setText(data.pversion);
            workflow.setText(data.workFlowDescribe);



        }



    }
}
