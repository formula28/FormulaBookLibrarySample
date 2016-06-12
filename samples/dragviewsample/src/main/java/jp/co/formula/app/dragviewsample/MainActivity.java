package jp.co.formula.app.dragviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import jp.co.formula.app.formulabooklibrary.widget.FBL_DragViewOnTouchListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.helloworld);
        text.setOnTouchListener(new FBL_DragViewOnTouchListener(text){

            @Override
            public boolean isDragStart() {
                return true;
            }

            @Override
            public boolean isDragEnd() {
                return true;
            }
        });
    }
}
