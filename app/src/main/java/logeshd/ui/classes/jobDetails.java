package logeshd.ui.classes;

public class jobDetails {
    String icon,title,company;
    String month,date;

    public jobDetails(String icon, String title, String company, String month, String date) {
        this.icon = icon;
        this.title = title;
        this.company = company;
        this.month = month;
        this.date = date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
