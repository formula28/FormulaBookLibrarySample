package jp.co.formula.app.activitysstacksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import jp.co.formula.app.formulabooklibrary.logger.FBL_Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // アプリのアイコン表示.
        ImageView imgv = (ImageView) findViewById(R.id.app_icon);
        imgv.setImageDrawable(App.getInstance().getAppIcon());

        // アプリ名+バージョンコード+バージョン名表示
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("AppName:" + App.getInstance().getAppName()
                + "\nVersionCode:" + App.getInstance().getAppVersionCode()
                + "\nVersionName:" + App.getInstance().getAppVersionName()
        );

        // Activity起動ボタン.
        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FBL_Logger.enter();
                App.getInstance().startActivity(SubActivity.class);
                FBL_Logger.leave();
            }
        });
    }
}
