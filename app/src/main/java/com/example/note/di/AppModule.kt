package com.example.note.di

import android.app.Application
import androidx.room.Room
import com.example.note.feature.note.data.local.NoteDao
import com.example.note.feature.note.data.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides app level singleton dependencies.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Singleton
	@Provides
	fun provideDatabase(application: Application): NoteDatabase =
		Room.databaseBuilder(
			application,
			NoteDatabase::class.java,
			NoteDatabase.DATABASE_NAME
		).build()

	@Provides
	@Singleton
	fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao
}