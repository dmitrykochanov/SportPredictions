package com.dmko.sportpredictions.data.api;

import com.dmko.sportpredictions.data.entities.EventResponse;
import com.dmko.sportpredictions.data.entities.Prediction;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    String BASE_URL = "http://mikonatoruri.win/";

    @GET("/list.php")
    Observable<EventResponse> getEvents(@Query("category") String category);

    @GET("/post.php")
    Observable<Prediction> getPrediction(@Query("article") String article);
}