package com.giants3.hd.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.utils.entity.ErpWorkFlowReport;

import butterknife.Bind;

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
            percentage.setText("完成"+(int) (data.percentage*100)+"%");

        }

        @Override
        public View getContentView() {
            return mView;
        }
    }

}
