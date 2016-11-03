package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.helper.ImageLoaderFactory;
import com.giants3.hd.android.helper.ImageViewerHelper;
import com.giants3.hd.appdata.AProduct;
import com.giants3.hd.data.net.HttpUrl;

/**
 * Created by david on 2016/2/14.
 */
public class ProductListAdapter
        extends AbstractAdapter<AProduct> {


//    private View.OnClickListener itemClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            ViewHolder viewHolder = (ViewHolder) v.getTag();
//
//            Context context = v.getContext();
//            if (context instanceof ProductListFragment.OnFragmentInteractionListener) {
//                ((ProductListFragment.OnFragmentInteractionListener) context).onFragmentInteraction(viewHolder.mItem);
//            }
//
//
//        }
//    };


    public ProductListAdapter(Context context) {
        super(context);


    }

    @Override
    protected Bindable<AProduct> createViewHolder(int itemViewType) {
        return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.product_list_content, null));
    }


    public class ViewHolder implements Bindable<AProduct> {
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
            ImageLoaderFactory.getInstance().displayImage(HttpUrl.completeUrl(aProduct.thumbnail), image);
            image.setTag(aProduct.url);
            image.setOnClickListener(imageClickListener);
            mView.setBackgroundResource(R.drawable.list_item_bg_selector);
        }

        @Override
        public View getContentView() {
            return mView;
        }
    }


    private View.OnClickListener imageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String url = (String) v.getTag();
            ImageViewerHelper.view(v.getContext(), url);

        }
    };
}
