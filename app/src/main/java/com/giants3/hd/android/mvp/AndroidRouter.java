package com.giants3.hd.android.mvp;

import android.content.Context;
import android.content.Intent;

/**
 * Created by davidleen29 on 2017/6/3.
 */

public interface AndroidRouter {



      void startActivityForResult(Intent intent , int requestCode);


      Context getContext();

}
