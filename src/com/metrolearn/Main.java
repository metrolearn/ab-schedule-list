package com.metrolearn;

import ab_schedule_list.Period;
import ab_schedule_list.SchoolDay;
import enums.DayFlag;
import enums.DayType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Stack;

public class Main {

  private static final String ER_FRIDAY_ADAY_FN = "periodsCSV/erFridayAday.csv";
  private static final String ER_FRIDAY_BDAY_FN = "periodsCSV/erFridayBday.csv";
  private static final String FRIDAY_ADAY_FN = "periodsCSV/fridayAday.csv";
  private static final String FRIDAY_BDAY_FN = "periodsCSV/fridayBday.csv";
  private static final String MONDAY_ADAY_FN = "periodsCSV/mondayAday.csv";
  private static final String MONDAY_BDAY_FN = "periodsCSV/mondayBday.csv";
  private static final String TUESDAY_ADAY_FN = "periodsCSV/tuesdayAday.csv";
  private static final String TUESDAY_BDAY_FN = "periodsCSV/tuesdayBday.csv";
  private static final String THURSDAY_ADAY_FN = "periodsCSV/thursdayAday.csv";
  private static final String THURSDAY_BDAY_FN = "periodsCSV/thursdayBday.csv";
  private static final String WEDNESDAY_ADAY_FN = "periodsCSV/wensdayAday.csv";
  private static final String WEDNESDAY_BDAY_FN = "periodsCSV/wensdayBday.csv";

  public static void main(String[] args) {

    Stack<LocalDate> nonSchoolDays = new Stack<LocalDate>();
    Stack<SchoolDay> schoolDays = new Stack<SchoolDay>();
    Stack<LocalDate> ABDays = new Stack<LocalDate>();
    Stack<Period> erFridayAdayStack = createPeriodStack(ER_FRIDAY_ADAY_FN);
    Stack<Period> erFridayBdayStack = createPeriodStack(ER_FRIDAY_BDAY_FN);
    Stack<Period> fridayAdayStack = createPeriodStack(FRIDAY_ADAY_FN);
    Stack<Period> fridayBdayStack = createPeriodStack(FRIDAY_BDAY_FN);
    Stack<Period> mondayAdayStack = createPeriodStack(MONDAY_ADAY_FN);
    Stack<Period> mondayBdayStack = createPeriodStack(MONDAY_BDAY_FN);
    Stack<Period> tuesdayAdayStack = createPeriodStack(TUESDAY_ADAY_FN);
    Stack<Period> tuesdayBdayStack = createPeriodStack(TUESDAY_BDAY_FN);
    Stack<Period> thursdayAdayStack = createPeriodStack(THURSDAY_ADAY_FN);
    Stack<Period> thursdayBdayStack = createPeriodStack(THURSDAY_BDAY_FN);
    Stack<Period> wednesdayAdayStack = createPeriodStack(WEDNESDAY_ADAY_FN);
    Stack<Period> wednesdayBdayStack = createPeriodStack(WEDNESDAY_BDAY_FN);

    /*Defining ABDays */
    addABDays(ABDays);
    /*Defining Non School Days*/
    setNonSchoolDays(nonSchoolDays);
    /* Start of Year */
    LocalDate startOfYear = LocalDate.of(2018, 8, 29);
    /* Set Basis for AB Days*/
    setABDays(nonSchoolDays, schoolDays, ABDays, startOfYear);
    /* Set Day Flags based on AB Days and Days of the week */
    setDayType(schoolDays);
    /* Set Periods Based on days */
    setPeriods(
        schoolDays,
        erFridayAdayStack,
        erFridayBdayStack,
        fridayAdayStack,
        fridayBdayStack,
        mondayAdayStack,
        mondayBdayStack,
        tuesdayAdayStack,
        tuesdayBdayStack,
        thursdayAdayStack,
        thursdayBdayStack,
        wednesdayAdayStack,
        wednesdayBdayStack);

    /* Printing CSV format for mlcpdx.com/booked for just periods 1,3,7
     * As Periods 1,3,7 are in the computer lab */
    Room313ToBookedCSVPrint(schoolDays);
  }

  /*Printing in line Google App (Calendar) Script
   *Output can be used in script.google.com  */
  private static void printforGCal(Stack<SchoolDay> schoolDays) {
    for (SchoolDay s : schoolDays) {
      System.out.println(s.getPeriodsforCal());
    }
  }

  private static void Room313ToBookedCSVPrint(Stack<SchoolDay> schoolDays) {
    for (SchoolDay s : schoolDays) { // foreach grade in grades

      Stack<Period> periods = s.getPeriods();

      if (periods != null) {
        for (Period p : periods) {
          String pName = p.getName();
          if (pName.compareTo("Period 1") == 0
              || pName.compareTo("Period 3") == 0
              || pName.compareTo("Period 7") == 0) {

            String str =
                "\"bpemberton@apps4pps.net\",\"[Technology Location] Room 313 Computer Lab | 29 CPU's, 1 Teacher Workstation\","
                    + "\"HS CLASS "
                    + pName
                    + "\", \"--\" ,"
                    + "\""
                    + s.getLocalDate()
                    + " "
                    + p.getBegin()
                    + "\","
                    + "\""
                    + s.getLocalDate()
                    + " "
                    + p.getEnd()
                    + "\"";

            System.out.println(str);
          }
        }
      }
    }
  }

  private static void setPeriods(
      Stack<SchoolDay> schoolDays,
      Stack<Period> erFridayAdayStack,
      Stack<Period> erFridayBdayStack,
      Stack<Period> fridayAdayStack,
      Stack<Period> fridayBdayStack,
      Stack<Period> mondayAdayStack,
      Stack<Period> mondayBdayStack,
      Stack<Period> tuesdayAdayStack,
      Stack<Period> tuesdayBdayStack,
      Stack<Period> thursdayAdayStack,
      Stack<Period> thursdayBdayStack,
      Stack<Period> wednesdayAdayStack,
      Stack<Period> wednesdayBdayStack) {

    Stack<LocalDate> erf = new Stack<LocalDate>();
    erf.add(LocalDate.of(2018, 9, 28));
    erf.add(LocalDate.of(2018, 10, 26));
    erf.add(LocalDate.of(2018, 11, 30));
    erf.add(LocalDate.of(2018, 12, 14));
    erf.add(LocalDate.of(2019, 1, 25));
    erf.add(LocalDate.of(2019, 2, 22));
    erf.add(LocalDate.of(2019, 3, 22));
    erf.add(LocalDate.of(2019, 4, 26));
    erf.add(LocalDate.of(2019, 5, 31));

    for (SchoolDay s : schoolDays) {
      DayOfWeek currDayOfWeek = s.getLocalDate().getDayOfWeek();
      DayType currDayType = s.getDayType();
      LocalDate currDate = s.getLocalDate();
      switch (currDayOfWeek) {
        case MONDAY:
          if (currDayType == DayType.A_Regular) s.setPeriods(mondayAdayStack);
          if (currDayType == DayType.B_Regular) s.setPeriods(mondayBdayStack);
        case TUESDAY:
          if (currDayType == DayType.A_Metro) s.setPeriods(tuesdayAdayStack);
          if (currDayType == DayType.B_Metro) s.setPeriods(tuesdayBdayStack);
          break;
        case WEDNESDAY:
          if (currDayType == DayType.Check_And_Connect) s.setPeriods(wednesdayAdayStack);
          if (currDayType == DayType.B_Flex) s.setPeriods(wednesdayBdayStack);
          break;
        case THURSDAY:
          if (currDayType == DayType.A_GL_Metro) s.setPeriods(thursdayAdayStack);
          if (currDayType == DayType.B_GL_Metro) s.setPeriods(thursdayBdayStack);
          break;
          /* Extra logic for Early Release fridays*/
        case FRIDAY:
          if (currDayType == DayType.A_Friday) {
            s.setPeriods(fridayAdayStack);
          }
          if (currDayType == DayType.B_Friday) {
            s.setPeriods(fridayBdayStack);
          }
          if (erf.search(currDate) != -1) {
            if (s.getDayType() == DayType.A_Friday) {
              s.setDayType(DayType.Early_Release_Friday);
              s.setPeriods(erFridayAdayStack);
            } else {
              s.setPeriods(erFridayBdayStack);
            }
          }

          /* For one "Last friday of school month" not Georgian calendar month */
          if (s.getLocalDate() == LocalDate.of(2019, Month.MARCH, 22)) {
            s.setPeriods(erFridayBdayStack);
          }
          break;
      }
    }
  }

  private static Stack<Period> createPeriodStack(String fileName) {
    Stack<Period> periods = new Stack<Period>();
    Reader in = null;
    try {
      in = new FileReader(fileName);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Iterable<CSVRecord> records = null;
    try {
      assert in != null;
      records = CSVFormat.DEFAULT.withHeader().parse(in);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (CSVRecord record : records) {
      String name = record.get(0);
      String begin[] = record.get(1).split(":");
      String end[] = record.get(2).split(":");

      LocalTime begin_lt =
          LocalTime.of(Integer.parseInt(begin[0]), Integer.parseInt(begin[1].trim()));
      LocalTime end_lt = LocalTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1].trim()));

      periods.add(new Period(name, begin_lt, end_lt));
    }

    return periods;
  }

  private static void setDayType(Stack<SchoolDay> schoolDays) {

    for (SchoolDay s : schoolDays) {
      DayOfWeek currDayOfWeek = s.getLocalDate().getDayOfWeek();
      DayFlag currDayFlag = s.getDayFlag();

      switch (currDayOfWeek) {
        case MONDAY:
          if (currDayFlag == DayFlag.Aday) s.setDayType(DayType.A_Regular);
          if (currDayFlag == DayFlag.Bday) s.setDayType(DayType.B_Regular);
          break;
        case TUESDAY:
          if (currDayFlag == DayFlag.Aday) s.setDayType(DayType.A_Metro);
          if (currDayFlag == DayFlag.Bday) s.setDayType(DayType.B_Metro);
          break;
        case WEDNESDAY:
          if (currDayFlag == DayFlag.Aday) s.setDayType(DayType.Check_And_Connect);
          if (currDayFlag == DayFlag.Bday) s.setDayType(DayType.B_Flex);
          break;
        case THURSDAY:
          if (currDayFlag == DayFlag.Aday) s.setDayType(DayType.A_GL_Metro);
          if (currDayFlag == DayFlag.Bday) s.setDayType(DayType.B_GL_Metro);
          break;
        case FRIDAY:
          if (currDayFlag == DayFlag.Aday) s.setDayType(DayType.A_Friday);
          if (currDayFlag == DayFlag.Bday) s.setDayType(DayType.B_Friday);
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
