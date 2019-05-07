package logeshd.analysed.service;

import java.util.List;

import logeshd.analysed.apis.challenge;
import logeshd.analysed.apis.databases;
import logeshd.analysed.apis.e_book;
import logeshd.analysed.apis.joblistings;
import logeshd.analysed.apis.jobseekers;
import logeshd.analysed.apis.recruiter;
import logeshd.analysed.apis.skills;
import logeshd.analysed.apis.userDetails;
import logeshd.analysed.apis.status;
import logeshd.analysed.apis.task;
import logeshd.analysed.apis.users;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MainService {
    String BASE_URL = "http://analysed.in/analysed/webservices/";

    /*********************************************************************************/
    //Common-Tools

    @GET("common/getProfilePic.php")
    Call<status> getProfilePicApi(@Query("email") String email, @Query("userRole") String userRole);

    @GET("common/getResume.php")
    Call<status> getResumeApi(@Query("email") String email);

    @GET("common/getProfile.php")
    Call<userDetails> getProfileApi(@Query("email") String email, @Query("userRole") String userRole);

    @FormUrlEncoded
    @POST("common/editProfile.php")
    Call<String> editProfileApi(@Field("title") String title, @Field("text") String text, @Field("userRole") String userRole, @Field("email") String email);

    /*********************************************************************************/
    //Common

    @GET("common/login.php")
    Call<userDetails> loginApi(@Query("username") String username, @Query("password") String password);

    @POST("common/signup.php")
    Call<status> checkForSignupApi(@Body users u);

    @POST("common/signup1.php")
    Call<status> signupJSApi(@Body jobseekers j);

    @POST("common/signup2.php")
    Call<status> signupRCApi(@Body recruiter r);

    @Multipart
    @POST("common/profileUpload.php")
    Call<status> uploadImageApi(@Part MultipartBody.Part file, @Part("userRole") RequestBody userRole);

    @Multipart
    @POST("common/resumeUpload.php")
    Call<status> uploadResumeApi(@Part MultipartBody.Part file);

    /*********************************************************************************/
    //Recruiters Dashboard

    @POST("recruiter/createJob.php")
    Call<status> createJob(@Body joblistings j);

    @GET("recruiter/database.php")
    Call<List<databases>> getDatabase(@Query("username") String username);

    @GET("recruiter/joblisting.php")
    Call<List<joblistings>> getJobListings(@Query("username") String username);

    @FormUrlEncoded
    @POST("recruiter/closeJob.php")
    Call<String> closeJob(@Field("id") int id);

    @GET("recruiter/viewTasks.php")
    Call<List<task>> getAssignedTasks(@Query("username") String username);

    @GET("recruiter/createTask.php")
    Call<List<jobseekers>> getJobseekers(@Query("jobid") int jobid);

    @POST("recruiter/createTask1.php")
    Call<status> createTask(@Body task t);

    @GET("recruiter/viewChallenges.php")
    Call<List<challenge>> getAssignedChallenges(@Query("username") String username);

    @POST("recruiter/createChallenge.php")
    Call<status> createChallenge(@Body challenge c);

    /*********************************************************************************/
    //Jobseekers Dashboard

    @GET("jobseeker/viewTasks.php")
    Call<List<task>> getTasks(@Query("username") String username);

    @GET("jobseeker/viewChallenges.php")
    Call<List<challenge>> getChallenges();

    @GET("jobseeker/viewSkills.php")
    Call<skills> getSkillsApi(@Query("email") String email, @Query("user_id") int user_id);

    @GET("jobseeker/getEvents.php")
    Call<List<e_book>> getEventsApi(@Query("seeker_email") String seeker_email);
}
