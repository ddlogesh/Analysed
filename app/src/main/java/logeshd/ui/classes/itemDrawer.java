package logeshd.ui.classes;

public class itemDrawer {
    String title,url;

    public itemDrawer(String title) {
        this.title = title;
    }

    public itemDrawer(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
