package com.giants3.hd.data.interractor;

import com.giants3.hd.data.net.RestApi;

import rx.Observable;
import rx.Scheduler;

/**  上传材料图片
 *
 * Created by david on 2015/9/14.
 */
public class UploadMaterialPictureCase extends UseCase {


    private final long materialId;
    private final byte[] data;
    RestApi restApi;



    public UploadMaterialPictureCase(Scheduler threadExecutor, Scheduler postExecutionThread,  long materialId,byte[] data , RestApi restApi) {
        super(threadExecutor, postExecutionThread);
        this.materialId = materialId;
        this.data = data;
        this.restApi=restApi;


    }

    @Override
    protected Observable buildUseCaseObservable() {



       return  restApi.uploadMaterialPicture(materialId,data);



    }
}
