package com.dmko.sportpredictions.ui.screens.events;

import com.dmko.sportpredictions.data.entities.Event;
import com.dmko.sportpredictions.ui.base.mvp.BasePresenter;
import com.dmko.sportpredictions.ui.base.mvp.BaseView;

import java.util.List;

public interface EventsContract {

    interface View extends BaseView {

        void showLoading(boolean isLoading);

        void setEvents(List<Event> events);

        void openEventScreen(Event event);
    }

    interface Presenter extends BasePresenter<View> {

        void loadEvents(String category);

        void onEventClicked(Event event);
    }
}

