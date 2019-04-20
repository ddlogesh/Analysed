package logeshd.analysed.jobSeeker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import logeshd.analysed.R;
import logeshd.analysed.apis.challenge;

public class recycleViewChallenges extends RecyclerView.Adapter<recycleViewChallenges.MyViewHolder> {
    private Context context;
    private ArrayList<challenge> items;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title,tv_desc;
        RelativeLayout layout_bg;

        ImageView iv_cancel;
        TextView tv_task,tv_description,tv_id,tv_duration,tv_deadline,tv_posted_by,tv_ways,tv_hint;
        AlertDialog alertDialog;

        public MyViewHolder(View view) {
            super(view);

            this.tv_title = (TextView) view.findViewById(R.id.tv_title);
            this.tv_desc = (TextView) view.findViewById(R.id.tv_desc);
            this.layout_bg = (RelativeLayout) view.findViewById(R.id.layout_bg);

            ViewGroup viewGroup = view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.j_alert_challenge, viewGroup, false);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView);
            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            iv_cancel = (ImageView) dialogView.findViewById(R.id.iv_cancel);
            tv_task = (TextView) dialogView.findViewById(R.id.tv_task);
            tv_description = (TextView) dialogView.findViewById(R.id.tv_description);
            tv_id = (TextView) dialogView.findViewById(R.id.tv_id);
            tv_duration = (TextView) dialogView.findViewById(R.id.tv_duration);
            tv_deadline = (TextView) dialogView.findViewById(R.id.tv_deadline);
            tv_posted_by = (TextView) dialogView.findViewById(R.id.tv_posted_by);
            tv_ways = (TextView) dialogView.findViewById(R.id.tv_ways);
            tv_hint = (TextView) dialogView.findViewById(R.id.tv_hint);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.show();
                }
            });
        }
    }

    public recycleViewChallenges(Context context, ArrayList<challenge> items) {
        this.context=context;
        this.items = items;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.r_recycle_tasks, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        challenge p = (challenge) this.items.get(position);

        holder.tv_title.setText(p.getChallenge_name());
        holder.tv_desc.setText(p.getChallenge_description());
        Log.d("ddlogesh",p.getChallenge_name());

        String[] colors = new String[]{"text_bg_dark_violet_curve_less_corner_fill", "text_bg_dark_red_curve_less_corner_fill"};
        holder.layout_bg.setBackgroundResource(context.getResources().getIdentifier(colors[position % 2], "drawable", context.getPackageName()));

        holder.tv_task.setText(p.getChallenge_name());
        holder.tv_description.setText(p.getChallenge_description());
        holder.tv_id.setText(p.getChallengeId());
        holder.tv_duration.setText(p.getChallenge_time() + " Hr");
        holder.tv_deadline.setText(getDateStr(p.getChallenge_end()));
        holder.tv_posted_by.setText(p.getEmail());
        holder.tv_ways.setText(p.getWay());
        holder.tv_hint.setText(p.getHint());

        holder.iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.alertDialog.dismiss();
            }
        });
    }

    public int getItemCount() {
        return this.items.size();
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