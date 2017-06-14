package com.alpha.shift;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ShiftList.json"));
            writer.write(jsonString);
            writer.flush();
            writer.close();
        }
        catch (IOException e) {}
    }

    public static ArrayList<Shift> loadShiftListFromJson() {
        ArrayList<Shift> shiftArrayList = null;
        return shiftArrayList;
    }

}
