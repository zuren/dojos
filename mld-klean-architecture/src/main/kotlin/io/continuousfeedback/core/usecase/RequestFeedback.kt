package io.continuousfeedback.core.usecase

import com.madetech.clean.usecase.AsynchronousUseCase
import io.continuousfeedback.core.usecase.RequestFeedback.Presenter
import io.continuousfeedback.core.usecase.RequestFeedback.Request

interface RequestFeedback : AsynchronousUseCase<Request, Presenter> {
    data class Request(val receiverId: Int)

    interface Presenter {
        fun presentNotification(notification: Notification)
        fun presentError(error: Error)
        data class Notification(val email: String)
        enum class Error { RECEIVER_NOT_FOUND }
    }
}