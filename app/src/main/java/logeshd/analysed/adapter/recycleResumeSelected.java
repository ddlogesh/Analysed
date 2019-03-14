package logeshd.analysed.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import logeshd.analysed.R;
import logeshd.analysed.classes.itemDrawer;

public class recycleResumeSelected extends Adapter<recycleResumeSelected.MyViewHolder> {
    private ArrayList<itemDrawer> items;

    public class MyViewHolder extends ViewHolder {
        public TextView tv_text;

        public MyViewHolder(View view) {
            super(view);
            this.tv_text = (TextView) view.findViewById(R.id.tv_text);
        }
    }

    public recycleResumeSelected(ArrayList<itemDrawer> items) {
        this.items = items;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.resume_recycle_selected, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_text.setText(((itemDrawer) this.items.get(position)).getTitle());
    }

    public int getItemCount() {
        return this.items.size();
    }
}
