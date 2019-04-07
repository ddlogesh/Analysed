package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

public class task {
    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("taskId")
    private String taskId;

    @SerializedName("seeker_email")
    private String seeker_email;

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

    public task(int id, String email, String taskId, String seeker_email, String task_name, String task_desription, String task_end, String task_time, String end_task, String file, int t_score, int score, String c_date) {
        this.id = id;
        this.email = email;
        this.taskId = taskId;
        this.seeker_email = seeker_email;
        this.task_name = task_name;
        this.task_desription = task_desription;
        this.task_end = task_end;
        this.task_time = task_time;
        this.end_task = end_task;
        this.file = file;
        this.t_score = t_score;
        this.score = score;
        this.c_date = c_date;
    }

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
}
