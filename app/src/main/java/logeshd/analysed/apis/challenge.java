package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

public class challenge {
    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("challengeId")
    private String challengeId;

    @SerializedName("challenge_end")
    private String challenge_end;

    @SerializedName("challenge_name")
    private String challenge_name;

    @SerializedName("challenge_description")
    private String challenge_description;

    @SerializedName("winners")
    private String winners;

    @SerializedName("challenge_time")
    private String challenge_time;

    @SerializedName("end_challenge")
    private String end_challenge;

    @SerializedName("seeker_email")
    private String seeker_email;

    @SerializedName("file")
    private String file;

    @SerializedName("t_score")
    private int t_score;

    @SerializedName("score")
    private int score;

    @SerializedName("cash")
    private String cash;

    @SerializedName("coupan")
    private String coupan;

    @SerializedName("service")
    private String service;

    @SerializedName("job")
    private String job;

    @SerializedName("other")
    private String other;

    public challenge(int id, String email, String challengeId, String challenge_end, String challenge_name, String challenge_description, String winners, String challenge_time, String end_challenge, String seeker_email, String file, int t_score, int score) {
        this.id = id;
        this.email = email;
        this.challengeId = challengeId;
        this.challenge_end = challenge_end;
        this.challenge_name = challenge_name;
        this.challenge_description = challenge_description;
        this.winners = winners;
        this.challenge_time = challenge_time;
        this.end_challenge = end_challenge;
        this.seeker_email = seeker_email;
        this.file = file;
        this.t_score = t_score;
        this.score = score;
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

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public String getChallenge_end() {
        return challenge_end;
    }

    public void setChallenge_end(String challenge_end) {
        this.challenge_end = challenge_end;
    }

    public String getChallenge_name() {
        return challenge_name;
    }

    public void setChallenge_name(String challenge_name) {
        this.challenge_name = challenge_name;
    }

    public String getChallenge_description() {
        return challenge_description;
    }

    public void setChallenge_description(String challenge_description) {
        this.challenge_description = challenge_description;
    }

    public String getWinners() {
        return winners;
    }

    public void setWinners(String winners) {
        this.winners = winners;
    }

    public String getChallenge_time() {
        return challenge_time;
    }

    public void setChallenge_time(String challenge_time) {
        this.challenge_time = challenge_time;
    }

    public String getEnd_challenge() {
        return end_challenge;
    }

    public void setEnd_challenge(String end_challenge) {
        this.end_challenge = end_challenge;
    }

    public String getSeeker_email() {
        return seeker_email;
    }

    public void setSeeker_email(String seeker_email) {
        this.seeker_email = seeker_email;
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

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getCoupan() {
        return coupan;
    }

    public void setCoupan(String coupan) {
        this.coupan = coupan;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
