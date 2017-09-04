package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.entity.ErpOrder;

/**
 * Created by david on 2016/2/14.
 */
public class OrderListAdapter
        extends AbstractAdapter<ErpOrder> {






    public OrderListAdapter(Context context) {
        super(context);


    }

    @Override
    protected Bindable<ErpOrder> createViewHolder(int itemViewType) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.list_item_order, null, false);
        return new ViewHolder(view);
    }






    public class ViewHolder  implements Bindable<ErpOrder> {
        public final View mView;
        public final TextView os_no;
        public final TextView os_dd;
        public final TextView cus_no;
        public final TextView cus_os_no;
        public final TextView sal;

        public ErpOrder mItem;

        public ViewHolder(View view) {


            mView = view;
            os_no = (TextView) view.findViewById(R.id.os_no);
            os_dd = (TextView) view.findViewById(R.id.os_dd);
            cus_os_no = (TextView) view.findViewById(R.id.cus_os_no);
            cus_no = (TextView) view.findViewById(R.id.cus_no);
            sal = (TextView) view.findViewById(R.id.sal);

        }

        @Override
        public void bindData(AbstractAdapter<ErpOrder> adapter, ErpOrder data, int position) {

            final ErpOrder order = getItem
                    (position);
            mItem = order;
            cus_no.setText(order.cus_no);
            os_no.setText(order.os_no);
            os_dd.setText(order.os_dd);
            cus_os_no.setText(order.cus_os_no);
            sal.setText(order.sal_name==null?"":order.sal_name );

            mView.setBackgroundResource(R.drawable.list_item_bg_selector);
//            mView.setOnClickListener(
//                    itemClickListener);
        }

        @Override
        public View getContentView() {
            return mView;
        }

    }
}
