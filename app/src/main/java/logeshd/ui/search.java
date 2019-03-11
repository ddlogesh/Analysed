package logeshd.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import logeshd.ui.adapter.listSearchRecent;
import logeshd.ui.adapter.listSearchRecommend;
import logeshd.ui.adapter.listViewDrawer;
import logeshd.ui.classes.itemDrawer;

public class search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        ListView l1=(ListView)findViewById(R.id.search_list_recent);
        ArrayList<itemDrawer> arr = new ArrayList<itemDrawer>();
        final listSearchRecent adapter = new listSearchRecent(this, arr);
        adapter.clear();

        adapter.add(new itemDrawer("UX Designer"));
        adapter.add(new itemDrawer("iOS App Developer"));
        l1.setAdapter(adapter);

        ListView l2=(ListView)findViewById(R.id.search_list_recommend);
        ArrayList<itemDrawer> arr1 = new ArrayList<itemDrawer>();
        final listSearchRecommend adapter1 = new listSearchRecommend(this, arr1);
        adapter1.clear();

        adapter1.add(new itemDrawer("UX Designer"));
        adapter1.add(new itemDrawer("Senior Designer"));
        adapter1.add(new itemDrawer("Android App Developer"));
        adapter1.add(new itemDrawer("Project Manager"));
        l2.setAdapter(adapter1);

        ImageView iv_home = findViewById(R.id.iv_home);
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.super.onBackPressed();
            }
        });
    }
}
