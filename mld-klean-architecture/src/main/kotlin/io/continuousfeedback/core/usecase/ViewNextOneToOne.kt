package io.continuousfeedback.core.usecase

import com.madetech.clean.usecase.AsynchronousUseCase

interface ViewNextOneToOne : AsynchronousUseCase<ViewNextOneToOne.Request, ViewNextOneToOne.Presenter> {
    data class Request(val teamMemberId: Int)

    interface Presenter {
        fun presentOneToOne(oneToOne: OneToOne)
        data class OneToOne(val date: String, val teamMemberId: Int)
    }
}