package com.dmko.sportpredictions.ui.screens.events.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmko.sportpredictions.R;
import com.dmko.sportpredictions.data.entities.Event;
import com.dmko.sportpredictions.ui.screens.events.EventsContract;

import java.util.Collections;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> events;
    private final EventsContract.Presenter presenter;

    public EventsAdapter(EventsContract.Presenter presenter) {
        this.events = Collections.emptyList();
        this.presenter = presenter;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(itemView, presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.bindEvent(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
