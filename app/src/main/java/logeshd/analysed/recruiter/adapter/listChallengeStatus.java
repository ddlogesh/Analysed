package logeshd.analysed.recruiter.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.apis.challenge;

public class listChallengeStatus extends ArrayAdapter<challenge> {

    public listChallengeStatus(Context context, ArrayList<challenge> history) {
        super(context, 0, history);
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        final challenge p = (challenge) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.r_list_status, parent, false);
        }

        TextView tv_title= convertView.findViewById(R.id.tv_title);
        TextView tv_sub_title= convertView.findViewById(R.id.tv_sub_title);
        final TextView tv_live= convertView.findViewById(R.id.tv_live);
        TextView tv_duration= convertView.findViewById(R.id.tv_duration);
        final TextView tv_more= convertView.findViewById(R.id.tv_more);
        TextView tv_email= convertView.findViewById(R.id.tv_email);
        final TextView tv_total= convertView.findViewById(R.id.tv_total);
        final TextView tv_candidate= convertView.findViewById(R.id.tv_candidate);
        TextView tv_download= convertView.findViewById(R.id.tv_download);

        final RelativeLayout layout_more= convertView.findViewById(R.id.layout_more);
        final RelativeLayout layout_done= convertView.findViewById(R.id.layout_done);
        final ImageView iv_status= convertView.findViewById(R.id.iv_status);

        Typeface custom_font1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font3 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_bold.ttf");

        tv_title.setTypeface(custom_font2);
        tv_sub_title.setTypeface(custom_font1);
        tv_live.setTypeface(custom_font3);

        tv_title.setText(p.getChallenge_name());
        tv_sub_title.setText(p.getChallenge_description());
        tv_duration.setText(p.getChallenge_time()+" hours");
        tv_email.setText(p.getSeeker_email());

        if(p.getEnd_challenge().equals("x")){
            tv_live.setVisibility(View.VISIBLE);
            iv_status.setVisibility(View.GONE);

        }
        else{
            tv_live.setVisibility(View.GONE);
            iv_status.setVisibility(View.VISIBLE);
        }

        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_more.getText().equals("MORE")){
                    layout_more.setVisibility(View.VISIBLE);
                    tv_more.setText("LESS");

                    if(p.getEnd_challenge().equals("x"))
                        layout_done.setVisibility(View.GONE);
                    else{
                        layout_done.setVisibility(View.VISIBLE);
                        tv_total.setText(Integer.toString(p.getT_score()));
                        tv_candidate.setText(Integer.toString(p.getScore()));
                    }
                }
                else {
                    layout_more.setVisibility(View.GONE);
                    tv_more.setText("MORE");
                }
            }
        });

        tv_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
}

//TextView tv_completed= convertView.findViewById(R.id.tv_completed);
//TextView tv_progress= convertView.findViewById(R.id.tv_progress);
//RoundCornerProgressBar pb_status = (RoundCornerProgressBar) convertView.findViewById(R.id.pb_status);
//RelativeLayout layout1= convertView.findViewById(R.id.layout1);
//RelativeLayout layout0= convertView.findViewById(R.id.layout0);
//RelativeLayout layout01= convertView.findViewById(R.id.layout01);

//tv_completed.setTypeface(custom_font2);
//tv_progress.setTypeface(custom_font1);

        /*if(p.getLayoutStatus()){

        }
        else{
        layout0.setVisibility(View.GONE);
        //layout1.setVisibility(View.VISIBLE);

        //tv_progress.setText(p.getProgress()+"%");
        //pb_status.setProgress(p.getProgress());
        }*/