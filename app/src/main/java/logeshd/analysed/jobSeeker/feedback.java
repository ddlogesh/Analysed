package logeshd.analysed.jobSeeker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import logeshd.analysed.R;

public class feedback extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.j_feedback);

        final EditText ev_text = (EditText) findViewById(R.id.ev_text);
        final TextView tv_write = (TextView) findViewById(R.id.tv_write);

        tv_write.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (ev_text.getEditableText().length() > 0) {
                    RelativeLayout layout_done = (RelativeLayout) feedback.this.findViewById(R.id.layout_done);
                    RelativeLayout layout_feed = (RelativeLayout) feedback.this.findViewById(R.id.layout_feed);
                    layout_done.setVisibility(View.VISIBLE);
                    layout_feed.setVisibility(View.GONE);
                }
            }
        });

        ev_text.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (ev_text.getEditableText().length() > 0) {
                    tv_write.setBackgroundResource(R.drawable.button_blue_gradient);
                } else {
                    tv_write.setBackgroundResource(R.drawable.button_very_light_white_solid);
                }
            }
        });

        ((TextView) findViewById(R.id.tv_goto)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(), dashboard.class);
                startActivity(i1);
            }
        });

        ((RelativeLayout) findViewById(R.id.layout_cancel)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getApplicationContext(),dashboard.class);
                startActivity(i1);
            }
        });
    }
}
