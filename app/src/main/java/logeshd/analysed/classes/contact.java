package logeshd.analysed.classes;

public class contact {
    String mobile;
    String name;
    Boolean status;

    public contact(String name, String mobile, Boolean status) {
        this.name = name;
        this.mobile = mobile;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
