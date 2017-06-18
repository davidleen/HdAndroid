package com.giants3.hd.android.presenter;

import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.WorkFlow;
import com.giants3.hd.utils.entity.WorkFlowMessage;

/**流程消息 P层接口
 * Created by davidleen29 on 2016/9/23.
 */

public interface WorkFlowMessagePresenter extends  BasePresenter {



    void loadData();



    void showMessage(WorkFlowMessage itemAtPosition);

    void setMySendShow();

    void setMyReceiveShow();
}
