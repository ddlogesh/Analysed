package logeshd.analysed.jobSeeker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import logeshd.analysed.R;
import logeshd.analysed.apis.challenge;

public class listViewChallengesComp extends ArrayAdapter<challenge> {
    public listViewChallengesComp(Context context, ArrayList<challenge> history) {
        super(context, 0, history);
    }

    public View getView(int position, View cView, ViewGroup parent) {
        final challenge p = (challenge) getItem(position);
        if (cView == null) {
            cView = LayoutInflater.from(getContext()).inflate(R.layout.j_list_view_tasks_completed, parent, false);
        }
        final View convertView = cView;

        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_date= (TextView) convertView.findViewById(R.id.tv_date);

        tv_title.setText(p.getChallenge_name());
        tv_date.setText(getDateStr(p.getChallenge_end()));

        ViewGroup viewGroup = convertView.findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.j_alert_challenge, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView iv_cancel = (ImageView) dialogView.findViewById(R.id.iv_cancel);
        TextView tv_task = (TextView) dialogView.findViewById(R.id.tv_task);
        TextView tv_description = (TextView) dialogView.findViewById(R.id.tv_description);
        TextView tv_id = (TextView) dialogView.findViewById(R.id.tv_id);
        TextView tv_duration = (TextView) dialogView.findViewById(R.id.tv_duration);
        TextView tv_accuracy = (TextView) dialogView.findViewById(R.id.tv_accuracy);
        TextView tv_posted_date = (TextView) dialogView.findViewById(R.id.tv_posted_date);
        TextView tv_deadline = (TextView) dialogView.findViewById(R.id.tv_deadline);
        TextView tv_posted_by = (TextView) dialogView.findViewById(R.id.tv_posted_by);
        TextView tv_ways = (TextView) dialogView.findViewById(R.id.tv_ways);
        TextView tv_hint = (TextView) dialogView.findViewById(R.id.tv_hint);

        tv_task.setText(p.getChallenge_name());
        tv_description.setText(p.getChallenge_description());
        tv_id.setText(p.getChallengeId());
        tv_duration.setText(p.getChallenge_time() + " Hr");
        tv_deadline.setText(getDateStr(p.getChallenge_end()));
        tv_posted_by.setText(p.getEmail());
        tv_ways.setText(p.getWay());
        tv_hint.setText(p.getHint());

        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

        return convertView;
    }

    public String getDateStr(String end_date){
        try{
            String day="",dates="";

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            SimpleDateFormat sdf_date=new SimpleDateFormat("dd MMM, ", Locale.US);
            SimpleDateFormat sdf_day=new SimpleDateFormat("EEE", Locale.US);

            Date d=new Date();
            Date d1=sdf.parse(end_date);

            if(sdf.format(d).equals(sdf.format(d1))) {
                day = "Today";
                return day;
            }
            else {
                dates = sdf_date.format(d1);
                day = sdf_day.format(d1);
                return dates+day;
            }
        }
        catch (Exception e){
            Log.d("ddlogesh",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
