package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;

/**
 * Created by davidleen29 on 2017/6/23.
 */

class GetWorkFlowMaterialsUseCase extends DefaultUseCase {
    private final String osNo;
    private final int itm;
    private String workFlowCode;
    private final RestApi restApi;

    public GetWorkFlowMaterialsUseCase(String osNo, int itm,String workFlowCode, RestApi restApi) {
        super();
        this.osNo = osNo;
        this.itm = itm;
        this.workFlowCode = workFlowCode;
        this.restApi = restApi;
    }

    @Override
    protected Observable buildUseCaseObservable() {

       return  restApi.getWorkFlowMaterials(osNo,itm,workFlowCode);
    }
}
