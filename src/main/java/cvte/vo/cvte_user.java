package cvte.vo;

public class cvte_user {

    int id;
    String username;
    String realname;
    String pwd;
    String phone;
    int delete1;

    public cvte_user() {
    }

    public cvte_user(int id, String username, String realname, String pwd, String phone, int delete1) {

        this.id = id;
        this.username = username;
        this.realname = realname;
        this.pwd = pwd;
        this.phone = phone;
        this.delete1 = delete1;
    }

    @Override
    public String toString() {
        return "cvte_user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", phone='" + phone + '\'' +
                ", delete=" + delete1 +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDelete1() {
        return delete1;
    }

    public void setDelete1(int delete) {
        this.delete1 = delete;
    }

    public cvte_user(String username, String realname, String pwd, String phone, int delete1) {
        this.username = username;
        this.realname = realname;
        this.pwd = pwd;
        this.phone = phone;
        this.delete1 = delete1;
    }
}