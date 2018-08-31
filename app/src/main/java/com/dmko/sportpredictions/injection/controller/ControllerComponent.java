package com.dmko.sportpredictions.injection.controller;

import com.dmko.sportpredictions.injection.scopes.ControllerScope;
import com.dmko.sportpredictions.ui.screens.prediction.PredictionActivity;
import com.dmko.sportpredictions.ui.screens.events.EventsActivity;

import dagger.Subcomponent;

@ControllerScope
@Subcomponent(modules = {ControllerModule.class, PresenterModule.class})
public interface ControllerComponent {

    void inject(EventsActivity eventsActivity);

    void inject(PredictionActivity predictionActivity);
}
