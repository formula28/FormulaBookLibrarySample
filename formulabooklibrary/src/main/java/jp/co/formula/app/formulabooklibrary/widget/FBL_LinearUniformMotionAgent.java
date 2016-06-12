package jp.co.formula.app.formulabooklibrary.widget;

import android.view.View;
import android.view.ViewGroup;

/**
 * 等速直線運動.
 * 親Viewの内側を一定速度で移動し、境界にぶつかると反射する.
 * Created by @formula on 2016/06/12.
 */
public class FBL_LinearUniformMotionAgent extends FBL_ViewTravelAgent {
    /** 移動速度[dp/sec]. */
    protected double mVelocity = 10;
    /** 初期移動方向. */
    protected double mDegree = 0;
    /** 1フレーム毎の移動距離(横方向)[px]. */
    protected int mDx = 0;
    /** 1フレーム毎の移動距離(縦方向)[px]. */
    protected int mDy = 0;

    /**
     * コンストラクタ.
     * @param aView 移動するView.
     * @param aVelocity 移動速度.
     * @param aDegree 初期移動方向.
     */
    public FBL_LinearUniformMotionAgent(View aView, double aVelocity, double aDegree) {
        super(aView);
        mVelocity = aVelocity;
        mDegree = aDegree;
        if (mTravelingView != null) {
            float density = mTravelingView.getResources().getDisplayMetrics().density;
            mDx = (int) Math.round (mVelocity * Math.cos(Math.toRadians(mDegree)) * density * mInterval / 1000);
            mDy = (int) Math.round (mVelocity * Math.sin(Math.toRadians(mDegree)) * density * mInterval / 1000);
        }
    }

    /**
     * 移動の自動終了判定.
     * 親Viewの境界にぶつかったら停止する.
     * @return true:終了する, false:終了しない.
     */
    @Override
    protected boolean isAutoStop() {
        boolean ret = false;
        if (mTravelingView == null || mTimer == null) {
            ret = true;
        }
        return ret;
    }

    /**
     * 内部状態の更新(1フレーム毎に移動する前に呼び出される).
     */
    @Override
    protected void update() {
        if (mTravelingView != null) {
            mLeft = mTravelingView.getLeft() + mDx;
            mRight = mTravelingView.getRight() + mDx;
            mTop = mTravelingView.getTop() + mDy;
            mBottom = mTravelingView.getBottom() + mDy;

            // 親View境界衝突判定.
            ViewGroup parent = (ViewGroup) mTravelingView.getParent();
            if (mDx >= 0 && mRight > parent.getWidth()) {
                mDx *= -1;
            }
            if (mDx <= 0 && mLeft < 0) {
                mDx *= -1;
            }
            if (mDy >= 0 && mBottom > parent.getHeight()) {
                mDy *= -1;
            }
            if (mDy <= 0 && mTop < 0) {
                mDy *= -1;
            }
        }
    }
}
