package com.giants3.hd.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.DisplayMetrics;

import com.giants3.hd.android.activity.QRCaptureActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 *
 * 二维码 生成 帮助类
 * Created by david on 2015/12/19.
 */
public class QRHelper {



    private Bitmap generateQRImage(Context context,String content)
    {

        DisplayMetrics displaymetrics =
        context.getResources().getDisplayMetrics();
        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;

        int newWidth=screenWidth>screenHeight?screenWidth/2:screenHeight/2;

        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, newWidth, newWidth);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.TRANSPARENT);
                }
            }
            return bmp;
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return null;
    }


    public   void startScan(Activity activity)
    {

        IntentIntegrator integrator = new IntentIntegrator(activity);
//                 integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
//                 integrator.setPrompt("Scan a barcode");
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("消费者从[我的]-[我的卡包]中打开二维码放入框内，即可自动扫描");


        integrator.setCaptureActivity(QRCaptureActivity.class);
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.initiateScan();
    }



    public String onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            String contents = result.getContents();
            return contents;
        }
        return null;
    }
}
