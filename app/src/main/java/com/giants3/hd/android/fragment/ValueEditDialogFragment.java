package com.giants3.hd.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.giants3.hd.android.R;

import java.util.List;

/**
 * Created by davidleen29 on 2016/6/13.
 */
public class ValueEditDialogFragment extends DialogFragment {


    private Class<?> valueType;
    private String title;
    private Object value;
    private String titleString;
    private String oldValue;
    private ValueChangeListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       return   inflater.inflate(R.layout.dialog_value_edit,null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView oldValueTextView= (TextView) view.findViewById(R.id.oldValue);
        oldValueTextView.setText(oldValue);
        final TextView newValueTextView= (TextView) view.findViewById(R.id.newValue);
        getDialog().setTitle(titleString);
        View save=view.findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String value=newValueTextView.getText().toString();

               if(listener!=null)
               {
                   listener.onValueChange(titleString, oldValue,value);
               }


                dismiss();


            }
        });
    }

    public void set(String titleString,String oldValue,ValueChangeListener listener) {
        this.titleString = titleString;
        this.oldValue = oldValue;
        this.listener = listener;
    }

    public void set(String titleString, List<Object> datas , ValueChangeListener listener) {
        this.titleString = titleString;
        this.oldValue = oldValue;
        this.listener = listener;
    }
    public interface  ValueChangeListener
    {public void onValueChange(String title,String oldValue,String newValue);}

}
