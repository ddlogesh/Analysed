package logeshd.analysed.recruiter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import logeshd.analysed.R;
import logeshd.analysed.jobSeeker.dashboard;

public class startHiring0 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.r_start_hiring0);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        EditText ev_recruiter_name1 = (EditText) findViewById(R.id.ev_recruiter_name1);
        EditText ev_organization_name1 = (EditText) findViewById(R.id.ev_organization_name1);
        EditText ev_designation = (EditText) findViewById(R.id.ev_designation);
        EditText ev_position = (EditText) findViewById(R.id.ev_position);
        EditText ev_skill = (EditText) findViewById(R.id.ev_skill);
        EditText ev_qualification = (EditText) findViewById(R.id.ev_qualification);
        EditText ev_experience = (EditText) findViewById(R.id.ev_experience);
        EditText ev_job_location = (EditText) findViewById(R.id.ev_job_location);
        EditText ev_package = (EditText) findViewById(R.id.ev_package);
        EditText ev_timings = (EditText) findViewById(R.id.ev_timings);
        EditText ev_description = (EditText) findViewById(R.id.ev_description);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        tv_title.setTypeface(custom_font2);
        ev_recruiter_name1.setTypeface(custom_font1);
        ev_organization_name1.setTypeface(custom_font1);
        ev_designation.setTypeface(custom_font1);
        ev_position.setTypeface(custom_font1);
        ev_skill.setTypeface(custom_font1);
        ev_qualification.setTypeface(custom_font1);
        ev_experience.setTypeface(custom_font1);
        ev_job_location.setTypeface(custom_font1);
        ev_package.setTypeface(custom_font1);
        ev_timings.setTypeface(custom_font1);
        ev_description.setTypeface(custom_font1);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dashboard.class));
            }
        });
    }
}
