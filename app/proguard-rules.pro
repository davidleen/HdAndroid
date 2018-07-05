# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\sdk\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontoptimize

-keep public class com.giants3.hd.android.R$*{
    public static final int *;
}
-keepattributes *Annotation*,Signature
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider


#################google guice###########################
-keep class com.google.inject.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.annotation.** { *; }
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes *Annotation*
-keep public class * extends com.google.inject.AbstractModule
-keep class com.google.inject.Binder
-keepclassmembers class * {
    @com.google.inject.Inject <fields>;
    @com.google.inject.Inject <init>(...);
}
#################google guice###########################




#android support######

-keep public class * extends android.support.v4.**
-keep class android.support.v4.** { *; }


-keep public class * extends android.support.v7.**
-keep class android.support.v7.** { *; }


-keepclasseswithmembers class com.giants3.android.analysis.UmengAnalysisImpl {
   public *;
}

#==========eventbus 2.*=====================
-keepclassmembers class ** {
    public void onEvent*(***);
}

# Only required if you use AsyncExecutor
-keepclassmembers class * extends de.greenrobot.event.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

-keep class com.giants3.hd.entity.** { *; }
-keep class com.giants3.hd.noEntity.** { *; }
-keep class com.giants3.hd.appdata.** { *; }

# =======butterknife7.*========
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}













############RXandrid  rxjava############
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontnote rx.internal.util.PlatformDependent
############RXandrid  rxjava############

##---------------Begin: proguard configuration for Gson ----------
-keep public class com.google.gson.**
-keep public class com.google.gson.** {public private protected *;}








##---------------Begin: proguard configuration for PUSH ----------


-dontwarn com.umeng.**
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**
-dontwarn com.meizu.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class com.meizu.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}

##---------------end: proguard configuration for PUSH ----------




##---------------End: proguard configuration for Gson ----------

#关闭所有警告， 打包
-dontwarn  **