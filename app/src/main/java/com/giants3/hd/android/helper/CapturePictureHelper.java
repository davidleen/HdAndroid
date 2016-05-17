package com.giants3.hd.android.helper;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.giants3.hd.android.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by david on 2016/5/9.
 */
public class CapturePictureHelper implements View.OnClickListener {

    public static final int DEFAULT_OUT_WIDTH = 600;
    public static final int DEFAULT_OUT_HEIGHT = 600;
    public int outWidth = DEFAULT_OUT_WIDTH;
    public int outHeight = DEFAULT_OUT_HEIGHT;
    Fragment activity;
    private View ly_from_album;
    private View ly_from_camera;
    private AlertDialog mAlertDialog;

    OnPictureGetListener listener;

    public CapturePictureHelper(Fragment activity, OnPictureGetListener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    public final static int FROM_ALBUM = 9911;
    public final static int START_CAMERA = 9912;
    public final static int SET_PIC = 9913;
    private Bitmap upLoadBitmap;
    private File tempFilePath;


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK) return;

        switch (requestCode) {
            case FROM_ALBUM:
                if (data != null) {
                    initTempFile();
                    startPhotoZoom(data.getData(),Uri.fromFile(tempFilePath));
                }
                break;

            case START_CAMERA:


                if (tempFilePath.exists()) {


                    startPhotoZoom(Uri.fromFile(tempFilePath),Uri.fromFile(tempFilePath));
                }

                break;

            case SET_PIC:
                if (data != null) {
                    setPicToView(data);
                }
                break;

        }
    }


    /**
     * 将捕捉后的图片显示
     *
     * @param data
     */

    private void setPicToView(Intent data) {
        Bundle extras = data.getExtras();

//            if(upLoadBitmap!=null&&!upLoadBitmap.isRecycled())

        if(tempFilePath.exists())
        {

                  upLoadBitmap=  BitmapFactory.decodeFile(tempFilePath.getPath());
            if (upLoadBitmap != null) {

                if (listener != null) {
                    listener.onPictureGet(upLoadBitmap);
                } else {
                    upLoadBitmap.recycle();
                }
                if (tempFilePath != null && tempFilePath.exists()) {
                    tempFilePath.delete();
                }

            }

        }




    }

    /**
     * 从图库中选择
     *
     * @param data
     */
    private void startPhotoZoom(Uri data,Uri dest) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(data, "image/*");
            intent.putExtra("crop", true);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", outWidth);
            intent.putExtra("outputY", outHeight);
            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,dest);
            activity.startActivityForResult(intent, SET_PIC);
        } catch (Exception e) {
            e.printStackTrace();
            ToastHelper.showShort("相册打开失败");
        }
    }


    public void pickPhoto() {

        if (mAlertDialog == null) {
            final View pickPanel = activity.getActivity().getLayoutInflater().inflate(
                    R.layout.dialog_pick_photo, null);
            ly_from_album = pickPanel
                    .findViewById(R.id.ly_from_gallery);
            ly_from_camera = pickPanel
                    .findViewById(R.id.ly_from_camera);

            ly_from_album.setOnClickListener(this);
            ly_from_camera.setOnClickListener(this);
            mAlertDialog = new AlertDialog.Builder(activity.getContext())
                    .setTitle("图片选择")
                    .setView(pickPanel)
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();
                                }
                            }).create();
            mAlertDialog.setCanceledOnTouchOutside(false);
        }

        mAlertDialog.show();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ly_from_camera:
                if (mAlertDialog != null) {
                    mAlertDialog.dismiss();
                }
                try {
                    Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    initTempFile();
                    intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFilePath));
                    intent2.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 50);
                    activity.startActivityForResult(intent2, START_CAMERA);
                } catch (Exception e) {

                    e.printStackTrace();
                    ToastHelper.showShort("相机打开失败");
                }
                break;
            case R.id.ly_from_gallery:
                if (mAlertDialog != null) {
                    mAlertDialog.dismiss();
                }
                try {
                    Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                    intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    activity.startActivityForResult(intent1, FROM_ALBUM);
                } catch (Exception e) {

                    ToastHelper.showShort("图库打开失败");
                }
                break;
        }

    }

    /**
     * 回调接口
     */
    public interface OnPictureGetListener {
        public void onPictureGet(Bitmap bitmap);

    }

    private void initTempFile()
    {
        if(tempFilePath==null||!tempFilePath.exists())
                 tempFilePath=  new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
    }
}
