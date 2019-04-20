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
import java.util.List;
import java.util.Locale;

import logeshd.analysed.R;
import logeshd.analysed.apis.e_book;
import logeshd.analysed.apis.task;

public class recycleViewEvents extends RecyclerView.Adapter<recycleViewEvents.MyViewHolder> {
    private Context context;
    private List<e_book> items;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title,tv_venue,tv_organiser,tv_event_date,tv_booking_date,tv_event_count;
        RelativeLayout layout_bg;

        public MyViewHolder(View view) {
            super(view);

            this.tv_title = (TextView) view.findViewById(R.id.tv_title);
            this.tv_venue = (TextView) view.findViewById(R.id.tv_venue);
            this.tv_organiser = (TextView) view.findViewById(R.id.tv_organiser);
            this.tv_event_date = (TextView) view.findViewById(R.id.tv_event_date);
            this.tv_booking_date = (TextView) view.findViewById(R.id.tv_booking_date);
            this.tv_event_count = (TextView) view.findViewById(R.id.tv_event_count);
            this.layout_bg = (RelativeLayout) view.findViewById(R.id.layout_bg);
        }
    }

    public recycleViewEvents(Context context, List<e_book> items) {
        this.context=context;
        this.items = items;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.j_recycle_events, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        e_book p = (e_book) this.items.get(position);

        holder.tv_title.setText(p.getEvent_name());
        holder.tv_venue.setText(p.getEvent_venue());
        holder.tv_organiser.setText(p.getEmail());
        holder.tv_event_date.setText(getDateStr(p.getEvent_date()));
        holder.tv_booking_date.setText(getDateStr(p.getRdate()));
        holder.tv_event_count.setText(Integer.toString(p.getPpl_count()));

        String[] colors = new String[]{"text_bg_dark_violet_curve_less_corner_fill", "text_bg_dark_red_curve_less_corner_fill"};
        holder.layout_bg.setBackgroundResource(context.getResources().getIdentifier(colors[position % 2], "drawable", context.getPackageName()));
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