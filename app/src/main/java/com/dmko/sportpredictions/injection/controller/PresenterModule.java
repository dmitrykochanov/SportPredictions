package com.dmko.sportpredictions.injection.controller;

import com.dmko.sportpredictions.data.NewsRepository;
import com.dmko.sportpredictions.injection.scopes.ControllerScope;
import com.dmko.sportpredictions.ui.screens.prediction.PredictionContract;
import com.dmko.sportpredictions.ui.screens.prediction.PredictionPresenter;
import com.dmko.sportpredictions.ui.screens.events.EventsContract;
import com.dmko.sportpredictions.ui.screens.events.EventsPresenter;
import com.dmko.sportpredictions.utils.SchedulersFacade;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @ControllerScope
    public EventsContract.Presenter provideEventsPresenter(SchedulersFacade schedulers, NewsRepository newsRepository) {
        return new EventsPresenter(schedulers, newsRepository);
    }

    @Provides
    @ControllerScope
    public PredictionContract.Presenter provideArticlePresenter(SchedulersFacade schedulers, NewsRepository newsRepository) {
        return new PredictionPresenter(schedulers, newsRepository);
    }
}
