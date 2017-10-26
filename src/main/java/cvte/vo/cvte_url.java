package cvte.vo;

import java.util.Date;

public class cvte_url {

    int id;
    String long_url;
    String short_url;
    Date date;

    public cvte_url() {
    }

    @Override
    public String toString() {
        return "cvte_url{" +
                "id=" + id +
                ", long_url='" + long_url + '\'' +
                ", short_url='" + short_url + '\'' +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public cvte_url(String long_url, String short_url, Date date) {

        this.long_url = long_url;
        this.short_url = short_url;
        this.date = date;
    }

    public cvte_url(int id, String long_url, String short_url, Date date) {

        this.id = id;
        this.long_url = long_url;
        this.short_url = short_url;
        this.date = date;
    }
}
