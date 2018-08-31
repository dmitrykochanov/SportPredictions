package com.dmko.sportpredictions.ui.screens.prediction.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmko.sportpredictions.R;
import com.dmko.sportpredictions.data.entities.Article;
import com.dmko.sportpredictions.data.entities.Prediction;

public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER = 0;
    private static final int ARTICLE = 1;

    private Prediction prediction;

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        switch (viewType) {
            case HEADER:
                itemView = inflater.inflate(R.layout.item_article_header, parent, false);
                return new ArticleHeaderViewHolder(itemView);
            case ARTICLE:
                itemView = inflater.inflate(R.layout.item_article, parent, false);
                return new ArticleViewHolder(itemView);
            default:
                throw new IllegalArgumentException("wrong view type: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArticleHeaderViewHolder) {
            ((ArticleHeaderViewHolder) holder).bindArticleHeader(prediction.getTeam1(), prediction.getTeam2(),
                    prediction.getTime(), prediction.getTournament());
        } else if (holder instanceof ArticleViewHolder) {
            //position - 1 because header is not in the list
            Article article = prediction.getArticles().get(position - 1);
            ((ArticleViewHolder) holder).bindArticle(article);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return HEADER;
        return ARTICLE;
    }

    @Override
    public int getItemCount() {
        if (prediction == null) return 0;
        //size + 1 because header is not in the list
        return prediction.getArticles().size() + 1;
    }
}
