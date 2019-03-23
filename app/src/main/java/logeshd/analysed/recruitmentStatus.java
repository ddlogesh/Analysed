package logeshd.analysed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import logeshd.analysed.adapter.listRecStatus;
import logeshd.analysed.classes.recStatusDetails;

public class recruitmentStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_status);

        ListView l1 = (ListView) findViewById(R.id.list_task);
        final ArrayList<recStatusDetails> arr = new ArrayList();
        final listRecStatus adapter = new listRecStatus(this, arr);
        adapter.clear();
        adapter.add(new recStatusDetails(true,"Task1", "Desc1",1));
        adapter.add(new recStatusDetails(true,"Task2", "Desc2",0));
        adapter.add(new recStatusDetails(false,70));
        adapter.add(new recStatusDetails(true,"Task3", "Desc3",-1));
        l1.setAdapter(adapter);

        ImageView iv_home= findViewById(R.id.iv_home);
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),dashboard.class);
                startActivity(i1);
            }
        });

        ImageView iv_create= findViewById(R.id.iv_create);
        iv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),createTask.class);
                startActivity(i1);
            }
        });
    }
}
