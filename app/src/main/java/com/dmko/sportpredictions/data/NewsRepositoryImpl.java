package com.dmko.sportpredictions.data;

import com.dmko.sportpredictions.data.api.NewsApi;
import com.dmko.sportpredictions.data.entities.Event;
import com.dmko.sportpredictions.data.entities.EventResponse;
import com.dmko.sportpredictions.data.entities.Prediction;

import java.util.List;

import io.reactivex.Observable;
import timber.log.Timber;

public class NewsRepositoryImpl implements NewsRepository {

    private final NewsApi newsApi;

    public NewsRepositoryImpl(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    @Override
    public Observable<List<Event>> getEvents(String category) {
        return newsApi.getEvents(category)
                .map(EventResponse::getEvents)
                .doOnNext(events -> Timber.d("Loaded %s %s events from the network", events.size(), category));
    }

    @Override
    public Observable<Prediction> getPrediction(String article) {
        return newsApi.getPrediction(article)
                .doOnNext(prediction -> Timber.d("Loaded article from the network: %s", article));
    }
}
