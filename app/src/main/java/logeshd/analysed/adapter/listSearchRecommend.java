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

public class listSearchRecommend extends ArrayAdapter<itemDrawer> {
    public listSearchRecommend(Context context, ArrayList<itemDrawer> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        itemDrawer p = (itemDrawer) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_list_recommend, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.tv_job_title)).setText(p.getTitle());
        ((ImageView) convertView.findViewById(R.id.iv_icon)).setImageResource(getContext().getResources().getIdentifier("facino_icon", "drawable", getContext().getPackageName()));

        return convertView;
    }
}
