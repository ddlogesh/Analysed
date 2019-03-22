package logeshd.analysed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import logeshd.analysed.R;
import logeshd.analysed.classes.itemDrawer;

public class listSearchRecent extends ArrayAdapter<itemDrawer> {
    public listSearchRecent(Context context, ArrayList<itemDrawer> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        itemDrawer p = (itemDrawer) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_search_recent, parent, false);
        }

        View v_line = convertView.findViewById(R.id.v_line);
        ((TextView) convertView.findViewById(R.id.tv_menu_title)).setText(p.getTitle());
        ((ImageView) convertView.findViewById(R.id.iv_icon)).setImageResource(getContext().getResources().getIdentifier("portfolio_icon", "drawable", getContext().getPackageName()));

        if (position == 1) {
            v_line.setVisibility(8);
        }

        return convertView;
    }
}
