package com.madetech.clean.usecase

interface AsynchronousUseCase<REQUEST, PRESENTER> {
    fun execute(request: REQUEST, presenter: PRESENTER)
}