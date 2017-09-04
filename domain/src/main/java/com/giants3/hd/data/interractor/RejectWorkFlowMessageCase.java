package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import java.io.File;

import rx.Observable;


/**
 * 审核流程传递
 *
 *
 */
public class RejectWorkFlowMessageCase extends DefaultUseCase {


    private final long workFlowMessageId;
    private final File[] file;
    private final String memo;

    RestApi restApi;
    public RejectWorkFlowMessageCase(long messageId, File[] file, String memo, RestApi restApi) {

        this.workFlowMessageId = messageId;
        this.file = file;
        this.memo = memo;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return restApi.rejectWorkFlowMessage ( workFlowMessageId,  file,   memo);


    }
}
