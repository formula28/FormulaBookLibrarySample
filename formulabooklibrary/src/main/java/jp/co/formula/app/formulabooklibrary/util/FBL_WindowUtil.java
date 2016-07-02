package jp.co.formula.app.formulabooklibrary.util;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

import jp.co.formula.app.formulabooklibrary.R;
import jp.co.formula.app.formulabooklibrary.logger.FBL_Logger;

/**
 * Window関連ユーティリティ.
 * Created by @formula on 2016/06/19.
 */
public class FBL_WindowUtil {

    /**
     * ディスプレイのメトリクス取得.
     * @param aContext コンテキスト.
     * @return ディスプレイのメトリクス(取得できなかった場合はnull, メトリクスからdensity, scaledDensityなどを取得可能).
     */
    public static DisplayMetrics getDisplayMetrics(Context aContext) {
        FBL_Logger.enter();

        DisplayMetrics ret = null;
        if (aContext != null && aContext.getResources() != null) {
            ret = aContext.getResources().getDisplayMetrics();
        }

        FBL_Logger.leave(ret);
        return ret;
    }

    /**
     * アプリケーションのディスプレイサイズ[px]取得(全画面-ナビゲーションバーのサイズ).
     * @param aContext コンテキスト.
     * @return Point(サイズ x=width, y=height) (取得できない場合は(0,0)).
     */
    public static Point getApplicationDisplaySize(Context aContext) {
        FBL_Logger.enter();

        Point p = new Point(0,0);
        if (aContext != null) {
            WindowManager win = FBL_SystemServiceManager.getWindowManager(aContext);
            if (win != null) {
                Display display = win.getDefaultDisplay();
                if (display != null) {
                    display.getSize(p);
                }
            }
        }

        FBL_Logger.leave(p);
        return p;
    }

    /**
     * ハードウェアのディスプレイサイズ[px]取得(全画面のサイズ).
     * API Version 13以上対応.
     * @param aContext コンテキスト.
     * @return Point(サイズ x=width, y=height) (取得できない場合は(0,0)).
     */
    public static Point getHardwareDisplaySize(Context aContext) {
        FBL_Logger.enter();

        Point p = new Point(0,0);
        if (aContext != null) {
            WindowManager win = FBL_SystemServiceManager.getWindowManager(aContext);
            if (win != null) {
                Display display = win.getDefaultDisplay();
                if (display != null) {
                    FBL_Logger.i("API version: %d", Build.VERSION.SDK_INT);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        display.getRealSize(p);
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                        try {
                            Method getRawWidth = Display.class.getMethod("getRawWidth");
                            Method getRawHeight = Display.class.getMethod("getRawHeight");
                            int width = (Integer) getRawWidth.invoke(display);
                            int height = (Integer) getRawHeight.invoke(display);
                            p.set(width, height);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        FBL_Logger.leave(p);
        return p;
    }

    /**
     * ナビゲーションバーサイズ[px]取得.
     * @param aContext コンテキスト.
     * @return Point(サイズ x=width, y=height) (取得できない場合は(0,0)).
     */
    public static Point getNavigationBarSize(Context aContext) {
        FBL_Logger.enter();

        Point p = new Point(0, 0);
        if (aContext != null) {
            Point hSize = getHardwareDisplaySize(aContext);
            if (hSize.x > 0 && hSize.y > 0) {
                Point dSize = getApplicationDisplaySize(aContext);
                int orientation = aContext.getResources().getConfiguration().orientation;
                FBL_Logger.i("orientation = %d", orientation);
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    p.x = hSize.x - dSize.x;
                    p.y = hSize.y;
                } else {
                    p.x = hSize.x;
                    p.y = hSize.y - dSize.y;
                }
            }
        }

        FBL_Logger.leave(p);
        return p;
    }

    /**
     * ステータスバーサイズ[px]取得.
     * @param aContext コンテキスト.
     * @return Point(サイズ x=width, y=height) (取得できない場合は(0,0)).
     */
    public static Point getStatusBarSize(Context aContext) {
        FBL_Logger.enter();

        Point p = new Point(0,0);
        if (aContext != null) {
            p.x = getApplicationDisplaySize(aContext).x;
            int resourceId = aContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                p.y = aContext.getResources().getDimensionPixelSize(resourceId);
            }
        }

        FBL_Logger.leave(p);
        return p;
    }

    /**
     * アクションバーサイズ[px]取得.
     * @param aContext コンテキスト.
     * @return Point(サイズ x=width, y=height) (取得できない場合は(0,0)).
     */
    public static Point getActionBarSize(Context aContext) {
        FBL_Logger.enter();

        Point p = new Point(0,0);
        if (aContext != null) {
            p.x = getApplicationDisplaySize(aContext).x;
            TypedValue outValue = new TypedValue();
            aContext.getTheme().resolveAttribute(R.attr.actionBarSize, outValue, true);
            p.y = (int) outValue.getDimension(getDisplayMetrics(aContext));
        }

        FBL_Logger.leave(p);
        return p;
    }

    /**
     * コンテンツ表示ディスプレイサイズ[px]取得(全画面 - ナビゲーションバー - ステータスバー - アクションバー のサイズ).
     * @param aContext コンテキスト.
     * @return Point(サイズ x=width, y=height) (取得できない場合は(0,0)).
     */
    public static Point getContentDisplaySize(Context aContext) {
        FBL_Logger.enter();

        Point p = new Point(0,0);
        if (aContext != null) {
            Point ads = getApplicationDisplaySize(aContext);
            Point abs = getActionBarSize(aContext);
            Point sbs = getStatusBarSize(aContext);
            p.x = ads.x;
            p.y = ads.y - abs.y -sbs.y;
        }

        FBL_Logger.leave(p);
        return p;
    }
}
