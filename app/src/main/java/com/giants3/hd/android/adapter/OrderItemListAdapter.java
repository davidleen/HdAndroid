package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.utils.entity.ErpOrder;
import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.OrderItem;

/**
 * Created by david on 2016/2/14.
 */
public class OrderItemListAdapter
        extends AbstractAdapter<OrderItem> {






    public OrderItemListAdapter(Context context) {
        super(context);


    }

    @Override
    protected Bindable<OrderItem> createViewHolder(int itemViewType) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.list_item_order_item, null, false);
        return new ViewHolder(view);
    }






    public class ViewHolder  implements Bindable<OrderItem> {
        public final View mView;
        public final TextView os_no;

        public final TextView productName;
        public final TextView pVersion;

        public OrderItem mItem;

        public ViewHolder(View view) {


            mView = view;
            os_no = (TextView) view.findViewById(R.id.os_no);
            productName = (TextView) view.findViewById(R.id.productName);
            pVersion = (TextView) view.findViewById(R.id.pVersion);


        }

        @Override
        public void bindData(AbstractAdapter<OrderItem> adapter, OrderItem data, int position) {


            mItem = data;

            os_no.setText(data.osNo);
            productName.setText(data.prdNo);
            pVersion.setText(data.pVersion);

            mView.setBackgroundResource(R.drawable.list_item_bg_selector);
        }

        @Override
        public View getContentView() {
            return mView;
        }

    }
}
