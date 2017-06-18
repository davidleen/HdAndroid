package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.utils.entity.ErpOrder;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.OrderItem;

import butterknife.Bind;

/**
 * Created by david on 2016/2/14.
 */
public class OrderItemListAdapter
        extends AbstractAdapter<ErpOrderItem> {






    public OrderItemListAdapter(Context context) {
        super(context);


    }

    @Override
    protected Bindable<ErpOrderItem> createViewHolder(int itemViewType) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.list_item_order_item, null, false);
        return new ViewHolder(view);
    }






    public class ViewHolder  extends BaseBindable<ErpOrderItem> {

        @Bind(R.id.os_no)
        public   TextView os_no;
        @Bind(R.id.productName)
        public   TextView productName;
        @Bind(R.id.pVersion)
        public   TextView pVersion;
        public ErpOrderItem mItem;

        public ViewHolder(View view) {
            super(view);

        }

        @Override
        public void bindData(AbstractAdapter<ErpOrderItem> adapter, ErpOrderItem data, int position) {


            mItem = data;

            os_no.setText(data.os_no);
            productName.setText(data.prd_name);
            pVersion.setText(data.pVersion);
            mView.setBackgroundResource(R.drawable.list_item_bg_selector);
        }



    }
}
