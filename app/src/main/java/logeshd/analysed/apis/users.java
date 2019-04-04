package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

public class users {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("UserRole")
    private String UserRole;

    @SerializedName("reume")
    private String resume;

    @SerializedName("verified")
    private int verified;

    @SerializedName("verifykey")
    private String verifykey;

    @SerializedName("verifykey2")
    private String verifykey2;

    public users(String username, String password, String userRole, int verified) {
        this.username = username;
        this.password = password;
        UserRole = userRole;
        this.verified = verified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public String getVerifykey() {
        return verifykey;
    }

    public void setVerifykey(String verifykey) {
        this.verifykey = verifykey;
    }

    public String getVerifykey2() {
        return verifykey2;
    }

    public void setVerifykey2(String verifykey2) {
        this.verifykey2 = verifykey2;
    }
}
