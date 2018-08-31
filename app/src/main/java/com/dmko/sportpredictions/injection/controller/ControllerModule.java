package com.dmko.sportpredictions.injection.controller;

import com.dmko.sportpredictions.injection.scopes.ControllerScope;
import com.dmko.sportpredictions.ui.screens.events.EventsContract;
import com.dmko.sportpredictions.ui.screens.events.recyclerview.EventsAdapter;
import com.dmko.sportpredictions.ui.screens.prediction.recyclerview.ArticlesAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {

    @Provides
    @ControllerScope
    public EventsAdapter provideEventsAdapter(EventsContract.Presenter presenter) {
        return new EventsAdapter(presenter);
    }

    @Provides
    @ControllerScope
    public ArticlesAdapter provideArticlesAdapter() {
        return new ArticlesAdapter();
    }
}
