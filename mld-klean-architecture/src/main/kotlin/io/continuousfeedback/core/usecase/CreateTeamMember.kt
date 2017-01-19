package io.continuousfeedback.core.usecase

import com.madetech.clean.usecase.AsynchronousUseCase
import io.continuousfeedback.core.usecase.CreateTeamMember.Presenter
import io.continuousfeedback.core.usecase.CreateTeamMember.Request

interface CreateTeamMember  : AsynchronousUseCase<Request, Presenter> {
    data class Request(val email:String)
    interface Presenter {
        fun onSuccess()
    }
}