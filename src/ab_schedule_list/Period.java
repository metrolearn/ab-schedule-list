package ab_schedule_list;

import java.time.LocalTime;

public class Period {

    private String name;
    private LocalTime begin;
    private LocalTime end;

    public Period(String name, LocalTime begin, LocalTime end) {

        this.name = name;
        this.begin = begin;
        this.end = end;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getBegin() {
        return begin;
    }

    public void setBegin(LocalTime begin) {
        this.begin = begin;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}
