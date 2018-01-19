package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.entity.TableData;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.ErpWorkFlow;

/**
 * Created by davidleen29 on 2018/1/14.
 */

public class ErpOrderItemListAdapter extends ItemListAdapter<ErpOrderItem> {
    public ErpOrderItemListAdapter(Context context) {
        super(context);
        setTableData(TableData.resolveData(context, R.array.table_erp_order_item));
    }

    @Override
    protected boolean bindFieldData(View view, ErpOrderItem data, String field) {



        if(field.equalsIgnoreCase("currentOverDueDay"))
        {
            TextView tv= (TextView) view;
            int currentOverDueDay=data.currentOverDueDay;

            if(currentOverDueDay==0)
            {
                tv.setText(" ");
            }
            else {



                if (currentOverDueDay > 0) {
                    tv.setText("超期" + Math.abs(currentOverDueDay) + "天");
                } else {
                    tv.setText( "剩余" + Math.abs(currentOverDueDay) + "天");
                }


            }

            return true;

        }

        if(field.equalsIgnoreCase("totalLimit"))
        {
            TextView tv= (TextView) view;
            int totalLimit=data.totalLimit;

            if(totalLimit==0)
            {
                tv.setText(" ");
            }
            else {

                String pre="";
                if( data.workFlowState== ErpWorkFlow.STATE_WORKING)
                {
                    pre="预计";

                }
                if (totalLimit > 0) {
                    tv.setText(pre+"超期" + Math.abs(totalLimit )+ "天");
                } else {
                    tv.setText(pre+"提前" + Math.abs(totalLimit )+ "天");
                }

            }

            return true;
        }



        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        ErpOrderItem item=getItem(position);
        if(item!=null) {

            if (item.currentOverDueDay > 5)

            {
                view.setBackgroundResource(R.drawable.icon_workflow_limit_exceed_5_selector);

            } else if (item.currentOverDueDay > 0) {
                view.setBackgroundResource(R.drawable.icon_workflow_limit_selector);
            } else if (item.currentOverDueDay != 0 && Math.abs(item.currentOverDueDay) <= item.currentAlertDay) {
                view.setBackgroundResource(R.drawable.icon_workflow_alert_selector);
            } else {
                view.setBackgroundResource(R.drawable.list_item_bg_selector);
            }


        }



        return view;




    }
}
