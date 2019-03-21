package logeshd.analysed.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.classes.contactDetails;

public class listTasks extends ArrayAdapter<contactDetails> {
    public listTasks(Context context, ArrayList<contactDetails> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        contactDetails p = (contactDetails) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_tasks, parent, false);
        }

        TextView tv_task= convertView.findViewById(R.id.tv_task);
        TextView tv_tag= convertView.findViewById(R.id.tv_tag);

        Typeface custom_font1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");

        tv_task.setTypeface(custom_font1);
        tv_tag.setTypeface(custom_font2);

        tv_task.setText(p.getName());
        tv_tag.setText(p.getMobile());

        String[] colors = new String[]{"to_do_button_blue", "to_do_button_red", "to_do_button_orange"};
        tv_tag.setBackgroundResource(getContext().getResources().getIdentifier(colors[position % 3], "drawable", getContext().getPackageName()));

        RadioButton ck_select = convertView.findViewById(R.id.rb_task);
        ck_select.setChecked(p.getStatus());

        return convertView;
    }
}
