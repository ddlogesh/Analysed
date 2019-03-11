package logeshd.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import spencerstudios.com.bungeelib.Bungee;

public class terms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/arial.ttf");

        for(int i=0;i<8;i++){
            int t_id=getResources().getIdentifier("tv_title"+Integer.toString(i+1),"id",getPackageName());
            int m_id=getResources().getIdentifier("tv_msg"+Integer.toString(i+1),"id",getPackageName());
            TextView title=findViewById(t_id);
            TextView msg=findViewById(m_id);

            title.setPaintFlags(title.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
            title.setTypeface(custom_font1);
            msg.setTypeface(custom_font2);
        }
    }

    @Override
    public void onBackPressed() {
        //Intent i1=new Intent(terms.this, dashboard.class);
        //startActivity(i1);
        //Bungee.inAndOut(terms.this);
        super.onBackPressed();
    }
}
