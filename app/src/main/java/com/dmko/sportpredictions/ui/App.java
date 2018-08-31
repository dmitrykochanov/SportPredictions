package com.dmko.sportpredictions.ui;

import android.app.Application;
import android.os.StrictMode;

import com.dmko.sportpredictions.BuildConfig;
import com.dmko.sportpredictions.injection.application.ApplicationComponent;
import com.dmko.sportpredictions.injection.application.DaggerApplicationComponent;
import com.dmko.sportpredictions.injection.application.DataModule;
import com.dmko.sportpredictions.utils.ProductionTree;

import timber.log.Timber;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ProductionTree());
        }
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .dataModule(new DataModule())
                    .build();
        }
        return applicationComponent;
    }
}
