package com.giants3.hd.android.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.giants3.hd.android.R;
import com.giants3.hd.android.helper.CapturePictureHelper;
import com.giants3.hd.android.helper.ToastHelper;
import com.giants3.hd.data.utils.GsonUtils;
import com.giants3.hd.exception.HdException;
import com.giants3.hd.utils.entity.Material;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MaterialDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MaterialDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaterialDetailFragment extends BaseFragment implements View.OnClickListener {


    public static String ARG_ITEM = "ARG_ITEM";

    private static final int MAX_MEMO_ROW_LINE = 3;

    Material material;


    private OnFragmentInteractionListener mListener;


    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.capture)
    View capture;

    @Bind(R.id.upload)
    View upload;


    @Bind(R.id.materialCode)
    TextView materialCode;
    @Bind(R.id.materialName)
    TextView materialName;
    @Bind(R.id.typeName)
    TextView typeName;
    @Bind(R.id.className)
    TextView className;
    @Bind(R.id.mLong)
    TextView mLong;
    @Bind(R.id.mWidth)
    TextView mWidth;
    @Bind(R.id.mHeight)
    TextView mHeight;
    @Bind(R.id.unit)
    TextView unit;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.spec)
    TextView spec;
    @Bind(R.id.available)
    TextView available;
    @Bind(R.id.discount)
    TextView discount;
    @Bind(R.id.unitRatio)
    TextView unitRatio;
    @Bind(R.id.ingredientRatio)
    TextView ingredientRatio;
    @Bind(R.id.memo)
    TextView memo;
    @Bind(R.id.outOfService)
    TextView outOfService;


    CapturePictureHelper capturePictureHelper;


    private Bitmap newPicture;

    public MaterialDetailFragment() {
        // Required empty public constructor
    }


    public static MaterialDetailFragment newInstance(Material Material) {
        MaterialDetailFragment fragment = new MaterialDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEM, GsonUtils.toJson(Material));
        fragment.setArguments(args);
        return fragment;
    }

    public static MaterialDetailFragment newInstance(Bundle args) {
        MaterialDetailFragment fragment = new MaterialDetailFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            try {
                material = GsonUtils.fromJson(getArguments().getString(ARG_ITEM), Material.class);
            } catch (HdException e) {
                e.printStackTrace();
                ToastHelper.show("参数异常");
                getActivity().finish();
            }


        }
        capturePictureHelper = new CapturePictureHelper(this, new CapturePictureHelper.OnPictureGetListener() {
            @Override
            public void onPictureGet(Bitmap bitmap) {

                newPicture = bitmap;
                image.setImageBitmap(bitmap);
                upload.setVisibility(View.VISIBLE);

            }
        });

    }


    @OnClick(R.id.capture)
    public void capturePicture() {
        capturePictureHelper.pickPhoto();

    }

    @OnClick(R.id.upload)
    public void uploadPicture() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_material_detail, container, false);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        capturePictureHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bindData();

    }

    private void bindData() {
        typeName.setText(material.typeName);
        materialCode.setText(material.code);
        materialName.setText(material.name);
        price.setText(String.valueOf(material.price));
        unit.setText(material.unitName);
        className.setText(material.className);
        mLong.setText(String.valueOf(material.wLong));
        mWidth.setText(String.valueOf(material.wWidth));
        mHeight.setText(String.valueOf(material.wHeight));
        available.setText(String.valueOf(material.available));
        discount.setText(String.valueOf(material.discount));
        spec.setText(material.spec);
        memo.setText(material.memo);
        unitRatio.setText(String.valueOf(material.unitRatio));
        ingredientRatio.setText(String.valueOf(material.ingredientRatio));
        outOfService.setText(material.outOfService ? "停用" : "使用中");
        ImageLoader.getInstance().displayImage(material.url, image);
        upload.setVisibility(newPicture == null ? View.GONE : View.VISIBLE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }


    @Override
    protected void onLoginRefresh() {


    }

}
