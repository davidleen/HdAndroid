package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import java.io.File;

import rx.Observable;

/**
 * 接受流程传递
 *
 *
 */
public class ReceiveWorkFlowMessageCase extends DefaultUseCase {


    private final long workFlowMessageId;
    private final File[] files;
    private final String area;
    private String memo;
    RestApi restApi;



    public ReceiveWorkFlowMessageCase(  long workFlowMessageId, File[] files,String area, RestApi restApi) {

        this.workFlowMessageId = workFlowMessageId;
        this.files = files;
        this.area = area;
        this.memo = memo;

        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.receiveWorkFlowMessageCase( workFlowMessageId,files,area);



    }
}
