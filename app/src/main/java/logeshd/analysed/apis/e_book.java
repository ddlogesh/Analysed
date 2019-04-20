package logeshd.analysed.apis;

import com.google.gson.annotations.SerializedName;

public class e_book {
    @SerializedName("id")
    private int id;

    @SerializedName("seeker_email")
    private String seeker_email;

    @SerializedName("email")
    private String email;

    @SerializedName("eventId")
    private int eventId;

    @SerializedName("rdate")
    private String rdate;

    @SerializedName("event_name")
    private String event_name;

    @SerializedName("event_venue")
    private String event_venue;

    @SerializedName("event_date")
    private String event_date;

    @SerializedName("review")
    private String review;

    @SerializedName("notif")
    private int notif;

    @SerializedName("remainder")
    private String remainder;

    @SerializedName("ppl_count")
    private int ppl_count;

    public e_book() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeeker_email() {
        return seeker_email;
    }

    public void setSeeker_email(String seeker_email) {
        this.seeker_email = seeker_email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_venue() {
        return event_venue;
    }

    public void setEvent_venue(String event_venue) {
        this.event_venue = event_venue;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getNotif() {
        return notif;
    }

    public void setNotif(int notif) {
        this.notif = notif;
    }

    public String getRemainder() {
        return remainder;
    }

    public void setRemainder(String remainder) {
        this.remainder = remainder;
    }

    public int getPpl_count() {
        return ppl_count;
    }

    public void setPpl_count(int ppl_count) {
        this.ppl_count = ppl_count;
    }
}
