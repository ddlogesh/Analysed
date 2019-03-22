package logeshd.analysed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import logeshd.analysed.adapter.listSearchRecent;
import logeshd.analysed.adapter.listSearchRecommend;
import logeshd.analysed.classes.itemDrawer;

public class search extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.search);

        ListView l1 = (ListView) findViewById(R.id.search_list_recent);
        listSearchRecent adapter = new listSearchRecent(this, new ArrayList());
        adapter.clear();
        adapter.add(new itemDrawer("UX Designer","favicon",""));
        adapter.add(new itemDrawer("iOS App Developer","favicon",""));
        l1.setAdapter(adapter);

        ListView l2 = (ListView) findViewById(R.id.search_list_recommend);
        listSearchRecommend adapter1 = new listSearchRecommend(this, new ArrayList());
        adapter1.clear();
        adapter1.add(new itemDrawer("UX Designer","favicon",""));
        adapter1.add(new itemDrawer("Senior Designer","favicon",""));
        adapter1.add(new itemDrawer("Android App Developer","favicon",""));
        adapter1.add(new itemDrawer("Project Manager","favicon",""));
        l2.setAdapter(adapter1);

        ((ImageView) findViewById(R.id.iv_home)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),dashboard.class);
                startActivity(i1);
            }
        });
    }
}
