package com.dmko.sportpredictions.injection.application;

import com.dmko.sportpredictions.data.api.NewsApi;
import com.dmko.sportpredictions.data.NewsRepository;
import com.dmko.sportpredictions.data.NewsRepositoryImpl;
import com.dmko.sportpredictions.injection.scopes.ApplicationScope;
import com.dmko.sportpredictions.utils.SchedulersFacade;
import com.dmko.sportpredictions.utils.SchedulersFacadeImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @ApplicationScope
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(NewsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    public SchedulersFacade provideSchedulersFacade() {
        return new SchedulersFacadeImpl();
    }

    @Provides
    @ApplicationScope
    public NewsApi provideNewsApi(Retrofit retrofit) {
        return retrofit.create(NewsApi.class);
    }

    @Provides
    @ApplicationScope
    public NewsRepository provideNewsRepository(NewsApi newsApi) {
        return new NewsRepositoryImpl(newsApi);
    }
}
