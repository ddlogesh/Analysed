package logeshd.analysed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.pwittchen.swipe.library.rx2.Swipe;
import com.github.pwittchen.swipe.library.rx2.SwipeListener;
import spencerstudios.com.bungeelib.Bungee;

public class takeTour extends AppCompatActivity {
    private boolean[] arr = new boolean[]{true, false, false, false, false, false};
    private int[] img_arr = new int[]{R.drawable.tour_1, R.drawable.tour_2, R.drawable.tour_3, R.drawable.tour_4, R.drawable.tour_5, R.drawable.tour_6};
    private int k = 0;
    private int left = 1;
    
    Swipe swipe;
    TextView tv_contact;
    TextView tv_finish;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_tour);
        
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        tv_finish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));
                Bungee.inAndOut(takeTour.this);
            }
        });
        
        swipe = new Swipe();
        swipe.setListener(new SwipeListener() {
            @Override
            public void onSwipingLeft(MotionEvent event) {
                
            }

            @Override
            public boolean onSwipedLeft(MotionEvent event) {
                arr[k] = false;
                if (k + 1 == 6) {
                    k = 5;
                    left = -1;
                } else {
                    k = k + 1;
                    left = 1;
                }
                arr[k] = true;
                if (k == 5) {
                    tv_contact.setVisibility(View.VISIBLE);
                    tv_finish.setVisibility(View.VISIBLE);
                }
                else {
                    tv_contact.setVisibility(View.GONE);
                    tv_finish.setVisibility(View.GONE);
                }
                setSwiper();
                return true;
            }

            @Override
            public void onSwipingRight(MotionEvent event) {
            }

            @Override
            public boolean onSwipedRight(MotionEvent event) {
                arr[k] = false;
                if (k - 1 == -1) {
                    k = 0;
                    left = -1;
                } else {
                    k = k - 1;
                    left = 0;
                }
                arr[k] = true;
                if (k == 5) {
                    tv_contact.setVisibility(View.VISIBLE);
                    tv_finish.setVisibility(View.VISIBLE);
                }
                else {
                    tv_contact.setVisibility(View.GONE);
                    tv_finish.setVisibility(View.GONE);
                }
                setSwiper();
                return true;
            }

            @Override
            public void onSwipingUp(MotionEvent event) {
            }

            @Override
            public boolean onSwipedUp(MotionEvent event) {
                return false;
            }

            @Override
            public void onSwipingDown(MotionEvent event) {
            }

            @Override
            public boolean onSwipedDown(MotionEvent event) {
                return false;
            }
        });
    }

    private void setSwiper() {
        int i;
        for (i = 0; i < 6; i++) {
            ((ImageView) findViewById(getResources().getIdentifier("iv_tab"+Integer.toString(i + 1), "id", getPackageName()))).setBackgroundResource(R.drawable.inactive_light_grey_circle);
        }
        for (i = 0; i < 6; i++) {
            ImageView iv_bg = (ImageView) findViewById(R.id.iv_bg);
            if (arr[i]) {
                iv_bg.setImageResource(img_arr[i]);
                ((ImageView) findViewById(getResources().getIdentifier("iv_tab"+Integer.toString(i + 1), "id", getPackageName()))).setBackgroundResource(R.drawable.active_black_circle);
                if (left == 1) {
                    YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_bg);
                } else if (left == 0) {
                    YoYo.with(Techniques.SlideInLeft).duration(500).playOn(iv_bg);
                }
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), login.class));
        Bungee.inAndOut(this);
    }
}
