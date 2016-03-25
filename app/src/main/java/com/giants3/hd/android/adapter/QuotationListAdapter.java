package com.giants3.hd.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.activity.ProductDetailActivity;
import com.giants3.hd.android.fragment.ProductDetailFragment;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.appdata.AQuotation;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.data.utils.StringUtils;


import java.util.List;

/**
 * Created by david on 2016/2/14.
 */
public class QuotationListAdapter
        extends RecyclerView.Adapter<QuotationListAdapter.ViewHolder> {

    private List<AQuotation> mValues;

    private Context context;


    public QuotationListAdapter(Context context, List<AQuotation> items) {
        this.context = context;
        mValues = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.productlistactivity_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        AQuotation aQuotation = mValues.get(position);
        holder.mItem = aQuotation;
        holder.mIdView.setText(aQuotation.name);
        holder.mContentView.setText(aQuotation.salesMan);

//        if (!StringUtils.isEmpty(aQuotation.url)) {
//            picasso.load(HttpUrl.completeUrl(aQuotation.url)).into(holder.image);
//        } else
//            picasso.load(R.mipmap.ic_launcher).into(holder.image);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Context context = v.getContext();
//                Intent intent = new Intent(context, ProductDetailActivity.class);
//                intent.putExtra(ProductDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//
//                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setData(List<AQuotation> data) {
        this.mValues = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView image;
        public AQuotation mItem;

        public ViewHolder(View view) {
            super(view);
            // ButterKnife.bind(this, view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            image = (ImageView) view.findViewById(R.id.image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
