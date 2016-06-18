package jp.co.formula.app.formulabooklibrary.util;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.Stack;

import jp.co.formula.app.formulabooklibrary.logger.FBL_Logger;

/**
 * アプリケーション.
 * Created by @formula on 2016/06/18.
 */
public class FBL_ApplicationUtil extends Application {
    /** インスタンス. */
    protected static FBL_ApplicationUtil sInstance;
    /** Activityスタック. */
    protected Stack<Activity> mActivityStack;
    /** Serviceスタック. */
    protected Stack<Service> mServiceStack;
    /** PackageInfo. */
    protected PackageInfo mPackageInfo;

    /**
     * インスタンス取得.
     * @return アプリケーションインスタンス.
     */
    public static FBL_ApplicationUtil getInstance() {
        return sInstance;
    }

    /**
     * コンストラクタ(インスタンス保持).
     */
    public FBL_ApplicationUtil() {
        sInstance = this;
        sInstance.mActivityStack = new Stack<>();
        sInstance.mServiceStack = new Stack<>();
    }

    /** ### アプリケーション情報 start. ### */
    /**
     * アプリバージョンコード取得.
     * @return Manifestのandroid:versionCodeで指定される非負整数(取得できない場合-1).
     */
    public int getAppVersionCode() {
        FBL_Logger.enter();
        int ret = -1;
        if (mPackageInfo != null) {
            ret = mPackageInfo.versionCode;
        }
        FBL_Logger.leave(ret);
        return ret;
    }
    /**
     * アプリバージョン名取得.
     * @return Manifestのandroid:versionNameで指定される名称(取得できない場合null).
     */
    public String getAppVersionName() {
        FBL_Logger.enter();
        String ret = null;
        if (mPackageInfo != null) {
            ret = mPackageInfo.versionName;
        }
        FBL_Logger.leave(ret);
        return ret;
    }
    /**
     * アプリケーション名取得.
     * @return Manifestのandroid:labelで指定される名称.
     */
    public String getAppName() {
        FBL_Logger.enter();
        String ret = null;
        if (mPackageInfo != null
                && mPackageInfo.applicationInfo != null) {
            ret = String.valueOf(mPackageInfo.applicationInfo.loadLabel(getPackageManager()));
        }
        FBL_Logger.leave(ret);
        return ret;
    }

    /**
     * アプリケーションアイコン取得.
     * @return Manifestのandroid:iconで指定されるアイコン画像.
     */
    public Drawable getAppIcon() {
        FBL_Logger.enter();
        Drawable ret = null;
        if (mPackageInfo != null
                && mPackageInfo.applicationInfo != null) {
            ret = mPackageInfo.applicationInfo.loadIcon(getPackageManager());
        }
        FBL_Logger.leave(ret);
        return ret;
    }
    /** ### アプリケーション情報 end. ### */

    /** ### Activity start. ### */
    /**
     * 最上面のActivity取得.
     * @return Activity.
     */
    public Activity getTopActivity() {
        FBL_Logger.enter();
        Activity ret = mActivityStack.peek();
        FBL_Logger.leave(ret);
        return ret;
    }

    /**
     * Activity push.
     * 最上面にActivityを追加する.
     * @param activity Activity.
     */
    protected void pushActivityStack(Activity activity) {
        FBL_Logger.enter();
        if (activity != null) {
            mActivityStack.push(activity);
        }
        FBL_Logger.leave(mActivityStack);
    }

    /**
     * Activity pop.
     * 最上面のActivityを削除する.
     */
    protected void popActivityStack() {
        FBL_Logger.enter();
        mActivityStack.pop();
        FBL_Logger.leave(mActivityStack);
    }

    /**
     * Activity pop.
     * 最上面のActivityを削除する.
     */
    protected void removeActivityStack(Activity activity) {
        FBL_Logger.enter();
        mActivityStack.remove(activity);
        FBL_Logger.leave(mActivityStack);
    }

    /**
     * 全Activity終了.
     */
    public void finishAllAct() {
        FBL_Logger.enter();
        while(!mActivityStack.empty()) {
            Activity act = mActivityStack.pop();
            act.finish();
        }
        FBL_Logger.leave();
    }

    /**
     * 単純Activity起動.
     * @param clazz Activityクラス.
     */
    public void startActivity(Class<? extends Activity> clazz) {
        FBL_Logger.enter(clazz);
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FBL_Logger.leave();
    }
    /**
     * 単純Activity起動.
     * @param clazz Activityクラス名.
     */
    public void startActivity(String clazz) {
        FBL_Logger.enter(clazz);
        Intent intent = new Intent();
        intent.setClassName(this, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FBL_Logger.leave();
    }
    /** ### Activity end. ### */


    /** ### プロセスとライフサイクル start. ### */
    /**
     * アプリプロセス起動時処理.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        // Activityライフサイクル監視開始.
        registerActivityLifecycleCallbacks(mActivityListener);

        // パッケージ情報取得.
        try {
            mPackageInfo = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_META_DATA
            );
            FBL_Logger.setTag(getAppName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * アプリプロセス停止時処理.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        // Activityライフサイクル監視終了.
        unregisterActivityLifecycleCallbacks(mActivityListener);
    }

    /** Activityライフサイクル監視リスナ. */
    ActivityLifecycleCallbacks mActivityListener = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            pushActivityStack(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            // nop.
        }

        @Override
        public void onActivityResumed(Activity activity) {
            // nop.
        }

        @Override
        public void onActivityPaused(Activity activity) {
            // nop.
        }

        @Override
        public void onActivityStopped(Activity activity) {
            // nop.
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            // nop.
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            FBL_Logger.enter(activity);
            removeActivityStack(activity);
            FBL_Logger.leave();
        }
    };
    /** ### プロセスとライフサイクル end. ### */
}
