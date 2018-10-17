package ab_schedule_list;

import java.time.LocalTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period)) return false;
        Period period = (Period) o;
        return Objects.equals(getName(), period.getName()) &&
                Objects.equals(getBegin(), period.getBegin()) &&
                Objects.equals(getEnd(), period.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBegin(), getEnd());
    }

    @Override
    public String toString() {
        return "Period{" +
                "name='" + name + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
