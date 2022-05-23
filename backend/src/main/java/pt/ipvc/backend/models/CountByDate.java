package pt.ipvc.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.Date;

public class CountByDate {

    @Basic
    @Column(name = "data")
    private Date data;

    @Basic
    @Column(name = "count")
    private long count;

    public CountByDate(Date data, long count) {
        this.data = data;
        this.count = count;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountByDate{" +
                "data=" + data +
                ", count=" + count +
                '}';
    }
}
