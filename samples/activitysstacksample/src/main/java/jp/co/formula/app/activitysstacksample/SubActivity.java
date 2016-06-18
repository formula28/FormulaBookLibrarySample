package jp.co.formula.app.activitysstacksample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import jp.co.formula.app.formulabooklibrary.logger.FBL_Logger;

/**
 * Created by @formula on 2016/06/18.
 */
public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

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

        // 全Activity終了ボタン.
        Button fbt = (Button) findViewById(R.id.finish_button);
        fbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FBL_Logger.enter();
                App.getInstance().finishAllAct();
                FBL_Logger.leave();
            }
        });
    }
}
