package logeshd.analysed.recruiter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import logeshd.analysed.R;
import logeshd.analysed.classes.database;

public class listDatabase extends ArrayAdapter<database> {
    public listDatabase(Context context, ArrayList<database> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        database p = (database) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.r_list_database, parent, false);
        }
        View v_line = convertView.findViewById(R.id.v_line);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_position = (TextView) convertView.findViewById(R.id.tv_position);
        TextView tv_resume = (TextView) convertView.findViewById(R.id.tv_resume);

        Typeface custom_font1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial_bold.ttf");
        tv_name.setTypeface(custom_font1);
        tv_position.setTypeface(custom_font1);
        tv_resume.setTypeface(custom_font2);

        tv_name.setText(p.getName());
        tv_position.setText(p.getPosition());

        ((ImageView) convertView.findViewById(R.id.iv_profile)).setImageResource(getContext().getResources().getIdentifier("sample_image", "drawable", getContext().getPackageName()));

        if (position == 0) {
            v_line.setVisibility(View.GONE);
        } else {
            v_line.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
