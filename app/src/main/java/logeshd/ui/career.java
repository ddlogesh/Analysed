package logeshd.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import spencerstudios.com.bungeelib.Bungee;

public class career extends AppCompatActivity {

    private TextView tv_title1,tv_msg;
    ImageView iv_icon,iv1,iv2,iv3,iv4;
    private boolean current=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.career);

        tv_title1=findViewById(R.id.tv_title1);
        tv_msg=findViewById(R.id.tv_msg1);
        //iv_icon=findViewById(R.id.iv_bg);

        /*iv1=findViewById(R.id.iv1);
        iv2=findViewById(R.id.iv2);
        iv3=findViewById(R.id.iv3);
        iv4=findViewById(R.id.iv4);*/

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/arial.ttf");
        tv_title1.setTypeface(custom_font1);
        tv_msg.setTypeface(custom_font2);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*tv_title1.setVisibility(View.INVISIBLE);
        tv_title2.setVisibility(View.INVISIBLE);
        tv_title3.setVisibility(View.INVISIBLE);
        tv_msg.setVisibility(View.INVISIBLE);
        iv_icon.setVisibility(View.INVISIBLE);

        iv1.setVisibility(View.INVISIBLE);
        iv2.setVisibility(View.INVISIBLE);
        iv3.setVisibility(View.INVISIBLE);
        iv4.setVisibility(View.INVISIBLE);

        final ScrollView sv=findViewById(R.id.scroll);
        sv.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrolly = sv.getScrollY();
                Log.d("ddlogesh1", "scrolly: " + scrolly);
                if(scrolly>250 && current){
                    current=false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv1.setVisibility(View.VISIBLE);
                            iv2.setVisibility(View.VISIBLE);
                            iv3.setVisibility(View.VISIBLE);
                            iv4.setVisibility(View.VISIBLE);
                            tv_title3.setVisibility(View.VISIBLE);

                            YoYo.with(Techniques.BounceInLeft).duration(500).playOn(iv1);
                            YoYo.with(Techniques.BounceIn).duration(700).playOn(iv2);
                            YoYo.with(Techniques.BounceInRight).duration(500).playOn(iv3);

                            YoYo.with(Techniques.BounceInLeft).duration(500).playOn(iv4);
                            YoYo.with(Techniques.BounceIn).duration(700).playOn(iv5);
                            YoYo.with(Techniques.BounceInRight).duration(500).playOn(iv6);

                            YoYo.with(Techniques.FlipInY).duration(300).playOn(tv_title3);
                            YoYo.with(Techniques.BounceIn).duration(700).playOn(iv7);
                        }
                    },50);
                }
                if(scrolly<50)
                    current=true;
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_title2.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FlipInY).duration(300).playOn(tv_title2);
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
        },500);*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*Intent i1=new Intent(getApplicationContext(), dashboard.class);
        startActivity(i1);
        Bungee.inAndOut(career.this);*/
    }
}
