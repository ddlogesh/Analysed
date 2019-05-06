package logeshd.analysed.common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import logeshd.analysed.R;
import logeshd.analysed.utils.SharedPref;
import spencerstudios.com.bungeelib.Bungee;

public class tour extends AppCompatActivity {
    private boolean[] arr = new boolean[]{true, false, false};
    private int[] img_arr;
    ImageView iv_bg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_tour);

        iv_bg = findViewById(R.id.iv_bg);

        if(SharedPref.getInt(getApplicationContext(),"user_role") == 1)
            img_arr = new int[]{R.drawable.tour_r_1, R.drawable.tour_r_2, R.drawable.tour_r_3};
        else
            img_arr = new int[]{R.drawable.tour_j_1, R.drawable.tour_j_2, R.drawable.tour_j_3};

        iv_bg.setImageResource(img_arr[0]);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                arr[0] = false;
                arr[1] = true;
                setSwiper();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        arr[1] = false;
                        arr[2] = true;
                        setSwiper();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(SharedPref.getInt(getApplicationContext(),"user_role") == 1)
                                    startActivity(new Intent(getApplicationContext(), logeshd.analysed.recruiter.dashboard.class));
                                else
                                    startActivity(new Intent(getApplicationContext(), logeshd.analysed.jobSeeker.dashboard.class));

                                Bungee.slideLeft(tour.this);
                            }
                        },1500);
                    }
                },1500);
            }
        },1500);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(SharedPref.getInt(getApplicationContext(),"user_role") == 1)
            startActivity(new Intent(getApplicationContext(), logeshd.analysed.recruiter.dashboard.class));
        else
            startActivity(new Intent(getApplicationContext(), logeshd.analysed.jobSeeker.dashboard.class));
    }

    private void setSwiper() {
        int i;
        for (i = 0; i < 3; i++) {
            if (arr[i]) {
                iv_bg.setImageResource(img_arr[i]);
                ((ImageView) findViewById(getResources().getIdentifier("iv_tab"+Integer.toString(i + 1), "id", getPackageName()))).setBackgroundResource(R.drawable.active_black_circle);
                YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_bg);
            }
            else
                ((ImageView) findViewById(getResources().getIdentifier("iv_tab"+Integer.toString(i + 1), "id", getPackageName()))).setBackgroundResource(R.drawable.inactive_light_grey_circle);
        }
    }

    public void onBackPressed() {
        if(SharedPref.getInt(getApplicationContext(),"user_role") == 1)
            startActivity(new Intent(getApplicationContext(), logeshd.analysed.recruiter.dashboard.class));
        else
            startActivity(new Intent(getApplicationContext(), logeshd.analysed.jobSeeker.dashboard.class));

        Bungee.slideLeft(tour.this);
    }
}
