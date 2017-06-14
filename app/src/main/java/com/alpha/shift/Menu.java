package com.alpha.shift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        monthViewButtonOnClick();
        weekViewButtonOnClick();
        dayViewButtonOnClick();
    }

    private void monthViewButtonOnClick() {
        Button button = (Button) findViewById(R.id.btn_MonthView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,MonthView.class));
            }
        });
    }

    private void weekViewButtonOnClick() {
        Button button = (Button) findViewById(R.id.btn_WeekView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,WeekView.class));
            }
        });
    }

    private void dayViewButtonOnClick() {
        Button button = (Button) findViewById(R.id.btn_DayView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,DayView.class));
            }
        });
    }
}
