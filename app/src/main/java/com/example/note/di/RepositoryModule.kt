package com.example.note.di

import com.example.note.feature.note.data.repository.NoteRepositoryImpl
import com.example.note.feature.note.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides app level abstract dependencies
 * related to the repository.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

	/**
	 * Provides the [NoteRepository] by using
	 * implementation of [NoteRepositoryImpl].
	 */
	@Binds
	@Singleton
	abstract fun bindNoteRepositoryImpl(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository
}