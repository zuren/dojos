package io.continuousfeedback.core.usecase

interface RequestFeedback {
    data class Request(val receiverId: Int)

    fun execute(request: Request, presenter: Presenter)

    interface Presenter {
        fun presentNotification(notification:Notification)
        fun presentError(error: Error)
    }
    data class Notification(val email: String)
    enum class Error { RECEIVER_NOT_FOUND }
}