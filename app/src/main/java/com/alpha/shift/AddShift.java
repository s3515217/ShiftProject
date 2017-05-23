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

import static java.lang.Integer.valueOf;

public class AddShift extends AppCompatActivity {

    EditText startTimeText, dateText, endTimeText;
    int hour_x, minute_x, day_x, month_x, year_x;

    public void showTimePickerOnClick() {
        startTimeText = (EditText) findViewById(R.id.TextStartTime);
        endTimeText = (EditText) findViewById(R.id.TextEndTime);
        dateText = (EditText) findViewById(R.id.TextDate);

        final Calendar cal = Calendar.getInstance();
        hour_x = cal.get(Calendar.HOUR_OF_DAY);
        minute_x = cal.get(Calendar.MINUTE);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        month_x = cal.get(Calendar.MONTH);
        year_x = cal.get(Calendar.YEAR);

        endTimeText.setText("00:00");
        startTimeText.setText("00:00");
        dateText.setText(day_x + "/" + valueOf(month_x+1) + "/" + year_x);

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
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(3);
            }
        });


    }

    @Override
    protected Dialog onCreateDialog(int id) {
     if (id == 1) {
         return new TimePickerDialog(this,startTimePickerListener,hour_x,minute_x,false);
     }
     if (id == 2) {
         return new TimePickerDialog(this,endTimePickerListener,hour_x,minute_x,false);
     }
     if (id == 3) {
         return new DatePickerDialog(this,datePickerListener,year_x,month_x ,day_x);
     }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener startTimePickerListener
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x=hourOfDay;
            minute_x=minute;
            startTimeText.setText(hour_x + ":" + minute_x);
        }
    };

    private TimePickerDialog.OnTimeSetListener endTimePickerListener
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
            day_x=dayOfMonth;
            month_x=month+1;
            year_x=year;
            dateText.setText(day_x + "/" + month_x + "/" + year_x);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shift);

        showTimePickerOnClick();
    }
}
