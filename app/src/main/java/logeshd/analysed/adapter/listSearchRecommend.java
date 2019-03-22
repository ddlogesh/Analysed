package logeshd.analysed.adapter;

import android.content.Context;
import android.graphics.Color;
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_search_recommend, parent, false);
        }

        TextView tv_job_title=convertView.findViewById(R.id.tv_job_title);
        TextView tv_status=convertView.findViewById(R.id.tv_status);

        tv_job_title.setText(p.getTitle());
        tv_status.setText(p.getStatus());
        
        if(p.getStatus().equals("APPLIED"))
            tv_status.setTextColor(Color.parseColor("#07CC07"));
        else if(p.getStatus().equals("PENDING"))
            tv_status.setTextColor(Color.parseColor("#FF5900"));
        else if(p.getStatus().equals("IN-TOUCH"))
            tv_status.setTextColor(Color.parseColor("#FF5900"));
        else if(p.getStatus().equals("REJECTED"))
            tv_status.setTextColor(Color.parseColor("#ff0000"));
        else if(p.getStatus().equals("HIRED"))
            tv_status.setTextColor(Color.parseColor("#07CC07"));

        ((ImageView) convertView.findViewById(R.id.iv_icon)).setImageResource(getContext().getResources().getIdentifier("facino_icon", "drawable", getContext().getPackageName()));

        return convertView;
    }
}
