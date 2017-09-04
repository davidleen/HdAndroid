package com.giants3.hd.android.viewer;

import com.giants3.hd.noEntity.RemoteData;
import com.giants3.hd.entity.WorkFlowMessage;

/**
 * 流程消息管理界面接口
 * <p>
 * Created by davidleen29 on 2016/9/23.
 */

public interface WorkFlowMessageViewer extends BaseViewer {
    void setMyReceiveMessage(RemoteData<WorkFlowMessage> remoteData);



    void setMySendMessage(RemoteData<WorkFlowMessage> remoteData);



}
