package logeshd.analysed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import logeshd.analysed.adapter.listShareProfile;
import logeshd.analysed.classes.databaseDetails;

public class shareProfile extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.share_profile);

        ListView l1 = (ListView) findViewById(R.id.list_people);
        listShareProfile adapter = new listShareProfile(this, new ArrayList());
        adapter.clear();
        adapter.add(new databaseDetails("kiran@analysed.com", "Kiran D", "Owner", "sample_image"));
        adapter.add(new databaseDetails("logesh@analysed.com", "Logesh D", "sample_image"));
        adapter.add(new databaseDetails("sagar@analysed.com", "Sagar D", "sample_image"));
        adapter.add(new databaseDetails("vivek@analysed.com", "Vivek D", "sample_image"));
        l1.setAdapter(adapter);

        TextView tv_close_button = findViewById(R.id.tv_close_button);
        tv_close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),dashboard.class);
                startActivity(i1);
            }
        });
    }
}
