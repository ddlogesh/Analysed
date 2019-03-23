package logeshd.analysed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import logeshd.analysed.R;
import logeshd.analysed.classes.itemDrawer;
import logeshd.analysed.classes.recStatusDetails;

public class recycleTasks extends RecyclerView.Adapter<recycleTasks.MyViewHolder> {
    private Context context;
    private ArrayList<recStatusDetails> items;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title,tv_desc,tv_count,tv_live;
        public RelativeLayout layout_bg;

        public MyViewHolder(View view) {
            super(view);

            this.tv_title = (TextView) view.findViewById(R.id.tv_title);
            this.tv_desc = (TextView) view.findViewById(R.id.tv_desc);
            this.tv_count = (TextView) view.findViewById(R.id.tv_count);
            this.tv_live= (TextView) view.findViewById(R.id.tv_live);
            this.layout_bg = (RelativeLayout) view.findViewById(R.id.layout_bg);
        }
    }

    public recycleTasks(Context context, ArrayList<recStatusDetails> items) {
        this.context=context;
        this.items = items;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_tasks, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        recStatusDetails p = (recStatusDetails) this.items.get(position);

        holder.tv_title.setText(p.getTitle());
        holder.tv_desc.setText(p.getSubTitle());
        holder.tv_count.setText("Count: " + p.getProgress());

        if(p.getStatus()==1) {
            holder.tv_live.setText("LIVE");
            holder.tv_live.setBackgroundResource(R.drawable.text_bg_green_curve_less_width);
        }
        else if(p.getStatus()==0) {
            holder.tv_live.setText("FINISHED");
            holder.tv_live.setBackgroundResource(R.drawable.text_bg_orange_curve_less_width);
        }

        String[] colors = new String[]{"text_bg_dark_violet_curve_less_corner_fill", "text_bg_dark_red_curve_less_corner_fill"};
        holder.layout_bg.setBackgroundResource(context.getResources().getIdentifier(colors[position % 2], "drawable", context.getPackageName()));
    }

    public int getItemCount() {
        return this.items.size();
    }
}