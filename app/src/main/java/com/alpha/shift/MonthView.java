package com.alpha.shift;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MonthView extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    Date date = new Date();
    TextView textBox;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);
        textBox  = (TextView) findViewById(R.id.textView);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle(dateFormatMonth.format(date));

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

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

        for (int i = 0; i < shiftArrayList.size(); i++) {
            Shift shift = shiftArrayList.get(i);
            Event event = new Event(Color.RED,shift.getStartTime().getTimeInMillis(), shift.getName()+" "+shift.getStringStartTime()+"-" +shift.getStringEndTime());
            compactCalendar.addEvent(event);
        }

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendar.getEvents(dateClicked);
                textBox.setText("");
                for (int i = 0; i < events.size(); i++) {
                    textBox.append(events.get(i).getData().toString() + "\n");
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

        addNewShiftButtonOnClick();
    }

    private void addNewShiftButtonOnClick() {
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonthView.this,AddShift.class));
            }
        });

    }
}
