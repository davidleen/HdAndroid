package com.giants3.hd.android.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.helper.AndroidUtils;
import com.giants3.hd.android.helper.CapturePictureHelper;
import com.giants3.hd.android.helper.ImageLoaderFactory;
import com.giants3.hd.android.helper.ImageLoaderHelper;
import com.giants3.hd.android.helper.ImageViewerHelper;
import com.giants3.hd.android.helper.SharedPreferencesHelper;
import com.giants3.hd.android.mvp.AndroidRouter;
import com.giants3.hd.android.mvp.workflowmessagereceive.PresenterImpl;
import com.giants3.hd.data.net.HttpUrl;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.entity.WorkFlowMessage;
import com.giants3.hd.entity.WorkFlowWorker;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.StringUtils;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;

import static com.giants3.hd.android.mvp.WorkFlowMessageReceive.Presenter;
import static com.giants3.hd.android.mvp.WorkFlowMessageReceive.Viewer;

//import com.giants3.hd.android.helper.PictureHelper;

/**
 * 流程接受处理事件
 */
public class WorkFlowMessageReceiveActivity extends BaseHeadViewerActivity<Presenter> implements Viewer {


    public static final String KEY_MESSAGE = "KEY_MESSAGE";
    private static final String KEY_MESSAGE_ID = "KEY_MESSAGE_ID";
    @Bind(R.id.picture)
    public ImageView picture;
    @Bind(R.id.fromFlow)
    public TextView fromFlow;
    @Bind(R.id.toFlow)
    public TextView toFlow;
    @Bind(R.id.tranQty)
    public TextView tranQty;
    @Bind(R.id.name)
    public TextView name;
    @Bind(R.id.orderName)
    public TextView orderName;
    @Bind(R.id.batNo)
    public TextView batNo;
    @Bind(R.id.cus_no)
    public TextView cus_no;
    @Bind(R.id.productName)
    public TextView productName;
    @Bind(R.id.mrpNo)
    public TextView mrpNo;
    @Bind(R.id.qty)
    public TextView qty;
    @Bind(R.id.unitName)
    public TextView unitName;
    @Bind(R.id.area)
    public TextView area;
    @Bind(R.id.sendMemo)
    public TextView sendMemo;
    @Bind(R.id.createTime)
    public TextView createTime;
    @Bind(R.id.state)
    public TextView state;
    @Bind(R.id.memo)
    public EditText memo;
    @Bind(R.id.panel_factory)
    public View panel_factory;
    @Bind(R.id.factory)
    public TextView factory;
    @Bind(R.id.picture1)
    ImageView picture1;
    @Bind(R.id.picture2)
    ImageView picture2;
    @Bind(R.id.picture3)
    ImageView picture3;
    @Bind(R.id.panel_picture2)
    View panel_picture2;
    @Bind(R.id.panel_picture1)
    View panel_picture1;
    @Bind(R.id.panel_picture3)
    View panel_picture3;
    @Bind(R.id.delete1)
    View delete1;
    @Bind(R.id.delete2)
    View delete2;
    @Bind(R.id.delete3)
    View delete3;
    @Bind(R.id.addPicture)
    ImageView addPicture;
    @Bind(R.id.receive)
    View receive;


    @Bind(R.id.panel_receiver)
    public View panel_receiver;
    @Bind(R.id.receiver)
    public TextView receiver;

    @Bind(R.id.panel_sender)
    public View panel_sender;
    @Bind(R.id.sender)
    public TextView sender;




    @Bind(R.id.reject)
    View reject;
    CapturePictureHelper capturePictureHelper;


    public static void start(AndroidRouter router, long workflowMessageId  , int requestMessageOperate) {






        Intent intent = new Intent(router.getContext(), WorkFlowMessageReceiveActivity.class);
        intent.putExtra(WorkFlowMessageReceiveActivity.KEY_MESSAGE_ID,workflowMessageId);
        router.startActivityForResult(intent, requestMessageOperate);
    }


    public static void start(AndroidRouter router, WorkFlowMessage workFlowMessage, int requestMessageOperate) {


        Intent intent = new Intent(router.getContext(), WorkFlowMessageReceiveActivity.class);
        intent.putExtra(WorkFlowMessageReceiveActivity.KEY_MESSAGE, GsonUtils.toJson(workFlowMessage));
        router.startActivityForResult(intent, requestMessageOperate);
    }

//    public static void start(Fragment fragment, WorkFlowMessage workFlowMessage, int requestMessageOperate) {
//
//
//        Intent intent = new Intent(fragment.getActivity(), WorkFlowMessageReceiveActivity.class);
//        intent.putExtra(WorkFlowMessageReceiveActivity.KEY_MESSAGE, GsonUtils.toJson(workFlowMessage));
//        fragment.startActivityForResult(intent, requestMessageOperate);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("流程接收");
        WorkFlowMessage workFlowMessage = null;
        try {
            workFlowMessage = GsonUtils.fromJson(getIntent().getStringExtra(KEY_MESSAGE), WorkFlowMessage.class);
        } catch (HdException e) {
            e.printStackTrace();
        }
        if (workFlowMessage != null) {

            getPresenter().setWorkFlowMessage(workFlowMessage);

        }else {


            long workflowMessageId =getIntent().getLongExtra(KEY_MESSAGE_ID,0);
            if(workflowMessageId!=0)
            {
                getPresenter().setWorkFlowMessageId(workflowMessageId);

            }else
            {
                finish();return;
            }
        }



        capturePictureHelper = new CapturePictureHelper(this, new CapturePictureHelper.OnPictureGetListener() {


            @Override
            public void onPictureFileGet(String filePath) {
                // WorkFlowMessageReceiveActivity.this.getCacheDir()
                File newPath = new File(AndroidUtils.getCacheDir(), Calendar.getInstance().getTimeInMillis() + "");

                File tempFile = new File(filePath);
                tempFile.renameTo(newPath);


                getPresenter().onNewPictureFileSelected(newPath);
            }
        });


    }

    @Override
    protected Presenter onLoadPresenter() {
        return new PresenterImpl();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        registerForContextMenu(addPicture);
        addPicture.setOnClickListener(this);
        receive.setOnClickListener(this);
        reject.setOnClickListener(this);
        delete1.setOnClickListener(this);
        delete2.setOnClickListener(this);
        delete3.setOnClickListener(this);
        picture1.setOnClickListener(this);
        picture2.setOnClickListener(this);
        picture3.setOnClickListener(this);
        picture.setOnClickListener(this);

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected View getContentView() {

        return LayoutInflater.from(this).inflate(R.layout.activity_work_flow_message_receive, null);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode != RESULT_OK) return;
        capturePictureHelper.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    protected void onViewClick(int id, View v) {

        switch (id) {
            case R.id.addPicture:

                if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
                } else
                    capturePictureHelper.pickFromCamera(true);


                break;


            case R.id.receive:
                getPresenter().receiveWorkFlow();

                break;

            case R.id.reject:
                getPresenter().rejectWorkFlow();

                break;

            case R.id.delete1:
                getPresenter().deleteFile(0);
                break;
            case R.id.delete2:
                getPresenter().deleteFile(1);
                break;
            case R.id.delete3:

                getPresenter().deleteFile(2);
                break;

            case R.id.picture1:
            case R.id.picture2:
            case R.id.picture3:
            case R.id.picture: {
                String url = (String) v.getTag();
                ImageViewerHelper.view(this, url);
            }


            break;


        }


    }

    @Override
    public void bindPicture(List<File> files) {


        int size = files.size();
        String[] pictures = new String[size];
        for (int i = 0; i < size; i++) {
            pictures[i] = Uri.fromFile(files.get(i)).toString();
        }
        showPictures(pictures, true);
    }

    private void showPictures(String[] pictureUrl, boolean canDelete) {

        ImageView[] imageview = new ImageView[]{picture1, picture2, picture3};
        View[] panel = new View[]{panel_picture1, panel_picture2, panel_picture3};
        View[] deletes = new View[]{delete1, delete2, delete3};
        int size = pictureUrl.length;
        for (int i = 0; i < 3; i++) {

            if (i < size) {
                ImageLoaderFactory.getInstance().displayImage(pictureUrl[i], imageview[i], ImageLoaderHelper.getLocalDisplayOptions());
                imageview[i].setTag(pictureUrl[i]);
            }
            panel[i].setVisibility(i < size ? View.VISIBLE : View.GONE);
            deletes[i].setVisibility(i < size && canDelete ? View.VISIBLE : View.GONE);
        }

        addPicture.setVisibility(size >= 3 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void bindWorkFlowMessage(WorkFlowMessage data) {


        name.setText(data.name);
        orderName.setText(data.orderName);
        qty.setText(String.valueOf(data.orderItemQty));
        productName.setText(data.productName);
        batNo.setText(data.bat_no);
        cus_no.setText(data.cus_no);

        toFlow.setText(data.toFlowName);
        fromFlow.setText(data.fromFlowName);
        tranQty.setText(String.valueOf(data.transportQty));
        factory.setText(String.valueOf(data.factoryName));
        mrpNo.setText(data.mrpNo == null ? "" : data.mrpNo);
        area.setText(data.area);
        sendMemo.setText(data.sendMemo);



        panel_receiver.setVisibility(data.receiverId>0?View.VISIBLE:View.GONE);
        panel_sender.setVisibility(data.senderId>0?View.VISIBLE:View.GONE);

        receiver.setText(data.receiverName+ "   "+data.receiveTimeString);
        sender.setText(data.senderName+ "   "+data.createTimeString);


        panel_factory.setVisibility(StringUtils.isEmpty(data.factoryName) ? View.GONE : View.VISIBLE);


        ImageLoaderFactory.getInstance().displayImage(HttpUrl.completeUrl(data.url), picture);
        picture.setTag(HttpUrl.completeUrl(data.url));
        unitName.setText("");

        memo.setText(data.memo);


        String stateText = "";
        switch (data.state) {
            case WorkFlowMessage.STATE_SEND:
                stateText = "待接收";
                break;
            case WorkFlowMessage.STATE_RECEIVE:
                stateText = "待审核";
                break;
            case WorkFlowMessage.STATE_REWORK:
                stateText = "返工";
                break;
            case WorkFlowMessage.STATE_REJECT:
                stateText = "审核未通过";
                break;
            case WorkFlowMessage.STATE_PASS:
                stateText = "已通过";
                break;

        }

        state.setText(stateText);
        createTime.setText(data.createTimeString.substring(0, 10));
        String[] pictures = StringUtils.split(data.pictures);
        int size = pictures.length;
        for (int i = 0; i < size; i++) {
            pictures[i] = HttpUrl.completeUrl(pictures[i]);
        }

        showPictures(pictures, false);

        boolean canReceiveOrReject = false;
        //当前用户在目标流程上有权限
        List<WorkFlowWorker> workFlowWorkers = SharedPreferencesHelper.getInitData().workFlowWorkers;

        for (WorkFlowWorker workFlow : workFlowWorkers) {
            if (workFlow.workFlowStep == data.toFlowStep && workFlow.receive) {
                canReceiveOrReject = true;
                break;
            }


        }
        canReceiveOrReject = canReceiveOrReject && data.state == WorkFlowMessage.STATE_SEND;


        receive.setVisibility(canReceiveOrReject ? View.VISIBLE : View.GONE);
        reject.setVisibility(canReceiveOrReject ? View.VISIBLE : View.GONE);
        addPicture.setVisibility(canReceiveOrReject ? View.VISIBLE : View.GONE);
        memo.setEnabled(canReceiveOrReject);


    }

    @Override
    public void onPermissionGranted(String permission) {
        if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permission))

            capturePictureHelper.pickFromCamera(true);

    }

    @Override
    public void finishOk() {

        setResult(RESULT_OK);
        finish();
    }
}
