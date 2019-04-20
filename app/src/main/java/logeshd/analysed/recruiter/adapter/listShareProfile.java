package logeshd.analysed.recruiter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import logeshd.analysed.R;
import logeshd.analysed.classes.database;

public class listShareProfile extends ArrayAdapter<database> {
    public listShareProfile(Context context, ArrayList<database> history) {
        super(context, 0, history);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        database p = (database) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.z_list_share_profile, parent, false);
        }

        ImageView iv_profile_image = convertView.findViewById(R.id.iv_profile_image);
        ((TextView) convertView.findViewById(R.id.tv_full_name)).setText(p.getName());
        ((TextView) convertView.findViewById(R.id.tv_email)).setText(p.getPic());
        TextView tv_access = (TextView) convertView.findViewById(R.id.tv_access);

        try {
            if (p.getPosition().length() > 0) {
                tv_access.setText(p.getPosition());
                tv_access.setBackgroundResource(R.drawable.text_bg_pink_curve_less_corner);
            } else {
                tv_access.setBackgroundColor(Color.parseColor("#00000000"));
            }
        } catch (Exception e) {
            tv_access.setBackgroundColor(Color.parseColor("#00000000"));
            e.printStackTrace();
        }

        View v_line = convertView.findViewById(R.id.v_line);
        if (position == 0) {
            v_line.setVisibility(8);
        } else {
            v_line.setVisibility(0);
        }

        return convertView;
    }
}
