package com.example.note.feature.note.domain.usecase.base

/**
 * To executes business logic.
 *
 * @author Lokik Soni
 * Created On 10-06-2022
 */
abstract class BaseUseCase<in I, O> {

	/**
	 * Override this function to execute business logic
	 * and return the output [O].
	 * @param parameter input to the business logic.
	 */
	abstract operator fun invoke(parameter: I): O
}