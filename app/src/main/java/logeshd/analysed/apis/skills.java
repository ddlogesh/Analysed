package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

public class skills {
    @SerializedName("r_total")
    private int r_total;

    @SerializedName("r_skill")
    private float r_skill;

    @SerializedName("c_total")
    private int c_total;

    @SerializedName("c_skill")
    private float c_skill;

    @SerializedName("t_total")
    private int t_total;

    @SerializedName("t_skill")
    private float t_skill;

    public skills(int r_total, float r_skill, int c_total, float c_skill, int t_total, float t_skill) {
        this.r_total = r_total;
        this.r_skill = r_skill;
        this.c_total = c_total;
        this.c_skill = c_skill;
        this.t_total = t_total;
        this.t_skill = t_skill;
    }

    public int getR_total() {
        return r_total;
    }

    public void setR_total(int r_total) {
        this.r_total = r_total;
    }

    public float getR_skill() {
        return r_skill;
    }

    public void setR_skill(float r_skill) {
        this.r_skill = r_skill;
    }

    public int getC_total() {
        return c_total;
    }

    public void setC_total(int c_total) {
        this.c_total = c_total;
    }

    public float getC_skill() {
        return c_skill;
    }

    public void setC_skill(float c_skill) {
        this.c_skill = c_skill;
    }

    public int getT_total() {
        return t_total;
    }

    public void setT_total(int t_total) {
        this.t_total = t_total;
    }

    public float getT_skill() {
        return t_skill;
    }

    public void setT_skill(float t_skill) {
        this.t_skill = t_skill;
    }
}
