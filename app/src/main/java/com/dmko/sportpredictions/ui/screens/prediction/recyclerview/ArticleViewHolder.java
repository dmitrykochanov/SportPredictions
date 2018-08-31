package com.dmko.sportpredictions.ui.screens.prediction.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dmko.sportpredictions.R;
import com.dmko.sportpredictions.data.entities.Article;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_header) TextView textHeader;
    @BindView(R.id.text) TextView text;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindArticle(Article article) {
        if (article.getHeader().isEmpty()) {
            textHeader.setVisibility(View.GONE);
        } else {
            textHeader.setText(article.getHeader());
        }
        text.setText(article.getText());
    }
}
