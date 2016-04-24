package jp.co.formula.app.formulabooklibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import jp.co.formula.app.formulabooklibrary.R;

/**
 * Created by @formula on 2016/04/23.
 */
public class FBLTreeLayout extends LinearLayout {
    View mRootView;
    ImageView mItemIcon;
    TextView mItemTitle;
    public FBLTreeLayout(Context context) {
        this(context, null);
    }
    public FBLTreeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * コンストラクタ.
     * @param context コンテキスト.
     * @param attrs 属性値.
     * @param defStyleAttr スタイル.
     */
    public FBLTreeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRootView = inflate(context, R.layout.fbl_tree_layout, this);
        mItemIcon = (ImageView) mRootView.findViewById(R.id.item_icon);
        mItemTitle = (TextView) mRootView.findViewById(R.id.item_title);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FBLTreeLayout);
        if (a != null) {
            Drawable icon = a.getDrawable(R.styleable.FBLTreeLayout_fbl_icon);
            if (icon != null) {
                mItemIcon.setImageDrawable(icon);
            }
            String title = a.getString(R.styleable.FBLTreeLayout_fbl_title);
            if (title != null) {
                mItemTitle.setText(title);
            }
        }
    }
}
