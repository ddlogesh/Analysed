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
import logeshd.ui.classes.jobDetails;

public class listJobTimeline extends ArrayAdapter<jobDetails> {

    public listJobTimeline(Context context, ArrayList<jobDetails> history){
        super(context, 0, history);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        jobDetails p= getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.jobs_list_timeline, parent, false);
        }

        TextView tv_month = (TextView) convertView.findViewById(R.id.tv_month);
        TextView tv_day = (TextView) convertView.findViewById(R.id.tv_day);
        ImageView iv_job_icon = (ImageView) convertView.findViewById(R.id.iv_job_icon);
        TextView tv_job_title = (TextView) convertView.findViewById(R.id.tv_job_title);
        TextView tv_job_company = (TextView) convertView.findViewById(R.id.tv_job_company);

        tv_month.setText(p.getMonth());
        tv_day.setText(p.getDate());
        tv_job_title.setText(p.getTitle());
        tv_job_company.setText(p.getCompany());

        int drawable = getContext().getResources().getIdentifier("apple_icon","drawable",getContext().getPackageName());
        iv_job_icon.setImageResource(drawable);

        return convertView;
    }
}