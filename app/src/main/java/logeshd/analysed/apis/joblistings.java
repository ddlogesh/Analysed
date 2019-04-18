package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

public class joblistings {
    @SerializedName("id")
    private int id;

    @SerializedName("position")
    private String position;

    @SerializedName("skills_req")
    private String skills_req;

    @SerializedName("qual_req")
    private String qual_req;

    @SerializedName("exp_req")
    private String exp_req;

    @SerializedName("job_location")
    private String job_location;

    @SerializedName("job_description")
    private String job_description;

    @SerializedName("package")
    private String packages;

    @SerializedName("job_time")
    private String job_time;

    @SerializedName("postedby")
    private String postedby;

    @SerializedName("createdon")
    private String createdon;

    @SerializedName("endon")
    private String endon;

    public joblistings() { }

    public joblistings(int id, String position, String skills_req, String qual_req, String exp_req, String job_location, String job_description, String packages, String job_time, String postedby, String createdon, String endon) {
        this.id = id;
        this.position = position;
        this.skills_req = skills_req;
        this.qual_req = qual_req;
        this.exp_req = exp_req;
        this.job_location = job_location;
        this.job_description = job_description;
        this.packages = packages;
        this.job_time = job_time;
        this.postedby = postedby;
        this.createdon = createdon;
        this.endon = endon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSkills_req() {
        return skills_req;
    }

    public void setSkills_req(String skills_req) {
        this.skills_req = skills_req;
    }

    public String getQual_req() {
        return qual_req;
    }

    public void setQual_req(String qual_req) {
        this.qual_req = qual_req;
    }

    public String getExp_req() {
        return exp_req;
    }

    public void setExp_req(String exp_req) {
        this.exp_req = exp_req;
    }

    public String getJob_location() {
        return job_location;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getJob_time() {
        return job_time;
    }

    public void setJob_time(String job_time) {
        this.job_time = job_time;
    }

    public String getPostedby() {
        return postedby;
    }

    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public String getEndon() {
        return endon;
    }

    public void setEndon(String endon) {
        this.endon = endon;
    }
}
