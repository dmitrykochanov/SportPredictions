package com.dmko.sportpredictions.ui.screens.events;

import com.dmko.sportpredictions.data.NewsRepository;
import com.dmko.sportpredictions.data.entities.Event;
import com.dmko.sportpredictions.ui.base.mvp.impl.BasePresenterImpl;
import com.dmko.sportpredictions.utils.SchedulersFacade;

public class EventsPresenter extends BasePresenterImpl<EventsContract.View> implements EventsContract.Presenter {

    private final SchedulersFacade schedulers;
    private final NewsRepository newsRepository;

    public EventsPresenter(SchedulersFacade schedulers, NewsRepository newsRepository) {
        this.schedulers = schedulers;
        this.newsRepository = newsRepository;
    }

    @Override
    public void loadEvents(String category) {
        if (getView().isInternetAvailable()) {
            getView().showLoading(true);
            addDisposable(newsRepository.getEvents(category)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe(events -> {
                        if (isViewAttached()) {
                            getView().showLoading(false);
                            getView().setEvents(events);
                        }
                    }, this::handleThrowable));
        } else {
            getView().showNoInternetToast();
        }
    }

    @Override
    public void onEventClicked(Event event) {
        if (isViewAttached()) {
            getView().openEventScreen(event);
        }
    }
}
