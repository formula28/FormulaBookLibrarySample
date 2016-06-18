package jp.co.formula.app.formulabooklibrary.widget;

import android.view.MotionEvent;
import android.view.View;

import jp.co.formula.app.formulabooklibrary.logger.FBL_Logger;

/**
 * Viewをドラッグして位置を移動できるようにするためのOnTouchListener実装.
 * Created by @formula on 2016/06/11.
 */
abstract public class FBL_DragViewOnTouchListener implements View.OnTouchListener {
    /** ドラッグ中フラグ. */
    private boolean mIsDragStarted = false;
    /** ドラッグするView. */
    private View mDragView;
    /** 前回タッチ位置(X). */
    private float mOldX = 0f;
    /** 前回タッチ位置(Y). */
    private float mOldY = 0f;

    /**
     * コンストラクタ.
     * @param aDragView ドラッグ対象のView.
     */
    public FBL_DragViewOnTouchListener(View aDragView) {
        mDragView = aDragView;
    }
    /**
     * ドラッグ開始判定(タッチダウン時にドラッグを開始するかの判定方法を実装すること).
     * @return true:ドラッグ開始する, false:ドラッグ開始しない.
     */
    abstract public boolean isDragStart();

    /**
     * ドラッグ終了判定(タッチアップ時にドラッグを開始するかの判定方法を実装すること).
     * @return true:ドラッグ終了する, false:ドラッグ終了しない.
     */
    abstract public boolean isDragEnd();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        FBL_Logger.enter(motionEvent.getAction());

        switch (motionEvent.getAction()) {
        case MotionEvent.ACTION_DOWN:
            mIsDragStarted = isDragStart();
            mOldX = motionEvent.getRawX();
            mOldY = motionEvent.getRawY();
            break;
        case MotionEvent.ACTION_UP:
            mIsDragStarted = !isDragEnd();
            break;
        case MotionEvent.ACTION_MOVE:
            if (mDragView != null && mIsDragStarted) {
                int left = mDragView.getLeft() + (int)(motionEvent.getRawX() - mOldX);
                int top = mDragView.getTop() + (int)(motionEvent.getRawY() - mOldY);
                mDragView.layout(left, top, left + mDragView.getWidth(), top + mDragView.getHeight());
                mOldX = motionEvent.getRawX();
                mOldY = motionEvent.getRawY();
            }
            break;
        default:
            break;
        }

        FBL_Logger.leave();
        return true;
    }
}
