package logeshd.analysed.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import logeshd.analysed.R;
import logeshd.analysed.recruiter.viewTasks;

public class splashScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.c_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i1=new Intent(getApplicationContext(), viewTasks.class);
                startActivity(i1);
            }
        },100);
    }

    //3500
}
