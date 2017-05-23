package com.giants3.hd.android.viewer;

import com.giants3.hd.utils.entity.ErpOrderItemProcess;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.entity.WorkFlowMessage;

/**
 * 流程消息管理界面接口
 * <p>
 * Created by davidleen29 on 2016/9/23.
 */

public interface WorkFlowMessageViewer extends BaseViewer {
    void setMyReceiveMessage(RemoteData<WorkFlowMessage> remoteData);

    void clearData();

    void setOrderItemRelate(ErpOrderItemProcess erpOrderItem);

//    void setNextWorkFlow(WorkFlow workFlow);

    void showSenPanel();

    void setMySendMessage(RemoteData<WorkFlowMessage> remoteData);


    void warnQtyInput(String message);

    void updateSendQty(int qty);

    /**
     * 订单发送不足处理
     */
    void updateNotTotalSend(boolean notEnough);

    void hideCheckConfirmDialog();

}
