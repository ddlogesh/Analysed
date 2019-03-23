package logeshd.analysed.jobSeeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.jobSeeker.adapter.listSearchRecommend;
import logeshd.analysed.classes.drawer;

public class viewJobsApplied extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_view_jobs_applied);

        ListView lv = (ListView) findViewById(R.id.list_applicant);
        ArrayList<drawer> arr=new ArrayList<>();
        listSearchRecommend adapter = new listSearchRecommend(this, arr);
        adapter.add(new drawer("UX Designer","favicon","APPLIED"));
        adapter.add(new drawer("Senior Designer","favicon","IN-TOUCH"));
        adapter.add(new drawer("Android App Developer","favicon","HIRED"));
        adapter.add(new drawer("Project Manager","favicon","PENDING"));
        adapter.add(new drawer("Project Manager","favicon","REJECTED"));
        lv.setAdapter(adapter);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dashboard.class));
            }
        });
    }
}
