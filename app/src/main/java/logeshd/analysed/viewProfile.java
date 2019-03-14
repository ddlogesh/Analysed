package logeshd.analysed;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class viewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        TextView tv_name = findViewById(R.id.tv_name);
        TextView tv_profession = findViewById(R.id.tv_profession);
        TextView tv_text1 = findViewById(R.id.tv_text1);
        TextView tv_progress = findViewById(R.id.tv_progress);

        TextView tv_first_name1 = findViewById(R.id.tv_first_name1);
        TextView ev_first_name1 = findViewById(R.id.ev_first_name1 );
        TextView tv_last_name1 = findViewById(R.id.tv_last_name1);
        TextView ev_last_name1 = findViewById(R.id.ev_last_name1);
        TextView tv_qualification = findViewById(R.id.tv_qualification);
        TextView ev_qualification = findViewById(R.id.ev_qualification);
        TextView tv_year_passing = findViewById(R.id.tv_year_passing);
        TextView ev_year_passing = findViewById(R.id.ev_year_passing);
        TextView tv_experience = findViewById(R.id.tv_experience);
        TextView ev_experience = findViewById(R.id.ev_experience);
        TextView tv_email = findViewById(R.id.tv_email);
        TextView ev_email = findViewById(R.id.ev_email);
        TextView tv_location = findViewById(R.id.tv_location);
        TextView ev_location = findViewById(R.id.ev_location);
        TextView tv_mobile = findViewById(R.id.tv_mobile);
        TextView ev_mobile = findViewById(R.id.ev_mobile);

        TextView tv_saved_count = findViewById(R.id.tv_saved_count);
        TextView tv_title2 = findViewById(R.id.tv_title2);
        TextView tv_applied_count = findViewById(R.id.tv_applied_count);
        TextView tv_title3 = findViewById(R.id.tv_title3);

        TextView complete_button = findViewById(R.id.complete_button);

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
        ev_mobile.setTypeface(custom_font1);

        tv_saved_count.setTypeface(custom_font3);
        tv_title2.setTypeface(custom_font2);
        tv_applied_count.setTypeface(custom_font3);
        tv_title3.setTypeface(custom_font2);

        complete_button.setTypeface(custom_font2);
    }
}
