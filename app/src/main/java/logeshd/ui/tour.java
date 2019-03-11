package logeshd.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.pwittchen.swipe.library.rx2.Swipe;
import com.github.pwittchen.swipe.library.rx2.SwipeListener;

import spencerstudios.com.bungeelib.Bungee;

public class tour extends AppCompatActivity {

    private static final String TAG = "ddlogesh1";;
    private int tv_arr[]={R.string.virtual,R.string.differ,R.string.services};

    private TextView tv_title;
    ImageView iv_img1,iv_img2,iv_img3,iv_next,iv_tab1,iv_tab2,iv_tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour);

        tv_title=findViewById(R.id.tv_title);
        iv_next=findViewById(R.id.iv_next);

        iv_img1=findViewById(R.id.iv_img1);
        iv_img2=findViewById(R.id.iv_img2);
        iv_img3=findViewById(R.id.iv_img3);

        iv_tab1=findViewById(R.id.iv_tab1);
        iv_tab2=findViewById(R.id.iv_tab2);
        iv_tab3=findViewById(R.id.iv_tab3);

        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        tv_title.setTypeface(custom_font2);

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iv_img1.getVisibility()==View.VISIBLE){
                    YoYo.with(Techniques.SlideOutLeft).duration(500).playOn(iv_img1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv_img1.setVisibility(View.GONE);
                        }
                    },500);
                    iv_img2.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_img2);

                    iv_tab1.setBackgroundResource(R.drawable.splash_tab2);
                    iv_tab2.setBackgroundResource(R.drawable.splash_tab1);

                    tv_title.setText(tv_arr[1]);
                }
                else if(iv_img2.getVisibility()==View.VISIBLE){
                    YoYo.with(Techniques.SlideOutLeft).duration(500).playOn(iv_img2);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv_img2.setVisibility(View.GONE);
                        }
                    },500);
                    iv_img3.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_img3);

                    iv_tab2.setBackgroundResource(R.drawable.splash_tab2);
                    iv_tab3.setBackgroundResource(R.drawable.splash_tab1);

                    tv_title.setText(tv_arr[2]);
                }
                else if(iv_img3.getVisibility()==View.VISIBLE){
                    Intent i1=new Intent(getApplicationContext(), dashboard.class);
                    startActivity(i1);
                    Bungee.slideLeft(tour.this);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(Intent.ACTION_MAIN);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent1);
    }
}