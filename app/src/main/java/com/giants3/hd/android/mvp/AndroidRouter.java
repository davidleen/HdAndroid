package com.giants3.hd.android.mvp;

import android.content.Intent;
import android.content.Context;

/**
 * Created by davidleen29 on 2017/6/3.
 */

public interface AndroidRouter {



      void startActivityForResult(Intent intent , int requestCode);


      Context getContext();

}
