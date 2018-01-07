package com.giants3.hd.android.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * a abstract class do some job to reduce code about bind view to holder and
 * easy on use baseAdapter
 *
 * @param <D>
 * @author davidleen29
 * @创建时间 2013年11月14日
 */
public abstract class AbstractAdapter<D> extends BaseAdapter {

    protected Context context;
    protected String TAG;
    protected LayoutInflater inflater;
    protected List<D> datas;


    private  int selectedPosition;

    public AbstractAdapter(Context context) {
        this(context, null);
    }

    /**
     * set the datas Arrays and notify update
     *
     * @param arrayData
     */
    public void setDataArray(List<D> arrayData) {
        datas.clear();
        if (arrayData != null) {
            datas.addAll(arrayData);
        }

        notifyDataSetChanged();
    } /**
     * set the datas Arrays and notify update
     *
     * @param arrayData
     */
    public void setDataArray(D[] arrayData) {
        datas.clear();

        List<D> newList=new ArrayList<>();
        for(D temp:arrayData)
        {
            newList.add(temp);
        }
        if (arrayData != null) {
            datas.addAll(newList);
        }

        notifyDataSetChanged();
    }

    /**
     * constructor
     *
     * @param context
     * @param initDatas
     */
    public AbstractAdapter(Context context, List<D> initDatas) {

        this.context = context;
        TAG = this.getClass().getName();
        if (initDatas == null) {
            this.datas = new ArrayList<D>();
        } else {
            this.datas = initDatas;
        }

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return datas.size();
    }

    @Override
    public D getItem(int position) {

        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }


    public void addItem(D data) {

        datas.add(data);
    }


    public void removeItem(D data) {

        datas.remove(data);
    }


    /**
     * get the binder base on viewType;
     * 获取对应itemType的控件集合绑定工具
     *
     * @param itemViewType
     * @return
     */
    protected abstract Bindable<D> createViewHolder(int itemViewType);

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        Bindable<D> holder;
        if (convertView == null) {


            Bindable<D> obj = createViewHolder(type);
            convertView = obj.getContentView();
            convertView.setTag(obj);
        }
        holder = (Bindable<D>) convertView.getTag();
        D data = getItem(position);

        holder.bindData(this, data, position);
        convertView.setSelected(selectedPosition==position);
        return convertView;

    }

    public List<D> getDatas() {
        return datas;
    }

    public void addDataArray(List<D> arrayData) {

        if (arrayData != null) {
            datas.addAll(arrayData);
        }

        notifyDataSetChanged();

    }

    public void clear() {

        if(datas!=null) {
            datas.clear();
            notifyDataSetChanged();
        }


    }

    /**
     * a bindable interface with a bindData method
     * 数据绑定类 包含绑定的控件成员 绑定方法。
     *
     * @param <D>
     * @author davidleen29
     * @创建时间 2013年11月13日
     */


    public interface Bindable<D> {
        /**
         * bind the data message
         *
         * @param data     the data params
         * @param position the position of data in the datasArray
         */
        public abstract void bindData(AbstractAdapter<D> adapter, D data, int position);

        public abstract View getContentView();

    }


    public abstract class BaseBindable<D> implements  Bindable<D>
    {
        protected final View mView;

        public BaseBindable(View view)
        {
            ButterKnife.bind(this, view);
            mView=view;
        }

        @Override
        public View getContentView() {
            return mView;
        }

    }

    protected Context getContext() {
        return context;
    }


    public void setSelectedPosition(int selectedPosition) {
        if(selectedPosition>=0&&selectedPosition<getCount()) {
            this.selectedPosition = selectedPosition;
        }else
        {
            this.selectedPosition=-1;
        }

    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
