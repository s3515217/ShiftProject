package com.alpha.shift;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static java.lang.Integer.valueOf;

public class AddShift extends AppCompatActivity {

    EditText dateText, startTimeText, endTimeText;
    int hour_x, minute_x, day_x, month_x, year_x;
    int start_hour, start_minute, end_hour, end_minute;

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
        dateText.setText(day_x + "/" + valueOf(month_x + 1) + "/" + year_x);

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
            return new TimePickerDialog(this, startTimePickerListener, hour_x, minute_x, false);
        }
        if (id == 2) {
            return new TimePickerDialog(this, endTimePickerListener, hour_x, minute_x, false);
        }
        if (id == 3) {
            return new DatePickerDialog(this, datePickerListener, year_x, month_x, day_x);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener startTimePickerListener
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            minute_x = minute;
            start_hour = hourOfDay;
            start_minute = minute;
            startTimeText.setText(hour_x + ":" + minute_x);
        }
    };

    private TimePickerDialog.OnTimeSetListener endTimePickerListener
            = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x = hourOfDay;
            minute_x = minute;
            end_hour = hourOfDay;
            end_minute = minute;
            endTimeText.setText(hour_x + ":" + minute_x);
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            day_x = dayOfMonth;
            month_x = month;
            year_x = year;

            Calendar cal = Calendar.getInstance();
            cal.set(year, month, dayOfMonth, 0, 0, 0);
            long longDate = cal.getTimeInMillis();

            SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateText.setText(dFormat.format(longDate));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);

        showTimePickerOnClick();
        saveButtonOnClick();
        cancelButtonOnClick();
    }

    private void saveButtonOnClick() {
        final EditText nameText = (EditText) findViewById(R.id.TextShiftName);
        Button saveButton = (Button) findViewById(R.id.btn_Save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar start = Calendar.getInstance();
                start.set(year_x,month_x,day_x,start_hour,start_minute);
                Calendar end = Calendar.getInstance();
                end.set(year_x,month_x,day_x,end_hour,end_minute);

                Shift shift = new Shift(nameText.getText().toString(), start, end);



//        Create hard coded data
                Calendar start1 = Calendar.getInstance();
                start1.set(2017,5,18,6,0,0);
                Calendar end1 = Calendar.getInstance();
                end1.set(2017,5,18,8,0,0);
                Shift s1 = new Shift("Cafe",start1,end1);

                Calendar start2 = Calendar.getInstance();
                start2.set(2017,5,17,6,0,0);
                Calendar end2 = Calendar.getInstance();
                end2.set(2017,5,17,8,0,0);
                Shift s2 = new Shift("Library",start2,end2);

                Calendar start3 = Calendar.getInstance();
                start3.set(2017,5,18,14,0,0);
                Calendar end3 = Calendar.getInstance();
                end3.set(2017,5,18,20,0,0);
                Shift s3 = new Shift("Campus",start3,end3);

//        ArrayList<Shift> shiftArrayList = Shift.loadShiftListFromJson();
                ArrayList<Shift> shiftArrayList = new ArrayList<Shift>();
                shiftArrayList.add(s1);
                shiftArrayList.add(s2);
                shiftArrayList.add(s3);


                boolean clash = false;
                for (int i = 0; i < shiftArrayList.size(); i++) {
                    Shift oldShift = shiftArrayList.get(i);

                    if (!(shift.getStartTime().before(oldShift.getStartTime()) & shift.getEndTime().before(oldShift.getStartTime()) |
                            shift.getStartTime().after(oldShift.getEndTime()) & shift.getEndTime().after(oldShift.getEndTime()))) {
                        clash = true;
                    }
                }

                if (clash) {
                    Toast.makeText(AddShift.this, "Clash", Toast.LENGTH_LONG).show();
//                    At this point, when the shifts are clashed, we don't create a new shift
//                    (Because we want the user to change immediately. Not because the program will crash)
                }
                else {
                    shiftArrayList.add(shift);
//                      Shift.saveShiftListToJson(shiftArrayList);
                    Toast.makeText(AddShift.this, shift.toString(), Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }

    private void cancelButtonOnClick() {
        Button cancelButton = (Button) findViewById(R.id.btn_Cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

