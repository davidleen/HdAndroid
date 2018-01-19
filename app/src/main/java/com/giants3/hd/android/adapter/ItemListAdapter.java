package com.giants3.hd.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.Utils;
import com.giants3.hd.android.activity.LongTextActivity;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.android.helper.ImageLoaderFactory;
import com.giants3.hd.android.helper.ImageViewerHelper;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.utils.StringUtils;

/**
 * 表格item adapter
 * <p>
 * 根据配置好的结构数据  自动构建adapteritem
 * Created by david on 2016/2/14.
 */

/**
 *
 */
public class ItemListAdapter<T>
        extends AbstractAdapter<T> {


    private static final int DEFAULT_ROW_HEIGHT = Utils.dp2px(91);
    public static final int MAXLINES = 5;

    public TableData tableData;

    private Context context;


    private int selectedPosition = -1;


    private int rowHeight = DEFAULT_ROW_HEIGHT;

    public ItemListAdapter(Context context) {
        super(context);
        this.context = context;


    }


    /**
     * 设置行高
     *
     * @param valueInDp
     */
    public void setRowHeight(int valueInDp) {

        rowHeight = Utils.dp2px(valueInDp);
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


        LinearLayout convertView = new LinearLayout(context);
        convertView.setBackgroundResource(R.drawable.list_item_bg_selector);
        LinearLayout linearLayout = new LinearLayout(getContext());
        convertView.addView(linearLayout);
        ViewHolder viewHolder = new ViewHolder(convertView, tableData);
        linearLayout.setGravity(Gravity.LEFT|Gravity.CENTER);
        viewHolder.contentView = linearLayout;

        if (tableData != null)
            viewHolder.views = new View[tableData.size];

        for (int i = 0; i < tableData.size; i++) {


            View v;
            if (TableData.TYPE_IMAGE == tableData.type.get(i)) {

                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(R.mipmap.icon_photo);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                v = imageView;
            } else {
                TextView textView = new TextView(getContext());
                textView.setGravity(Gravity.CENTER);
                textView.setMaxLines(MAXLINES);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                v = textView;

                if (TableData.TYPE_LONG_TEXT == tableData.type.get(i)) {
                    textView.setOnClickListener(longTextViewClickListener);
                }
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(tableData.width.get(i), rowHeight);

            viewHolder.views[i] = v;

            linearLayout.addView(v, layoutParams);
        }
        linearLayout.setDividerDrawable(context.getResources().getDrawable(R.drawable.icon_divider));
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);


        return viewHolder;
    }


    public void onBindViewHolder(final ViewHolder holder, int position) {
        T orderItem = getItem(position);
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

        public View contentView;

        public ViewHolder(View view, TableData tableData) {


            mView = view;
            this.tableData = tableData;
        }

        public void bind(T orderItem) {
            mItem = orderItem;


            for (int i = 0; i < tableData.size; i++) {


                String field = tableData.fields.get(i);
                //判断是否有自定义实现
                if(bindFieldData(views[i],orderItem,field))
                {
                    continue;
                }

                Object o = getData(field, orderItem);
//                try {
//                    o = orderItem.getClass().getField(tableData.fields[i]).get(orderItem);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                }
                if (tableData.type.get(i) == TableData.TYPE_IMAGE) {

                    ImageView imageView = (ImageView) views[i];
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    String url = o == null ? "" : String.valueOf(o);
                    ImageLoaderFactory.getInstance().displayImage(HttpUrl.completeUrl(url), imageView);
                    String viewPictureUrl = url;
                    if (!StringUtils.isEmpty(tableData.relateField.get(i))) {
                        Object relateValue = getData(tableData.relateField.get(i), orderItem);
                        if (relateValue != null && !StringUtils.isEmpty(relateValue.toString())) {
                            viewPictureUrl = relateValue.toString();
                        }

                    }
                    imageView.setTag(viewPictureUrl);
                    imageView.setOnClickListener(imageViewClickListener);

                } else
                    //序号列 处理
                    if (tableData.type.get(i) == TableData.TYPE_INDEX) {
                        TextView textView = (TextView) views[i];
                        if (o instanceof Integer) {
                            int index = ((Integer) o).intValue();
                            textView.setText("" + (index + 1));
                        }
                    } else {
                        TextView textView = (TextView) views[i];
                        String stringValue = "";
                        if (o != null) {
                            if (o instanceof Number) {
                                Number d = (Number) o;


                                if (Math.abs(d.doubleValue()) < 0.001)
                                    stringValue = "";
                                else
                                    stringValue = d.toString();
                            } else {
                                stringValue = String.valueOf(o);
                            }
                        }
                        textView.setText(stringValue);
                        textView.setTag(tableData.headNames.get(i));

                    }


            }


        }


        @Override
        public void bindData(final AbstractAdapter<T> adapter, T data, final int position) {
            bind(data);
            contentView.setBackgroundColor(adapter.getSelectedPosition() == position ? Color.GRAY : Color.TRANSPARENT);

        }

        @Override
        public View getContentView() {
            return mView;
        }


    }


    /**
     * 自定义数据绑定， 子类可以实现 未实现，默认使用反射处理。
     * @param data
     * @param field
     * @return
     */
    protected boolean bindFieldData(View view, T data, String field)
    {

        return false;
    }
    /**
     * 通过反射获取数据
     *
     * @param field
     * @param object
     * @return
     */
    public Object getData(String field, T object) {

        try {
            return object.getClass().getField(field).get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    private View.OnClickListener imageViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String url = (String) v.getTag();
            if (!TextUtils.isEmpty(url)) {
                ImageViewerHelper.view(v.getContext(), url);
            }


        }
    };


    /**
     * 长文本型的字段点击事件处理。
     */
    private View.OnClickListener longTextViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v instanceof TextView) {

                TextView textView = (TextView) v;


                if (textView.getLineCount() >= MAXLINES) {

                    String name = (String) v.getTag();
                    Intent intent = new Intent(v.getContext(), LongTextActivity.class);
                    intent.putExtra(LongTextActivity.PARAM_TITLE, name);
                    intent.putExtra(LongTextActivity.PARAM_CONTENT, textView.getText().toString());
                    v.getContext().startActivity(intent);


                }
            }


        }
    };


    private View generateListHead(TableData tableData) {

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setGravity(Gravity.LEFT|Gravity.CENTER);
        int totalWidth = 0;
        if (tableData != null) {

            for (int i = 0; i < tableData.size; i++) {
                TextView textView = new TextView(context);
                textView.setGravity(Gravity.CENTER);

                // textView.setScrollContainer(true);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(tableData.width.get(i), Utils.dp2px(40));
                textView.setText(tableData.headNames.get(i));
                linearLayout.addView(textView, layoutParams);
                totalWidth += tableData.width.get(i);
            }
        }
        linearLayout.setDividerDrawable(context.getResources().getDrawable(R.drawable.icon_divider));
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        View tempView=new View(context);
//        tempView.setLayoutParams(new AbsListView.LayoutParams(totalWidth, ViewGroup.LayoutParams.WRAP_CONTENT));


        return linearLayout;

    }


}
