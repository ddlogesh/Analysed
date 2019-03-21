package logeshd.analysed;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import logeshd.analysed.adapter.listContacts;
import logeshd.analysed.adapter.listTasks;
import logeshd.analysed.classes.contactDetails;

public class toDoList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list);

        TextView tv_date = (TextView) findViewById(R.id.tv_date);
        TextView tv_count = (TextView) findViewById(R.id.tv_count);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_bold.ttf");

        tv_date.setTypeface(custom_font2);
        tv_count.setTypeface(custom_font1);

        ListView l1 = (ListView) findViewById(R.id.list_task);
        final ArrayList<contactDetails> arr = new ArrayList();
        final listTasks adapter = new listTasks(this, arr);
        adapter.clear();
        adapter.add(new contactDetails("Meeting with Kiran", "Reminder", true));
        adapter.add(new contactDetails("Wake Up", "Alarm", true));
        adapter.add(new contactDetails("Meeting with Kiran", "Reminder", false));
        adapter.add(new contactDetails("Wake Up", "Alarm", false));
        l1.setAdapter(adapter);
    }
}
