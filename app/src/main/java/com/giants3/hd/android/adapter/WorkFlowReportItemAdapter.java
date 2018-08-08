package com.giants3.hd.android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.entity.ErpWorkFlowReport;
import com.giants3.hd.utils.StringUtils;

import java.util.Calendar;

import butterknife.Bind;
import de.greenrobot.common.DateUtils;

/**订单的生产进度列表 adapter
 * Created by davidleen29 on 2017/3/4.
 */

public class WorkFlowReportItemAdapter extends AbstractAdapter<ErpWorkFlowReport> {



    public WorkFlowReportItemAdapter(Context context) {
        super(context);
    }

    @Override
    protected Bindable<ErpWorkFlowReport> createViewHolder(int itemViewType) {
        return new WorkFlowReportItemAdapter.ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_work_flow_report, null));
    }


    public class ViewHolder extends BaseBindable<ErpWorkFlowReport> {

        public ErpWorkFlowReport mItem;
        @Bind(R.id.progress)
        public ProgressBar progress;
        @Bind(R.id.workFlowName)
        public TextView workFlowName;
        @Bind(R.id.percentage)
        public TextView percentage;
        @Bind(R.id.limit)
        public TextView limit;
        @Bind(R.id.workflowstate)
        public TextView workflowstate;
        @Bind(R.id.startDate)
        public TextView startDate;
        @Bind(R.id.endDate)
        public TextView endDate;


        public ViewHolder(View view) {
            super(view);
            progress.setMax(100);

        }


        @Override
        public void bindData(AbstractAdapter<ErpWorkFlowReport> adapter, ErpWorkFlowReport data, int position) {
            ErpWorkFlowReport aProduct = data;
            mItem = aProduct;
            progress.setProgress((int) (data.percentage*100));
            workFlowName.setText(data.workFlowName);
            String percentText = "完成" + (int) (data.percentage * 100) + "%";

            if(data.sendingQty>0&&data.percentage<1)
            {
                percentText+=",\n 已发送未接收数量:"+data.sendingQty;
            }

            percentage.setText(percentText);

            limit.setText("期限:"+data.limitDay+"天");
            String text="";
            if(data.percentage>=1)
            {
                text+="已完成";

                long endDate = data.endDate;
                if(endDate==0)
                {
                    endDate= Calendar.getInstance().getTimeInMillis();
                }
                text+="    用天数:"+ DateUtils.getDayDifference(data.startDate, endDate)+"天";


            }else
            {
                text+="未完成,";
            }
                text+= "   "+((data.isOverDue?"超期":(data.percentage>=1?"提前":"剩余")) +Math.abs(data.overDueDay)+"天");



            workflowstate.setText(text );
            workflowstate.setTextColor(data.isOverDue? Color.RED:Color.BLUE);
            workflowstate.setVisibility(data.startDate>0?View.VISIBLE:View.GONE);
//            startDate.setVisibility(data.startDate>0?View.VISIBLE:View.GONE);
//            endDate.setVisibility(data.endDate>0?View.VISIBLE:View.GONE);

            if(data.limitDay>0)
            {
                if(data.isOverDue)
                {
                    if (data.overDueDay>5)
                    {
                        workflowstate.setTextColor(  Color.RED );
                    }else
                    {
                        workflowstate.setTextColor( getContext().getResources().getColor(R.color.orange ));
                    }
                }else
                {
                    //进入预警期显示红色
                    if (data.overDueDay>-data.alertDay)
                    {
                        workflowstate.setTextColor(  Color.RED );
                    }else
                    {
                        workflowstate.setTextColor(  Color.BLUE );
                    }

                }


            }else
            {
                workflowstate.setTextColor(  Color.BLACK );
            }

            startDate.setText("开始时间:"+(StringUtils.isEmpty(data.startDateString)?"":data.startDateString));
            endDate.setText("结束时间:"+(StringUtils.isEmpty(data.endDateString)?"":data.endDateString));


        }

        @Override
        public View getContentView() {
            return mView;
        }
    }



}
