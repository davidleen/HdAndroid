package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.entity.CustomFieldData;
import com.giants3.hd.android.helper.ImageViewerHelper;

/**
 * Created by david on 2016/2/14.
 */
public class ProductItemAdapter
        extends AbstractAdapter<CustomFieldData> {




    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ViewHolder viewHolder = (ViewHolder) v.getTag();

            Context context = v.getContext();
//            if (context instanceof ProductListFragment.OnFragmentInteractionListener) {
//                ((ProductListFragment.OnFragmentInteractionListener) context).onFragmentInteraction(viewHolder.mItem);
//            }


        }
    };


    public ProductItemAdapter(Context context) {
        super(context);

        CustomFieldData fieldData=new CustomFieldData();
        fieldData.fieldCName="单位";




    }

    @Override
    protected Bindable<CustomFieldData> createViewHolder(int itemViewType) {
        return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.layout_product_item, null));
    }


    public class ViewHolder implements Bindable<CustomFieldData> {
        public final View mView;
        public final TextView title;
        public final TextView name;
        public final ImageView edit;
        public CustomFieldData mItem;

        public ViewHolder(View view) {

            // ButterKnife.bind(this, view);
            mView = view;
            title = (TextView) view.findViewById(R.id.title);
            name = (TextView) view.findViewById(R.id.name);
            edit = (ImageView) view.findViewById(R.id.edit);
        }


        @Override
        public void bindData(AbstractAdapter<CustomFieldData> adapter, CustomFieldData data, int position) {
            CustomFieldData aProduct = data;
            mItem = aProduct;
            title.setText(data.fieldCName);
            name.setText(data.getValue());

            mView.setBackgroundResource(R.drawable.list_item_bg_selector);
            mView.setOnClickListener(itemClickListener);
        }

        @Override
        public View getContentView() {
            return mView;
        }
    }


    private View.OnClickListener imageClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String url= (String) v.getTag();
            ImageViewerHelper.view(v.getContext(),url);

        }
    };
}
