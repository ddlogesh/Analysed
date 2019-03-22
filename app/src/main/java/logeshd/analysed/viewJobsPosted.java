package logeshd.analysed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import logeshd.analysed.adapter.listSearchRecommend;
import logeshd.analysed.classes.itemDrawer;
import spencerstudios.com.bungeelib.Bungee;

public class viewJobsPosted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_jobs_posted);

        ListView lv = (ListView) findViewById(R.id.list_applicant);
        ArrayList<itemDrawer> arr=new ArrayList<>();
        listSearchRecommend adapter = new listSearchRecommend(this, arr);
        adapter.add(new itemDrawer("UX Designer","favicon","APPLIED"));
        adapter.add(new itemDrawer("Senior Designer","favicon","IN-TOUCH"));
        adapter.add(new itemDrawer("Android App Developer","favicon","HIRED"));
        adapter.add(new itemDrawer("Project Manager","favicon","PENDING"));
        adapter.add(new itemDrawer("Project Manager","favicon","REJECTED"));
        lv.setAdapter(adapter);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), dashboard.class));
            }
        });
    }
}
