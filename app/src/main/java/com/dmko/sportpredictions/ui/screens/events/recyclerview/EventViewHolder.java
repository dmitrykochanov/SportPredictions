package com.dmko.sportpredictions.ui.screens.events.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dmko.sportpredictions.R;
import com.dmko.sportpredictions.data.entities.Event;
import com.dmko.sportpredictions.ui.screens.events.EventsContract;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class EventViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_title) TextView textTitle;
    @BindView(R.id.text_preview) TextView textPreview;
    @BindView(R.id.text_coefficient) TextView textCoefficient;
    @BindView(R.id.text_time) TextView textTime;
    @BindView(R.id.text_place) TextView textPlace;

    private Event event;

    public EventViewHolder(View itemView, EventsContract.Presenter presenter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(v -> presenter.onEventClicked(event));
    }

    public void bindEvent(Event event) {
        this.event = event;
        textTitle.setText(event.getTitle());
        textPreview.setText(event.getPreview());
        textCoefficient.setText(event.getCoefficient());
        textTime.setText(event.getTime());
        textPlace.setText(event.getPlace());
    }
}
