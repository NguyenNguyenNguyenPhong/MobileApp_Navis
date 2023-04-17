package com.example.mbbapp.Test;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface API_Interface {

     String BASE_URL = "http://203.171.20.94:8903";

    @GET("/VehicleGroup/GetVehicleGroupByUser")
    Call<String> getUnitName(@Header("Cookie")  String authToken);

    //http://203.171.20.94:8903/Schedule/GetListScheduleByUnit?fromDate=2023-02-03&toDate=2023-02-03&unitId=33
    @GET("/Schedule/GetListScheduleByUnit")
    Call<List<GetListScheduleByUnitModel>> getSchedule(@Header("Cookie")  String authToken,
                                               @Query("fromDate") String fromDate,
                                               @Query("toDate") String toDate,
                                               @Query("unitId") int unitId);

    //http://203.171.20.94:8903/Driver/GetAllDriverByUser
    @GET("/Driver/GetAllDriverByUser")
    Call<List<DriverModel>> getAllDriver(@Header("Cookie")  String authToken);
}
