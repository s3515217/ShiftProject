package com.alpha.shift;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DayView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        WeekView dayView = (WeekView) findViewById(R.id.dayView);
        dayView.setOnEventClickListener(mEventClickListener);
        dayView.setMonthChangeListener(mMonthChangeListener);

        addNewShiftButtonOnClick();
    }

    private WeekView.EventClickListener mEventClickListener
    = new WeekView.EventClickListener() {
        @Override
        public void onEventClick(WeekViewEvent event, RectF eventRect) {
        }
    };

    private WeekView.MonthChangeListener mMonthChangeListener
            = new WeekView.MonthChangeListener() {
        @Override
        public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
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

            List<WeekViewEvent> list = new ArrayList<>();
            for (int i = 0; i < shiftArrayList.size(); i++) {
                Shift shift = shiftArrayList.get(i);
                list.add(new WeekViewEvent(i,shift.getName(),shift.getStartTime(),shift.getEndTime()));
            }
            return list;
        }
    };

    private void addNewShiftButtonOnClick() {
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DayView.this,AddShift.class));
            }
        });

    }
}
