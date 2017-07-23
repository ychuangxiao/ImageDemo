# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/banditcat/Library/Android/sdk/tools/proguard/proguard-android.txt
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

-keepattributes SourceFile,LineNumberTable
-dontpreverify
-flattenpackagehierarchy
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-ignorewarnings



-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService


-keepattributes *Annotation*

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}


-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

## ----------------------------------
##   ########## Gson混淆    ##########
## ----------------------------------
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.** { *; }






-dontwarn com.google.**
-keep class com.google.**  {*;}

# As described in tools/proguard/examples/android.pro - ignore all warnings.
-dontwarn android.support.**
-keep class android.support.** {*;}

-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}
## ----------------------------------
##   ########## glid 混淆    ##########
## ----------------------------------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}


-keepclassmembers class ** {
    public void onEvent*(**);
    void onEvent*(**);
}

# Only required if you use AsyncExecutor
-keepclassmembers class * extends de.greenrobot.event.util.ThrowableFailureEvent {
    *;
}


-dontwarn android.net.**
-keep class android.net.**  {*;}

-dontwarn org.apache.http.**
-keep class org.apache.http.**  {*;}


-dontwarn de.greenrobot.**
-keep class de.greenrobot.**  {*;}



## ----------------------------------
##   ########## dagger相关混淆    ##########
## ----------------------------------
-dontwarn dagger.**
-keep class dagger.**  {*;}

-dontwarn org.hamcrest.**
-keep class org.hamcrest.**  {*;}


-dontwarn javax.inject.**
-keep class javax.inject.**  {*;}

## ----------------------------------
##   ########## OKHttp相关混淆    ##########
## ----------------------------------
-dontwarn okhttp3.**
-keep class okhttp3.**  {*;}
-dontwarn okio.**
-keep class okio.**  {*;}
-dontwarn retrofit2.**
-keep class retrofit2.**  {*;}


-dontwarn org.objenesis.**
-keep class org.objenesis.**  {*;}




-dontwarn com.getkeepsafe.**
-keep class com.getkeepsafe.**  {*;}

## ----------------------------------
##   ########## Rx相关混淆    ##########
## ----------------------------------
-dontwarn rx.**
-keep class rx.**  {*;}

-dontwarn org.robovm.apple.**
-keep class org.robovm.apple.**  {*;}

-dontwarn java.util.Optional.**
-keep class java.util.Optional.**  {*;}

-dontwarn org.apache.harmony.**
-keep class org.apache.harmony.**  {*;}

-dontwarn sun.misc.Unsafe.**
-keep class sun.misc.Unsafe.**  {*;}

-dontwarn com.android.**
-keep class com.android.**  {*;}

-dontwarn com.squareup.**
-keep class com.squareup.**  {*;}


-dontwarn com.bumptech.**
-keep class com.bumptech.**  {*;}


-dontwarn sun.security.**
-keep class sun.security.**  {*;}

## ----------------------------------
##   ########## 数据库混淆    ##########
## ----------------------------------
-dontwarn io.realm.**
-keep class io.realm.**  {*;}
-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class *
-dontwarn javax.**
-dontwarn io.realm.**

## ----------------------------------
##   ########## butterknife混淆    ##########
## ----------------------------------
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
   @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
 @butterknife.* <methods>;
}

-dontwarn butterknife.internal.**


-dontwarn butterknife.compiler.**
-keep class butterknife.compiler.**  {*;}


-keep class **$$ViewInjector { *; }

-keepnames class * { @butterknife.InjectView *;}

-dontwarn butterknife.Views$InjectViewProcessor

-dontwarn com.gc.materialdesign.views.**


## ----------------------------------
##   ########## ilogie混淆    ##########
## ----------------------------------
-dontwarn com.ilogie.android.transformer.**
-keep class com.ilogie.android.transformer.**  {*;}


-dontwarn com.aspsine.**
-keep class com.aspsine.**  {*;}


-dontwarn com.ximpleware.**
-keep class com.ximpleware.**  {*;}

-dontwarn com.wdullaer.**
-keep class com.wdullaer.**  {*;}


## ----------------------------------
##   ########## 项目pojo 和 试图模型 混淆    ##########
## ----------------------------------
-dontwarn com.sb.app.model.**
-keep class com.sb.app.model.**  {*;}

-dontwarn com.sb.data.entitys.**
-keep class com.sb.data.entitys.**  {*;}



-dontwarn com.sb.app.views.widget.**
-keep class com.sb.app.views.widget.**  {*;}

