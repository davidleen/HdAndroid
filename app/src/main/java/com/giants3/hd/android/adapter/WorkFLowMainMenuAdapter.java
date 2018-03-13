package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.giants3.hd.android.R;

import butterknife.Bind;

/**
 * Created by davidleen29 on 2017/6/23.
 */

public class WorkFLowMainMenuAdapter extends AbstractAdapter<String> {


    public WorkFLowMainMenuAdapter(Context context) {
        super(context);
    }

    @Override
    protected Bindable<String> createViewHolder(int itemViewType) {

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.list_item_main_menu, null, false);
        return new WorkFLowMainMenuAdapter.ViewHolder(view);

    }




    public static class ViewHolder extends AbstractViewHolder<String> {

        @Bind(R.id.text)
        public TextView text;
        @Bind(R.id.countText)
        public TextView countText;

        public String mItem;

        public ViewHolder(View view) {
            super(view);
        }


        @Override
        public void bindData(AbstractAdapter<String> adapter, String data, int position) {
            text.setText(data);

        }


        public void setMessageCount(int count) {


            countText.setText(count>99?"99+":String.valueOf(count));
            countText.setVisibility(count>0?View.VISIBLE:View.GONE);
        }
    }
}
