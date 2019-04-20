package logeshd.analysed.common;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import logeshd.analysed.R;

public class privacy extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.z_privacy);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial.ttf");

        for (int i = 0; i < 6; i++) {
            int t_id = getResources().getIdentifier("tv_title"+Integer.toString(i + 1), "id", getPackageName());
            int m_id = getResources().getIdentifier("tv_msg"+Integer.toString(i + 1), "id", getPackageName());
            TextView title = (TextView) findViewById(t_id);
            TextView msg = (TextView) findViewById(m_id);
            title.setPaintFlags(title.getPaintFlags() | 8);
            title.setTypeface(custom_font1);
            msg.setTypeface(custom_font2);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
