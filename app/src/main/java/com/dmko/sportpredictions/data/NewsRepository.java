package com.dmko.sportpredictions.data;

import com.dmko.sportpredictions.data.entities.Event;
import com.dmko.sportpredictions.data.entities.Prediction;

import java.util.List;

import io.reactivex.Observable;

public interface NewsRepository {

    Observable<List<Event>> getEvents(String category);

    Observable<Prediction> getPrediction(String article);
}
