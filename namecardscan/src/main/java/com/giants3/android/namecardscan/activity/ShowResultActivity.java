package com.giants3.android.namecardscan.activity;

import java.io.IOException;
import java.io.InputStream;

import com.giants3.android.namecardscan.R;
import com.intsig.openapilib.OpenApi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import a_vcard.android.syncml.pim.VDataBuilder;
import a_vcard.android.syncml.pim.vcard.VCardException;
import a_vcard.android.syncml.pim.vcard.VCardParser;

public class ShowResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_result);

		Intent data = getIntent();
		String trim = data.getStringExtra(OpenApi.EXTRA_KEY_IMAGE);
		if (!TextUtils.isEmpty(trim)) {
			ImageView imageView = (ImageView) findViewById(R.id.imageView1);
			imageView.setImageBitmap(BitmapFactory.decodeFile(trim));
		}

		String vcf = data.getStringExtra(OpenApi.EXTRA_KEY_VCF);

		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText(vcf);
		VCardParser parser=new VCardParser();
		try {
			VDataBuilder builder = new VDataBuilder();
			parser.parse(vcf, builder);
			String result=builder.getResult();
			//builder.vNodeList.get(0).propList.get(0).propValue
		} catch (VCardException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
