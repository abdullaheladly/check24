package com.abdullah996.check24.di

import android.content.Context
import androidx.room.Room
import com.abdullah996.check24.data.database.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    )= Room.databaseBuilder(
        context,
        ProductsDatabase::class.java,
        "products_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ProductsDatabase)=database.productsDao()
}