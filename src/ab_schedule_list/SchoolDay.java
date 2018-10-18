package ab_schedule_list;

import enums.DayFlag;
import enums.DayType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import java.util.Stack;

public class SchoolDay {

  private DayFlag dayFlag;
  private DayType dayType;
  private LocalDate localDate;
  private String description;
  private Stack<Period> periods;

  public SchoolDay() {

    setDayFlag(DayFlag.Default);
    setDayType(DayType.Default);
  }

  public SchoolDay(LocalDate localDate) {
    super();
    this.localDate = localDate;
  }

  public Stack<Period> getPeriods() {
    return periods;
  }

  public void setPeriods(Stack<Period> periods) {
    this.periods = periods;
  }

  public String getPeriodsforCal() {

    String periodStr = null;
    String dayStr = null;
    // createEvent('Test 11 Landing',
    //                    new Date('October 21, 2018  8:15:00 AM PDT'),
    //    new Date
    // ('October 21, 2018  8:30:00 AM PDT'));

    if (this.dayType != null || this.dayType != null) {

      String pStr = this.dayFlag.toString() + " " + this.dayType.toString();
      String dDate = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
      dayStr = "createAllDayEvent('" + pStr + "', new Date('" + dDate + "'));\n";

      for (Period p : periods) {

        String s1 = "createEvent('";
        String sDescription = p.getName();
        String s2 = "', new Date('";
        String sBegin =
            localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
                + " "
                + p.getBegin()
                    .format(
                        DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                            .withZone(ZoneId.systemDefault()));
        String s3 = "'), new Date('";
        String sEnd = "October 21, 2018  8:30:00 AM PDT";
        String s5 = "'))";
        sEnd =
            localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
                + " "
                + p.getEnd()
                    .format(
                        DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                            .withZone(ZoneId.systemDefault()));
        periodStr = s1 + sDescription + s2 + sBegin + s3 + sEnd + s5 + ";";
      }

    }
      return "hscal."+dayStr + "hscal."+periodStr;

  }
  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  public DayType getDayType() {
    return dayType;
  }

  public void setDayType(DayType dayType) {
    this.dayType = dayType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DayFlag getDayFlag() {
    return dayFlag;
  }

  public void setDayFlag(DayFlag dayFlag) {
    this.dayFlag = dayFlag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SchoolDay)) return false;
    SchoolDay schoolDay = (SchoolDay) o;
    return getDayFlag() == schoolDay.getDayFlag()
        && getDayType() == schoolDay.getDayType()
        && Objects.equals(getLocalDate(), schoolDay.getLocalDate())
        && Objects.equals(getDescription(), schoolDay.getDescription())
        && Objects.equals(getPeriods(), schoolDay.getPeriods());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDayFlag(), getDayType(), getLocalDate(), getDescription(), getPeriods());
  }

  @Override
  public String toString() {
    return "SchoolDay{"
        + "dayFlag="
        + dayFlag
        + ", dayType="
        + dayType
        + ", localDate="
        + localDate
        + ", description='"
        + description
        + '\''
        + ", periods="
        + periods
        + '}';
  }
}
