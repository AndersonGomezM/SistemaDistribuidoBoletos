package edu.ucne.appboletos.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.appboletos.data.local.AppDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleLocal {
    @Singleton
    @Provides
    fun providesDababase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "EmpleosReferenceDB"
        ).fallbackToDestructiveMigration().build()
    }
}