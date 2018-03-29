package com.giants3.android.zxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.DisplayMetrics;

import com.giants3.android.api.qrCode.QRCodeResult;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Created by davidleen29 on 2018/3/28.
 */

public class QRCodeFactory {


    public static  void start(Activity activity,String title)
    {

        IntentIntegrator integrator = new IntentIntegrator(activity);
//                 integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
//                 integrator.setPrompt("Scan a barcode");
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt(title);


        integrator.setCaptureActivity(QRCaptureActivity.class);
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.initiateScan();
    }

    public static QRCodeResult onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);


        if (result != null) {
            QRCodeResult qrCodeResult = new QRCodeResult();
            qrCodeResult.contents=result.getContents();
            qrCodeResult.rawBytes=result.getRawBytes();
            qrCodeResult.errorCorrectionLevel=result.getErrorCorrectionLevel();
            qrCodeResult.orientation=result.getOrientation();
            qrCodeResult.formatName=result.getFormatName();

            return qrCodeResult;

        }

        return null;


    }


    public static  Bitmap generateQRImage(Context context, String content)
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
}
