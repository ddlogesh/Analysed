package logeshd.analysed.classes;

public class recStatus {
    private String title,subTitle;
    private Boolean layoutStatus;
    private int status,progress;

    public recStatus(Boolean layoutStatus, String title, String subTitle, int status) {
        this.layoutStatus = layoutStatus;
        this.title = title;
        this.subTitle = subTitle;
        this.status = status;
    }

    public recStatus(String title, String subTitle, int status, int progress) {
        this.title = title;
        this.subTitle = subTitle;
        this.status = status;
        this.progress = progress;
    }

    public recStatus(Boolean layoutStatus, int progress) {
        this.layoutStatus = layoutStatus;
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Boolean getLayoutStatus() {
        return layoutStatus;
    }

    public void setLayoutStatus(Boolean layoutStatus) {
        this.layoutStatus = layoutStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
