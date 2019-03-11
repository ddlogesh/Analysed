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

public class listSearchRecommend extends ArrayAdapter<itemDrawer> {

    public listSearchRecommend(Context context, ArrayList<itemDrawer> history){
        super(context, 0, history);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        itemDrawer p= getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_list_recommend, parent, false);
        }

        TextView tv_job_title = (TextView) convertView.findViewById(R.id.tv_job_title);
        tv_job_title.setText(p.getTitle());

        ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
        int drawable = getContext().getResources().getIdentifier("apple_icon","drawable",getContext().getPackageName());
        iv_icon.setImageResource(drawable);

        return convertView;
    }
}