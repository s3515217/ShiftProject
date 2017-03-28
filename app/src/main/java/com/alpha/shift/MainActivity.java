package com.alpha.shift;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText timeText, dateTime, endTimeText;
    int hour_x, minute_x;
    static final int DIALOG_id = 0;

    public void showTimePickerOnClick() {
        timeText = (EditText) findViewById(R.id.TextTime);
        endTimeText = (EditText) findViewById(R.id.TextEndTime);

        endTimeText.setText("00:00");
        timeText.setText("00:00");

        final Calendar cal = Calendar.getInstance();
        hour_x = cal.get(Calendar.HOUR_OF_DAY);
        minute_x = cal.get(Calendar.MINUTE);

        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_id);
            }
        });
        endTimeText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DIALOG_id);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
     if (id == DIALOG_id) {
         return new TimePickerDialog(this,timePickerListener,hour_x,minute_x,false);
     }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x=hourOfDay;
            minute_x=minute;
            timeText.setText(hour_x + ":" + minute_x);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTimePickerOnClick();
    }
}
