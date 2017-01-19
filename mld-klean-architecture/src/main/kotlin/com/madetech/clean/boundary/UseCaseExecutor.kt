package com.madetech.clean.boundary

import com.madetech.clean.usecase.AsynchronousUseCase
import kotlin.reflect.KClass

class UseCaseExecutor(val unsafeConstructor: UnsafeConstructor) : AsynchronousUseCaseExecutor {
    override fun <U : AsynchronousUseCase<R, P>, R, P> executeUseCase(useCase: KClass<U>, request: R, presenter: P) {
        val useCaseInstance = unsafeConstructor.unsafeConstruct(useCase)
        if (useCaseInstance == null) throw Exception("Use Case Not Found")
        useCaseInstance.execute(request, presenter)
    }

    interface UnsafeConstructor {
        fun <P, R, U : AsynchronousUseCase<R, P>> unsafeConstruct(useCase: KClass<in U>): AsynchronousUseCase<R, P>?
    }
}