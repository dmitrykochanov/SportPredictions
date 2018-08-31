package com.dmko.sportpredictions.ui.screens.prediction;

import com.dmko.sportpredictions.data.entities.Prediction;
import com.dmko.sportpredictions.ui.base.mvp.BasePresenter;
import com.dmko.sportpredictions.ui.base.mvp.BaseView;

public interface PredictionContract {

    interface View extends BaseView {

        void showLoading(boolean isLoading);

        void setPrediction(Prediction prediction);
    }

    interface Presenter extends BasePresenter<View> {

        void loadPrediction(String article);
    }
}
