package com.dmko.sportpredictions.injection.application;

import com.dmko.sportpredictions.injection.controller.ControllerComponent;
import com.dmko.sportpredictions.injection.controller.ControllerModule;
import com.dmko.sportpredictions.injection.controller.PresenterModule;
import com.dmko.sportpredictions.injection.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {DataModule.class})
public interface ApplicationComponent {

    ControllerComponent newControllerComponent(ControllerModule controllerModule, PresenterModule presenterModule);
}
