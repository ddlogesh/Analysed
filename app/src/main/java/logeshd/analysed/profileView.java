package logeshd.analysed;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class profileView extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.view_profile);

        TextView tv_name = (TextView) findViewById(R.id.tv_name);
        TextView tv_profession = (TextView) findViewById(R.id.tv_profession);
        TextView tv_text1 = (TextView) findViewById(R.id.tv_text1);
        TextView tv_progress = (TextView) findViewById(R.id.tv_progress);
        TextView tv_first_name1 = (TextView) findViewById(R.id.tv_first_name1);
        TextView ev_first_name1 = (TextView) findViewById(R.id.ev_first_name1);
        TextView tv_last_name1 = (TextView) findViewById(R.id.tv_last_name1);
        TextView ev_last_name1 = (TextView) findViewById(R.id.ev_last_name1);
        TextView tv_qualification = (TextView) findViewById(R.id.tv_qualification);
        TextView ev_qualification = (TextView) findViewById(R.id.ev_qualification);
        TextView tv_year_passing = (TextView) findViewById(R.id.tv_year_passing);
        TextView ev_year_passing = (TextView) findViewById(R.id.ev_year_passing);
        TextView tv_experience = (TextView) findViewById(R.id.tv_experience);
        TextView ev_experience = (TextView) findViewById(R.id.ev_experience);
        TextView tv_email = (TextView) findViewById(R.id.tv_email);
        TextView ev_email = (TextView) findViewById(R.id.ev_email);
        TextView tv_location = (TextView) findViewById(R.id.tv_location);
        TextView ev_location = (TextView) findViewById(R.id.ev_location);
        TextView tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        TextView ev_mobile = (TextView) findViewById(R.id.ev_mobile);
        TextView tv_saved_count = (TextView) findViewById(R.id.tv_saved_count);
        TextView tv_title2 = (TextView) findViewById(R.id.tv_title2);
        TextView tv_applied_count = (TextView) findViewById(R.id.tv_applied_count);
        TextView tv_title3 = (TextView) findViewById(R.id.tv_title3);
        TextView complete_button = (TextView) findViewById(R.id.complete_button);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        tv_name.setTypeface(custom_font2);
        tv_profession.setTypeface(custom_font1);
        tv_text1.setTypeface(custom_font1);
        tv_progress.setTypeface(custom_font1);
        tv_first_name1.setTypeface(custom_font2);
        ev_first_name1.setTypeface(custom_font1);
        tv_last_name1.setTypeface(custom_font2);
        ev_last_name1.setTypeface(custom_font1);
        tv_qualification.setTypeface(custom_font2);
        ev_qualification.setTypeface(custom_font1);
        tv_year_passing.setTypeface(custom_font2);
        ev_year_passing.setTypeface(custom_font1);
        tv_experience.setTypeface(custom_font2);
        ev_experience.setTypeface(custom_font1);
        tv_email.setTypeface(custom_font2);
        ev_email.setTypeface(custom_font1);
        tv_location.setTypeface(custom_font2);
        ev_location.setTypeface(custom_font1);
        tv_mobile.setTypeface(custom_font2);
        tv_name.setTypeface(custom_font1);
        tv_saved_count.setTypeface(custom_font3);
        tv_title2.setTypeface(custom_font2);
        tv_applied_count.setTypeface(custom_font3);
        tv_title3.setTypeface(custom_font2);
        complete_button.setTypeface(custom_font2);

        tv_text1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),profileComplete.class);
                startActivity(i1);
            }
        });

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),dashboard.class);
                startActivity(i1);
            }
        });
    }
}
