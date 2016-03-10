package com.giants3.hd.android;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.ViewDebug;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by david on 2016/1/15.
 */
@SuppressLint("NewApi")
public class SkinContext extends Context {


    private Resources skinResources;
    private AssetManager skinAssetManager;

    @Override
    public AssetManager getAssets() {
        return skinAssetManager;
    }

    @Override
    public Resources getResources() {
        return skinResources;
    }

    @Override
    public PackageManager getPackageManager() {
        return context.getPackageManager();
    }

    @Override
    public ContentResolver getContentResolver() {
        return context.getContentResolver();
    }

    @Override
    public Looper getMainLooper() {
        return context.getMainLooper();
    }

    @Override
    public Context getApplicationContext() {
        return context.getApplicationContext();
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        context.registerComponentCallbacks(callback);
    }


    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        context.unregisterComponentCallbacks(callback);
    }

    @Override
    public void setTheme(int resid) {
        context.setTheme(resid);
    }

    @Override
    @ViewDebug.ExportedProperty(
        deepExport = true
    )
    public Resources.Theme getTheme() {
        return context.getTheme();
    }

    @Override
    public ClassLoader getClassLoader() {
        return context.getClassLoader();
    }

    @Override
    public String getPackageName() {
        return context.getPackageName();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return context.getApplicationInfo();
    }

    @Override
    public String getPackageResourcePath() {
        return context.getPackageResourcePath();
    }

    @Override
    public String getPackageCodePath() {
        return context.getPackageCodePath();
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return context.getSharedPreferences(name, mode);
    }

    @Override
    public FileInputStream openFileInput(String name) throws FileNotFoundException {
        return context.openFileInput(name);
    }

    @Override
    public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
        return context.openFileOutput(name, mode);
    }

    @Override
    public boolean deleteFile(String name) {
        return context.deleteFile(name);
    }

    @Override
    public File getFileStreamPath(String name) {
        return context.getFileStreamPath(name);
    }

    @Override
    public File getFilesDir() {
        return context.getFilesDir();
    }

    @Override
    public File getNoBackupFilesDir() {
        return context.getNoBackupFilesDir();
    }

    @Nullable
    @Override
    public File getExternalFilesDir(String type) {
        return context.getExternalFilesDir(type);
    }

    @Override
    public File[] getExternalFilesDirs(String type) {
        return context.getExternalFilesDirs(type);
    }

    @Override
    public File getObbDir() {
        return context.getObbDir();
    }

    @Override
    public File[] getObbDirs() {
        return context.getObbDirs();
    }

    @Override
    public File getCacheDir() {
        return context.getCacheDir();
    }

    @Override
    public File getCodeCacheDir() {
        return context.getCodeCacheDir();
    }

    @Nullable
    @Override
    public File getExternalCacheDir() {
        return context.getExternalCacheDir();
    }

    @Override
    public File[] getExternalCacheDirs() {
        return context.getExternalCacheDirs();
    }

    @Override
    public File[] getExternalMediaDirs() {
        return context.getExternalMediaDirs();
    }

    @Override
    public String[] fileList() {
        return context.fileList();
    }

    @Override
    public File getDir(String name, int mode) {
        return context.getDir(name, mode);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        return context.openOrCreateDatabase(name, mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        return context.openOrCreateDatabase(name, mode, factory, errorHandler);
    }

    @Override
    public boolean deleteDatabase(String name) {
        return context.deleteDatabase(name);
    }

    @Override
    public File getDatabasePath(String name) {
        return context.getDatabasePath(name);
    }

    @Override
    public String[] databaseList() {
        return context.databaseList();
    }

    @Override
    @Deprecated
    public Drawable getWallpaper() {
        return context.getWallpaper();
    }

    @Override
    @Deprecated
    public Drawable peekWallpaper() {
        return context.peekWallpaper();
    }

    @Override
    @Deprecated
    public int getWallpaperDesiredMinimumWidth() {
        return context.getWallpaperDesiredMinimumWidth();
    }

    @Override
    @Deprecated
    public int getWallpaperDesiredMinimumHeight() {
        return context.getWallpaperDesiredMinimumHeight();
    }

    @Override
    @Deprecated
    public void setWallpaper(Bitmap bitmap) throws IOException {
        context.setWallpaper(bitmap);
    }

    @Override
    @Deprecated
    public void setWallpaper(InputStream data) throws IOException {
        context.setWallpaper(data);
    }

    @Override
    @Deprecated
    public void clearWallpaper() throws IOException {
        context.clearWallpaper();
    }

    @Override
    public void startActivity(Intent intent) {
        context.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        context.startActivity(intent, options);
    }

    @Override
    public void startActivities(Intent[] intents) {
        context.startActivities(intents);
    }

    @Override
    public void startActivities(Intent[] intents, Bundle options) {
        context.startActivities(intents, options);
    }

    @Override
    public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        context.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    @Override
    public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
        context.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    @Override
    public void sendBroadcast(Intent intent) {
        context.sendBroadcast(intent);
    }

    @Override
    public void sendBroadcast(Intent intent, String receiverPermission) {
        context.sendBroadcast(intent, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission) {
        context.sendOrderedBroadcast(intent, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        context.sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user) {
        context.sendBroadcastAsUser(intent, user);
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission) {
        context.sendBroadcastAsUser(intent, user, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        context.sendOrderedBroadcastAsUser(intent, user, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    @Deprecated
    public void sendStickyBroadcast(Intent intent) {
        context.sendStickyBroadcast(intent);
    }

    @Override
    @Deprecated
    public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        context.sendStickyOrderedBroadcast(intent, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    @Deprecated
    public void removeStickyBroadcast(Intent intent) {
        context.removeStickyBroadcast(intent);
    }

    @Override
    @Deprecated
    public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {
        context.sendStickyBroadcastAsUser(intent, user);
    }

    @Override
    @Deprecated
    public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle user, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
        context.sendStickyOrderedBroadcastAsUser(intent, user, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    @Deprecated
    public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {
        context.removeStickyBroadcastAsUser(intent, user);
    }

    @Nullable
    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        return context.registerReceiver(receiver, filter);
    }

    @Nullable
    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler) {
        return context.registerReceiver(receiver, filter, broadcastPermission, scheduler);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        context.unregisterReceiver(receiver);
    }

    @Nullable
    @Override
    public ComponentName startService(Intent service) {
        return context.startService(service);
    }

    @Override
    public boolean stopService(Intent service) {
        return context.stopService(service);
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return context.bindService(service, conn, flags);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        context.unbindService(conn);
    }

    @Override
    public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments) {
        return context.startInstrumentation(className, profileFile, arguments);
    }

    @Override
    public Object getSystemService(String name) {
        return context.getSystemService(name);
    }

    @Override
    public String getSystemServiceName(Class<?> serviceClass) {
        return context.getSystemServiceName(serviceClass);
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        return context.checkPermission(permission, pid, uid);
    }

    @Override
    public int checkCallingPermission(String permission) {
        return context.checkCallingPermission(permission);
    }

    @Override
    public int checkCallingOrSelfPermission(String permission) {
        return context.checkCallingOrSelfPermission(permission);
    }

    @Override
    public int checkSelfPermission(String permission) {
        return context.checkSelfPermission(permission);
    }

    @Override
    public void enforcePermission(String permission, int pid, int uid, String message) {
        context.enforcePermission(permission, pid, uid, message);
    }

    @Override
    public void enforceCallingPermission(String permission, String message) {
        context.enforceCallingPermission(permission, message);
    }

    @Override
    public void enforceCallingOrSelfPermission(String permission, String message) {
        context.enforceCallingOrSelfPermission(permission, message);
    }

    @Override
    public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
        context.grantUriPermission(toPackage, uri, modeFlags);
    }

    @Override
    public void revokeUriPermission(Uri uri, int modeFlags) {
        context.revokeUriPermission(uri, modeFlags);
    }

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        return context.checkUriPermission(uri, pid, uid, modeFlags);
    }

    @Override
    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        return context.checkCallingUriPermission(uri, modeFlags);
    }

    @Override
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        return context.checkCallingOrSelfUriPermission(uri, modeFlags);
    }

    @Override
    public int checkUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags) {
        return context.checkUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags);
    }

    @Override
    public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {
        context.enforceUriPermission(uri, pid, uid, modeFlags, message);
    }

    @Override
    public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {
        context.enforceCallingUriPermission(uri, modeFlags, message);
    }

    @Override
    public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {
        context.enforceCallingOrSelfUriPermission(uri, modeFlags, message);
    }

    @Override
    public void enforceUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags, String message) {
        context.enforceUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags, message);
    }

    @Override
    public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
        return context.createPackageContext(packageName, flags);
    }

    @Override
    public Context createConfigurationContext(Configuration overrideConfiguration) {
        return context.createConfigurationContext(overrideConfiguration);
    }

    @Override
    public Context createDisplayContext(Display display) {
        return context.createDisplayContext(display);
    }

    @Override
    public boolean isRestricted() {
        return context.isRestricted();
    }

    private Context context;
}
