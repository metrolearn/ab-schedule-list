package com.metrolearn;

import ab_schedule_list.SchoolDay;

import java.time.LocalDate;
import java.util.Stack;

public class Main {

  public static void main(String[] args) {

    Stack<LocalDate> nonSchoolDays = new Stack<LocalDate>();

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

    // vars
    Stack<SchoolDay> schoolDays = new Stack<SchoolDay>();
    LocalDate startOfYear = LocalDate.of(2018, 8, 27);
    for (int daysToAdd = 0; daysToAdd <= 365; daysToAdd++) {

      LocalDate currDate = startOfYear.plusDays(daysToAdd);
      Boolean schoolDay;

      SchoolDay s = new SchoolDay(currDate);
      schoolDay = isWeekDay(currDate);

      if (schoolDay) schoolDays.push(s);
    }

    for (SchoolDay s : schoolDays) { // foreach grade in grades
      System.out.print(s); // print that grade
    }
  }

  /** @param currDate */
  private static boolean isWeekDay(LocalDate currDate) {
    // true if currDate is a Sat or Sun

    return currDate.getDayOfWeek().getValue() < 6;
  }
}
