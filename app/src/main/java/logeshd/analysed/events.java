package logeshd.analysed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.savvi.rangedatepicker.CalendarPickerView;

import java.util.Date;

public class events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_events);

        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date d=new Date(2017,1,1);
        Date d1=new Date(2019,1,1);
        calendar.init(d,d1).inMode(CalendarPickerView.SelectionMode.RANGE);
    }
}
