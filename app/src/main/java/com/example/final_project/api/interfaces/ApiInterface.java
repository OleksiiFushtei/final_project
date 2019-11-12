package com.example.final_project.api.interfaces;

import com.example.final_project.models.ControllerListItemModel;
import com.example.final_project.models.ControllerModel;
import com.example.final_project.models.SensorModel;
import com.example.final_project.models.SensorTypeModel;
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

    //auth
    @PUT("/api/User")
    Call<SignUpDataModel> signUp(@Body SignUpDataModel signUpDatamodel);

    @POST("/api/Token/Auth")
    Call<TokenModel> signIn(@Body SignInDataModel signInDataModel);

    //controllers
    //get list of available controllers
    @GET("/api/Controller")
    Call<List<ControllerListItemModel>> listControllers();

    //add new controller
    @PUT("/api/Controller")
    Call<ControllerModel> addController(@Body ControllerModel controllerModel);

    //edit controller
    @POST("/api/Controller")
    Call<ControllerModel> editController(@Body ControllerModel controllerModel);

    //get selected controller
    @GET("api/Controller/{id}")
    Call<ControllerModel> getController(@Path("id") int id);

    //delete controller
    @DELETE("api/Controller/{id}")
    Call<ControllerModel> deleteController(@Path("id") int id);

    //sensors
    //get list of available sensors
    @GET("/api/Sensor/All/{controllerId}")
    Call<List<SensorModel>> listSensors(@Path("controllerId") int id);

    //get selected controller
    @GET("/api/Sensor/{id}")
    Call<SensorModel> getSensor(@Path("id") int id);

    //delete controller
    @DELETE("/api/Sensor/{id}")
    Call<SensorModel> deleteSensor(@Path("id") int id);

    //add new sensor
    @PUT("/api/Sensor")
    Call<SensorModel> addSensor(@Body SensorModel sensorModel);

    //edit sensor
    @POST("/api/Sensor")
    Call<SensorModel> editSensor(@Body SensorModel sensorModel);

    //get type of sensor
    @GET("/api/SensorType")
    Call<SensorTypeModel> getSensorType(@Path("id") int id);

}