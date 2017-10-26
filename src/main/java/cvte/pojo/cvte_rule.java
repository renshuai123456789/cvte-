package cvte.pojo;

public class cvte_rule {

    int id;
    int ruletime;
    int rulenum;
    int rulelooknum;

    @Override
    public String toString() {
        return "cvte_rule{" +
                "id=" + id +
                ", ruletime=" + ruletime +
                ", rulenum=" + rulenum +
                ", rulelooknum=" + rulelooknum +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuletime() {
        return ruletime;
    }

    public void setRuletime(int ruletime) {
        this.ruletime = ruletime;
    }

    public int getRulenum() {
        return rulenum;
    }

    public void setRulenum(int rulenum) {
        this.rulenum = rulenum;
    }

    public int getRulelooknum() {
        return rulelooknum;
    }

    public void setRulelooknum(int rulelooknum) {
        this.rulelooknum = rulelooknum;
    }

    public cvte_rule(int ruletime, int rulenum, int rulelooknum) {
        this.ruletime = ruletime;
        this.rulenum = rulenum;
        this.rulelooknum = rulelooknum;
    }

    public cvte_rule() {
    }

    public cvte_rule(int id, int ruletime, int rulenum, int rulelooknum) {

        this.id = id;
        this.ruletime = ruletime;
        this.rulenum = rulenum;
        this.rulelooknum = rulelooknum;
    }
}
