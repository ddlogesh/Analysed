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

public class listViewDrawer extends ArrayAdapter<itemDrawer> {

    public listViewDrawer(Context context, ArrayList<itemDrawer> history){
        super(context, 0, history);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        itemDrawer p= getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_drawer, parent, false);
        }

        TextView tv_menu_title = (TextView) convertView.findViewById(R.id.tv_menu_title);
        tv_menu_title.setText(p.getTitle());

        ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
        int drawable = getContext().getResources().getIdentifier(p.getUrl(),"drawable",getContext().getPackageName());
        iv_icon.setImageResource(drawable);

        return convertView;
    }
}