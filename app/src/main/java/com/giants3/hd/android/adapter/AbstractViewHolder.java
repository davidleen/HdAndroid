package com.giants3.hd.android.adapter;

import android.view.View;

import butterknife.ButterKnife;

/**
 * 所有viewholder 的父类
 * 执行控件绑定
 * @param <D>
 */
public abstract class AbstractViewHolder<D> implements AbstractAdapter.Bindable<D> {

    protected View v;

    public AbstractViewHolder(View v) {
        this.v = v;
        ButterKnife.bind(this, v);
    }


    @Override
    public View getContentView() {
        return v;
    }
}