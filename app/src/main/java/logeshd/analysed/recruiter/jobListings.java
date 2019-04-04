package logeshd.analysed.recruiter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.jobSeeker.adapter.listViewJobs;
import logeshd.analysed.classes.job;

public class jobListings extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.r_job_listings);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        TextView tv_invite = (TextView) findViewById(R.id.tv_invite);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        tv_invite.setTypeface(custom_font1);
        tv_title.setTypeface(custom_font2);

        ListView l1 = (ListView) findViewById(R.id.job_list_timeline);
        listViewJobs adapter = new listViewJobs(this, new ArrayList());
        adapter.clear();
        adapter.add(new job("apple_icon", "UX Designer", "Apple", "May", "10"));
        adapter.add(new job("apple_icon", "Web Developer", "Microsoft", "Jun", "25"));
        adapter.add(new job("apple_icon", "Software Developer", "Analysed", "Sep", "15"));
        l1.setAdapter(adapter);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });
    }
}
