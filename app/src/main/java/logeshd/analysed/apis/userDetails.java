package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

public class userDetails {
    @SerializedName("user_id")
    private int user_id;

    @SerializedName("UserRole")
    private String UserRole;

    @SerializedName("id")
    private int id;

    @SerializedName("fname")
    private String fname;

    @SerializedName("lname")
    private String lname;

    @SerializedName("referal")
    private String referal;

    @SerializedName("phone")
    private String phone;

    @SerializedName("organisation")
    private String organisation;

    @SerializedName("designation")
    private String designation;

    @SerializedName("address")
    private String address;

    @SerializedName("picture")
    private String picture;

    /*****************************************************/

    @SerializedName("Qualification")
    private String Qualification;

    @SerializedName("yearofpassing")
    private String yearofpassing;

    @SerializedName("Experience")
    private String Experience;

    @SerializedName("location")
    private String location;

    @SerializedName("PhNumber")
    private String PhNumber;

    @SerializedName("resume")
    private String resume;

    @SerializedName("resumename")
    private String resumename;

    @SerializedName("skills")
    private String skills;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getReferal() {
        return referal;
    }

    public void setReferal(String referal) {
        this.referal = referal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
}
