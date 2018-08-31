package com.dmko.sportpredictions.ui.base.mvp;


public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();

    void stop();

    void handleThrowable(Throwable throwable);
}
