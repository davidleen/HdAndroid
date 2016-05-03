package com.giants3.hd.android.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.Utils;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.helper.ImageViewerHelper;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.utils.StringUtils;
import com.giants3.hd.utils.entity.Quotation;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 表格item adapter
 * Created by david on 2016/2/14.
 */

/**
 *
 */
public class ItemListAdapter<T>
        extends AbstractAdapter< T> {


    private static final int DEFAULT_ROW_HEIGHT = Utils.dp2px(91);

    public TableData tableData;

    private Context context;


    private int rowHeight=DEFAULT_ROW_HEIGHT;

    public ItemListAdapter(Context context) {
        super(context);
        this.context = context;


    }


    /**
     * 设置行高
     * @param valueInDp
     */
    public void setRowHeight(int valueInDp)
    {

        rowHeight=Utils.dp2px(valueInDp);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    protected Bindable<T> createViewHolder(int itemViewType) {

        switch (itemViewType) {
            case 0://表头

                return new HeadViewHolder(generateListHead(tableData));


            case 1:
                return onCreateViewHolder();

        }

        return onCreateViewHolder();
    }


    public ViewHolder onCreateViewHolder() {


        LinearLayout linearLayout = new LinearLayout(getContext());
        ViewHolder viewHolder = new ViewHolder(linearLayout, tableData);
        linearLayout.setGravity(Gravity.CENTER);


        if (tableData != null)
            viewHolder.views = new View[tableData.size];
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

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(tableData.width[i], rowHeight);


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
        Object orderItem = getItem(position);
        holder.mItem = orderItem;
        holder.bind(orderItem);


    }


    @Override
    public T getItem(int position) {

        if (position == 0) return null;
        return super.getItem(position - 1);
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }


    public void setTableData(TableData tableData) {

        this.tableData = tableData;
        notifyDataSetInvalidated();

    }




    public class HeadViewHolder implements Bindable<T> {


        View mView;

        public HeadViewHolder(View view) {

            mView = view;
        }


        @Override
        public void bindData(AbstractAdapter<T> adapter, T data, int position) {

        }

        @Override
        public View getContentView() {
            return mView;
        }
    }


    public class ViewHolder implements Bindable<T> {

        public View[] views;
        public View mView;
        private TableData tableData;
        private Object mItem;

        public ViewHolder(View view, TableData tableData) {


            mView = view;

            this.tableData = tableData;
        }

        public void bind(Object orderItem) {
            mItem = orderItem;


            for (int i = 0; i < tableData.size; i++) {
                Object o = null;
                try {
                    o = orderItem.getClass().getField(tableData.fields[i]).get(orderItem);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                if (tableData.type[i] == TableData.TYPE_IMAGE) {

                    ImageView imageView = (ImageView) views[i];
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    String url =o==null?"": String.valueOf(o);
                    ImageLoader.getInstance().displayImage(HttpUrl.completeUrl(url), imageView);
                    imageView.setTag(url);
                    imageView.setOnClickListener(listener);

                } else {
                    TextView textView = (TextView) views[i];



                    textView.setText(o==null?"":String.valueOf(o));
                }


            }


        }


        @Override
        public void bindData(AbstractAdapter<T> adapter, T data, int position) {
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
            if (!TextUtils.isEmpty(url)) {
                ImageViewerHelper.view(v.getContext(), url);
            }


        }
    };


    private View generateListHead(TableData tableData) {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setGravity(Gravity.CENTER);
        int totalWidth = 0;
        if (tableData != null) {

            for (int i = 0; i < tableData.size; i++) {
                TextView textView = new TextView(context);
                textView.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(tableData.width[i], Utils.dp2px(40));
                textView.setText(tableData.headNames[i]);
                linearLayout.addView(textView, layoutParams);
                totalWidth += tableData.width[i];
            }
        }
        linearLayout.setDividerDrawable(context.getResources().getDrawable(R.drawable.icon_divider));
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        View tempView=new View(context);
//        tempView.setLayoutParams(new AbsListView.LayoutParams(totalWidth, ViewGroup.LayoutParams.WRAP_CONTENT));


        return linearLayout;

    }
}
