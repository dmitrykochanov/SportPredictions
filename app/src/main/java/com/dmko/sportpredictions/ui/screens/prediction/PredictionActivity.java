package com.dmko.sportpredictions.ui.screens.prediction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.dmko.sportpredictions.R;
import com.dmko.sportpredictions.data.entities.Article;
import com.dmko.sportpredictions.data.entities.Prediction;
import com.dmko.sportpredictions.ui.base.mvp.impl.BaseActivity;
import com.dmko.sportpredictions.ui.screens.prediction.recyclerview.ArticlesAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class PredictionActivity extends BaseActivity implements PredictionContract.View {

    private static final String ARG_ARTICLE = "com.dmko.article";
    private static final String ARG_TITLE = "com.dmko.title";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.progress_loading) ProgressBar progressLoading;
    @BindView(R.id.recycler_articles) RecyclerView recyclerArticles;

    @Inject PredictionContract.Presenter presenter;
    @Inject ArticlesAdapter articlesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);
        getControllerComponent().inject(this);
        ButterKnife.bind(this);
        presenter.attachView(this);

        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerArticles.setLayoutManager(new LinearLayoutManager(this));
        recyclerArticles.setAdapter(articlesAdapter);

        String title = getIntent().getStringExtra(ARG_TITLE);
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String article = getIntent().getStringExtra(ARG_ARTICLE);
        presenter.loadPrediction(article);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showLoading(boolean isLoading) {
        progressLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPrediction(Prediction prediction) {
        Article predictionArticle = new Article();
        predictionArticle.setHeader(getString(R.string.prediction));
        predictionArticle.setText(prediction.getPrediction());
        prediction.getArticles().add(predictionArticle);

        articlesAdapter.setPrediction(prediction);
    }

    public static Intent newIntent(Context context, String article, String title) {
        Intent intent = new Intent(context, PredictionActivity.class);
        intent.putExtra(ARG_ARTICLE, article);
        intent.putExtra(ARG_TITLE, title);
        return intent;
    }
}
