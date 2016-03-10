package com.giants3.hd.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.Utils;
import com.giants3.hd.android.activity.PictureViewActivity;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.data.entity.ErpOrderItem;
import com.giants3.hd.data.net.HttpUrl;
import com.squareup.picasso.Picasso;

/**
 * 订单货款item adapter
 * Created by david on 2016/2/14.
 */
public class OrderItemListAdapter
        extends AbstractAdapter< ErpOrderItem> {


    private static final int DEFAULT_ROW_HEIGHT = Utils.dp2px(91);

    public TableData tableData;
    Picasso picasso;
    private Context context;


    public OrderItemListAdapter(Context context) {
        super(context);
        this.context = context;
        picasso = Picasso.with(context);
        picasso.setLoggingEnabled(true);
    }

    @Override
    protected Bindable<ErpOrderItem> createViewHolder(int itemViewType) {
        return onCreateViewHolder();
    }



    public ViewHolder onCreateViewHolder( ) {



        LinearLayout linearLayout = new LinearLayout(getContext());
        ViewHolder viewHolder = new ViewHolder(linearLayout, tableData);
        linearLayout.setGravity(Gravity.CENTER);

        viewHolder.views = new View[tableData.size];
        if (tableData != null)
            for (int i = 0; i < tableData.size; i++) {


                View v;
                if (TableData.TYPE_IMAGE == tableData.type[i]) {

                    ImageView imageView = new ImageView(getContext());
                    imageView.setImageResource(R.mipmap.icon_photo);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    v = imageView;
                } else {
                    TextView textView = new TextView(getContext());

                    textView.setGravity(Gravity.CENTER);

                    v = textView;
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(tableData.width[i], DEFAULT_ROW_HEIGHT);


                viewHolder.views[i] = v;


                linearLayout.addView(v, layoutParams);
            }
        linearLayout.setDividerDrawable(context.getResources().getDrawable(R.drawable.icon_divider));
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setBackgroundResource(R.drawable.list_item_bg_selector);

        linearLayout.setClickable(true);

        return viewHolder;
    }


    public void onBindViewHolder(final ViewHolder holder, int position) {
        ErpOrderItem orderItem = getItem(position);
        holder.mItem = orderItem;
        holder.bind(orderItem);

        //holder.cus_os_no.setText(order.cus_os_no);
//        holder.est_dd.setText(order.est_dd);
//        holder.so_data.setText(order.so_data);


    }

    public void setTableData(TableData tableData) {

        this.tableData = tableData;


    }


    public class ViewHolder   implements Bindable<ErpOrderItem> {

        public View[] views;
        public View mView;
        private TableData tableData;
        private ErpOrderItem mItem;

        public ViewHolder(View view, TableData tableData) {


            mView = view;

            this.tableData = tableData;
        }

        public void bind(ErpOrderItem orderItem) {
            mItem = orderItem;


            for (int i = 0; i < tableData.size; i++) {
                Object o = null;
                try {
                    o = ErpOrderItem.class.getField(tableData.fields[i]).get(orderItem);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                if (tableData.type[i] == TableData.TYPE_IMAGE) {

                    ImageView imageView = (ImageView) views[i];
                    String url = String.valueOf(o);
                    picasso.load(HttpUrl.completeUrl(url)).resize(tableData.width[i],DEFAULT_ROW_HEIGHT).centerCrop( ).placeholder(R.mipmap.ic_launcher).into(imageView);
                    imageView.setTag(url);
                    imageView.setOnClickListener(listener);


                } else {
                    TextView textView = (TextView) views[i];

                    textView.setText(String.valueOf(o));
                }


            }


        }


        @Override
        public void bindData(AbstractAdapter<ErpOrderItem> adapter, ErpOrderItem data, int position) {
            bind(data);
        }

        @Override
        public View getContentView() {
            return mView;
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String url = (String) v.getTag();
            Intent intent = new Intent(v.getContext(), PictureViewActivity.class);
            intent.putExtra(PictureViewActivity.EXTRA_URL, url);
            v.getContext().startActivity(intent);


        }
    };
}
