package logeshd.analysed.jobSeeker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.classes.drawer;

public class listViewTasksComp extends ArrayAdapter<drawer> {
    public listViewTasksComp(Context context, ArrayList<drawer> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        drawer p = (drawer) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.j_list_view_tasks_completed, parent, false);
        }

        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_date= (TextView) convertView.findViewById(R.id.tv_date);
        tv_title.setText(p.getTitle());
        tv_date.setText(p.getUrl());

        return convertView;
    }
}
