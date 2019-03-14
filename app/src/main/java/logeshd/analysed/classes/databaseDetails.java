package logeshd.analysed.classes;

public class databaseDetails {
    String name;
    String pic;
    String position;
    String url;

    public databaseDetails(String pic, String name, String url) {
        this.pic = pic;
        this.name = name;
        this.url = url;
    }

    public databaseDetails(String pic, String name, String position, String url) {
        this.pic = pic;
        this.name = name;
        this.position = position;
        this.url = url;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
