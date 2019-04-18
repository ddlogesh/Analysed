package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

public class databases {
    @SerializedName("id")
    private String id;

    @SerializedName("fname")
    private String fname;

    @SerializedName("lname")
    private String lname;

    @SerializedName("picture")
    private String picture;

    @SerializedName("position")
    private String position;

    @SerializedName("Qualification")
    private String Qualification;

    @SerializedName("yearofpassing")
    private String yearofpassing;

    @SerializedName("Experience")
    private String Experience;

    @SerializedName("location")
    private String location;

    @SerializedName("Email")
    private String Email;

    @SerializedName("skills")
    private String skills;

    public databases(String id, String fname, String lname, String picture, String position, String qualification, String yearofpassing, String experience, String location, String email, String skills) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.picture = picture;
        this.position = position;
        Qualification = qualification;
        this.yearofpassing = yearofpassing;
        Experience = experience;
        this.location = location;
        Email = email;
        this.skills = skills;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getYearofpassing() {
        return yearofpassing;
    }

    public void setYearofpassing(String yearofpassing) {
        this.yearofpassing = yearofpassing;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
