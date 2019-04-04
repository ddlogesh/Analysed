package logeshd.analysed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import logeshd.analysed.classes.recStatus;
import logeshd.analysed.recruiter.adapter.listStatus;
import logeshd.analysed.recruiter.createChallenge;
import logeshd.analysed.recruiter.createTask;
import logeshd.analysed.recruiter.dashboard;

public class viewChallengeStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_view_challenge_status);

        ListView l1 = (ListView) findViewById(R.id.list_challenge);
        final ArrayList<recStatus> arr = new ArrayList<>();
        final listStatus adapter = new listStatus(this, arr);
        adapter.clear();
        adapter.add(new recStatus(true,"Challenge1", "Desc1",1));
        adapter.add(new recStatus(true,"Challenge2", "Desc2",-1));
        //adapter.add(new recStatus(false,70));
        adapter.add(new recStatus(true,"Challenge3", "Desc3",0));
        l1.setAdapter(adapter);

        ImageView iv_home= findViewById(R.id.iv_home);
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });

        ImageView iv_create= findViewById(R.id.iv_create);
        iv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), createChallenge.class);
                startActivity(i1);
            }
        });
    }
}
