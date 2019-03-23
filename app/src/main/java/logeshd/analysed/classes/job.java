package logeshd.analysed.classes;

public class job {
    String company;
    String date;
    String icon;
    String month;
    String title;

    public job(String icon, String title, String company, String month, String date) {
        this.icon = icon;
        this.title = title;
        this.company = company;
        this.month = month;
        this.date = date;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
