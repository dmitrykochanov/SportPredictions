package com.dmko.sportpredictions.ui.screens.prediction.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dmko.sportpredictions.R;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class ArticleHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_teams) TextView textTeams;
    @BindView(R.id.text_time) TextView textTime;
    @BindView(R.id.text_tournament) TextView textTournament;

    public ArticleHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindArticleHeader(String team1, String team2, String time, String tournament) {
        String teams = String.format("%s - %s", team1, team2);
        textTeams.setText(teams);
        textTime.setText(time);
        textTournament.setText(tournament);
    }
}
