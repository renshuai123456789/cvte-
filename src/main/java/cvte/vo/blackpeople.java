package cvte.vo;

import java.util.Date;

public class blackpeople {
    int id;
    int userid;
    Date starttime;
    Date lasttime;

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

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    @Override
    public String toString() {
        return "blackpeople{" +
                "id=" + id +
                ", userid=" + userid +
                ", starttime=" + starttime +
                ", lasttime=" + lasttime +
                '}';
    }

    public blackpeople(int userid, Date starttime, Date lasttime) {
        this.userid = userid;
        this.starttime = starttime;
        this.lasttime = lasttime;
    }

    public blackpeople() {

    }

    public blackpeople(int id, int userid, Date starttime, Date lasttime) {

        this.id = id;
        this.userid = userid;
        this.starttime = starttime;
        this.lasttime = lasttime;
    }
}
