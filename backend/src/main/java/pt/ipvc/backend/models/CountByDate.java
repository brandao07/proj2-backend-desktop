package pt.ipvc.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.Date;

public class CountByDate {

    private Date data;

    private Long count;

    public CountByDate(Date data, Long count) {
        this.data = data;
        this.count = count;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
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
