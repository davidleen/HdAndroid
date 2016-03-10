package com.giants3.hd.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.Utils;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.fragment.OrderListFragment;
import com.giants3.hd.data.entity.ErpOrder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 表格头部
 * Created by david on 2016/2/14.
 */
public class TableHeadAdapter
        extends AbstractRecyclerAdapter<TableHeadAdapter.ViewHolder, String> {


    public TableData tableData;


    private Context context;
    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


//            ErpOrder order = (ErpOrder) v.getTag();
//
//            Context context = v.getContext();
//            if (context instanceof OrderListFragment.OnFragmentInteractionListener) {
//                ((OrderListFragment.OnFragmentInteractionListener) context).onFragmentInteraction(order);
//            }


        }
    };
    ;


    public TableHeadAdapter(Context context) {
        super(context);
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = new TextView(getContext());
        view.setGravity(Gravity.CENTER);
        view.setMinHeight(Utils.dp2px(40));
        view.setBackgroundResource(R.drawable.list_item_bg_selector);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        final String value = getItem(position);
        ViewGroup.LayoutParams layoutParams = holder.textView.getLayoutParams();
        if (layoutParams == null)
            layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = tableData.width[position];
        holder.textView.setLayoutParams(layoutParams);


        holder.textView.setText(getItem(position));
        holder.textView.setOnClickListener(itemClickListener);

    }

    public void setTableData(TableData tableData) {

        this.tableData = tableData;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView;

        public String item;

        public ViewHolder(View view) {
            super(view);
            // ButterKnife.bind(this, view);
            textView = (TextView) view;
            ;
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}
