package com.dmko.sportpredictions.ui.base.mvp.impl;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dmko.sportpredictions.R;
import com.dmko.sportpredictions.injection.controller.ControllerComponent;
import com.dmko.sportpredictions.injection.controller.ControllerModule;
import com.dmko.sportpredictions.injection.controller.PresenterModule;
import com.dmko.sportpredictions.ui.App;
import com.dmko.sportpredictions.ui.base.mvp.BaseView;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected ControllerComponent getControllerComponent() {
        return ((App) getApplication())
                .getApplicationComponent()
                .newControllerComponent(new ControllerModule(), new PresenterModule());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void showNoInternetToast() {
        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showServerError() {
        Toast.makeText(this, R.string.server_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUnknownError() {
        Toast.makeText(this, R.string.unknown_error, Toast.LENGTH_LONG).show();
    }
}
