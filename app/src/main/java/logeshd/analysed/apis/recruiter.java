package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

public class recruiter {
    @SerializedName("id")
    private int id;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("fname")
    private String fname;

    @SerializedName("lname")
    private String lname;

    @SerializedName("referal")
    private String referal;

    @SerializedName("email")
    private String email;

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

    public recruiter(int id, String created_at, String fname, String lname, String referal, String email, String phone, String organisation, String designation, String address, String picture) {
        this.id = id;
        this.created_at = created_at;
        this.fname = fname;
        this.lname = lname;
        this.referal = referal;
        this.email = email;
        this.phone = phone;
        this.organisation = organisation;
        this.designation = designation;
        this.address = address;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
