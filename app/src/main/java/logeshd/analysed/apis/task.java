package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class task {
    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("taskId")
    private String taskId;

    @SerializedName("seeker_email")
    private String seeker_email;

    @SerializedName("seeker_email_list")
    private List<String> seeker_email_list;

    @SerializedName("task_acc")
    private String task_acc;

    @SerializedName("task_name")
    private String task_name;

    @SerializedName("task_desription")
    private String task_desription;

    @SerializedName("task_end")
    private String task_end;

    @SerializedName("task_time")
    private String task_time;

    @SerializedName("end_task")
    private String end_task;

    @SerializedName("file")
    private String file;

    @SerializedName("t_score")
    private int t_score;

    @SerializedName("score")
    private int score;

    @SerializedName("c_date")
    private String c_date;

    /****************************************************************************************/

    @SerializedName("way")
    private String way;

    @SerializedName("hint")
    private String hint;

    /****************************************************************************************/

    public task() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSeeker_email() {
        return seeker_email;
    }

    public void setSeeker_email(String seeker_email) {
        this.seeker_email = seeker_email;
    }

    public List<String> getSeeker_email_list() {
        return seeker_email_list;
    }

    public void setSeeker_email_list(List<String> seeker_email_list) {
        this.seeker_email_list = seeker_email_list;
    }

    public String getTask_acc() {
        return task_acc;
    }

    public void setTask_acc(String task_acc) {
        this.task_acc = task_acc;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_desription() {
        return task_desription;
    }

    public void setTask_desription(String task_desription) {
        this.task_desription = task_desription;
    }

    public String getTask_end() {
        return task_end;
    }

    public void setTask_end(String task_end) {
        this.task_end = task_end;
    }

    public String getTask_time() {
        return task_time;
    }

    public void setTask_time(String task_time) {
        this.task_time = task_time;
    }

    public String getEnd_task() {
        return end_task;
    }

    public void setEnd_task(String end_task) {
        this.end_task = end_task;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getT_score() {
        return t_score;
    }

    public void setT_score(int t_score) {
        this.t_score = t_score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
