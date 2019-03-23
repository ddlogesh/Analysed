package logeshd.analysed.adapter;

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
import logeshd.analysed.classes.itemDrawer;

public class listDashboardDrawer extends ArrayAdapter<itemDrawer> {
    public listDashboardDrawer(Context context, ArrayList<itemDrawer> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        itemDrawer p = (itemDrawer) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_nav_drawer, parent, false);
        }
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_rounded.ttf");
        TextView tv_menu_title = (TextView) convertView.findViewById(R.id.tv_menu_title);
        tv_menu_title.setText(p.getTitle());
        tv_menu_title.setTypeface(custom_font);

        if (position == 0) {
            tv_menu_title.setTextColor(Color.parseColor("#458DDF"));
        } else {
            tv_menu_title.setTextColor(Color.parseColor("#000000"));
        }
        ((ImageView) convertView.findViewById(R.id.iv_icon)).setImageResource(getContext().getResources().getIdentifier(p.getUrl(), "drawable", getContext().getPackageName()));
        return convertView;
    }
}
