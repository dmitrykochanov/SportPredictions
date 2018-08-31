package com.dmko.sportpredictions.utils;

import io.reactivex.Scheduler;

public interface SchedulersFacade {

    Scheduler io();

    Scheduler ui();
}
