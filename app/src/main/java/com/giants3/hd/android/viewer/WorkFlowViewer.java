package com.giants3.hd.android.viewer;

import com.giants3.hd.entity.ErpOrderItem;
import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity.WorkFlow;
import com.giants3.hd.entity.WorkFlowMessage;

/**
 *  流程消息管理界面接口
 *
 * Created by davidleen29 on 2016/9/23.
 */

public interface WorkFlowViewer extends  BaseViewer{
    void setMyReceiveMessage(RemoteData<WorkFlowMessage> remoteData);

    void clearData();

    void setOrderItemRelate(ErpOrderItem erpOrderItem);

    void setNextWorkFlow(WorkFlow workFlow);

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
