package com.example.note.feature.note.domain.usecase.base

/**
 * To executes business logic with power of Coroutines.
 *
 * @author Lokik Soni
 * Created On 11-06-2022
 */
abstract class SuspendUseCase<in I, O> {

	/**
	 * Override this function to execute business logic
	 * and return the output [O].
	 * @param parameter input to the business logic.
	 */
	abstract suspend operator fun invoke(parameter: I): O
}