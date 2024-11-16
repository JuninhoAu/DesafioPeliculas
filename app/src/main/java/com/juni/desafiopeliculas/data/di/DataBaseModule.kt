package com.juni.desafiopeliculas.data.di

import android.content.Context
import androidx.room.Room
import com.juni.desafiopeliculas.data.local.MovieDao
import com.juni.desafiopeliculas.data.local.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun provideTaskDao(todoDataBase: MovieDataBase): MovieDao {
        return  todoDataBase.movieDao()
    }
    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext appContext: Context): MovieDataBase {
        return Room.databaseBuilder(appContext, MovieDataBase::class.java, "movieDataBase").build()
    }
}
