package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.utils.entity.WorkFlowReport;

import butterknife.ButterKnife;

/**订单的生产进度列表 adapter
 * Created by davidleen29 on 2017/3/4.
 */

public class WorkFlowReportItemAdapter extends AbstractAdapter<WorkFlowReport> {

    public WorkFlowReportItemAdapter(Context context) {
        super(context);
    }

    @Override
    protected Bindable<WorkFlowReport> createViewHolder(int itemViewType) {
        return new WorkFlowReportItemAdapter.ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_work_flow_report, null));
    }


    public class ViewHolder implements Bindable<WorkFlowReport> {
        public final View mView;

        public WorkFlowReport mItem;
        public ProgressBar progress;
        public TextView workFlowName;
        public TextView percentage;

        public ViewHolder(View view) {


            mView = view;
            progress = (ProgressBar) view.findViewById(R.id.progress);
            workFlowName = (TextView) view.findViewById(R.id.workFlowName);
            percentage = (TextView) view.findViewById(R.id.percentage);
            progress.setMax(100);

        }


        @Override
        public void bindData(AbstractAdapter<WorkFlowReport> adapter, WorkFlowReport data, int position) {
            WorkFlowReport aProduct = data;
            mItem = aProduct;
            progress.setProgress((int) (data.percentage*100));
            workFlowName.setText(data.workFlowName);
            percentage.setText("完成"+(int) (data.percentage*100)+"%");

        }

        @Override
        public View getContentView() {
            return mView;
        }
    }

}
