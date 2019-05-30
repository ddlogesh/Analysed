package logeshd.analysed.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import logeshd.analysed.R;
import logeshd.analysed.utils.SharedPref;

public class splashScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(SharedPref.getBoolean(getApplicationContext(),"is_logged_in")){
                    if(SharedPref.getInt(getApplicationContext(),"user_role") == 1)
                        startActivity(new Intent(getApplicationContext(), logeshd.analysed.recruiter.dashboard.class));
                    else
                        startActivity(new Intent(getApplicationContext(), logeshd.analysed.jobSeeker.dashboard.class));
                }
                else
                    startActivity(new Intent(getApplicationContext(), login.class));
            }
        },3500);
    }

    //3500
}

/*
/var/www/analysed.in/analysed/webservices/common
/var/www/analysed.in/analysed/webservices/jobseeker
/var/www/analysed.in/analysed/webservices/recruiter
/var/www/analysed.in/analysed/Pages/dashboard
 */