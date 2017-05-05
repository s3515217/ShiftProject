package com.alpha.shift;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText startTimeText, dateText, endTimeText;
    int hour_x, minute_x, date_x, month_x, year_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Calendar cal = Calendar.getInstance();
        date_x = cal.get(Calendar.DAY_OF_MONTH);
        month_x = cal.get(Calendar.MONTH);
        year_x = cal.get(Calendar.YEAR);
        hour_x = cal.get(Calendar.HOUR_OF_DAY);
        minute_x = cal.get(Calendar.MINUTE);

        showDatePickerOnClick();
        showTimePickerOnClick();
    }

    public void showDatePickerOnClick() {
        dateText = (EditText) findViewById(R.id.TextDate);
        dateText.setText(date_x+"/"+month_x+1+"/"+year_x);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(3);
            }
        });
    }

    public void showTimePickerOnClick() {
        startTimeText = (EditText) findViewById(R.id.TextStartTime);
        endTimeText = (EditText) findViewById(R.id.TextEndTime);
        endTimeText.setText("00:00");
        startTimeText.setText("00:00");
        startTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(1);
            }
        });
        endTimeText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(2);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
            return new TimePickerDialog(this,timePickerListenerForStartTime,hour_x,minute_x,false);
        }
        if (id == 2) {
            return new TimePickerDialog(this,timePickerListenerForEndTime,hour_x,minute_x,false);
        }
        if (id == 3) {
            return new DatePickerDialog(this,datePickerListener,year_x,month_x,date_x);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListenerForStartTime
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x=hourOfDay;
            minute_x=minute;
            startTimeText.setText(hour_x + ":" + minute_x);
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListenerForEndTime
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x=hourOfDay;
            minute_x=minute;
            endTimeText.setText(hour_x + ":" + minute_x);
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            date_x=dayOfMonth;
            month_x=month+1;
            year_x=year;
            dateText.setText(date_x + "/" + month_x + "/" + year_x);
        }
    };
}
