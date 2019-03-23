package logeshd.analysed.common;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import logeshd.analysed.R;
import spencerstudios.com.bungeelib.Bungee;

public class terms extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_terms);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");

        for (int i = 0; i < 8; i++) {
            TextView title = (TextView) findViewById(getResources().getIdentifier("tv_title"+Integer.toString(i + 1), "id", getPackageName()));
            TextView msg = (TextView) findViewById(getResources().getIdentifier("tv_msg"+Integer.toString(i + 1), "id", getPackageName()));

            title.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG | title.getPaintFlags());
            title.setTypeface(custom_font1);
            msg.setTypeface(custom_font2);
        }

        ((ImageView) findViewById(R.id.bt_back)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));
                Bungee.slideDown(terms.this);
            }
        });
    }

    public void onBackPressed() {
        startActivity(new Intent(this, login.class));
        Bungee.slideDown(this);
    }
}
