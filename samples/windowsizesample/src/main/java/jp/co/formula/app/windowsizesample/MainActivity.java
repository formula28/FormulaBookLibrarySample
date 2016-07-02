package jp.co.formula.app.windowsizesample;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import jp.co.formula.app.formulabooklibrary.logger.FBL_Logger;
import jp.co.formula.app.formulabooklibrary.util.FBL_WindowUtil;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.helloworld);

        // ステータスバー非表示
        final View decor = getWindow().getDecorView();
        decor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_IMMERSIVE
                        );
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        FBL_Logger.enter(hasFocus);
        StringBuffer sb = new StringBuffer();
        sb.append("Some Object Size [px].\n");
        sb.append(String.format("density: %f.\n\n", FBL_WindowUtil.getDisplayMetrics(this).density).toString());
        Point p = FBL_WindowUtil.getHardwareDisplaySize(this);
        sb.append(String.format("+ Hardware Display Size: (%d, %d)\n", p.x, p.y).toString());
        p = FBL_WindowUtil.getApplicationDisplaySize(this);
        sb.append(String.format("+ Applicaton Display Size: (%d, %d)\n    * (HDS - NBS)\n", p.x, p.y).toString());
        p = FBL_WindowUtil.getContentDisplaySize(this);
        sb.append(String.format("+ Content Display Size: (%d, %d)\n    * (HDS - NBS - SBS - ABS)\n", p.x, p.y).toString());
        p = FBL_WindowUtil.getNavigationBarSize(this);
        sb.append(String.format("+ NavigationBar Size: (%d, %d)\n", p.x, p.y).toString());
        p = FBL_WindowUtil.getStatusBarSize(this);
        sb.append(String.format("+ StatusBar Size: (%d, %d)\n", p.x, p.y).toString());
        p = FBL_WindowUtil.getActionBarSize(this);
        sb.append(String.format("+ ActionBar Size: (%d, %d)\n", p.x, p.y).toString());
        tv.setText(sb.toString());
        FBL_Logger.leave();
    }
}
