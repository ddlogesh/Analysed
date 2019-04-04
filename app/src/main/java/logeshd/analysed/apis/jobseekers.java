package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

public class jobseekers {
    @SerializedName("id")
    private int id;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("fname")
    private String fname;

    @SerializedName("lname")
    private String lname;

    @SerializedName("referal")
    private String referal;

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

    @SerializedName("PhNumber")
    private String PhNumber;

    @SerializedName("resume")
    private String resume;

    @SerializedName("resumename")
    private String resumename;

    @SerializedName("skills")
    private String skills;

    @SerializedName("picture")
    private String picture;

    @SerializedName("interest")
    private String interest;

    @SerializedName("projects")
    private String projects;

    @SerializedName("public_access_key")
    private String public_access_key;

    public jobseekers() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getReferal() {
        return referal;
    }

    public void setReferal(String referal) {
        this.referal = referal;
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

    public String getPhNumber() {
        return PhNumber;
    }

    public void setPhNumber(String phNumber) {
        PhNumber = phNumber;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getResumename() {
        return resumename;
    }

    public void setResumename(String resumename) {
        this.resumename = resumename;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getPublic_access_key() {
        return public_access_key;
    }

    public void setPublic_access_key(String public_access_key) {
        this.public_access_key = public_access_key;
    }
}
