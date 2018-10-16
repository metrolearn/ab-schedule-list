package com.metrolearn;

import ab_schedule_list.SchoolDay;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import enums.DayFlag;
import enums.DayType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

    Stack<LocalDate> nonSchoolDays = new Stack<LocalDate>();
    Stack<SchoolDay> schoolDays = new Stack<SchoolDay>();
    Stack<LocalDate> ABDays = new Stack<LocalDate>();

    addABDays(ABDays);
    setNonSchoolDays(nonSchoolDays);

    /* Start of Year */
    LocalDate startOfYear = LocalDate.of(2018, 8, 29);
    /* Set Basis for AB Days*/
    setABDays(nonSchoolDays, schoolDays, ABDays, startOfYear);
    /* Set Day Flags based on AB Days and Days of the week */
    setDayFlags(schoolDays);

        String fileName = "C:\\Users\\bpemberton\\IdeaProjects\\ab-schedule-list\\src\\periodsJson\\esrFridayAday.json";

        JsonArray array = getJsonElements(fileName);
        System.out.println(array.get(0));

//        for (SchoolDay s : schoolDays) { // foreach grade in grades
//      System.out.println(
//          s.getLocalDate() + " " + s.getDayFlag() + " " + s.getDayType()); // print that grade
//    }
  }

    private static JsonArray getJsonElements(String fileName) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonParser parser = new JsonParser();
        return parser.parse(br).getAsJsonArray();
    }

    private static void setDayFlags(Stack<SchoolDay> schoolDays) {
    for (SchoolDay s : schoolDays) {
      DayOfWeek currDayOfWeek = s.getLocalDate().getDayOfWeek();
      DayFlag currDayFlag = s.getDayFlag();
      switch (currDayOfWeek) {
        case MONDAY:
          if (currDayFlag == DayFlag.Aday) s.setDayType(DayType.AReg);
          if (currDayFlag == DayFlag.Bday) s.setDayType(DayType.BReg);
          break;
        case TUESDAY:
          if (currDayFlag == DayFlag.Aday) s.setDayType(DayType.AMet);
          if (currDayFlag == DayFlag.Bday) s.setDayType(DayType.BMet);
          break;
        case WEDNESDAY:
          if (currDayFlag == DayFlag.Aday) s.setDayType(DayType.AFlx);
          if (currDayFlag == DayFlag.Bday) s.setDayType(DayType.BFlx);
          break;
        case THURSDAY:
          if (currDayFlag == DayFlag.Aday) s.setDayType(DayType.AGLMet);
          if (currDayFlag == DayFlag.Bday) s.setDayType(DayType.BGLMet);
          break;
        case FRIDAY:
          if (currDayFlag == DayFlag.Aday) s.setDayType(DayType.AFri);
          if (currDayFlag == DayFlag.Bday) s.setDayType(DayType.BFri);
          break;
      }
    }
  }

  private static void setABDays(
      Stack<LocalDate> nonSchoolDays,
      Stack<SchoolDay> schoolDays,
      Stack<LocalDate> ABDays,
      LocalDate startOfYear) {
    for (int daysToAdd = 0; daysToAdd <= 365; daysToAdd++) {

      LocalDate currDate = startOfYear.plusDays(daysToAdd);
      Boolean schoolDay;

      SchoolDay s = new SchoolDay(currDate);
      schoolDay = isWeekDay(currDate);

      if (schoolDay) {
        if (nonSchoolDays.search(currDate) == -1) {
          // Start on Bday.
          if (schoolDays.size() == 0) {

            s.setDayFlag(DayFlag.Bday);
          }
          if (schoolDays.size() > 0) {

            DayFlag dayFlagPeek = schoolDays.peek().getDayFlag();
            if (dayFlagPeek == DayFlag.Bday || dayFlagPeek == DayFlag.ABday) {
              s.setDayFlag(DayFlag.Aday);
            } else {
              s.setDayFlag(DayFlag.Bday);
            }

            if (ABDays.search(currDate) != -1) {
              s.setDayFlag(DayFlag.ABday);
            }
          }

          if (currDate.isBefore(LocalDate.of(2019, 6, 10))) schoolDays.push(s);
        }
      }
    }
  }

  private static void addABDays(Stack<LocalDate> ABDays) {
    ABDays.add(LocalDate.of(2018, 10, 10));
  }

  private static void setNonSchoolDays(Stack<LocalDate> nonSchoolDays) {
    // Year Month Day
    // School and District Offices Closed
    nonSchoolDays.push(LocalDate.of(2018, 9, 3));

    // State wide Inservice Day
    nonSchoolDays.push(LocalDate.of(2018, 10, 12));

    // Planing Day
    nonSchoolDays.push(LocalDate.of(2018, 10, 29));

    // State wide Inservice Day
    nonSchoolDays.push(LocalDate.of(2018, 11, 12));

    // Day and Evening Conferences
    nonSchoolDays.push(LocalDate.of(2018, 11, 19));
    nonSchoolDays.push(LocalDate.of(2018, 11, 20));

    // School Closed and District Offices Open
    nonSchoolDays.push(LocalDate.of(2018, 11, 21));

    // School and District Offices Closed
    nonSchoolDays.push(LocalDate.of(2018, 11, 22));
    nonSchoolDays.push(LocalDate.of(2018, 11, 23));

    // School Closed and District Offices Open
    nonSchoolDays.push(LocalDate.of(2018, 12, 17));
    nonSchoolDays.push(LocalDate.of(2018, 12, 18));
    nonSchoolDays.push(LocalDate.of(2018, 12, 19));
    nonSchoolDays.push(LocalDate.of(2018, 12, 20));

    // School and District Offices Closed
    nonSchoolDays.push(LocalDate.of(2018, 12, 21));
    nonSchoolDays.push(LocalDate.of(2018, 12, 24));
    nonSchoolDays.push(LocalDate.of(2018, 12, 25));

    // School Closed and District Offices Open
    nonSchoolDays.push(LocalDate.of(2018, 12, 26));
    nonSchoolDays.push(LocalDate.of(2018, 12, 27));
    nonSchoolDays.push(LocalDate.of(2018, 12, 28));
    nonSchoolDays.push(LocalDate.of(2018, 12, 31));

    // School and District Offices Closed
    nonSchoolDays.push(LocalDate.of(2019, 1, 1));
    nonSchoolDays.push(LocalDate.of(2019, 1, 21));

    // Teacher Planing Day
    nonSchoolDays.push(LocalDate.of(2019, 1, 22));

    // School and District Offices Closed
    nonSchoolDays.push(LocalDate.of(2019, 2, 18));

    // School Closed and District Offices Open
    nonSchoolDays.push(LocalDate.of(2019, 3, 25));
    nonSchoolDays.push(LocalDate.of(2019, 3, 26));
    nonSchoolDays.push(LocalDate.of(2019, 3, 27));
    nonSchoolDays.push(LocalDate.of(2019, 3, 28));
    nonSchoolDays.push(LocalDate.of(2019, 3, 29));

    // Teacher Planing Day
    nonSchoolDays.push(LocalDate.of(2019, 4, 8));

    // School and District Offices Closed
    nonSchoolDays.push(LocalDate.of(2019, 5, 27));
  }

  /** @param currDate */
  private static boolean isWeekDay(LocalDate currDate) {
    // true if currDate is a Sat or Sun

    return currDate.getDayOfWeek().getValue() < 6;
  }
}
