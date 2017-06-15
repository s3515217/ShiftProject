package com.alpha.shift;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Shift {
    private String name;
    private Calendar startTime;
    private Calendar endTime;

    public Shift(String name, Calendar startTime,Calendar endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }



    public static void saveShiftListToJson(ArrayList<Shift> shiftArrayList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(shiftArrayList);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ShiftList.json"));
            writer.write(jsonString);
            writer.flush();
            writer.close();
        }
        catch (IOException e) {}
    }

    public static ArrayList<Shift> loadShiftListFromJson() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Shift>>() {}.getType();

        StringBuilder jsonString = new StringBuilder("");
        String strBuffer;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("ShiftList.json"));
            while((strBuffer = reader.readLine()) != null) {
                jsonString.append(strBuffer);
                jsonString.append('\n');
            }
        }
        catch (IOException e) {}



        ArrayList<Shift> shiftArrayList = gson.fromJson(jsonString.toString(),type);
        return shiftArrayList;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }


    public String getStringStartTime() {
        DateFormat df = new SimpleDateFormat("HH:mm");
        String time = df.format(getStartTime().getTime());
        return time;
    }

    public  String getStringEndTime() {
        DateFormat df = new SimpleDateFormat("HH:mm");
        String time = df.format(getEndTime().getTime());
        return time;
    }

    public String getStringDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(getStartTime().getTime());
        return date;
    }
    @Override
    public String toString() {
        return name + ": "+getStringDate()+
                "("+
                getStringStartTime()+"-"+getStringEndTime()
                +")";
    }
}
