package com.dmko.sportpredictions.ui.base.mvp.impl;


import com.dmko.sportpredictions.ui.base.mvp.BasePresenter;
import com.dmko.sportpredictions.ui.base.mvp.BaseView;

import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;
import timber.log.Timber;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    private T view;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void stop() {
        compositeDisposable.clear();
    }

    @Override
    public void handleThrowable(Throwable throwable) {
        Timber.e(throwable);
        if (throwable instanceof HttpException) {
            Timber.d("Server error");
            getView().showServerError();
        } else if (throwable instanceof IOException) {
            Timber.d("Network error");
            getView().showNetworkError();
        } else {
            Timber.d("Unknown error");
            getView().showUnknownError();
        }
    }

    protected T getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
