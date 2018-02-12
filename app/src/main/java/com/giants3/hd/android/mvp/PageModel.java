package com.giants3.hd.android.mvp;

import com.giants3.hd.noEntity.RemoteData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidleen29 on 2017/11/25.
 */

public interface PageModel<T> extends NewModel {


      void setKey(String key) ;


      String getKey();


      void setRemoteData(RemoteData<T> remoteData)  ;

      List<T> getDatas();


      RemoteData<T> getRemoteData() ;



      boolean hasNextPage();


        int getPageIndex();
        int getPageSize();

}
