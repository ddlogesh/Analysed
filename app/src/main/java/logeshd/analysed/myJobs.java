package logeshd.analysed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import logeshd.analysed.adapter.listJobTimeline;
import logeshd.analysed.classes.jobDetails;

public class myJobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_jobs);

        ListView l1=(ListView)findViewById(R.id.job_list_timeline);
        ArrayList<jobDetails> arr = new ArrayList<jobDetails>();
        final listJobTimeline adapter = new listJobTimeline(this, arr);
        adapter.clear();

        adapter.add(new jobDetails("apple_icon","UX Designer","Apple","May","10"));
        adapter.add(new jobDetails("apple_icon","Web Developer","Microsoft","Jun","25"));
        adapter.add(new jobDetails("apple_icon","Software Developer","Analysed","Sep","15"));
        l1.setAdapter(adapter);

        /*********************************************************************************/

        ImageView iv_home = findViewById(R.id.iv_home);
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });
    }
}
