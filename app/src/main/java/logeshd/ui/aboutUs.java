package logeshd.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.pwittchen.swipe.library.rx2.Swipe;
import com.github.pwittchen.swipe.library.rx2.SwipeListener;

import spencerstudios.com.bungeelib.Bungee;

public class aboutUs extends AppCompatActivity {

    private TextView tv_title1,tv_title2,tv_msg;
    ImageView iv_icon;

    Swipe swipe;
    private boolean arr[]={true,false,false,false,false,false,false};
    private int tv_arr[]={R.string.about,R.string.about,R.string.about,R.string.core,R.string.core,R.string.core,R.string.about};
    private int tv_ans_arr[]={R.string.ans_about_1,R.string.ans_about_2,R.string.ans_about_3,R.string.ans_core_1,R.string.ans_core_2,R.string.ans_core_3,R.string.ans_about_4};
    private int img_arr[]={R.drawable.img5,R.drawable.img5,R.drawable.img2,R.drawable.img2,R.drawable.img2,R.drawable.img2,R.drawable.img2};
    private int k=0;
    private int left=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        tv_title1=findViewById(R.id.tv_title1);
        tv_title2=findViewById(R.id.tv_title2);
        tv_msg=findViewById(R.id.tv_msg1);
        iv_icon=findViewById(R.id.iv_bg);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/arial_bold.ttf");
        tv_title1.setTypeface(custom_font2);
        tv_msg.setTypeface(custom_font1);
        tv_title2.setTypeface(custom_font1);

        tv_title2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_title2.getText().equals(getString(R.string.contact_next))) {
                    Intent i1 = new Intent(aboutUs.this, dashboard.class);
                    startActivity(i1);
                    Bungee.inAndOut(aboutUs.this);
                }
                else {
                    arr[k]=false;
                    if(k+1==7){
                        k=6;
                        left=-1;
                    }
                    else{
                        k++;
                        left=1;
                    }
                    arr[k]=true;
                    if(k==6)
                        tv_title2.setText(R.string.contact_next);
                    else
                        tv_title2.setText(R.string.next);
                    setSwiper();
                }
            }
        });

        swipe = new Swipe();
        swipe.setListener(new SwipeListener() {
            @Override
            public void onSwipingLeft(final MotionEvent event) {
            }

            @Override
            public boolean onSwipedLeft(final MotionEvent event) {
                arr[k]=false;
                if(k+1==7){
                    k=6;
                    left=-1;
                }
                else{
                    k++;
                    left=1;
                }
                arr[k]=true;
                if(k==6)
                    tv_title2.setText(R.string.contact_next);
                else
                    tv_title2.setText(R.string.next);
                setSwiper();
                return true;
            }

            @Override
            public void onSwipingRight(final MotionEvent event) {

            }

            @Override
            public boolean onSwipedRight(final MotionEvent event) {
                arr[k]=false;
                if(k-1==-1){
                    k=0;
                    left=-1;
                }
                else{
                    k--;
                    left=0;
                }
                arr[k]=true;
                if(k==6)
                    tv_title2.setText(R.string.contact_next);
                else
                    tv_title2.setText(R.string.next);
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

    @Override
    protected void onResume() {
        super.onResume();

        tv_title1.setVisibility(View.INVISIBLE);
        tv_title2.setVisibility(View.INVISIBLE);
        tv_msg.setVisibility(View.INVISIBLE);
        iv_icon.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_title2.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.Tada).duration(200).playOn(tv_title2);
            }
        },1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_msg.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeInUp).duration(300).playOn(tv_msg);
            }
        },1200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_title1.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FlipInY).duration(300).playOn(tv_title1);
            }
        },900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iv_icon.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.ZoomIn).duration(300).playOn(iv_icon);
            }
        },500);
    }

    private void setSwiper(){
        for(int i=0;i<7;i++){
            ImageView bg=findViewById(R.id.iv_bg);
            if(arr[i]) {
                bg.setImageResource(img_arr[i]);
                tv_title1.setText(tv_arr[i]);
                tv_msg.setText(tv_ans_arr[i]);

                if(left==1) {
                    YoYo.with(Techniques.SlideInRight).duration(500).playOn(tv_title1);
                    YoYo.with(Techniques.SlideInRight).duration(500).playOn(tv_msg);
                    YoYo.with(Techniques.SlideInRight).duration(500).playOn(iv_icon);
                }
                else if(left==0){
                    YoYo.with(Techniques.SlideInLeft).duration(500).playOn(tv_title1);
                    YoYo.with(Techniques.SlideInLeft).duration(500).playOn(tv_msg);
                    YoYo.with(Techniques.SlideInLeft).duration(500).playOn(iv_icon);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        YoYo.with(Techniques.Tada).duration(200).playOn(tv_title2);
                    }
                },500);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        Intent i1=new Intent(aboutUs.this, dashboard.class);
        startActivity(i1);
        Bungee.inAndOut(aboutUs.this);
    }
}
