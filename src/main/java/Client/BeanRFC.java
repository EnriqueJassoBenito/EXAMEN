package Client;

public class BeanRFC {

    private String name;

    private String ap;

    private String am;

    private String curp;

    private String date;

    //private String RFC;

    public BeanRFC() {
    }

    public BeanRFC(String name, String ap, String am, String curp, String date) {
        this.name = name;
        this.ap = ap;
        this.am = am;
        this.curp = curp;
        this.date = date;
        //this.RFC = RFC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
/*
    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }*/
}
