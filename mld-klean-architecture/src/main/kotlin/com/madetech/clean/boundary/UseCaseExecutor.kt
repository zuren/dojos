package com.madetech.clean.boundary

import com.madetech.clean.usecase.AsynchronousUseCase
import kotlin.reflect.KClass

class UseCaseExecutor(val unsafeConstructor: UnsafeConstructor) : AsynchronousUseCaseExecutor {
    override fun <U : AsynchronousUseCase<R, P>, R, P> executeUseCase(useCase: KClass<U>, request: R, presenter: P) {
        val useCaseInstance = unsafeConstructor.unsafeConstruct(useCase)
        if (useCaseInstance == null) throw Exception("Use Case Not Found")
        (useCaseInstance as U).execute(request, presenter)
    }

    interface UnsafeConstructor {
        fun unsafeConstruct(useCase: KClass<*>): AsynchronousUseCase<*, *>?
    }
}