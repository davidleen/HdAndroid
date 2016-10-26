package com.giants3.hd.android.mvp.MainAct;

import com.giants3.hd.android.helper.UpgradeUtil;
import com.giants3.hd.data.interractor.UseCase;
import com.giants3.hd.data.interractor.UseCaseFactory;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.utils.entity.RemoteData;
import com.giants3.hd.utils.noEntity.FileInfo;

import rx.Subscriber;

/**
 * Created by davidleen29 on 2016/10/21.
 */

public class MainActModel implements  MainActMvp.Model {
    @Override
    public void loadAppUpgradeInfo(Subscriber<RemoteData<FileInfo>> subscriber) {


        UseCaseFactory.getInstance().createLoadAppUpgradeInfoUseCase( ).execute(subscriber);

    }
}
