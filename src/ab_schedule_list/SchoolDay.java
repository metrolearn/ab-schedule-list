package ab_schedule_list;

import enums.*;

import java.time.LocalDate;

public class SchoolDay {

    private DayFlag dayFlag;
    private DayType dayType;
    private Period period;
    private LocalDate localDate;
    private String description;
    private Boolean nonSchoolDay;


    public SchoolDay() {

        setDayFlag(DayFlag.Default);
        setDayType(DayType.Default);
        setPeriod(Period.Default);

    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public SchoolDay(LocalDate localDate) {
        super();
        this.localDate = localDate;


    }


    public DayType getDayType() {
        return dayType;
    }

    public void setDayType(DayType dayType) {
        this.dayType = dayType;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getNonSchoolDay() {
        return nonSchoolDay;
    }

    public void setNonSchoolDay(Boolean nonSchoolDay) {
        this.nonSchoolDay = nonSchoolDay;
    }

    public DayFlag getDayFlag() {
        return dayFlag;
    }

    public void setDayFlag(DayFlag dayFlag) {
        this.dayFlag = dayFlag;
    }

}