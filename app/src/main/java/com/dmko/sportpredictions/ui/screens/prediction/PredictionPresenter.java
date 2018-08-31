package com.dmko.sportpredictions.ui.screens.prediction;

import com.dmko.sportpredictions.data.NewsRepository;
import com.dmko.sportpredictions.ui.base.mvp.impl.BasePresenterImpl;
import com.dmko.sportpredictions.utils.SchedulersFacade;

import timber.log.Timber;

public class PredictionPresenter extends BasePresenterImpl<PredictionContract.View> implements PredictionContract.Presenter {

    private final SchedulersFacade schedulers;
    private final NewsRepository newsRepository;

    public PredictionPresenter(SchedulersFacade schedulers, NewsRepository newsRepository) {
        this.schedulers = schedulers;
        this.newsRepository = newsRepository;
    }

    @Override
    public void loadPrediction(String article) {
        if (getView().isInternetAvailable()) {
            getView().showLoading(true);
            addDisposable(newsRepository.getPrediction(article)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe(prediction -> {
                        if (isViewAttached()) {
                            getView().showLoading(false);
                            getView().setPrediction(prediction);
                        }
                    }, this::handleThrowable));
        } else {
            getView().showNoInternetToast();
        }
    }
}
