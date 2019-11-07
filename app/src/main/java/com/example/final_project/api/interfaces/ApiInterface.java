package com.example.final_project.api.interfaces;

import com.example.final_project.models.ControllerListItemModel;
import com.example.final_project.models.ControllerModel;
import com.example.final_project.models.SignInDataModel;
import com.example.final_project.models.SignUpDataModel;
import com.example.final_project.models.TokenModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    //ok
    @PUT("/api/User")
    Call<SignUpDataModel> signUp(@Body SignUpDataModel signUpDatamodel);

    //ok
    @POST("/api/Token/Auth")
    Call<TokenModel> signIn(@Body SignInDataModel signInDataModel);

    //ok
    //get list of available controllers
    @GET("/api/Controller")
    Call<List<ControllerListItemModel>> listControllers();

    //add new controller
    @PUT("/api/Controller")
    Call<ControllerModel> addController(@Body ControllerModel controllerModel);

    //edit controller's settings
    @POST("/api/Controller")
    Call<ControllerModel> editController(@Body ControllerModel controllerModel);

    //get selected controller
    @GET("api/Controller/{id}")
    Call<ControllerModel> getController(@Path("id") int id);

    //delete selected controller
    @DELETE("api/Controller/{id}")
    Call<ControllerModel> deleteController(@Path("id") int id);

}