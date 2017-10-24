package cvte.vo;

import java.sql.Date;

public class blackpeople {
    int id;
    int userid;
    Date startime;
    Date lasttime;

    public blackpeople() {
    }

    public blackpeople(int id, int userid, Date startime, Date lasttime) {

        this.id = id;
        this.userid = userid;
        this.startime = startime;
        this.lasttime = lasttime;
    }

    @Override
    public String toString() {
        return "blackpeople{" +
                "id=" + id +
                ", userid=" + userid +
                ", startime=" + startime +
                ", lasttime=" + lasttime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getStartime() {
        return startime;
    }

    public void setStartime(Date startime) {
        this.startime = startime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }
}
