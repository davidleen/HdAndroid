package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.entity.Quotation;

import butterknife.Bind;

;


/**
 * 报价列表
 * Created by david on 2016/2/14.
 */
public class QuotationListAdapter
        extends AbstractAdapter<Quotation> {


    private Context context;


    public QuotationListAdapter(Context context) {
        super(context);
        this.context = context;


    }

    @Override
    protected Bindable<Quotation> createViewHolder(int itemViewType) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.list_item_quotation, null, false);
        return new ViewHolder(view);
    }


    public static class ViewHolder extends AbstractViewHolder<Quotation> {

        @Bind(R.id.qNumber)
        public TextView qNumber;
        @Bind(R.id.customer)
        public TextView customer;
        @Bind(R.id.saleman)
        public TextView saleman;
        @Bind(R.id.qDate)
        public TextView qDate;
        @Bind(R.id.type)
        public TextView type;

        @Bind(R.id.check)
        public View check;
        @Bind(R.id.overdue)
        public View overdue;

        public com.giants3.hd.entity.app.Quotation mItem;

        public ViewHolder(View view) {
            super(view);
        }


        @Override
        public void bindData(AbstractAdapter<Quotation> adapter, Quotation data, int position) {
            qNumber.setText(data.qNumber);
            qDate.setText(data.qDate);
            saleman.setText(data.salesman);
            customer.setText(data.customerName);
            type.setText(data.quotationTypeName);
            check.setSelected(data.isVerified);
            boolean isOverdueAndNotCheck = !data.isVerified && data.isOverdue();
            check.setVisibility(isOverdueAndNotCheck ? View.GONE : View.VISIBLE);
            overdue.setVisibility(isOverdueAndNotCheck ? View.VISIBLE : View.GONE);
        }


    }
}
