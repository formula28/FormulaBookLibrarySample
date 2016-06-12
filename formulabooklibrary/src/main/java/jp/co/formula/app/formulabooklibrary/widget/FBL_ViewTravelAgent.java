package jp.co.formula.app.formulabooklibrary.widget;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Viewを自動で移動させるクラス.
 * Created by @formula on 2016/06/12.
 */
public abstract class FBL_ViewTravelAgent {
    /** 移動するView. */
    protected View mTravelingView;
    /** Viewの左位置. */
    protected int mLeft = 0;
    /** Viewの上位置. */
    protected int mTop = 0;
    /** Viewの右位置. */
    protected int mRight = 0;
    /** Viewの下位置. */
    protected int mBottom = 0;
    /** View移動中. */
    protected boolean mIsTraveling = false;
    /** 定期処理用Timer. */
    protected Timer mTimer;
    /** 移動時間間隔. */
    protected long mInterval = 10;

    /**
     * コンストラクタ.
     * @param aView 動かすView.
     */
    public FBL_ViewTravelAgent(View aView) {
        mTravelingView = aView;
    }

    /**
     * Viewの移動開始.
     */
    public void startTravel() {
        if (mTravelingView != null) {
            stopTravel();
            mTimer = new Timer(true);
            mTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (mTravelingView.getHandler() != null) {
                        mTravelingView.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                travel();
                            }
                        });
                    }
                }
            }, mInterval, mInterval);
            mIsTraveling = true;
        }
    }

    /**
     * Viewの移動終了.
     */
    public void stopTravel() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            mIsTraveling = false;
        }
    }

    /**
     * 移動の自動終了判定.
     * @return true:終了する, false:終了しない.
     */
    protected abstract boolean isAutoStop();

    /**
     * 1フレーム毎の移動.
     * 内部状態の更新が必要な場合はオーバーライドせよ.
     */
    protected void travel() {
        if (mTravelingView != null) {
            update();
            mTravelingView.layout(mLeft, mTop, mRight, mBottom);
        }
        if (isAutoStop()) {
            stopTravel();
        }
    }

    /**
     * 内部状態の更新(1フレーム毎に移動する前に呼び出される).
     */
    protected abstract void update();
}
