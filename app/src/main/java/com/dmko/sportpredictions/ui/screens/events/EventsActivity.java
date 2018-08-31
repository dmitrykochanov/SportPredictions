package com.dmko.sportpredictions.ui.screens.events;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dmko.sportpredictions.R;
import com.dmko.sportpredictions.data.entities.Event;
import com.dmko.sportpredictions.ui.base.mvp.impl.BaseActivity;
import com.dmko.sportpredictions.ui.screens.events.recyclerview.EventsAdapter;
import com.dmko.sportpredictions.ui.screens.prediction.PredictionActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class EventsActivity extends BaseActivity implements EventsContract.View {

    private static final String STATE_LAST_ITEM_SELECTED = "last_item_selected";

    @BindView(R.id.recycler_events) RecyclerView recyclerEvents;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view) NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.layout_refresh) SwipeRefreshLayout swipeRefresh;

    @Inject EventsContract.Presenter presenter;
    @Inject EventsAdapter eventsAdapter;

    private int selectedItemId;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);
        getControllerComponent().inject(this);
        presenter.attachView(this);

        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        layoutManager = new LinearLayoutManager(this);
        recyclerEvents.setLayoutManager(layoutManager);
        recyclerEvents.setAdapter(eventsAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerEvents.addItemDecoration(dividerItemDecoration);

        navigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            selectedItemId = item.getItemId();
            drawerLayout.closeDrawers();
            loadEvents(item.getItemId());
            return true;
        });

        swipeRefresh.setOnRefreshListener(() -> loadEvents(selectedItemId));

        if (savedInstanceState != null) {
            selectedItemId = savedInstanceState.getInt(STATE_LAST_ITEM_SELECTED);
        } else {
            selectedItemId = R.id.category_football;
        }
        navigationView.getMenu().performIdentifierAction(selectedItemId, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt(STATE_LAST_ITEM_SELECTED, selectedItemId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showLoading(boolean isLoading) {
        swipeRefresh.setRefreshing(isLoading);
    }

    @Override
    public void setEvents(List<Event> events) {
        eventsAdapter.setEvents(events);
        layoutManager.scrollToPosition(0);
    }

    @Override
    public void openEventScreen(Event event) {
        Intent intent = PredictionActivity.newIntent(this, event.getArticle(), event.getTitle());
        startActivity(intent);
    }

    @SuppressWarnings("ConstantConditions")
    private void loadEvents(int itemId) {
        presenter.stop();
        switch (itemId) {
            case R.id.category_football:
                getSupportActionBar().setTitle(R.string.category_football);
                presenter.loadEvents("football");
                break;
            case R.id.category_hockey:
                getSupportActionBar().setTitle(R.string.category_hockey);
                presenter.loadEvents("hockey");
                break;
            case R.id.category_tennis:
                getSupportActionBar().setTitle(R.string.category_tennis);
                presenter.loadEvents("tennis");
                break;
            case R.id.category_basketball:
                getSupportActionBar().setTitle(R.string.category_basketball);
                presenter.loadEvents("basketball");
                break;
            case R.id.category_volleyball:
                getSupportActionBar().setTitle(R.string.category_volleyball);
                presenter.loadEvents("volleyball");
                break;
            case R.id.category_cybersport:
                getSupportActionBar().setTitle(R.string.category_cybersport);
                presenter.loadEvents("cybersport");
                break;
        }
    }
}
