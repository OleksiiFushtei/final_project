package com.example.final_project.api.interfaces;

import com.example.final_project.models.CommandModel;
import com.example.final_project.models.ConditionTypeModel;
import com.example.final_project.models.ControllerAccessModel;
import com.example.final_project.models.ControllerListItemModel;
import com.example.final_project.models.ControllerModel;
import com.example.final_project.models.DeviceConfigurationModel;
import com.example.final_project.models.DeviceModel;
import com.example.final_project.models.MeasureModel;
import com.example.final_project.models.ScriptModel;
import com.example.final_project.models.SensorModel;
import com.example.final_project.models.SignInDataModel;
import com.example.final_project.models.SignUpDataModel;
import com.example.final_project.models.TokenModel;

import java.util.List;

import dagger.BindsOptionalOf;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    //get selected sensor
    @GET("/api/Sensor/{id}")
    Call<SensorModel> getSensor(@Path("id") int id);

    //delete sensor
    @DELETE("/api/Sensor/{id}")
    Call<SensorModel> deleteSensor(@Path("id") int id);

    //add new sensor
    @PUT("/api/Sensor")
    Call<SensorModel> addSensor(@Body SensorModel sensorModel);

    //edit sensor
    @POST("/api/Sensor")
    Call<SensorModel> editSensor(@Body SensorModel sensorModel);


    //devices
    //get list of available devices
    @GET("/api/Device/All/{controllerId}")
    Call<List<DeviceModel>> listDevices(@Path("controllerId") int id, @Query("virtual") boolean virtual);

    //get selected device
    @GET("/api/Device/{id}")
    Call<DeviceModel> getDevice(@Path("id") int id);

    //delete device
    @DELETE("/api/Device/{id}")
    Call<DeviceModel> deleteDevice(@Path("id") int id);

    //add new device
    @PUT("/api/Device")
    Call<DeviceModel> addDevice(@Body DeviceModel deviceModel);

    //edit device
    @POST("/api/Device")
    Call<DeviceModel> editDevice(@Body DeviceModel deviceModel);


    //scripts
    //get list of available scripts
    @GET("/api/Script/All/{controllerId}")
    Call<List<ScriptModel>> listScripts(@Path("controllerId") int id);

    //get selected script
    @GET("/api/Script/{id}")
    Call<ScriptModel> getScript(@Path("id") int id);

    //delete script
    @DELETE("/api/Script/{id}")
    Call<ScriptModel> deleteScript(@Path("id") int id);

    //add script
    @PUT("/api/Script")
    Call<ScriptModel> addScript(@Body ScriptModel scriptModel);

    //edit script
    @POST("/api/Script")
    Call<ScriptModel> editScript(@Body ScriptModel scriptModel);

    //get condition types
    @GET("/api/ConditionType")
    Call<List<ConditionTypeModel>> listTypes();


    //commands
    //get list of commands
    @GET("/api/Command/All/{scriptId}")
    Call<List<CommandModel>> listCommands(@Path("scriptId") int id);

    //get selected command
    @GET("/api/Command/{id}")
    Call<CommandModel> getCommand(@Path("id") int id);

    //delete command
    @DELETE("/api/Command/{id}")
    Call<CommandModel> deleteCommand(@Path("id") int id);

    //add command
    @PUT("/api/Command")
    Call<CommandModel> addCommand(@Body CommandModel commandModel);

    //edit command
    @POST("/api/Command")
    Call<CommandModel> editCommand(@Body CommandModel commandModel);


    //device configurations
    //get config
    @GET("/api/DeviceConfiguration/{id}")
    Call<DeviceConfigurationModel> getConfig(@Path("id") int id);

    //delete config
    @DELETE("/api/DeviceConfiguration/{id}")
    Call<DeviceConfigurationModel> deleteConfig(@Path("id") int id);

    //add config
    @PUT("/api/DeviceConfiguration")
    Call<DeviceConfigurationModel> addConfig(@Body DeviceConfigurationModel deviceConfigurationModel);

    //edit config
    @POST("/api/DeviceConfiguration")
    Call<DeviceConfigurationModel> editConfig(@Body DeviceConfigurationModel deviceConfigurationModel);

    //measures
    @GET("/api/Measure/{deviceTypeId}")
    Call<List<MeasureModel>> listMeasures(@Path("deviceTypeId") int id);

    //access
    //controllers
    //get list of users for controller
    @GET("/api/Access/Controller/{id}")
    Call<List<ControllerListItemModel>> listUsersForControllers(@Path("id") int id);

    //delete user
    @DELETE("/api/Access/Controller/{id}")
    Call<ControllerAccessModel> revokeAccessFromController(@Path("id") int id);

    //add user
    @PUT("/api/Access/Controller")
    Call<ControllerListItemModel> addAccessToController(@Body ControllerAccessModel controllerAccessModel);

    //devices
    //
//    @GET("/api/Access/Device/{id}")
//    Call<List<DeviceAccessModel>> listUsersForDevices(@Path("id") int id);

    //
//    @PUT("/api/Access/Device")
//    Call<DeviceAccessModel> putSth(@Body DeviceAccessModel deviceAccessModel);

    //Firebase
    @POST("/api/User/Firebase")
    Call<String> postToken(@Body String token);
//    Call<String> postToken(@Query("token") String token);

    @DELETE("/api/User/Firebase")
    Call<String> deleteToken();
}