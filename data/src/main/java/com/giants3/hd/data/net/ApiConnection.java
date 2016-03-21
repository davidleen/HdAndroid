/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.giants3.hd.data.net;


import android.util.Base64;

import com.giants3.hd.crypt.CryptUtils;
import com.giants3.hd.exception.HdException;
import com.google.inject.Inject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Api Connection class used to retrieve data from the cloud.
 * Implements {@link java.util.concurrent.Callable} so when executed asynchronously can
 * return a value.
 */

public class ApiConnection {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";
    private static final MediaType mediaType = MediaType.parse(CONTENT_TYPE_VALUE_JSON);

    public static final String DEFAULT_CHAR_ENCODE = "UTF-8";
    private static boolean IS_CRYPT_JSON = false;
    public static final String DES_KEY = "d5b417051ca087f5a068f93b4769f654";
    private OkHttpClient okHttpClient = createClient();
    ;

    static {

    }

    @Inject
    public ApiConnection() {
    }


    public byte[] post(String url, byte[] data) throws HdException {


        RequestBody body = RequestBody.create(mediaType, data);
        Request request = null;
        try {
            request = new Request.Builder()
                    .url(new URL(url))
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                    .post(body).build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw HdException.create("url:" + url + ",is not a valid url");
        }
        try {
            return okHttpClient.newCall(request).execute().body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
            throw HdException.create(e);
        }


    }

    public String post(String url, String data) throws HdException {


        try {
            byte[] byteData = data.getBytes(DEFAULT_CHAR_ENCODE);
            if (IS_CRYPT_JSON) {
                byteData = CryptUtils.encryptDES(byteData, DES_KEY);
            }
            byte[] result = post(url, byteData);

            if (IS_CRYPT_JSON) {
                result = CryptUtils.decryptDES(result, DES_KEY);
            }
            return new String(result, DEFAULT_CHAR_ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw HdException.create(e);
        }

    }

    public byte[] get(String url) throws HdException {


        Base64 base;
        Request request = null;
        try {
            request = new Request.Builder()
                    .url(new URL(url))
                    .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON).get().build();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw HdException.create("url:" + url + ",is not a valid url");
        }
        try {
            return okHttpClient.newCall(request).execute().body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
            throw HdException.create(e);
        }
    }

    public String getString(String url) throws HdException {


        try {

            byte[] result = get(url);
            if (IS_CRYPT_JSON) {
                result = CryptUtils.decryptDES(result, DES_KEY);
            }
            return new String(result, DEFAULT_CHAR_ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw HdException.create(e);
        }

    }


    private static OkHttpClient createClient() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
        okHttpClient.setConnectTimeout(15000, TimeUnit.MILLISECONDS);

        return okHttpClient;
    }


}