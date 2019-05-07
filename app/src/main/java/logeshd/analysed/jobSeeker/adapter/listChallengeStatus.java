package logeshd.analysed.jobSeeker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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

public class listChallengeStatus extends ArrayAdapter<challenge> {

    public listChallengeStatus(Context context, ArrayList<challenge> history) {
        super(context, 0, history);
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        final challenge p = (challenge) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.j_list_score_board, parent, false);
        }

        TextView tv_title= convertView.findViewById(R.id.tv_title);
        TextView tv_sub_title= convertView.findViewById(R.id.tv_sub_title);
        TextView tv_score= convertView.findViewById(R.id.tv_score);

        Typeface custom_font1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/lato.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_rounded.ttf");

        tv_title.setTypeface(custom_font2);
        tv_sub_title.setTypeface(custom_font1);

        try {
            tv_title.setText(p.getChallenge_name());
            tv_sub_title.setText(p.getChallenge_description());
            tv_score.setText(p.getScore() + "/" + p.getT_score());
        }
        catch (Exception e){
            e.printStackTrace();
        }

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