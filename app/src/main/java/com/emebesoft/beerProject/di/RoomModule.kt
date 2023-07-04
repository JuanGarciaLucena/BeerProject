package com.emebesoft.beerProject.di

import android.content.Context
import androidx.room.Room
import com.emebesoft.beerProject.data.database.BeerDb
import com.emebesoft.beerProject.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, BeerDb::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideRickMortyDao(db: BeerDb) = db.getBeerDbDao()
}