package io.continuousfeedback.core.usecase

import com.madetech.clean.usecase.AsynchronousUseCase
import io.continuousfeedback.core.usecase.CreateOneToOne.Presenter
import io.continuousfeedback.core.usecase.CreateOneToOne.Request

interface CreateOneToOne : AsynchronousUseCase<Request, Presenter> {
    data class Request(val date: String, val teamMemberId: Int)

    interface Presenter {
        fun onSuccess()
    }
}