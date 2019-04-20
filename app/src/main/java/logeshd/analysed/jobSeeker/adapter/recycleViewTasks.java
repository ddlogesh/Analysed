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

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import logeshd.analysed.R;
import logeshd.analysed.apis.task;
import logeshd.analysed.classes.recStatus;

public class recycleViewTasks extends RecyclerView.Adapter<recycleViewTasks.MyViewHolder> {
    private Context context;
    private ArrayList<task> items;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title,tv_desc;
        RelativeLayout layout_bg;
        //RoundCornerProgressBar pb_status;

        ImageView iv_cancel;
        TextView tv_task,tv_description,tv_id,tv_duration,tv_accuracy,tv_posted_date,tv_deadline,tv_posted_by,tv_ways,tv_hint;
        AlertDialog alertDialog;

        public MyViewHolder(View view) {
            super(view);

            this.tv_title = (TextView) view.findViewById(R.id.tv_title);
            this.tv_desc = (TextView) view.findViewById(R.id.tv_desc);
            //this.tv_count = (TextView) view.findViewById(R.id.tv_count);
            //this.tv_live= (TextView) view.findViewById(R.id.tv_live);
            this.layout_bg = (RelativeLayout) view.findViewById(R.id.layout_bg);
            //pb_status = (RoundCornerProgressBar) view.findViewById(R.id.pb_status);

            ViewGroup viewGroup = view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.j_alert_task, viewGroup, false);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView);
            alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            iv_cancel = (ImageView) dialogView.findViewById(R.id.iv_cancel);
            tv_task = (TextView) dialogView.findViewById(R.id.tv_task);
            tv_description = (TextView) dialogView.findViewById(R.id.tv_description);
            tv_id = (TextView) dialogView.findViewById(R.id.tv_id);
            tv_duration = (TextView) dialogView.findViewById(R.id.tv_duration);
            tv_accuracy = (TextView) dialogView.findViewById(R.id.tv_accuracy);
            tv_posted_date = (TextView) dialogView.findViewById(R.id.tv_posted_date);
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

    public recycleViewTasks(Context context, ArrayList<task> items) {
        this.context=context;
        this.items = items;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.r_recycle_tasks, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        task p = (task) this.items.get(position);

        holder.tv_title.setText(p.getTask_name());
        holder.tv_desc.setText(p.getTask_desription());
        Log.d("ddlogesh",p.getTask_name());
        //holder.tv_count.setText("Count: " + p.getProgress());

        /*if(p.getStatus()==1) {
            holder.tv_count.setVisibility(View.VISIBLE);
            holder.tv_live.setVisibility(View.VISIBLE);
            holder.pb_status.setVisibility(View.GONE);
            holder.tv_live.setText("LIVE");
            holder.tv_live.setBackgroundResource(R.drawable.text_bg_green_curve_less_width);
        }
        else if(p.getStatus()==0) {
            holder.tv_count.setVisibility(View.VISIBLE);
            holder.tv_live.setVisibility(View.VISIBLE);
            holder.pb_status.setVisibility(View.GONE);
            holder.tv_live.setText("FINISHED");
            holder.tv_live.setBackgroundResource(R.drawable.text_bg_orange_curve_less_width);
        }
        else if(p.getStatus()==-1) {
            holder.tv_live.setVisibility(View.GONE);
            holder.tv_count.setVisibility(View.GONE);
            holder.pb_status.setVisibility(View.VISIBLE);
            holder.pb_status.setProgress(p.getProgress());
        }*/

        String[] colors = new String[]{"text_bg_dark_violet_curve_less_corner_fill", "text_bg_dark_red_curve_less_corner_fill"};
        holder.layout_bg.setBackgroundResource(context.getResources().getIdentifier(colors[position % 2], "drawable", context.getPackageName()));

        holder.tv_task.setText(p.getTask_name());
        holder.tv_description.setText(p.getTask_desription());
        holder.tv_id.setText(p.getTaskId());
        holder.tv_duration.setText(p.getTask_time() + " Hr");
        holder.tv_accuracy.setText(p.getTask_acc() + "%");
        holder.tv_posted_date.setText(getDateStr(p.getC_date()));
        holder.tv_deadline.setText(getDateStr(p.getTask_end()));
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