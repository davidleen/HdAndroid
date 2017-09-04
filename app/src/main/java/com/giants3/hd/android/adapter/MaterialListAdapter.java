package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.helper.ImageLoaderFactory;
import com.giants3.hd.android.helper.ImageViewerHelper;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.entity.Material;

import butterknife.Bind;

/**
 * 材料列表适配
 * Created by david on 2016/2/14.
 */
public class MaterialListAdapter
        extends AbstractAdapter<Material> {


//    private View.OnClickListener itemClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            ViewHolder viewHolder = (ViewHolder) v.getTag();
//
//            Context context = v.getContext();
//            if (context instanceof MaterialListFragment.OnFragmentInteractionListener) {
//                ((MaterialListFragment.OnFragmentInteractionListener) context).onFragmentInteraction(viewHolder.mItem);
//            }
//
//
//        }
//    };


    public MaterialListAdapter(Context context) {
        super(context);


    }

    @Override
    protected Bindable<Material> createViewHolder(int itemViewType) {
        return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.list_item_material, null));
    }


    public class ViewHolder extends AbstractViewHolder<Material> {
        public Material mItem;


        @Bind(R.id.materialCode)
        public TextView materialCode;
        @Bind(R.id.picture)
        public ImageView picture;
        @Bind(R.id.materialName)
        public TextView materialName;
        @Bind(R.id.materialType)
        public TextView materialType;
        @Bind(R.id.unit)
        public TextView unit;

        public ViewHolder(View view) {
            super(view);

        }


        @Override
        public void bindData(AbstractAdapter<Material> adapter, Material data, int position) {

            mItem = data;
            materialCode.setText(data.code);
            materialName.setText(data.name);
            materialType.setText(data.className);
            unit.setText(data.unitName);
            ImageLoaderFactory.getInstance().displayImage(HttpUrl.completeUrl(data.url), picture);
//            v.setOnClickListener(itemClickListener);
        }

        @Override
        public View getContentView() {
            return v;
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
