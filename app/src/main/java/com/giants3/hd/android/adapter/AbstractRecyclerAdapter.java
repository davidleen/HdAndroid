package com.giants3.hd.android.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * a abstract class do some job to reduce code about bind view to holder and
 * easy on use baseAdapter
 *
 * @param <D>
 * @author davidleen29
 * @创建时间 2013年11月14日
 */
public abstract class AbstractRecyclerAdapter<V extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<V> {

    protected Context context;
    protected String TAG;
    protected LayoutInflater inflater;
    protected List<D> datas;

    public AbstractRecyclerAdapter(Context context) {
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
    }

    @Override
    public void onBindViewHolder(V holder, int position) {

    }

    /**
     * constructor
     *
     * @param context
     * @param initDatas
     */
    public AbstractRecyclerAdapter(Context context, List<D> initDatas) {

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
    public int getItemCount() {

        return datas.size();
    }


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


    public List<D> getDatas() {
        return datas;
    }

    public void addDataArray(List<D> arrayData) {

        if (arrayData != null) {
            datas.addAll(arrayData);
        }

        notifyDataSetChanged();

    }


    protected Context getContext() {
        return context;
    }
}
