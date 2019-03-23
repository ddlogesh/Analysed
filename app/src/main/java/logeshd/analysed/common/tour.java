package logeshd.analysed.common;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import logeshd.analysed.R;
import logeshd.analysed.jobSeeker.dashboard;
import spencerstudios.com.bungeelib.Bungee;

public class tour extends AppCompatActivity {
    private static final String TAG = "ddlogesh1";
    ImageView iv_img1,iv_img2,iv_img3,iv_next,iv_tab1,iv_tab2,iv_tab3;

    private int[] tv_arr = new int[]{R.string.virtual, R.string.differ, R.string.services};
    private TextView tv_title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.c_tour);
        
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        iv_img1 = (ImageView) findViewById(R.id.iv_img1);
        iv_img2 = (ImageView) findViewById(R.id.iv_img2);
        iv_img3 = (ImageView) findViewById(R.id.iv_img3);
        iv_tab1 = (ImageView) findViewById(R.id.iv_tab1);
        iv_tab2 = (ImageView) findViewById(R.id.iv_tab2);
        iv_tab3 = (ImageView) findViewById(R.id.iv_tab3);
        
        tv_title.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf"));
        
        iv_next.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_img1.getVisibility() == View.VISIBLE) {
                    YoYo.with(Techniques.SlideOutLeft).duration(500).playOn(iv_img1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv_img1.setVisibility(View.GONE);
                        }
                    }, 500);

                    iv_img2.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_img2);

                    iv_tab1.setBackgroundResource(R.drawable.button_very_light_white_solid);
                    iv_tab2.setBackgroundResource(R.drawable.button_white_solid);
                    tv_title.setText(tv_arr[1]);
                }
                else if (iv_img2.getVisibility() == View.VISIBLE) {
                    YoYo.with(Techniques.SlideOutLeft).duration(500).playOn(iv_img2);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv_img2.setVisibility(View.GONE);
                        }
                    }, 500);

                    iv_img3.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_img3);

                    iv_tab2.setBackgroundResource(R.drawable.button_very_light_white_solid);
                    iv_tab3.setBackgroundResource(R.drawable.button_white_solid);
                    tv_title.setText(tv_arr[2]);
                }
                else if (iv_img3.getVisibility() == View.VISIBLE) {
                    startActivity(new Intent(getApplicationContext(), dashboard.class));
                    Bungee.slideLeft(tour.this);
                }
            }
        });
    }

    public void onBackPressed() {
        Intent intent1 = new Intent(Intent.ACTION_MAIN);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent1);
    }
}
