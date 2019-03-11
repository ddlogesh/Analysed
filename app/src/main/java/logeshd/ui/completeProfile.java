package logeshd.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class completeProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_profile);

        TextView tv_text1=findViewById(R.id.tv_text1);

        TextView tv_progress1=findViewById(R.id.tv_progress1);
        TextView tv_progress2=findViewById(R.id.tv_progress2);
        TextView tv_progress3=findViewById(R.id.tv_progress3);

        TextView tv_title1=findViewById(R.id.tv_title1);
        TextView tv_title2=findViewById(R.id.tv_title2);
        TextView tv_title3=findViewById(R.id.tv_title3);

        TextView tv_msg1=findViewById(R.id.tv_msg1);
        TextView tv_msg2=findViewById(R.id.tv_msg2);
        TextView tv_msg3=findViewById(R.id.tv_msg3);

        TextView tv_details1=findViewById(R.id.tv_details1);
        TextView tv_details2=findViewById(R.id.tv_details2);
        TextView tv_details3=findViewById(R.id.tv_details3);

        Typeface custom_font1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/arial_rounded.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/arial.ttf");

        tv_text1.setTypeface(custom_font1);

        tv_progress1.setTypeface(custom_font2);
        tv_progress2.setTypeface(custom_font2);
        tv_progress3.setTypeface(custom_font2);

        tv_title1.setTypeface(custom_font1);
        tv_title2.setTypeface(custom_font1);
        tv_title3.setTypeface(custom_font1);

        tv_msg1.setTypeface(custom_font2);
        tv_msg2.setTypeface(custom_font2);
        tv_msg3.setTypeface(custom_font2);

        tv_details1.setTypeface(custom_font2);
        tv_details2.setTypeface(custom_font2);
        tv_details3.setTypeface(custom_font2);
    }
}
