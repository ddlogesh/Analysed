package logeshd.analysed.service;

import java.util.List;

import logeshd.analysed.apis.databases;
import logeshd.analysed.apis.jobseekers;
import logeshd.analysed.apis.status;
import logeshd.analysed.apis.users;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MainService {
    String BASE_URL = "http://analysed.in/analysed/webservices/";

    /*@GET("js/getAll.php")
    Call<List<demo>> loadChanges();

    @GET("js/getSingle1.php")
    Call<List<demo>> getSingle(@Query("id") String id);

    @POST("js/upload1.php")
    Call<status> uploader(@Body demo d);*/

    @GET("js/validateUser.php")
    Call<users> loginApi(@Query("username") String username, @Query("password") String password);

    @POST("js/signup.php")
    Call<status> checkForSignupApi(@Body users u);

    @POST("js/signup1.php")
    Call<status> signupApi(@Body jobseekers j);

    @Multipart
    @POST("js/profileUpload.php")
    Call<status> uploadImageApi(@Part MultipartBody.Part file);

    @Multipart
    @POST("js/resumeUpload.php")
    Call<status> uploadResumeApi(@Part MultipartBody.Part file);

    @GET("js/database.php")
    Call<List<databases>> getDatabase(@Query("username") String username);


}
