package com.giants3.hd.android.helper;

import com.giants3.hd.utils.noEntity.BufferData;

/**
 * Created by davidleen29 on 2015/8/8.
 */
public class CacheManager {
    private static CacheManager ourInstance = new CacheManager();

    public static CacheManager getInstance() {
        return ourInstance;
    }

    private CacheManager() {
    }


    public BufferData bufferData;
}