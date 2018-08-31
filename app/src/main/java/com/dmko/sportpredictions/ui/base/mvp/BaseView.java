package com.dmko.sportpredictions.ui.base.mvp;


public interface BaseView {

    boolean isInternetAvailable();

    void showNoInternetToast();

    void showNetworkError();

    void showServerError();

    void showUnknownError();
}
