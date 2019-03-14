package logeshd.analysed;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import logeshd.analysed.adapter.listCreateTech;
import logeshd.analysed.classes.itemDrawer;

public class createTask extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.create_task);

        final ListView l1 = (ListView) findViewById(R.id.create_task_tech_list);
        ArrayList<itemDrawer> arr = new ArrayList();
        listCreateTech adapter = new listCreateTech(this, arr);
        adapter.clear();
        adapter.add(new itemDrawer("Technique1", "Hint1"));
        adapter.add(new itemDrawer("Technique2", "Hint2"));
        l1.setAdapter(adapter);

        Spinner sp_speaker = (Spinner) findViewById(R.id.sp_speaker);
        List<String> list1 = new ArrayList();
        list1.add("Logesh");
        list1.add("Vivek");
        list1.add("Nandan");
        list1.add("Select speaker");
        ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list1) {
            public int getCount() {
                return 3;
            }
        };
        sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_speaker.setAdapter(sp_adapter);
        sp_speaker.setSelection(3);
        sp_speaker.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 3) {
                    ((TextView) parent.getChildAt(0)).setTextColor(ViewCompat.MEASURED_STATE_MASK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView tv_view = (TextView) findViewById(R.id.tv_view);
        tv_view.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                l1.setVisibility(View.VISIBLE);
            }
        });

        ImageView iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_home.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),dashboard.class);
                startActivity(i1);
            }
        });

        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        TextView tv_create = (TextView) findViewById(R.id.tv_create);
        TextView tv_add = (TextView) findViewById(R.id.tv_add);
        TextView tv_plus = (TextView) findViewById(R.id.tv_plus);

        EditText ev_name = (EditText) findViewById(R.id.ev_name);
        EditText ev_description = (EditText) findViewById(R.id.ev_description);
        EditText ev_accuracy = (EditText) findViewById(R.id.ev_accuracy);
        EditText ev_deadline = (EditText) findViewById(R.id.ev_deadline);
        EditText ev_duration = (EditText) findViewById(R.id.ev_duration);
        EditText ev_ways = (EditText) findViewById(R.id.ev_ways);
        EditText ev_hint = (EditText) findViewById(R.id.ev_hint);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");

        tv_title.setTypeface(custom_font1);
        tv_create.setTypeface(custom_font2);
        tv_add.setTypeface(custom_font2);
        tv_view.setTypeface(custom_font3);
        tv_plus.setTypeface(custom_font3);

        ev_name.setTypeface(custom_font3);
        ev_description.setTypeface(custom_font3);
        ev_accuracy.setTypeface(custom_font3);
        ev_deadline.setTypeface(custom_font3);
        ev_duration.setTypeface(custom_font3);
        ev_ways.setTypeface(custom_font3);
        ev_hint.setTypeface(custom_font3);
    }
}
