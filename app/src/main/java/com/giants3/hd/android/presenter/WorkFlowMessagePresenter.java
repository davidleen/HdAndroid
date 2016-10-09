package com.giants3.hd.android.presenter;

import com.giants3.hd.utils.entity.ErpOrderItem;
import com.giants3.hd.utils.entity.WorkFlow;
import com.giants3.hd.utils.entity.WorkFlowMessage;

/**流程消息 P层接口
 * Created by davidleen29 on 2016/9/23.
 */

public interface WorkFlowMessagePresenter extends  BasePresenter {
    void sendFlow( );

    void loadAvailableOrderItemForTransform();

    void receiveWorkFlow(WorkFlowMessage message);

    /**
     * 通过流程审核
     * @param message
     */
    void passWorkFlow(WorkFlowMessage message);

    void clearData();

    void pickOrderItem();

    void loadData();

    void pickNextWorkFlow();

    void loadMySendMessage();

    void updateQty(int qty);

    void updateMemo(String s);

    /**
     * 审核被拒绝
     * @param message
     * @param rejectToWorkFlow 返工至指定流程
     * @param reason    原因
     */
    void rejectWorkFlow(WorkFlowMessage message, WorkFlow rejectToWorkFlow, String reason);
}
