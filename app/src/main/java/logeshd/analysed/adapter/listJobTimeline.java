package logeshd.analysed.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import logeshd.analysed.R;
import logeshd.analysed.classes.jobDetails;

public class listJobTimeline extends ArrayAdapter<jobDetails> {
    public listJobTimeline(Context context, ArrayList<jobDetails> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        jobDetails p = (jobDetails) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_jobs, parent, false);
        }
        TextView tv_month = (TextView) convertView.findViewById(R.id.tv_month);
        TextView tv_day = (TextView) convertView.findViewById(R.id.tv_day);
        ImageView iv_job_icon = (ImageView) convertView.findViewById(R.id.iv_job_icon);
        TextView tv_job_title = (TextView) convertView.findViewById(R.id.tv_job_title);
        TextView tv_job_company = (TextView) convertView.findViewById(R.id.tv_job_company);

        Typeface custom_font1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");

        tv_job_title.setTypeface(custom_font1);
        tv_job_company.setTypeface(custom_font2);

        tv_month.setText(p.getMonth());
        tv_day.setText(p.getDate());
        tv_job_title.setText(p.getTitle());
        tv_job_company.setText(p.getCompany());
        iv_job_icon.setImageResource(getContext().getResources().getIdentifier("facino_icon", "drawable", getContext().getPackageName()));

        return convertView;
    }
}
