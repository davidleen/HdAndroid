//package com.giants3.hd.android.widget;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.*;
//
//import com.giants3.hd.utils.entity.RemoteData;
//
//
//import junit.framework.Assert;
//
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * 模拟表格形式的列表
// * 带表头标题
// * 可以拖动表头左右滑动 查看未显示列。
// * 支持下一页上一页查看。
// * Created by davidleen29   qq:67320337
// * on 20160303
// */
//public class TableListView<T> extends LinearLayout {
//    public static final String TAG = "TableListView";
//    LayoutInflater layoutInflater;
//    Context context;
//
//    private boolean pageable;
//
//
//    /**
//     * 控件内字体大小
//     */
//    private float fontSize;
//
//
//    public TableListView(Context context) {
//        super(context);
//        this.context = context;
//
//        layoutInflater = LayoutInflater.from(context);
//
//
//    }
//
//    private String[] titles;
//    private String[] fields;
//    private float[] lengths;
//    private int[] types;
//    // PageController.PageListener pageListener;
//
//
//    private AdapterView.OnItemClickListener listener;
//
//
//    /**
//     * 数据绑定
//     *
//     * @param datas
//     */
//    public void bind(RemoteData<T> datas) {
//
//
//        adapter.setDataArray(datas.datas);
//        if (pageController != null) {
//            pageController.setPageInfo(datas);
//        }
//
//    }
//
//
//
//    public void setTitles(String[] titles) {
//        this.titles = titles;
//    }
//
//    public void setFields(String[] fields) {
//        this.fields = fields;
//    }
//
//    public void setLengths(float[] lengths) {
//        this.lengths = lengths;
//    }
//
//    public void setTypes(int[] types) {
//        this.types = types;
//    }
//
//
//    private JsonAdapter adapter;
//    //定制表格
//    ListView listView;
//
//
//    /**
//     * 根据参数构建控件布局。
//     */
//    public void config() {
//
//        Assert.assertNotNull(titles);
//        Assert.assertNotNull(fields);
//        Assert.assertNotNull(lengths);
//        Assert.assertNotNull(types);
//        Assert.assertTrue(titles.length == titles.length && fields.length == types.length && titles.length == fields.length);
//
//
//        //设置表头与表体的分割线
//
//       // setDividerDrawable(getResources().getDrawable(R.drawable.table_head_divider));
//        setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//
//        float totalWidth = 0;
//        for (float value : lengths) {
//            totalWidth += value;
//        }
//
//        //定制表格
//        listView = new ListView(context);
//       // listView.setSelector(R.drawable.empty_selector);
//        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        adapter = new JsonAdapter(context, fontSize);
//        listView.setAdapter(adapter);
//        //定制表头
//
////        HorizontalScrollView1 scrollView = new HorizontalScrollView1(context);
////        scrollView.setBackgroundColor(getResources().getColor(R.color.background_table_head));
////        scrollView.setHorizontalScrollBarEnabled(false);
////        scrollView.addView(generateHead(fontSize), new ScrollView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
////
////
////        scrollView.setScrollViewListener(new HorizontalScrollView1.ScrollViewListener() {
////            @Override
////            public void onScrollChanged(TwoDScrollView scrollView, int x, int y, int oldx, int oldy) {
////                //   Log.d(TAG, "x:" + x + ",Y:" + y + ",oldx:" + oldx + ",oldY:" + oldy);
////                //拖动表头 移动列表
////                //     if (pullToRefreshListView != null)
//////                Log.d(TAG, " linearLayout.getWidth():" + linearLayout.getWidth());
////                listView.scrollTo(x, listView.getScrollY());
////            }
////        });
//
//
//        addView(scrollView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//        LinearLayout.LayoutParams listViewLayout = new LinearLayout.LayoutParams(totalWidth >= getLayoutParams().width ? (int) totalWidth : LinearLayout.LayoutParams.MATCH_PARENT, 0);
//
////        LinearLayout.LayoutParams listViewLayout = new LinearLayout.LayoutParams(1280  , 0);
//        listViewLayout.weight = 1;
//        addView(listView, 1, listViewLayout);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                adapter.setSelectedPosition(position);
//                adapter.notifyDataSetChanged();
//                if (listener != null)
//                    listener.onItemClick(parent, view, position, id);
//
//
//            }
//        });
//
//
//        /**
//         * 加入分页控件
//         */
//        if (pageable) {
//            pageController = new PageController(context, pageListener, fontSize);
//
//            addView(pageController.getView(), 2);
//
//
//        }
//
//
//    }
//
//    public JSONObject getItemSelected() {
//
//
//        return adapter.getSelectedItem();
//
//    }
//
//
//    public void setonItemClickListener(AdapterView.OnItemClickListener listener) {
//
//
//        this.listener = listener;
//
//
//    }
//
//    public void setPageable(boolean pageable) {
//        this.pageable = pageable;
//    }
//
//    public boolean isPageable() {
//        return pageable;
//    }
//
//    public void setFontSize(float fontSize) {
//        this.fontSize = fontSize;
//    }
//
//
//    public int getPageSize() {
//        return pageController.getPageSize();
//    }
//
//
//    /**
//     * 适配列表界面
//     */
//    public class JsonAdapter extends BaseAdapter {
//
//        public static final String TAG = "JsonNoTitleAdapter";
//        private Context context;
//        private List<T> objects = new ArrayList<T>();
//        private int selectedPosition = -1;
//        int[] rowColor;
//        float fontSize;
//
//        public JsonAdapter(Context context, float fontSize) {
//            this.context = context;
//            rowColor = new int[3];
////
//            this.fontSize = fontSize;
//        }
//
//
//        @Override
//        public int getCount() {
//            return objects.size();
//        }
//
//
//        /**
//         * set the datas Arrays and notify update
//         *
//         * @param arrayData
//         */
//
//        public void setDataArray(List<T> arrayData) {
//            objects.clear();
//            selectedPosition = -1;
//            if (arrayData != null) {
//                objects.addAll(arrayData);
//            }
//
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public T getItem(int i) {
//            return objects.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//
//        @Override
//        public View getView(int position, View view, ViewGroup viewGroup) {
//
//
//            LinearLayout convertView;
//            if (view == null) {
//                convertView = generateRow(fontSize);
//
//            } else {
//                convertView = (LinearLayout) view;
//            }
//
//
//            //当前数据
//            JSONObject object = getItem(position);
//
//
////
////          int selectedPosition=  listView.getSelectedItemPosition();
//
//            convertView.setBackgroundColor(position == selectedPosition ? rowColor[2] : position % 2 != 0 ? rowColor[1] : rowColor[0]);
//
//            // convertView.setSelected(selectedPosition==position);
//            //数据绑定
//            int count = convertView.getChildCount();
//
//            for (int i = 0; i < count; i++) {
//                TextView tv = ((TextView) (convertView.getChildAt(i)));
//
//                switch (types[i]) {
//
//
////                    case Model.FieldColumn.FieldType.DATE:
////                        Date date = JsonDateUtils.from(object.optString(fields[i]));
////                        tv.setText(FieldData.DATE_FORMATE.format(date));
////                        break;
////                    default:
////                        tv.setText(object.optString(fields[i]));
//
//
//                }
//
//            }
//
//            return convertView;
//        }
//
//
//        public void setSelectedPosition(int selectedPosition) {
//            this.selectedPosition = selectedPosition;
//        }
//
//
//        public JSONObject getSelectedItem() {
//            if (selectedPosition == -1) return null;
//            return getItem(selectedPosition);
//        }
//    }
//
//    /**
//     * 生成表头行
//     *
//     * @return
//     */
//    private LinearLayout generateHead(float fontSize) {
//
//
//        //定制表头
//        final LinearLayout linearLayout = new LinearLayout(context);
//
//        linearLayout.setDividerDrawable(getResources().getDrawable(R.drawable.table_head_divider));
//        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//
//
//        int size = titles.length;
//
//        for (int i = 0; i < size; i++) {
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) lengths[i], ViewGroup.LayoutParams.WRAP_CONTENT);
//            TextView tv = new TextView(context);
//            tv.setTextSize(fontSize);
//
//            tv.setText(titles[i]);
//            tv.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // ToastUtils.show(v.getContext(), "i:=");
//                }
//            });
//            linearLayout.addView(tv, layoutParams);
//        }
//
//        return linearLayout;
//
//    }
//
//    /**
//     * 生成行
//     *
//     * @return
//     */
//    private LinearLayout generateRow(float fontSize) {
//
//
//        //定制表头
//        final LinearLayout linearLayout = new LinearLayout(context);
//        linearLayout.setDividerDrawable(getResources().getDrawable(R.drawable.table_head_divider));
//        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        int size = titles.length;
//        for (int i = 0; i < size; i++) {
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) lengths[i], ViewGroup.LayoutParams.WRAP_CONTENT);
//            TextView tv = (TextView) layoutInflater.inflate(R.layout.widgit_table_row_text, null);
//            tv.setTextSize(fontSize);
//            tv.setText(titles[i]);
//            linearLayout.addView(tv, layoutParams);
//
//        }
//
//        return linearLayout;
//
//    }
//
//
//    /**
//     * 排序回调接口
//     */
//    public interface SortChangeListener {
//
//
//        /**
//         * 排序回调接口
//         *
//         * @param index 排序列索引
//         * @param asc   是否升序
//         */
//        public void onSortChange(int index, boolean asc);
//
//    }
//
//}
