package com.activitizereactnative;

import android.app.Application;
import android.util.Log;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import com.facebook.react.shell.MainReactPackage;
import com.facebook.reactnative.androidsdk.FBSDKPackage;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private static CallbackManager mCallbackManager;

  @Override
  public void onCreate() {
    super.onCreate();
    // Initialize the SDK before executing any other operations,
    FacebookSdk.sdkInitialize(getApplicationContext());
    AppEventsLogger.activateApp(this);
  }

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    protected boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      if (mCallbackManager == null) {
        mCallbackManager = new CallbackManager.Factory().create();
      }
      ReactPackage packages[] = new ReactPackage[]{
              new MainReactPackage(),
              new FBSDKPackage(mCallbackManager),
      };
      return Arrays.<ReactPackage>asList(packages);
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
      return mReactNativeHost;
  }

  protected static CallbackManager getCallbackManager() {
    return mCallbackManager;
  }
}
