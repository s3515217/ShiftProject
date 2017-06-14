package com.alpha.shift;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.List;

public class DayView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        // Get a reference for the week view in the layout.
         WeekView dayView = (WeekView) findViewById(R.id.dayView);

// Set an action when any event is clicked.
        dayView.setOnEventClickListener(mEventClickListener);

// The week view has infinite scrolling horizontally. We have to provide the events of a
// month every time the month changes on the week view.
        dayView.setMonthChangeListener(mMonthChangeListener);

// Set long press listener for events.
        dayView.setEventLongPressListener(mEventLongPressListener);
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
            List<WeekViewEvent> list = new ArrayList<>();
            list.add(new WeekViewEvent(1,"name",2017,6,15,6,30,2017,6,15,7,30));
            return list;
        }
    };

    private WeekView.EventLongPressListener mEventLongPressListener
            = new WeekView.EventLongPressListener() {
        @Override
        public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

        }
    };

}
