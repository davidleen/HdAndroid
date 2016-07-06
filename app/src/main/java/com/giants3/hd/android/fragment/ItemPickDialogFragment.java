package com.giants3.hd.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.Utils;
import com.giants3.hd.android.adapter.AbstractAdapter;

import java.util.List;

/**
 * Created by davidleen29 on 2016/6/13.
 */
public class ItemPickDialogFragment<T> extends DialogFragment {



    private String title;
    List<T>  datas;
     private String titleString;
    private T oldData;
    private ValueChangeListener listener;
    AbstractAdapter<T> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       return   inflater.inflate(R.layout.dialog_item_pick,null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ListView listView= (ListView) view.findViewById(R.id.listView);
//        oldValueTextView.setText(oldValue);
//        final TextView newValueTextView= (TextView) view.findViewById(R.id.newValue);

        adapter=new AbstractAdapter<T>(getContext()) {
            @Override
            protected Bindable<T> createViewHolder(int itemViewType) {
                TextView tv=     new TextView(getContext());
                tv.setMinHeight(Utils.dp2px(36));
                return new ViewHolder(tv);
            }
        };


        listView.setAdapter( adapter
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               T object= adapter.getItem(position);

                if(listener!=null)
                {

                    listener.onValueChange(title,oldData,object);
                    dismiss();
                }

            }
        });
        adapter.setDataArray(datas);
        getDialog().setTitle(titleString);
        View btn_cancel=view.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                dismiss();


            }
        });
    }


    private class ViewHolder implements AbstractAdapter.Bindable<T>
    {

        View v;
        public ViewHolder(View v)
        {
                this.v=v;
        }
        @Override
        public void bindData(AbstractAdapter<T> adapter, T data, int position) {


            if(v instanceof  TextView)
            {
                ((TextView ) v).setText( String.valueOf(data));
            }
        }

        @Override
        public View getContentView() {
            return v;
        }
    }

    public void set(String titleString, List<T> datas ,T oldData, ValueChangeListener listener) {
        this.titleString = titleString;
        this.datas=datas;
        this.oldData = oldData;
        this.listener = listener;
    }
    public interface  ValueChangeListener<T>
    {public void onValueChange(String title, T oldValue, T newValue);}

}
