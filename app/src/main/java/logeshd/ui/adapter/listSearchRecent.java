package logeshd.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import logeshd.ui.R;
import logeshd.ui.classes.itemDrawer;

public class listSearchRecent extends ArrayAdapter<itemDrawer> {

    public listSearchRecent(Context context, ArrayList<itemDrawer> history){
        super(context, 0, history);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        itemDrawer p= getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_list_recent, parent, false);
        }

        View v_line = (View) convertView.findViewById(R.id.v_line);
        TextView tv_menu_title = (TextView) convertView.findViewById(R.id.tv_menu_title);
        tv_menu_title.setText(p.getTitle());

        ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
        int drawable = getContext().getResources().getIdentifier("portfolio_icon","drawable",getContext().getPackageName());
        iv_icon.setImageResource(drawable);

        if(position==1)
            v_line.setVisibility(View.GONE);

        return convertView;
    }
}