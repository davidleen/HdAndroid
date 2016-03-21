package com.giants3.hd.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.fragment.ProductListFragment;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.data.utils.StringUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by david on 2016/2/14.
 */
public class ProductListAdapter
        extends AbstractAdapter<AProduct> {


    Picasso picasso;

    private View.OnClickListener itemClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ViewHolder  viewHolder = (ViewHolder) v.getTag();

            Context context = v.getContext();
            if (context instanceof ProductListFragment.OnFragmentInteractionListener) {
                ((ProductListFragment.OnFragmentInteractionListener) context).onFragmentInteraction(viewHolder.mItem);
            }


        }
    };


    public ProductListAdapter(Context context ) {
        super(context);


        picasso = Picasso.with(context);
        picasso.setLoggingEnabled(true);
    }

    @Override
    protected Bindable<AProduct> createViewHolder(int itemViewType) {
        return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.productlistactivity_list_content,null));
    }







    public class ViewHolder   implements Bindable<AProduct>{
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView image;
        public AProduct mItem;

        public ViewHolder(View view) {

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

        @Override
        public void bindData(AbstractAdapter<AProduct> adapter, AProduct data, int position) {
            AProduct aProduct = data;
            mItem = aProduct;
            mIdView.setText(aProduct.name);
            mContentView.setText(aProduct.pVersion);
            picasso.load(HttpUrl.completeUrl(aProduct.url)).placeholder(R.mipmap.ic_launcher).into(image);
            mView.setBackgroundResource(R.drawable.list_item_bg_selector);
            mView.setOnClickListener(itemClickListener);
        }

        @Override
        public View getContentView() {
            return mView;
        }
    }
}