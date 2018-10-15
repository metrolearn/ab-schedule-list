package ab_schedule_list;

import enums.*;

public class SchoolDay {

    private DayFlag dayFlag;
    private DayType dayType;
    private Period period;
    private Integer day;
    private Integer month;
    private Integer year;
    private String description;
    private Boolean nonSchoolDay;


    public SchoolDay() {

        setDayFlag(DayFlag.Default);
        setDayType(DayType.Default);
        setPeriod(Period.Default);
        this.day = 0;
        this.month = 0;
        this.year = 0;

    }

    public SchoolDay( Integer month,Integer day, Integer year) {
        super();
        this.day = day;
        this.month = month;
        this.year = year;

    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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

};