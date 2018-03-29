package com.giants3.android.api.qrCode;

import android.content.Intent;

/**
 * Created by davidleen29 on 2018/3/27.
 */

public interface QRScanApi {


   void  startScan();

    QRCodeResult OnActivityResult(int requestCode,int resultCode,Intent data,QRCodeResult qrCodeResult);
}
