package logeshd.analysed.classes;

public class drawer {
    private String title,url,status;

    public drawer(String title) {
        this.title = title;
    }

    public drawer(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public drawer(String title, String url, String status) {
        this.title = title;
        this.url = url;
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
