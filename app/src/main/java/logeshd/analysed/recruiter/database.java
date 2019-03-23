package logeshd.analysed.recruiter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.recruiter.adapter.listDatabase;
import logeshd.analysed.jobSeeker.dashboard;

public class database extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_database);

        ListView l1 = (ListView) findViewById(R.id.lv_database);
        listDatabase adapter = new listDatabase(this, new ArrayList());
        adapter.clear();
        adapter.add(new logeshd.analysed.classes.database("sample_image", "Logesh D", "App Developer", "url"));
        adapter.add(new logeshd.analysed.classes.database("sample_image", "Sagar G", "UI/UX Designer", "url"));
        adapter.add(new logeshd.analysed.classes.database("sample_image", "Kiran", "Php Developer", "url"));
        l1.setAdapter(adapter);

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        EditText ev_skills = (EditText) findViewById(R.id.ev_skills);
        EditText ev_qualification = (EditText) findViewById(R.id.ev_qualification);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        tv_title.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf"));
        ev_skills.setTypeface(custom_font1);
        ev_qualification.setTypeface(custom_font1);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });
    }
}
