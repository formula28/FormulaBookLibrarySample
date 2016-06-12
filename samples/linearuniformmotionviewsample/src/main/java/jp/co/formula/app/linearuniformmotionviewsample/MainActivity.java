package jp.co.formula.app.linearuniformmotionviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import jp.co.formula.app.formulabooklibrary.widget.FBL_LinearUniformMotionAgent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = (TextView) findViewById(R.id.helloworld);
        FBL_LinearUniformMotionAgent agent = new FBL_LinearUniformMotionAgent(text, 300, 45);
        agent.startTravel();
    }
}
