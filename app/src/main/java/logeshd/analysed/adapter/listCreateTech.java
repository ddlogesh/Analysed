package logeshd.analysed.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import logeshd.analysed.R;
import logeshd.analysed.classes.itemDrawer;

public class listCreateTech extends ArrayAdapter<itemDrawer> {
    public listCreateTech(Context context, ArrayList<itemDrawer> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        itemDrawer p = (itemDrawer) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_create_task, parent, false);
        }
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");
        TextView ev_ways = (TextView) convertView.findViewById(R.id.ev_ways);
        TextView ev_hint = (TextView) convertView.findViewById(R.id.ev_hint);

        ev_ways.setText(p.getTitle());
        ev_hint.setText(p.getUrl());

        ev_ways.setTypeface(custom_font);
        ev_hint.setTypeface(custom_font);

        return convertView;
    }
}