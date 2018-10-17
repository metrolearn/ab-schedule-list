package ab_schedule_list;

import enums.DayFlag;
import enums.DayType;

import java.time.LocalDate;
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
    public String toString() {
        return "SchoolDay{" +
                "dayFlag=" + dayFlag +
                ", dayType=" + dayType +
                ", localDate=" + localDate +
                ", description='" + description + '\'' +
                ", periodsJson=" + periods +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolDay)) return false;
        SchoolDay schoolDay = (SchoolDay) o;
        return getDayFlag() == schoolDay.getDayFlag() &&
                getDayType() == schoolDay.getDayType() &&
                Objects.equals(getLocalDate(), schoolDay.getLocalDate()) &&
                Objects.equals(getDescription(), schoolDay.getDescription()) &&
                Objects.equals(getPeriods(), schoolDay.getPeriods());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDayFlag(), getDayType(), getLocalDate(), getDescription(), getPeriods());
    }
}
