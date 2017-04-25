package com.alpha.shift;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;



public class MainActivity extends AppCompatActivity {

    EditText startTimeText, dateTime, endTimeText;
    int hour_x, minute_x;
    static final int DIALOG_id = 0;




    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showTimePickerOnClick(EditText textField) {

        startTimeText = (EditText) findViewById(R.id.TextTime);
        endTimeText = (EditText) findViewById(R.id.TextEndTime);

        final Calendar cal = Calendar.getInstance();
        hour_x = cal.get(Calendar.HOUR_OF_DAY);
        minute_x = cal.get(Calendar.MINUTE);
        endTimeText.setText(hour_x + ":" + minute_x);
        startTimeText.setText(hour_x + ":" + minute_x);
        textField.setText(hour_x + ":" + minute_x);
        textField.setOnClickListener(new View.OnClickListener() {
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
            startTimeText.setText(hour_x + ":" + minute_x);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
